package com.example.geektrust.dto;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * A class that represents a water bill.
 *
 * @author Manoj SP
 */
public class WaterBill {

	private static WaterBill INSTANCE;

	private WaterBill() {
	}

	/**
	 * If the instance is null, create a new instance and return it. Otherwise,
	 * return the existing instance, making this class as Singleton.
	 * 
	 * @return The instance of the class.
	 */
	public static WaterBill getInstance() {
		if (Objects.isNull(INSTANCE)) {
			INSTANCE = new WaterBill();
		}
		return INSTANCE;
	}

	private int apartmentSize;
	private int numberOfPersonsInApartment;
	private int corporationWaterRatio;
	private int borewellWaterRatio;
	private Map<Integer, Integer> guestStayDetails;
	private double corporationWaterConsumed;
	private double borewellWaterConsumed;
	private double tankerWaterConsumed;
	private double corporationWaterCost;
	private double borewellWaterCost;
	private double tankerWaterCost;
	private long totalWaterConsumed;
	private long totalCost;

	public void setApartmentSize(int apartmentSize) {
		this.apartmentSize = apartmentSize;
	}
	
	public int getApartmentSize() {
		return apartmentSize;
	}
	
	public void setNumberOfPersonsInApartment(int numberOfPersonsInApartment) {
		this.numberOfPersonsInApartment = numberOfPersonsInApartment;
	}

	public int getNumberOfPersonsInApartment() {
		return numberOfPersonsInApartment;
	}

	public int getCorporationWaterRatio() {
		return corporationWaterRatio;
	}

	public void setCorporationWaterRatio(int corporationWaterRatio) {
		this.corporationWaterRatio = corporationWaterRatio;
	}

	public int getBorewellWaterRatio() {
		return borewellWaterRatio;
	}

	public void setBorewellWaterRatio(int borewellWaterRatio) {
		this.borewellWaterRatio = borewellWaterRatio;
	}

	public Map<Integer, Integer> getGuestStayDetails() {
		return guestStayDetails;
	}

	public void addGuests(int numberOfGuests, int numberOfDays) {
		if (Objects.isNull(guestStayDetails)) {
			guestStayDetails = new HashMap<>();
		}
		guestStayDetails.compute(numberOfGuests, (k, v) -> Objects.isNull(v) ? numberOfDays : v + numberOfDays);
	}

	public long getTotalWaterConsumed() {
		return totalWaterConsumed;
	}

	public void setTotalWaterConsumed(long totalWaterConsumed) {
		this.totalWaterConsumed = totalWaterConsumed;
	}

	public double getCorporationWaterConsumed() {
		return corporationWaterConsumed;
	}

	public void setCorporationWaterConsumed(double corporationWaterConsumed) {
		this.corporationWaterConsumed = corporationWaterConsumed;
	}

	public double getBorewellWaterConsumed() {
		return borewellWaterConsumed;
	}

	public void setBorewellWaterConsumed(double borewellWaterConsumed) {
		this.borewellWaterConsumed = borewellWaterConsumed;
	}

	public double getTankerWaterConsumed() {
		return tankerWaterConsumed;
	}

	public void setTankerWaterConsumed(double tankerWaterConsumed) {
		this.tankerWaterConsumed = tankerWaterConsumed;
	}

	public double getCorporationWaterCost() {
		return corporationWaterCost;
	}

	public void setCorporationWaterCost(double corporationWaterCost) {
		this.corporationWaterCost = corporationWaterCost;
	}

	public double getBorewellWaterCost() {
		return borewellWaterCost;
	}

	public void setBorewellWaterCost(double borewellWaterCost) {
		this.borewellWaterCost = borewellWaterCost;
	}

	public double getTankerWaterCost() {
		return tankerWaterCost;
	}

	public void setTankerWaterCost(double tankerWaterCost) {
		this.tankerWaterCost = tankerWaterCost;
	}

	public long getTotalCost() {
		return totalCost;
	}

	public void setTotalCost(long totalCost) {
		this.totalCost = totalCost;
	}

	@Override
	public int hashCode() {
		return Objects.hash(apartmentSize, numberOfPersonsInApartment, borewellWaterConsumed, borewellWaterCost,
				borewellWaterRatio, corporationWaterConsumed, corporationWaterCost, corporationWaterRatio,
				guestStayDetails, tankerWaterConsumed, tankerWaterCost, totalCost, totalWaterConsumed);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		WaterBill other = (WaterBill) obj;
		return apartmentSize == other.apartmentSize && numberOfPersonsInApartment == other.numberOfPersonsInApartment
				&& Double.doubleToLongBits(borewellWaterConsumed) == Double
						.doubleToLongBits(other.borewellWaterConsumed)
				&& Double.doubleToLongBits(borewellWaterCost) == Double.doubleToLongBits(other.borewellWaterCost)
				&& borewellWaterRatio == other.borewellWaterRatio
				&& Double.doubleToLongBits(corporationWaterConsumed) == Double
						.doubleToLongBits(other.corporationWaterConsumed)
				&& Double.doubleToLongBits(corporationWaterCost) == Double.doubleToLongBits(other.corporationWaterCost)
				&& corporationWaterRatio == other.corporationWaterRatio && guestStayDetails == other.guestStayDetails
				&& Double.doubleToLongBits(tankerWaterConsumed) == Double.doubleToLongBits(other.tankerWaterConsumed)
				&& Double.doubleToLongBits(tankerWaterCost) == Double.doubleToLongBits(other.tankerWaterCost)
				&& totalCost == other.totalCost && totalWaterConsumed == other.totalWaterConsumed;
	}

	@Override
	public String toString() {
		return "WaterBill [apartmentSize=" + apartmentSize + ", corporationWaterRatio=" + corporationWaterRatio
				+ ", borewellWaterRatio=" + borewellWaterRatio + ", guestCount=" + guestStayDetails
				+ ", corporationWaterConsumed=" + corporationWaterConsumed + ", borewellWaterConsumed="
				+ borewellWaterConsumed + ", tankerWaterConsumed=" + tankerWaterConsumed + ", corporationWaterCost="
				+ corporationWaterCost + ", borewellWaterCost=" + borewellWaterCost + ", tankerWaterCost="
				+ tankerWaterCost + ", totalWaterConsumed=" + totalWaterConsumed + ", totalCost=" + totalCost + "]";
	}

}
