package com.example.geektrust.validator;

import static com.example.geektrust.constant.WaterBillAppCommonConstants.INVALID_INPUT;
import static com.example.geektrust.constant.WaterBillAppCommonConstants.SPLITTER;

import java.util.Arrays;
import java.util.function.Predicate;

import com.example.geektrust.constant.ApartmentType;
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
		if (inputLine.replace(" ", "").isEmpty()) {
			throw new WaterBillAppException(INVALID_INPUT);
		}
		String[] inputData = SPLITTER.split(inputLine);
		switch (inputData[0]) {
			case "ALLOT_WATER":
				validateAllotWaterInput(inputData);
				break;
			case "ADD_GUESTS":
				validateAddGuestsInput(inputData);
				break;
			case "BILL":
				validateBillInput(inputData);
				break;
			default:
				throw new WaterBillAppException(INVALID_INPUT);
		}
	}

	/**
	 * It validates the input for the allotWater command
	 * 
	 * @param inputData The input data from the user
	 */
	private void validateAllotWaterInput(String[] inputData) throws WaterBillAppException {
		if (inputData.length != 3 || !isDigit.test(inputData[1])) {
			throw new WaterBillAppException(INVALID_INPUT);
		}
		String[] ratioInput = inputData[2].split(":");
		if (ratioInput.length != 2 || !isDigit.test(ratioInput[0]) || !isDigit.test(ratioInput[1])) {
			throw new WaterBillAppException(INVALID_INPUT);
		}
		if (!ApartmentType.getApartmentTypeBySize(Integer.valueOf(inputData[1])).isPresent()) {
			throw new WaterBillAppException(INVALID_INPUT);
		}
	}

	/**
	 * This function validates the input data for the `add guests` command
	 * 
	 * @param inputData The input data that the user has entered.
	 */
	private void validateAddGuestsInput(String[] inputData) throws WaterBillAppException {
		if (inputData.length != 2 || !isDigit.test(inputData[1])) {
			throw new WaterBillAppException(INVALID_INPUT);
		}
	}

	/**
	 * It validates the input data for the bill command
	 * 
	 * @param inputData The input data that the user has entered.
	 */
	private void validateBillInput(String[] inputData) throws WaterBillAppException {
		if (inputData.length != 1) {
			throw new WaterBillAppException(INVALID_INPUT);
		}
	}
}
