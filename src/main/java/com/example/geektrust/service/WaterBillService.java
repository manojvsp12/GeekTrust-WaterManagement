package com.example.geektrust.service;

import com.example.geektrust.constant.ApartmentType;
import com.example.geektrust.constant.WaterType;
import com.example.geektrust.dto.WaterBill;

/**
 * This class is used to calculate the water bill for an apartment.
 * 
 * @author Manoj SP
 *
 */
public interface WaterBillService {

	/**
	 * This function is used to allot water to the apartment
	 * 
	 * @param apartmentType The type of apartment.
	 * @param waterType     This is the type of water that is being allotted to the
	 *                      apartment.
	 * @param ratio         The ratio of water to be allotted to the apartment.
	 * @return WaterBill
	 */
	public WaterBill allotWater(ApartmentType apartmentType, WaterType waterType, int ratio);

	/**
	 * This function adds the number of guests to the water bill
	 * 
	 * @param numberOfGuests The number of guests to add to the water bill.
	 * @return A WaterBill object
	 */
	public WaterBill addGuests(int numberOfGuests);

	/**
	 * This function generates a water bill by calculating the total water
	 * consumption and the total cost
	 * 
	 * @return A WaterBill object
	 */
	public WaterBill generateBill();
}
