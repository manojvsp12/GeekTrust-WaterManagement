package com.example.geektrust.constant;

import java.util.regex.Pattern;

/**
 * This class contains all the constants used in the application
 *
 * @author Manoj SP
 */
public class WaterBillAppCommonConstants {
	
	private WaterBillAppCommonConstants() {
		
	}

	public static final Pattern SPLITTER = Pattern.compile("\\s+");

	public static final int DEFAULT_WATER_ALLOCATION = 10;

	public static final int NO_OF_DAYS = 30;

	public static final String BILL = "BILL";

	public static final String ADD_GUESTS = "ADD_GUESTS";

	public static final String ALLOT_WATER = "ALLOT_WATER";

	public static final String INVALID_INPUT = "Invalid input";

	public static final String EMPTY_SPACE = "";
	
	public static final String SPACE = " ";
	
	public static final String RATIO_SPLIT = ":";
	
	public static final int BILL_MAX_ALLOWED_INPUT_LENGTH = 1;
	
	public static final int ADD_GUESTS_MAX_ALLOWED_INPUT_LENGTH = 3;
	
	public static final int ALLOT_WATER_MAX_ALLOWED_RATIO_LENGTH = 2;
	
	public static final int ALLOT_WATER_MAX_ALLOWED_INPUT_LENGTH = 2;
	
	public static final String APARTMENT_CMD = "APARTMENT";
	
	public static final int APARTMENT_MAX_ALLOWED_INPUT_LENGTH = 3;
}
