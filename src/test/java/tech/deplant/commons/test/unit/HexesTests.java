package tech.deplant.commons.test.unit;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.parallel.Execution;
import org.junit.jupiter.api.parallel.ExecutionMode;
import tech.deplant.commons.Hexes;

import java.math.BigInteger;
import java.util.HexFormat;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
@Execution(ExecutionMode.CONCURRENT)
public class HexesTests {

	@Test
	public void all_sort_of_zeroes() {
		String zeroStrNull = null;
		String zeroStrEmpty = "";
		String zeroStr00 = "00";
		String zeroStr0x00 = "0x00";
		String zeroStrm0x00 = "-0x00";
		String zeroStr0x = "0x";
		String zeroStrm0x = "-0x";
		Assertions.assertEquals(0L, Hexes.asInt64(zeroStrNull));
		assertEquals(0L, Hexes.asInt64(zeroStrEmpty));
		assertEquals(0L, Hexes.asInt64(zeroStr00));
		assertEquals(0L, Hexes.asInt64(zeroStr0x00));
		assertEquals(0L, Hexes.asInt64(zeroStrm0x00));
		assertEquals(0L, Hexes.asInt64(zeroStr0x));
		assertEquals(0L, Hexes.asInt64(zeroStrm0x));
	}

	@Test
	public void as_signed_int() {
		assertEquals(2817, Hexes.asInt32("0x0b01"));
		assertEquals(-2817, Hexes.asInt32("-0x0b01"));
		assertEquals("-b01", Hexes.fromInt32(Hexes.asInt32("-0x0b01")));
		assertThrows(NumberFormatException.class, () -> Integer.parseInt("0xffffffff"));
	}

	@Test
	public void negative_int_to_hex() {
		assertEquals("-b01", Integer.toString(-2817, 16));
		assertEquals("fffff4ff", Integer.toUnsignedString(-2817, 16));
	}

	@Test
	public void as_unsigned_int() {
		assertEquals(2817, Hexes.asUint32("0x0b01"));
		assertThrows(NumberFormatException.class, () -> Hexes.asUint32("-0x0b01"));
		assertEquals("ffffffff", Hexes.fromUint32(Hexes.asUint32("0xffffffff")));
	}

	@Test
	public void as_big_int() {
		assertEquals(BigInteger.valueOf(-2817L), Hexes.asBigInt("-0x0b01"));
		assertEquals(BigInteger.valueOf(2817L), Hexes.asBigInt("0x0b01"));
//		assertEquals("-6997131599297339249706535080947115554476651590701556160712383051954228129270",
//		             Hexes.asBigInt("f087c38c58e0215a727bffaf972c2e0e1efa94670ce35af3e2db1303cb96920a")
//		                  .toString(10));
		assertEquals(new BigInteger("-948574999"), Hexes.asBigInt("-0x388a1b17"));
		assertEquals(new BigInteger("-948574999"), Hexes.asBigInt("-388a1b17"));
		assertEquals("ecf54ee29e440ddf31f3a005ccec35b36f0944c8f434b258bfdd510b859897c3",
		             new BigInteger(1,
		                            HexFormat.of()
		                                     .parseHex(
				                                     "ecf54ee29e440ddf31f3a005ccec35b36f0944c8f434b258bfdd510b859897c3"))
				             .toString(16));
		assertEquals("ecf54ee29e440ddf31f3a005ccec35b36f0944c8f434b258bfdd510b859897c3",
		             Hexes.asBigInt("ecf54ee29e440ddf31f3a005ccec35b36f0944c8f434b258bfdd510b859897c3")
		                  .toString(16));

	}


}
