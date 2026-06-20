package com.example.zomato.Service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.example.zomato.DTO.CustomerDto;
import com.example.zomato.DTO.ResponseStructure;
import com.example.zomato.Entity.Customer;
import com.example.zomato.Repository.CustomerRepository;

@Service
public class CustomerService {
	
	@Autowired 
	private CustomerRepository customerRepo;

	public void createaccount(CustomerDto cdto) {
		Customer c=new Customer();
		c.setName(cdto.getName());
		c.setMobno(cdto.getMobno());
		c.setGmail(cdto.getGmail());
		c.setAge(cdto.getAge());
		c.setGender(cdto.getGender());
		customerRepo.save(c);
	}

	public ResponseStructure<Customer> findcustomer(int id) {
		Optional<Customer> cust= customerRepo.findById(id);
		 ResponseStructure<Customer> rs=new ResponseStructure<Customer>();
		 if(cust.isPresent()) {
		 Customer c=cust.get();
		 rs.setStatuscode(HttpStatus.FOUND.value());
		 rs.setMessage("Customer with id :"+id+" Found");
		 rs.setData(c);
		 return rs;
		 }else {
			 rs.setStatuscode(HttpStatus.NOT_FOUND.value());
				rs.setMessage("Customer with id :"+id+"NOT FOUND");
				rs.setData(null);
				return rs; 
		 }
	}

		public ResponseStructure<Customer> deletecustomeracc(int id) {
			Optional<Customer> customeropt= customerRepo.findById(id);
			ResponseStructure<Customer> rs=new ResponseStructure<Customer>();
			if(customeropt.isPresent()) {
				Customer c=customeropt.get();
				customerRepo.deleteById(id);
				rs.setStatuscode(HttpStatus.OK.value());
				rs.setMessage("Customer with id :"+id+"Deleted");
				rs.setData(c);
			}else {
				rs.setStatuscode(HttpStatus.NOT_FOUND.value());
				rs.setMessage("Customerwith id :"+id+"NOT FOUND");
				rs.setData(null);
			}
			return rs;
	}
	

}
