package com.example.zomato.Service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.example.zomato.DTO.DeliveryPartnerDto;
import com.example.zomato.DTO.ResponseStructure;
import com.example.zomato.Entity.DeliveryPartner;
import com.example.zomato.Entity.Restaurant;
import com.example.zomato.Exception.DeliveryPartnerNotFoundExce;
import com.example.zomato.Exception.RestaurantNotFoundException;
import com.example.zomato.Repository.DeliveryPartnerRepository;

@Service
public class DeliveryPartnerService {

	@Autowired
	private DeliveryPartnerRepository dpRepository;

	public void savedeliverypartner(DeliveryPartnerDto dpdto) {
		DeliveryPartner dp = new DeliveryPartner();
		dp.setName(dpdto.getName());
		dp.setMobno(dpdto.getMobno());
		dp.setGmail(dpdto.getGmail());
		dp.setVehicleNo(dpdto.getVehicleNo());
		dpRepository.save(dp);
	}

// FINDING RESTAURANT BY IF AND ELSE METHOD

//	public ResponseStructure<DeliveryPartner> finddeliverypartner(int id) {
//		Optional<DeliveryPartner> dpopt=dpRepository.findById(id);
//		ResponseStructure<DeliveryPartner> rs=new ResponseStructure<DeliveryPartner>();
//		if(dpopt.isPresent()) {
//			DeliveryPartner dp=dpopt.get();
//			 rs.setStatuscode(HttpStatus.FOUND.value());
//			 rs.setMessage("DeliveryPartner with id :"+id+" Found");
//			 rs.setData(dp);
//			 }else {
//				 rs.setStatuscode(HttpStatus.NOT_FOUND.value());
//					rs.setMessage("Customer with id :"+id+" NOT FOUND");
//					rs.setData(null);
//			 }
//		return rs; 
//		}

//	FINDING RESTAURANT BY USING ORELSETHORW METHOD

	public ResponseStructure<DeliveryPartner> finddeliverypartner(int id) {
		DeliveryPartner dp = dpRepository.findById(id).orElseThrow(() -> new DeliveryPartnerNotFoundExce());
		ResponseStructure<DeliveryPartner> rs = new ResponseStructure<DeliveryPartner>();
		DeliveryPartner d = new DeliveryPartner();
		rs.setStatuscode(HttpStatus.FOUND.value());
		rs.setMessage("DeliveryPartner with id :" + id + " Found");
		rs.setData(d);
		return rs;
	}

//	public ResponseStructure<DeliveryPartner> deletedeliverypartner(int id) {
//		Optional<DeliveryPartner> dpopt=dpRepository.findById(id);
//		ResponseStructure<DeliveryPartner> rs=new ResponseStructure<DeliveryPartner>();
//		if(dpopt.isPresent()) {
//			DeliveryPartner dp=dpopt.get();
//			dpRepository.deleteById(id);
//			 rs.setStatuscode(HttpStatus.OK.value());
//		     rs.setMessage("DeliveryPartner with id " + id + " Deleted");
//		     rs.setData(dp);
//		    } else {
//		        rs.setStatuscode(HttpStatus.NOT_FOUND.value());
//		        rs.setMessage("DeliveryPartner with id " + id + " NOT FOUND");
//		        rs.setData(null);
//		    }
//		    return rs;
//		}
	
	
	
//	DELETING RESTAURANT BY USING ORELSETHORW METHOD

	public ResponseStructure<DeliveryPartner> deletedeliverypartner(int id) {
		DeliveryPartner dpopt = dpRepository.findById(id).orElseThrow(()-> new DeliveryPartnerNotFoundExce());
		ResponseStructure<DeliveryPartner> rs = new ResponseStructure<DeliveryPartner>();
		DeliveryPartner dp=new DeliveryPartner();
		dpRepository.deleteById(id);
		 rs.setStatuscode(HttpStatus.OK.value());
	     rs.setMessage("DeliveryPartner with id " + id + " Deleted");
	     rs.setData(dp);
	     return rs;
	}
}
