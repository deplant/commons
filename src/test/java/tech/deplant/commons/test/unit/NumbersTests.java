package tech.deplant.commons.test.unit;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.parallel.Execution;
import org.junit.jupiter.api.parallel.ExecutionMode;
import tech.deplant.commons.Numbers;

import static org.junit.jupiter.api.Assertions.assertEquals;

@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
@Execution(ExecutionMode.CONCURRENT)
public class NumbersTests {

	@Test
	public void all_sort_of_zeroes() {
		String zeroStrNull = null;
		String zeroStrEmpty = "";
		String zeroStr00 = "00";
		String zeroStr0x00 = "0x00";
		String zeroStrm0x00 = "-0x00";
		String zeroStr0x = "0x";
		String zeroStrm0x = "-0x";
		Assertions.assertEquals(0L, Numbers.hexStringToLong(zeroStrNull));
		assertEquals(0L, Numbers.hexStringToLong(zeroStrEmpty));
		assertEquals(0L, Numbers.hexStringToLong(zeroStr00));
		assertEquals(0L, Numbers.hexStringToLong(zeroStr0x00));
		assertEquals(0L, Numbers.hexStringToLong(zeroStrm0x00));
		assertEquals(0L, Numbers.hexStringToLong(zeroStr0x));
		assertEquals(0L, Numbers.hexStringToLong(zeroStrm0x));
	}

//	@Test
//	public void decimal_string_to_bigint_equals_itself() {
//		assertEquals("3000000000000",Numbers.hexStringToBigInt("3000000000000").toString());
//	}


}
