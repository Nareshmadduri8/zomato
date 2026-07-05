package com.example.zomato.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.geo.Point;
import org.springframework.data.redis.core.GeoOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import com.example.zomato.DTO.LocationDto;

@Service
public class DeliveryPartnerLocationService {

	private static final String GEO_KEY ="deliverypartner-locations";
	
	@Autowired
	private  RedisTemplate<String, String> redisTemplate;

	public DeliveryPartnerLocationService(RedisTemplate<String, String> redisTemplate) {
		this.redisTemplate = redisTemplate;
	}
	public void updateLocation(LocationDto locationdto) {
		redisTemplate.opsForGeo().add(
                GEO_KEY,
                new Point(locationdto.getLongitude(), locationdto.getLatitude()),
                String.valueOf(locationdto.getDeliveryPartnerId())
        );
		}
}
