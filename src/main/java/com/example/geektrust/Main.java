package com.example.geektrust;

import static com.example.geektrust.constant.WaterBillAppCommonConstants.ADD_GUESTS;
import static com.example.geektrust.constant.WaterBillAppCommonConstants.ALLOT_WATER;
import static com.example.geektrust.constant.WaterBillAppCommonConstants.BILL;
import static com.example.geektrust.constant.WaterBillAppCommonConstants.INVALID_INPUT;
import static com.example.geektrust.constant.WaterBillAppCommonConstants.SPLITTER;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Scanner;

import com.example.geektrust.constant.ApartmentType;
import com.example.geektrust.constant.WaterType;
import com.example.geektrust.dto.WaterBill;
import com.example.geektrust.exception.WaterBillAppException;
import com.example.geektrust.service.WaterBillService;
import com.example.geektrust.service.impl.WaterBillServiceImpl;
import com.example.geektrust.validator.WaterBillInputValidator;

/**
 * @author Manoj SP
 *
 */
public class Main {
	private static WaterBillService waterBillService;

	/**
	 * It reads the input file line by line and calls the processInput function to
	 * process each line
	 */
	public static void main(String[] args) {
		try (FileInputStream fis = new FileInputStream(args[0])) {
			waterBillService = new WaterBillServiceImpl();
			Scanner sc = new Scanner(fis);
			while (sc.hasNextLine()) {
				String nextLine = sc.nextLine();
				if (WaterBill.getInstance().getTotalCost() != 0 && nextLine.startsWith(BILL)) {
					sc.close();
					break;
				}
				processInput(nextLine);
			}
			sc.close();
		} catch (IOException | NumberFormatException e) {
			System.err.println("ERROR: " + INVALID_INPUT);
		} catch (WaterBillAppException e) {
			System.err.println("ERROR: " + e.getMessage());
		}
	}

	/**
	 * It validates the input, splits the input into an array of strings, and then
	 * calls the appropriate service method based on the first element of the array
	 * 
	 * @param inputLine The input line from the user
	 */
	private static void processInput(String inputLine) throws WaterBillAppException {
		WaterBillInputValidator validator = new WaterBillInputValidator();
		validator.validateInput(inputLine);
		String[] inputData = SPLITTER.split(inputLine);
		if (inputData[0].contentEquals(ALLOT_WATER)) {
			Integer size = Integer.valueOf(inputData[1]);
			String[] waterRatio = inputData[2].split(":");
			Integer corpWaterRatio = Integer.valueOf(waterRatio[0]);
			Integer boreWaterRatio = Integer.valueOf(waterRatio[1]);
			waterBillService.allotWater(ApartmentType.getApartmentTypeBySize(size).get(), WaterType.CORPORATION_WATER,
					corpWaterRatio);
			waterBillService.allotWater(ApartmentType.getApartmentTypeBySize(size).get(), WaterType.BOREWELL_WATER,
					boreWaterRatio);
		}
		if (inputData[0].contentEquals(ADD_GUESTS)) {
			Integer guestCount = Integer.valueOf(inputData[1]);
			waterBillService.addGuests(guestCount);
		}
		if (inputData[0].contentEquals(BILL)) {
			WaterBill waterBill = waterBillService.generateBill();
			System.out.println(String.format("%s\t%s", "TOTAL_WATER_CONSUMED_IN_LITRES", "TOTAL_COST"));
			System.out.println(
					String.format("%s\t\t\t\t%s", waterBill.getTotalWaterConsumed(), waterBill.getTotalCost()));
		}
	}
}
