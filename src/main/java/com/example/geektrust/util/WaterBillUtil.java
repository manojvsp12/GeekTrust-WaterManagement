package com.example.geektrust.util;

import java.util.AbstractMap;
import java.util.Map;
import java.util.function.DoubleUnaryOperator;

/**
 * A utility class which has two methods.
 * 1. `ratioToPercentage` - It takes two integers, adds them together, divides
 * each integer by the sum, and multiplies the result by 100.
 * 2. `percentageToValue` - It takes a value and a percentage and returns the
 * value of the percentage of the value.
 * 
 * @author Manoj SP
 *
 */
public final class WaterBillUtil {
	
	private WaterBillUtil() {
		
	}

	/**
	 * Converts two ratio into their respective percentage value.
	 * It takes two integers, adds them together, divides each integer by the sum,
	 * and multiplies the
	 * result by 100
	 * 
	 * @param ratio1 The first ratio
	 * @param ratio2 The number of times the second option was chosen.
	 * @return A Map.Entry<Double, Double>
	 */
	public static final Map.Entry<Double, Double> ratioToPercentage(int ratio1, int ratio2) {
		int overallRatio = Math.addExact(ratio1, ratio2);
		DoubleUnaryOperator getPercent = ratio -> ((double) ratio / (double) overallRatio) * 100;
		return new AbstractMap.SimpleEntry<>(getPercent.applyAsDouble(ratio1), getPercent.applyAsDouble(ratio2));
	}

	/**
	 * It takes a value and a percentage and returns the value of the percentage of
	 * the value
	 * 
	 * @param value   The value to calculate the percentage of.
	 * @param percent The percentage you want to calculate.
	 * @return The value of the percentage of the value.
	 */
	public static final double percentageToValue(int value, double percent) {
		return (value * percent) / 100;
	}
}
