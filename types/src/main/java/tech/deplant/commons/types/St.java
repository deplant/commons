package tech.deplant.commons.types;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URI;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;
import java.util.function.Consumer;
import java.util.function.Predicate;
import java.util.function.Supplier;
import java.util.regex.Pattern;

import static tech.deplant.commons.types.Obj.*;

public class St {

	private static final Pattern HEXADECIMAL_PATTERN = Pattern.compile("\\p{XDigit}+");
	private final static String SPLIT_PATTERN = "[\\s_\\-.]+";

	/**
	 * Downloads a file from URL and converts it into String
	 *
	 * @param path URL address in the network
	 * @return file contents as String
	 */
	public static String ofFile(String path) throws IOException {
		return Files.readString(Path.of(URI.create(path)));
	}

	/**
	 * Downloads a file from URL and converts it into String
	 *
	 * @param path URL address in the network
	 * @return file contents as String
	 */
	public static String ofUrl(String path) throws IOException {
		var con = URI.create(path).toURL().openConnection();
		var output = new ByteArrayOutputStream();
		con.getInputStream().transferTo(output);
		return output.toString();
	}

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

	public static boolean matchesPattern(String str, String patternString) {
		return matchesPattern(str, Pattern.compile(patternString));
	}

	public static boolean matchesPattern(String str, Pattern pattern) {
		return pattern.matcher(str).find();
	}

	public static Predicate<String> matchesPatterPredicate(String patternString) {
		return Pattern.compile(patternString).asMatchPredicate();
	}

	private static String cleanHexadecimalPrefix(String str) {
		if (Obj.isNull(str)) {
			return null;
		} else if (str.startsWith("0x")) {
			return substr(str, 2);
		} else if (str.startsWith("-0x")) {
			return substr(str, 3);
		} else if (str.startsWith("-")) {
			return substr(str, 1);
		} else {
			return str;
		}
	}

	public static boolean isHexadecimal(String str) {
		final String stringWithoutPrefix = cleanHexadecimalPrefix(str);
		return isNotEmpty(stringWithoutPrefix) && stringWithoutPrefix.length() % 2 == 0 &&
		       HEXADECIMAL_PATTERN.matcher(stringWithoutPrefix).matches();
	}

	public static Predicate<String> isHexadecimalPredicate() {
		return HEXADECIMAL_PATTERN.asMatchPredicate();
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
	 * Pascal case is a casing style in which the words for a class or type name are joined together
	 * and the first letter of each is capitalized, such as PascalCase
	 * Example: first_name -> FirstName
	 *
	 * @param text to process
	 * @return pascal-cased test
	 */
	public static String toPascalCase(String text) {
		var builder = new StringBuilder();
		String[] split = text.split(SPLIT_PATTERN);
		for (int i = 0; i < split.length; i++) {
			var str = split[i].toLowerCase();
			builder.append(St.substr(str, 0, 1).toUpperCase(Locale.ROOT) + St.substr(str, 1));
		}
		return builder.toString();
	}

	/**
	 * Camel case is a casing style in which the words for a class or type name are joined together
	 * and the first letter of each word starting from second is capitalized, such as camelCase
	 * Example: first_name -> firstName
	 *
	 * @param text to process
	 * @return camel-cased test
	 */
	public static String toCamelCase(String text) {
		var builder = new StringBuilder();
		String[] split = text.split(SPLIT_PATTERN);
		for (int i = 0; i < split.length; i++) {
			var str = split[i].toLowerCase();
			if (i > 0) {
				builder.append(St.substr(str, 0, 1).toUpperCase(Locale.ROOT) + St.substr(str, 1));
			} else {
				builder.append(str);
			}
		}
		return builder.toString();
	}

	public static String toKebabCase(String text) {
		List<String> lst = new ArrayList<>();
		String[] split = text.split(SPLIT_PATTERN);
		for (int i = 0; i < split.length; i++) {
			var str = split[i].toLowerCase();
			lst.add(str);
		}
		return String.join("-", lst);
	}

	public static String toSnakeCase(String text) {
		List<String> lst = new ArrayList<>();
		String[] split = text.split(SPLIT_PATTERN);
		for (int i = 0; i < split.length; i++) {
			var str = split[i].toLowerCase();
			lst.add(str);
		}
		return String.join("_", lst);
	}

	public static String toLowerDotCase(String text) {
		List<String> lst = new ArrayList<>();
		String[] split = text.split(SPLIT_PATTERN);
		for (int i = 0; i < split.length; i++) {
			var str = split[i].toLowerCase();
			lst.add(str);
		}
		return String.join(".", lst);
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
