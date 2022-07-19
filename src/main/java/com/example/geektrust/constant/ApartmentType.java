package com.example.geektrust.constant;

import java.util.Optional;
import java.util.stream.Stream;

/**
 * @author Manoj SP
 *
 */
public enum ApartmentType {

	TWO_BHK(2, 3), THREE_BHK(3, 5);

	private int apartmentSize;
	private int memberCount;

	ApartmentType(int apartmentSize, int memberCount) {
		this.apartmentSize = apartmentSize;
		this.memberCount = memberCount;
	}

	/**
	 * It returns the first apartment type that matches the given size
	 * 
	 * @param size The size of the apartment.
	 * @return Optional<ApartmentType>
	 */
	public static Optional<ApartmentType> getApartmentTypeBySize(Integer size) {
		return Stream.of(ApartmentType.values()).filter(apartment -> apartment.apartmentSize == size).findFirst();
	}

	/**
	 * This function returns the number of members in the group
	 * 
	 * @return The number of members in the group.
	 */
	public int getMemberCount() {
		return memberCount;
	}
}
