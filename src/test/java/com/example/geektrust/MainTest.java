package com.example.geektrust;

import static com.example.geektrust.constant.WaterBillAppCommonConstants.INVALID_INPUT;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.PrintStream;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.example.geektrust.constant.ApartmentType;
import com.example.geektrust.dto.WaterBill;

/**
 * @author Manoj SP
 *
 */
class MainTest {

	private final ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();

	@BeforeEach
	public void setUp() {
		System.setOut(new PrintStream(outputStreamCaptor));
		System.setErr(new PrintStream(outputStreamCaptor));
		WaterBill waterbill = WaterBill.getInstance();
		waterbill.setTotalCost(0);
		waterbill.setCorporationWaterConsumed(0);
		waterbill.setCorporationWaterCost(0);
		waterbill.setBorewellWaterConsumed(0);
		waterbill.setBorewellWaterCost(0);
		waterbill.setGuestCount(0);
		waterbill.setTankerWaterConsumed(0);
		waterbill.setTankerWaterCost(0);
		waterbill.setTotalWaterConsumed(0);
	}

	@Test
	void testAllotWater_valid() {
		String absolutePath = new File(this.getClass().getClassLoader().getResource("AllotWaterValid.txt").getFile())
				.getAbsolutePath();
		Main.main(new String[] { absolutePath });
		WaterBill waterbill = WaterBill.getInstance();
		assertEquals(ApartmentType.TWO_BHK, waterbill.getApartmentType());
		assertEquals(1, waterbill.getCorporationWaterRatio());
		assertEquals(2, waterbill.getBorewellWaterRatio());
	}

	@Test
	void testAddGuests_valid() {
		String absolutePath = new File(this.getClass().getClassLoader().getResource("AllotWaterValid.txt").getFile())
				.getAbsolutePath();
		Main.main(new String[] { absolutePath });
		absolutePath = new File(this.getClass().getClassLoader().getResource("AddGuestValid.txt").getFile())
				.getAbsolutePath();
		Main.main(new String[] { absolutePath });
		WaterBill waterbill = WaterBill.getInstance();
		assertEquals(2, waterbill.getGuestCount());
	}

	@Test
	void testBill_valid() {
		String absolutePath = new File(this.getClass().getClassLoader().getResource("AllotWaterValid.txt").getFile())
				.getAbsolutePath();
		Main.main(new String[] { absolutePath });
		absolutePath = new File(this.getClass().getClassLoader().getResource("BillValid.txt").getFile())
				.getAbsolutePath();
		Main.main(new String[] { absolutePath });
		WaterBill waterbill = WaterBill.getInstance();
		assertEquals(1200, waterbill.getTotalCost());
		assertEquals(900, waterbill.getTotalWaterConsumed());
	}

	@Test
	void testBillWithGuest_valid() {
		String absolutePath = new File(
				this.getClass().getClassLoader().getResource("AllotWaterWithGuestsAndBill.txt").getFile())
				.getAbsolutePath();
		Main.main(new String[] { absolutePath });
		WaterBill waterbill = WaterBill.getInstance();
		assertEquals(2500, waterbill.getTotalCost());
		assertEquals(1500, waterbill.getTotalWaterConsumed());
	}

	@Test
	void testNoCommand() {
		String absolutePath = new File(this.getClass().getClassLoader().getResource("NoCommand.txt").getFile())
				.getAbsolutePath();
		Main.main(new String[] { absolutePath });
	}

	@Test
	void testEmptyCommand() {
		String absolutePath = new File(this.getClass().getClassLoader().getResource("EmptyCommand.txt").getFile())
				.getAbsolutePath();
		Main.main(new String[] { absolutePath });
		assertEquals("ERROR: " + INVALID_INPUT, outputStreamCaptor.toString().trim());
	}

	@Test
	void testInvalidGuestCount() {
		String absolutePath = new File(
				this.getClass().getClassLoader().getResource("AddGuestInvalidGuestCount.txt").getFile())
				.getAbsolutePath();
		Main.main(new String[] { absolutePath });
		assertEquals("ERROR: " + INVALID_INPUT, outputStreamCaptor.toString().trim());
	}

	@Test
	void testIAllotWaterInvalidApartment() {
		String absolutePath = new File(
				this.getClass().getClassLoader().getResource("AllotWaterInvalidApartment.txt").getFile())
				.getAbsolutePath();
		Main.main(new String[] { absolutePath });
		assertEquals("ERROR: " + INVALID_INPUT, outputStreamCaptor.toString().trim());
	}

	@Test
	void testAllotWaterInvalidRatio() {
		String absolutePath = new File(
				this.getClass().getClassLoader().getResource("AllotWaterInvalidRatio.txt").getFile()).getAbsolutePath();
		Main.main(new String[] { absolutePath });
		assertEquals("ERROR: " + INVALID_INPUT, outputStreamCaptor.toString().trim());
	}

	@Test
	void testFileNotAvailable() {
		String absolutePath = new File(
				this.getClass().getClassLoader().getResource("AllotWaterInvalidRatio.txt").getFile()).getAbsolutePath();
		Main.main(new String[] { absolutePath.replace(".txt", "") });
		assertEquals("ERROR: " + INVALID_INPUT, outputStreamCaptor.toString().trim());
	}

	@Test
	void testInvalidApartmentSize() {
		String absolutePath = new File(
				this.getClass().getClassLoader().getResource("InvalidApartmentSize.txt").getFile()).getAbsolutePath();
		Main.main(new String[] { absolutePath });
		assertEquals("ERROR: " + INVALID_INPUT, outputStreamCaptor.toString().trim());
	}

	@Test
	void testMultipleBill() {
		String absolutePath = new File(
				this.getClass().getClassLoader().getResource("MultipleBill.txt").getFile()).getAbsolutePath();
		Main.main(new String[] { absolutePath });
		assertTrue(outputStreamCaptor.toString().trim().startsWith("TOTAL_WATER_CONSUMED_IN_LITRES"));
	}

	@Test
	void testBillWithInputs() {
		String absolutePath = new File(
				this.getClass().getClassLoader().getResource("BillWithInputs.txt").getFile()).getAbsolutePath();
		Main.main(new String[] { absolutePath });
		assertEquals("ERROR: " + INVALID_INPUT, outputStreamCaptor.toString().trim());
	}

	@Test
	void testInvalidCommand() {
		String absolutePath = new File(
				this.getClass().getClassLoader().getResource("InvalidCommand.txt").getFile()).getAbsolutePath();
		Main.main(new String[] { absolutePath });
		assertEquals("ERROR: " + INVALID_INPUT, outputStreamCaptor.toString().trim());
	}

}