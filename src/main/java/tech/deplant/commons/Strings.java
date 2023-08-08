package tech.deplant.commons;

import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.HexFormat;
import java.util.function.Consumer;
import java.util.function.Supplier;

import static tech.deplant.commons.Objs.*;

public class Strings {

	public static boolean isEmpty(String str) {
		return isNull(str) || str.isBlank();
	}

	public static boolean isNotEmpty(String str) {
		return isNotNull(str) && !str.isBlank();
	}

	public static String notEmpty(String str) {
		if (isEmpty(str)) {
			throw new IllegalArgumentException();
		}
		return str;
	}

	public static String notEmpty(String str, String message) {
		if (isEmpty(str)) {
			throw new IllegalArgumentException(message);
		}
		return str;
	}

	public static <T> void notEmptyDo(String str, Consumer<String> action) {
		if (isNotEmpty(str)) {
			notNull(action).accept(str);
		}
	}

	public static <X extends Throwable> String notEmptyThrow(String str,
	                                                         Supplier<? extends X> exceptionSupplier) throws X {
		if (isEmpty(str)) {
			throw notNull(exceptionSupplier).get();
		}
		return str;
	}

	public static String notEmptyElse(String str, String defaultStr) {
		return isNotEmpty(str) ? str : notEmpty(defaultStr);
	}

	public static String notEmptyElseLazy(String str, Supplier<String> defaultSupplier) {
		return isNotEmpty(str) ? str : notEmpty(notNull(defaultSupplier).get());
	}

	public static boolean notEmptyEquals(String originalStr, String compareStr) {
		return notEmpty(originalStr).equals(notEmpty(compareStr));
	}

	public static String substr(String originalStr, int beginIndex) {
		return substr(originalStr, beginIndex, false);
	}

	public static String substr(String originalStr, int beginIndex, int endIndex) {
		return substr(originalStr, beginIndex, endIndex, false);
	}

	public static String substr(final String originalStr, int beginIndex, boolean reversed) {
		if (isEmpty(originalStr)) {
			return "";
		}
		int len = originalStr.length();
		final String substred;
		if (beginIndex >= len) {
			substred = "";
		} else if (beginIndex <= 0) {
			substred = originalStr;
		} else {
			int begin = reversed ? 0 : beginIndex;
			int end = reversed ? len - beginIndex : len;
			substred = originalStr.substring(begin, end);
		}
		return substred;
	}

	public static String substr(final String originalStr, int beginIndex, int endIndex, boolean reversed) {
		if (isEmpty(originalStr)) {
			return "";
		}
		int len = originalStr.length();
		final String substred;
		if (beginIndex >= len || endIndex <= beginIndex || endIndex <= 0) {
			substred = "";
		} else if (beginIndex <= 0 && endIndex >= len) {
			substred = originalStr;
		} else {
			int begin = reversed ? len - endIndex : beginIndex;
			int end = reversed ? len - beginIndex : endIndex;
			substred = originalStr.substring(begin, end);
		}
		return substred;
	}

	/**
	 * Safe means this method shouldn't ever fail, it will return false on nulls,
	 * empty strings, bad indexes and all other possible inconsistencies.
	 * True will be shown only if both string are non-empty and substr result is non-empty and
	 * originalStr and substr result are equal.
	 *
	 * @param originalStr original string that will be substred
	 * @param beginIndex  substr begin index
	 * @param endIndex    substr end index
	 * @param reversed    should be true if indexes should be counted backwards (from the end of the string)
	 * @param compareStr  string to compare with substring result
	 * @return result of comparison
	 */
	public static boolean safeSubstrEquals(String originalStr,
	                                       int beginIndex,
	                                       int endIndex,
	                                       boolean reversed,
	                                       String compareStr) {
		String substred = substr(originalStr, beginIndex, endIndex, reversed);
		return isNotEmpty(compareStr) && isNotEmpty(substred) && compareStr.equals(substred);
	}

	public static String base64StringToHexString(String base64string) {
		return HexFormat.of().formatHex(Base64.getDecoder().decode(base64string));
	}

	public static String padLeftZeros(String inputString, int length) {
		if (inputString.length() >= length) {
			return inputString;
		}
		StringBuilder sb = new StringBuilder();
		while (sb.length() < length - inputString.length()) {
			sb.append('0');
		}
		sb.append(inputString);

		return sb.toString();
	}

	/**
	 * Utility method for preparing hex strings
	 *
	 * @param data Bytes to encode.
	 * @return Hex string
	 */
	public static String toHexString(byte[] data) {
		final char[] hexCode = "0123456789ABCDEF".toCharArray();
		final StringBuilder r = new StringBuilder(data.length * 2);
		for (byte b : data) {
			r.append(hexCode[(b >> 4) & 0xF]);
			r.append(hexCode[(b & 0xF)]);
		}
		return r.toString();
	}

	/**
	 * Utility method for preparing hex strings
	 *
	 * @param text Text string to encode.
	 * @return Hex string
	 */
	public static String toHexString(String text) {
		return toHexString(text.getBytes(StandardCharsets.UTF_8));
	}

	public static byte[] hexStringToBytes(String text) {
		final int len = text.length();

		// "111" is not a valid hex encoding.
		if (len % 2 != 0) {
			throw new IllegalArgumentException("hexBinary needs to be even-length: " + text);
		}
		byte[] out = new byte[len / 2];

		for (int i = 0; i < len; i += 2) {
			int h = hexCharToBin(text.charAt(i));
			int l = hexCharToBin(text.charAt(i + 1));
			if (h == -1 || l == -1) {
				throw new IllegalArgumentException("contains illegal character for hexBinary: " + text);
			}
			out[i / 2] = (byte) (h * 16 + l);
		}
		return out;
	}

	public static String hexStringToString(String text) {
		return new String(hexStringToBytes(text), StandardCharsets.UTF_8);
	}

	/**
	 * Custom hex2bin function based on new switch-case syntax
	 *
	 * @param hex Char to convert to binary int
	 * @return Binary integer value, -1 for wrong values
	 */
	private static int hexCharToBin(char hex) {
		return switch (hex) {
			case '0' -> 0b0000;
			case '1' -> 0b0001;
			case '2' -> 0b0010;
			case '3' -> 0b0011;
			case '4' -> 0b0100;
			case '5' -> 0b0101;
			case '6' -> 0b0110;
			case '7' -> 0b0111;
			case '8' -> 0b1000;
			case '9' -> 0b1001;
			case 'A' -> 0b1010;
			case 'B' -> 0b1011;
			case 'C' -> 0b1100;
			case 'D' -> 0b1101;
			case 'E' -> 0b1110;
			case 'F' -> 0b1111;
			default -> -1;
		};
	}

}
