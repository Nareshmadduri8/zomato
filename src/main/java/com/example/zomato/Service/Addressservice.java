package com.example.zomato.Service;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.example.zomato.Entity.Address;
import com.example.zomato.Entity.Coordinates;
import com.example.zomato.Entity.Customer;
import com.example.zomato.Exception.CustomerNotFoundException;
import com.example.zomato.Exception.ResponseNotFoundforCoardinatesException;
import com.example.zomato.Repository.AddressRepository;
import com.example.zomato.Repository.CustomerRepository;

@Service
public class Addressservice {

	@Autowired
	private RestTemplate restTemplate;

	@Autowired
	private CustomerRepository customerRepository;

	@Autowired
	private AddressRepository addressRepository;

	public void createnewaddress(Coordinates coordinates, int id) {
		Customer c = customerRepository.findById(id).orElseThrow(() -> new CustomerNotFoundException());
		String url = "https://us1.locationiq.com/v1/reverse?key=pk.3cdc3a5a72f82bb33771f7551a92c22a&lat="
				+ coordinates.getLatitude() + "&lon=" + coordinates.getLongitude() + "&format=json&";
		// 17.367574 lati
		// 78.537144
		try {
		Map<String, Object> response = (Map<String, Object>) restTemplate.getForObject(url, Object.class);
		if(response!=null) {
		Map<String, Object> address = (Map<String, Object>) response.get("address");
		System.out.println(address);
		Address a = new Address();
		a.setCity((String) address.get("city"));
		a.setCountry((String) address.get("country"));
		a.setLandmark((String) address.get("neighbourhood"));
		a.setPincode(Integer.parseInt((String) address.get("postcode")));
		a.setState((String) address.get("state"));
		a.setStreet((String) address.get("road"));
		a.setCoordinates(coordinates);
		addressRepository.save(a);
		c.getAddress().add(a);
		customerRepository.save(c);
		}else {
			throw new ResponseNotFoundforCoardinatesException();
		}
		}
		catch(Exception e) {
			throw new ResponseNotFoundforCoardinatesException();
		}
	}

}
