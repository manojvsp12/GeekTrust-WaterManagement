package com.example.geektrust.constant;

/**
 * An enum that holds the price chart for water types
 * on slab basis.
 * 
 * @author Manoj SP
 *
 */
public enum WaterPriceChart {

	CORPORATION_WATER(0, Integer.MAX_VALUE, 1),
	BOREWELL_WATER(0, Integer.MAX_VALUE, 1.5),
	TANKER_WATER_1(0, 500, 2),
	TANKER_WATER_2(501, 1500, 3),
	TANKER_WATER_3(1501, 3000, 5),
	TANKER_WATER_4(3001, Integer.MAX_VALUE, 8);

	private int startRange;
	private int endRange;
	private double price;

	WaterPriceChart(int startRange, int endRange, double price) {
		this.startRange = startRange;
		this.endRange = endRange;
		this.price = price;
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

}
