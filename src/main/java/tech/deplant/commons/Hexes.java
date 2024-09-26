package tech.deplant.commons;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.HexFormat;

public class Hexes {

	private static String cleanHexString(String hexString) {
		boolean isNegative = false;
		if (Strings.isEmpty(hexString)) {
			return "00";
		} else if (hexString.startsWith("0x")) {
			hexString = Strings.notEmptyElse(Strings.substr(hexString, 2), "00");
		} else if (hexString.startsWith("-0x")) {
			isNegative = true;
			hexString = Strings.notEmptyElse(Strings.substr(hexString, 3), "00");
		} else if (hexString.startsWith("-")) {
			isNegative = true;
			hexString = Strings.notEmptyElse(Strings.substr(hexString, 1), "00");
		}
		if ((hexString.length() % 2 != 0)) {
			hexString = "0" + hexString;
		}
		if (isNegative) {
			hexString = "-" + hexString;
		}
		return Strings.notEmptyElse(hexString, "00");
	}

	private static byte[] asBytes(String hexString) {
		return HexFormat.of().parseHex(hexString);
	}

	public static int asInt32(String hexString) {
		return Integer.parseInt(cleanHexString(hexString), 16);
	}

	public static int asUint32(String hexString) {
		return Integer.parseUnsignedInt(cleanHexString(hexString), 16);
	}

	public static String fromInt32(int int32) {
		return Integer.toString(int32, 16);
	}

	public static String fromUint32(int uint32) {
		return Integer.toUnsignedString(uint32, 16);
	}

	public static long asInt64(String hexString) {
		return Long.parseLong(cleanHexString(hexString), 16);
	}

	public static long asUint64(String hexString) {
		return Long.parseUnsignedLong(cleanHexString(hexString), 16);
	}

	public static String fromInt64(long int64) {
		return Long.toUnsignedString(int64, 16);
	}

	public static String fromUint64(long uint64) {
		return Long.toString(uint64, 16);
	}

	public static BigInteger asBigInt(String hexString) {
		String s = cleanHexString(hexString);
		if (s.startsWith("-")) {
			return new BigInteger(s, 16);
		} else {
			return new BigInteger(1, HexFormat.of().parseHex(s));
		}
	}

	public static BigDecimal asBigDec(String hexString, int scale) {
		return new BigDecimal(asBigInt(hexString), scale);
	}

}
