package com.example.geektrust.dto;

import java.util.Objects;

import com.example.geektrust.constant.ApartmentType;

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
	 * return the existing
	 * instance, making this class as Singleton.
	 * 
	 * @return The instance of the class.
	 */
	public static WaterBill getInstance() {
		if (Objects.isNull(INSTANCE)) {
			INSTANCE = new WaterBill();
		}
		return INSTANCE;
	}

	private ApartmentType apartmentType;
	private int corporationWaterRatio;
	private int borewellWaterRatio;
	private int guestCount;
	private double corporationWaterConsumed;
	private double borewellWaterConsumed;
	private double tankerWaterConsumed;
	private double corporationWaterCost;
	private double borewellWaterCost;
	private double tankerWaterCost;
	private long totalWaterConsumed;
	private long totalCost;

	public ApartmentType getApartmentType() {
		return apartmentType;
	}

	public void setApartmentType(ApartmentType apartmentType) {
		this.apartmentType = apartmentType;
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

	public int getGuestCount() {
		return guestCount;
	}

	public void setGuestCount(int guestCount) {
		this.guestCount = guestCount;
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
		return Objects.hash(apartmentType, borewellWaterConsumed, borewellWaterCost, borewellWaterRatio,
				corporationWaterConsumed, corporationWaterCost, corporationWaterRatio, guestCount, tankerWaterConsumed,
				tankerWaterCost, totalCost, totalWaterConsumed);
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
		return apartmentType == other.apartmentType
				&& Double.doubleToLongBits(borewellWaterConsumed) == Double
						.doubleToLongBits(other.borewellWaterConsumed)
				&& Double.doubleToLongBits(borewellWaterCost) == Double.doubleToLongBits(other.borewellWaterCost)
				&& borewellWaterRatio == other.borewellWaterRatio
				&& Double.doubleToLongBits(corporationWaterConsumed) == Double
						.doubleToLongBits(other.corporationWaterConsumed)
				&& Double.doubleToLongBits(corporationWaterCost) == Double.doubleToLongBits(other.corporationWaterCost)
				&& corporationWaterRatio == other.corporationWaterRatio && guestCount == other.guestCount
				&& Double.doubleToLongBits(tankerWaterConsumed) == Double.doubleToLongBits(other.tankerWaterConsumed)
				&& Double.doubleToLongBits(tankerWaterCost) == Double.doubleToLongBits(other.tankerWaterCost)
				&& totalCost == other.totalCost && totalWaterConsumed == other.totalWaterConsumed;
	}

	@Override
	public String toString() {
		return "WaterBill [apartmentType=" + apartmentType + ", corporationWaterRatio=" + corporationWaterRatio
				+ ", borewellWaterRatio=" + borewellWaterRatio + ", guestCount=" + guestCount
				+ ", corporationWaterConsumed=" + corporationWaterConsumed + ", borewellWaterConsumed="
				+ borewellWaterConsumed + ", tankerWaterConsumed=" + tankerWaterConsumed + ", corporationWaterCost="
				+ corporationWaterCost + ", borewellWaterCost=" + borewellWaterCost + ", tankerWaterCost="
				+ tankerWaterCost + ", totalWaterConsumed=" + totalWaterConsumed + ", totalCost=" + totalCost + "]";
	}

}
