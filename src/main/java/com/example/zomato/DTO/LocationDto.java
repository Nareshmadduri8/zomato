package com.example.zomato.DTO;

public class LocationDto {

	 	private Integer deliveryPartnerId;
	    private double latitude;
	    private double longitude;
	    
		public LocationDto() {
			super();
		}

		public LocationDto(Integer deliveryPartnerId, double latitude, double longitude) {
			super();
			this.deliveryPartnerId = deliveryPartnerId;
			this.latitude = latitude;
			this.longitude = longitude;
		}

		public int getDeliveryPartnerId() {
			return deliveryPartnerId;
		}

		public void setDeliveryPartnerId(int deliveryPartnerId) {
			this.deliveryPartnerId = deliveryPartnerId;
		}

		public double getLatitude() {
			return latitude;
		}

		public void setLatitude(double latitude) {
			this.latitude = latitude;
		}

		public double getLongitude() {
			return longitude;
		}

		public void setLongitude(double longitude) {
			this.longitude = longitude;
		}
	    
	    
}
