package tech.deplant.commons;

import java.time.Instant;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;

public class Dates {


	public static Instant fromUnixLong(long unixTimeLong) {
		return Instant.ofEpochSecond(unixTimeLong);
	}

	public static Instant fromUnixString(String unixTimeString) {
		return fromUnixLong(Long.parseLong(unixTimeString));
	}

	public static Instant fromUnixHexString(String hexString) {
		return fromUnixLong(Numbers.hexStringToLong(hexString));
	}

	public static String toIsoString(Instant utcMoment, int offsetMillis) {
		return toIsoString(toClientOffset(utcMoment, offsetMillis));
	}

	public static String toIsoString(Instant utcMoment) {
		return toIsoString(utcMoment, 0);
	}

	public static String toIsoString(OffsetDateTime dt) {
		return toFormattedString(dt, DateTimeFormatter.ISO_LOCAL_DATE_TIME);
	}

	public static String toCustomString(Instant utcMoment, int offsetMillis, String formatMask) {
		return toFormattedString(toClientOffset(utcMoment, offsetMillis), DateTimeFormatter.ofPattern(formatMask));
	}

	public static String toCustomString(Instant utcMoment, String formatMask) {
		return toFormattedString(toClientOffset(utcMoment, 0), DateTimeFormatter.ofPattern(formatMask));
	}

	public static OffsetDateTime toClientOffset(Instant utcMoment, int offsetMillis) {
		return utcMoment.atOffset(ZoneOffset.ofTotalSeconds(offsetMillis/1000));
	}

	public static String toFormattedString(OffsetDateTime dt, DateTimeFormatter formatter) {
		return dt.toZonedDateTime().format(formatter);
	}

}
