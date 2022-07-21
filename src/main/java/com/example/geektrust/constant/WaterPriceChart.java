package com.example.geektrust.constant;

/**
 * An enum that holds the price chart for water types
 * on slab basis.
 * 
 * @author Manoj SP
 *
 */
public enum WaterPriceChart {

	CORPORATION_WATER(0, Integer.MAX_VALUE, 1, Integer.MAX_VALUE),
	BOREWELL_WATER(0, Integer.MAX_VALUE, 1.5, Integer.MAX_VALUE),
	TANKER_WATER_1(0, 500, 2, 500),
	TANKER_WATER_2(501, 1500, 3, 1000),
	TANKER_WATER_3(1501, 3000, 5, 1500),
	TANKER_WATER_4(3001, Integer.MAX_VALUE, 8, Integer.MAX_VALUE - 3001);

	private int startRange;
	private int endRange;
	private double price;
	private int allotedMaxQuantity;

	WaterPriceChart(int startRange, int endRange, double price, int allotedMaxQuantity) {
		this.startRange = startRange;
		this.endRange = endRange;
		this.price = price;
		this.allotedMaxQuantity = allotedMaxQuantity;
	}

	/**
	 * This function returns the startRange
	 * 
	 * @return The startRange is being returned.
	 */
	public int getStartRange() {
		return startRange;
	}

	/**
	 * This function returns the end range of the current slab
	 * 
	 * @return The endRange is being returned.
	 */
	public int getEndRange() {
		return endRange;
	}

	/**
	 * This function returns the price
	 * 
	 * @return The price
	 */
	public double getPrice() {
		return price;
	}
	
	/**
	 * This function returns the alloted Max Quantity
	 * 
	 * @return The allotedMaxQuantity
	 */
	public int getAllotedMaxQuantity() {
		return allotedMaxQuantity;
	}

}
