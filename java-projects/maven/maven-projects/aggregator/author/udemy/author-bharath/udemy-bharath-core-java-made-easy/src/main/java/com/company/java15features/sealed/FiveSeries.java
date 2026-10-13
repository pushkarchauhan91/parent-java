package com.company.java15features.sealed;

public non-sealed class FiveSeries extends BMWSeries {
	
	public int start() {
		record PickupData(int pickupTime) {
			
		}
		return new PickupData(10).pickupTime;
		
	}

}
