package com.example.geektrust.service.impl;

import static com.example.geektrust.constant.WaterBillAppCommonConstants.DEFAULT_WATER_ALLOCATION;
import static com.example.geektrust.constant.WaterBillAppCommonConstants.NO_OF_DAYS;
import static com.example.geektrust.util.WaterBillUtil.percentageToValue;
import static com.example.geektrust.util.WaterBillUtil.ratioToPercentage;

import java.util.List;
import java.util.Map.Entry;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import com.example.geektrust.constant.ApartmentType;
import com.example.geektrust.constant.WaterPriceChart;
import com.example.geektrust.constant.WaterType;
import com.example.geektrust.dto.WaterBill;
import com.example.geektrust.service.WaterBillService;

/**
 * This class is used to calculate the water bill for an apartment.
 * 
 * @author Manoj SP
 *
 */
public class WaterBillServiceImpl implements WaterBillService {

	/**
	 * This function is used to allot water to the apartment
	 * 
	 * @param apartmentType The type of apartment.
	 * @param waterType     This is the type of water that is being allotted to the
	 *                      apartment.
	 * @param ratio         The ratio of water to be allotted to the apartment.
	 * @return WaterBill
	 */
	@Override
	public WaterBill allotWater(ApartmentType apartmentType, WaterType waterType, int ratio) {
		WaterBill waterBill = WaterBill.getInstance();
		waterBill.setApartmentType(apartmentType);
		switch (waterType) {
			case CORPORATION_WATER:
				waterBill.setCorporationWaterRatio(ratio);
				break;
			case BOREWELL_WATER:
				waterBill.setBorewellWaterRatio(ratio);
				break;
			default:
				break;
		}
		return waterBill;
	}

	/**
	 * This function adds the number of guests to the water bill
	 * 
	 * @param numberOfGuests The number of guests to add to the water bill.
	 * @return A WaterBill object
	 */
	@Override
	public WaterBill addGuests(int numberOfGuests) {
		WaterBill waterBill = WaterBill.getInstance();
		waterBill.setGuestCount(waterBill.getGuestCount() + numberOfGuests);
		return waterBill;
	}

	/**
	 * This function generates a water bill by calculating the total water
	 * consumption and the total cost
	 * 
	 * @return A WaterBill object
	 */
	@Override
	public WaterBill generateBill() {
		WaterBill waterBill = WaterBill.getInstance();
		calcTotalWaterConsumption(waterBill);
		calcTotalCost(waterBill);
		return waterBill;
	}

	/**
	 * It calculates the total water consumption of a water bill
	 * 
	 * @param waterBill The object that contains the water bill details.
	 */
	private void calcTotalWaterConsumption(WaterBill waterBill) {
		Entry<Double, Double> percent = ratioToPercentage(waterBill.getCorporationWaterRatio(),
				waterBill.getBorewellWaterRatio());
		double corpWaterConsumption = calcCorpWaterConsumption(waterBill, percent);
		double borewellWaterConsumption = calcBorewellWaterConsumption(waterBill, percent);
		double tankerWaterConsumption = calcTankerWaterConsumption(waterBill);
		waterBill.setCorporationWaterConsumed(corpWaterConsumption);
		waterBill.setBorewellWaterConsumed(borewellWaterConsumption);
		waterBill.setTankerWaterConsumed(tankerWaterConsumption);
		waterBill.setTotalWaterConsumed(
				Math.round(corpWaterConsumption + borewellWaterConsumption + tankerWaterConsumption));
	}

	/**
	 * It calculates the total cost of water consumed by a user
	 * 
	 * @param waterBill The object that contains the water consumption details.
	 */
	private void calcTotalCost(WaterBill waterBill) {
		waterBill.setCorporationWaterCost(
				calculatePrice(WaterType.CORPORATION_WATER, Math.round(waterBill.getCorporationWaterConsumed())));
		waterBill.setBorewellWaterCost(
				calculatePrice(WaterType.BOREWELL_WATER, Math.round(waterBill.getBorewellWaterConsumed())));
		waterBill.setTankerWaterCost(
				calculatePrice(WaterType.TANKER_WATER, Math.round(waterBill.getTankerWaterConsumed())));
		waterBill.setTotalCost(Math.round(waterBill.getCorporationWaterCost() + waterBill.getBorewellWaterCost()
				+ waterBill.getTankerWaterCost()));
	}

	/**
	 * It calculates the price of water based on the type of water and the
	 * quantity used
	 * 
	 * @param waterType    The type of water, which can be either BORE_WELL or
	 *                     TANKER.
	 * @param usedQuantity The amount of water used in the billing cycle.
	 */
	public double calculatePrice(WaterType waterType, final long usedQuantity) {
		double totalPrice = 0;
		Long quantity = Long.valueOf(usedQuantity);
		List<WaterPriceChart> priceSlabDetails = Stream.of(WaterPriceChart.values())
				.filter(type -> type.name().startsWith(waterType.name()))
				.filter(type -> type.getStartRange() <= usedQuantity).collect(Collectors.toList());
		for (int i = 0; i < priceSlabDetails.size(); i++) {
			WaterPriceChart slab = priceSlabDetails.get(i);
			int slabQuantity = i > 0 ? slab.getEndRange() - priceSlabDetails.get(i - 1).getEndRange()
					: slab.getEndRange();
			if ((quantity - slabQuantity) >= 0) {
				quantity = quantity - slabQuantity;
				totalPrice += slabQuantity * slab.getPrice();
			} else {
				totalPrice += quantity * slab.getPrice();
			}
		}
		return totalPrice;
	}

	/**
	 * Calculate the water consumption for corporation water
	 * 
	 * @param waterBill The water bill object that is being calculated.
	 * @param percent   The percentage of the water bill that the corporation is
	 *                  responsible for.
	 * @return The value of the water consumption for the corporation.
	 */
	private double calcCorpWaterConsumption(WaterBill waterBill, Entry<Double, Double> percent) {
		return percentageToValue(calculateMaxWaterAllocation(waterBill.getApartmentType().getMemberCount()),
				percent.getKey());
	}

	/**
	 * Calculate the borewell water consumption for a given water bill
	 * 
	 * @param waterBill The water bill object that is being calculated
	 * @param percent   This is the percentage of water consumption that is allowed
	 *                  for the apartment type.
	 */
	private double calcBorewellWaterConsumption(WaterBill waterBill, Entry<Double, Double> percent) {
		return percentageToValue(calculateMaxWaterAllocation(waterBill.getApartmentType().getMemberCount()),
				percent.getValue());
	}

	/**
	 * Calculate the maximum water allocation for the given guest count.
	 * 
	 * @param waterBill The water bill object that is being calculated.
	 * @return The maximum water allocation for the guest count.
	 */
	private double calcTankerWaterConsumption(WaterBill waterBill) {
		return calculateMaxWaterAllocation(waterBill.getGuestCount());
	}

	/**
	 * It calculates the maximum water allocation for a family of a given size
	 * 
	 * @param memberCount The number of members in the family.
	 * @return The maximum amount of water that can be allocated to a family.
	 */
	private int calculateMaxWaterAllocation(int memberCount) {
		return memberCount * DEFAULT_WATER_ALLOCATION * NO_OF_DAYS;
	}
}
