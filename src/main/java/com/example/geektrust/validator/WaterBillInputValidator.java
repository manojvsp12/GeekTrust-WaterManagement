package com.example.geektrust.validator;

import static com.example.geektrust.constant.WaterBillAppCommonConstants.ADD_GUESTS;
import static com.example.geektrust.constant.WaterBillAppCommonConstants.ADD_GUESTS_MAX_ALLOWED_INPUT_LENGTH;
import static com.example.geektrust.constant.WaterBillAppCommonConstants.ALLOT_WATER;
import static com.example.geektrust.constant.WaterBillAppCommonConstants.ALLOT_WATER_MAX_ALLOWED_INPUT_LENGTH;
import static com.example.geektrust.constant.WaterBillAppCommonConstants.ALLOT_WATER_MAX_ALLOWED_RATIO_LENGTH;
import static com.example.geektrust.constant.WaterBillAppCommonConstants.APARTMENT_CMD;
import static com.example.geektrust.constant.WaterBillAppCommonConstants.BILL;
import static com.example.geektrust.constant.WaterBillAppCommonConstants.BILL_MAX_ALLOWED_INPUT_LENGTH;
import static com.example.geektrust.constant.WaterBillAppCommonConstants.EMPTY_SPACE;
import static com.example.geektrust.constant.WaterBillAppCommonConstants.INVALID_INPUT;
import static com.example.geektrust.constant.WaterBillAppCommonConstants.RATIO_SPLIT;
import static com.example.geektrust.constant.WaterBillAppCommonConstants.SPACE;
import static com.example.geektrust.constant.WaterBillAppCommonConstants.SPLITTER;

import java.util.Arrays;
import java.util.function.Predicate;

import com.example.geektrust.constant.WaterBillAppCommonConstants;
import com.example.geektrust.exception.WaterBillAppException;

/**
 * @author Manoj SP
 *
 */
public class WaterBillInputValidator {
	
	// This is a lambda expression which is used to check if the input is a digit or
	// not.
	private final Predicate<String> isDigit = inputToValidate -> Arrays.asList(inputToValidate).stream()
			.flatMap(input -> input.chars().mapToObj(i -> (char) i)).allMatch(Character::isDigit);

	/**
	 * It validates the input and throws an exception if the input is invalid
	 * 
	 * @param inputLine The input line that is to be validated.
	 */
	public void validateInput(String inputLine) throws WaterBillAppException {
		if (inputLine.replace(SPACE, EMPTY_SPACE).isEmpty()) {
			throw new WaterBillAppException(INVALID_INPUT);
		}
		String[] inputData = SPLITTER.split(inputLine);
		switch (inputData[0]) {
		case ALLOT_WATER:
			validateAllotWaterInput(inputData);
			break;
		case ADD_GUESTS:
			validateAddGuestsInput(inputData);
			break;
		case BILL:
			validateBillInput(inputData);
			break;
		case APARTMENT_CMD:
			validateApartmentInput(inputData);
			break;
		default:
			throw new WaterBillAppException(INVALID_INPUT);
		}
	}

	private void validateApartmentInput(String[] inputData) throws WaterBillAppException {
		if (inputData.length != WaterBillAppCommonConstants.APARTMENT_MAX_ALLOWED_INPUT_LENGTH
				|| !isDigit.test(inputData[1]) || !isDigit.test(inputData[2])) {
			throw new WaterBillAppException(INVALID_INPUT);
		}
		Integer apartmentSize = Integer.valueOf(inputData[1]);
		Integer numberOfPersons = Integer.valueOf(inputData[2]);
		if (apartmentSize < 0 && numberOfPersons < 0) {
			throw new WaterBillAppException(INVALID_INPUT);
		}
		if (numberOfPersons > (apartmentSize * 2)) {
			throw new WaterBillAppException("Number of residents exceeded than the allowed limit");
		}
	}

	/**
	 * It validates the input for the allotWater command
	 * 
	 * @param inputData The input data from the user
	 */
	private void validateAllotWaterInput(String[] inputData) throws WaterBillAppException {
		if (inputData.length != ALLOT_WATER_MAX_ALLOWED_INPUT_LENGTH) {
			throw new WaterBillAppException(INVALID_INPUT);
		}
		String[] ratioInput = inputData[1].split(RATIO_SPLIT);
		if (ratioInput.length != ALLOT_WATER_MAX_ALLOWED_RATIO_LENGTH || !isDigit.test(ratioInput[0])
				|| !isDigit.test(ratioInput[1])) {
			throw new WaterBillAppException(INVALID_INPUT);
		}
	}

	/**
	 * This function validates the input data for the `add guests` command
	 * 
	 * @param inputData The input data that the user has entered.
	 */
	private void validateAddGuestsInput(String[] inputData) throws WaterBillAppException {
		if (inputData.length != ADD_GUESTS_MAX_ALLOWED_INPUT_LENGTH || !isDigit.test(inputData[1])|| !isDigit.test(inputData[2])) {
			throw new WaterBillAppException(INVALID_INPUT);
		}
		Integer numberOfDays = Integer.valueOf(inputData[2]);
		if (numberOfDays < 0 || numberOfDays > 30) {
			throw new WaterBillAppException(INVALID_INPUT);
		}
	}

	/**
	 * It validates the input data for the bill command
	 * 
	 * @param inputData The input data that the user has entered.
	 */
	private void validateBillInput(String[] inputData) throws WaterBillAppException {
		if (inputData.length != BILL_MAX_ALLOWED_INPUT_LENGTH) {
			throw new WaterBillAppException(INVALID_INPUT);
		}
	}
}
