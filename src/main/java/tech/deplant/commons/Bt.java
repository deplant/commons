package tech.deplant.commons;

import java.nio.ByteBuffer;
import java.util.Arrays;
import java.util.Base64;
import java.util.HexFormat;

public class Bt {

	public enum Encoding { HEX, BASE_64 }

	public static byte[] concatArrays(byte[]... byteArrays) {
		int totalSize = 0;
		for (byte[] barr : byteArrays) {
			totalSize += barr.length;
		}
		return concatArrays(totalSize, byteArrays);
	}

	public static byte[] concatArrays(int resultLength, byte[]... byteArrays) {
		byte[] resultByteArray = new byte[resultLength];
		ByteBuffer buffer = ByteBuffer.wrap(resultByteArray);
		for (byte[] bytes : byteArrays) {
			buffer.put(bytes);
		}
		resultByteArray = buffer.array();
		return resultByteArray;
	}

	public static long asInt(byte[] bytes) {
		return ByteBuffer.wrap(bytes).getInt();
	}

	public static long asInt(byte[] bytes, int beginIndex) {
		return ByteBuffer.wrap(bytes).getInt(beginIndex);
	}

	public static long asLong(byte[] bytes) {
		return ByteBuffer.wrap(bytes).getLong();
	}
	public static long asLong(byte[] bytes, int beginIndex) {
		return ByteBuffer.wrap(bytes).getLong(beginIndex);
	}

	public static byte[] substrArray(byte[] byteArray, int beginIndex, int endIndex) {
		return Arrays.copyOfRange(byteArray, beginIndex, endIndex);
	}

	public static String encode(byte[] byteArray, Encoding encoding) {
		return switch (encoding) {
			case HEX -> HexFormat.of().formatHex(byteArray);
			case BASE_64 -> Base64.getEncoder().encodeToString(byteArray);
		};
	}

	public static byte[] decode(String encodedString, Encoding encoding) {
		return switch (encoding) {
			case HEX -> HexFormat.of().parseHex(encodedString);
			case BASE_64 -> Base64.getDecoder().decode(encodedString);
		};
	}
}
