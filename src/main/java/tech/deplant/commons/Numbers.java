package tech.deplant.commons;

import java.math.BigDecimal;
import java.math.BigInteger;

public class Numbers {

	private static String preprocessHexString(String hexString) {
		if (Strings.isEmpty(hexString)) {
			return "00";
		} else if (hexString.startsWith("0x")) {
			hexString = Strings.notEmptyElse(Strings.substr(hexString, 2), "00");
		} else if (hexString.startsWith("-0x")) {
			hexString = "-" + Strings.notEmptyElse(Strings.substr(hexString, 3), "00");
		}
		return Strings.notEmptyElse(hexString, "00");
	}

	public static int hexStringToInt(String hexString) {
		return Integer.parseInt(preprocessHexString(hexString), 16);
	}

	public static long hexStringToLong(String hexString) {
		return Long.parseLong(preprocessHexString(hexString), 16);
	}

	public static BigInteger hexStringToBigInt(String hexString) {
		return new BigInteger(preprocessHexString(hexString), 16);
	}

	public static BigDecimal hexStringToBigDec(String hexString, int scale) {
		return new BigDecimal(hexStringToBigInt(hexString), scale);
	}

}
