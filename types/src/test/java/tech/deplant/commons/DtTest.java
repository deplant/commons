package tech.deplant.commons;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.parallel.Execution;
import org.junit.jupiter.api.parallel.ExecutionMode;
import tech.deplant.commons.Dt;

@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
@Execution(ExecutionMode.CONCURRENT)
public class DtTest {

	@Test
	public void parse_unix_time_and_convert_to_utc() {
		long unixLong = 1688634679L;
		String unixString = "1688634679";
		String utcString = "2023-07-06T09:11:19";
		Assertions.assertEquals(Dt.fromUnixLong(unixLong), Dt.fromUnixString(unixString));
		Assertions.assertEquals(utcString, Dt.toIsoString(Dt.fromUnixLong(unixLong)));
	}

	@Test
	public void add_offset_to_utc() {
		long unixLong = 1688634679L;
		Assertions.assertEquals("2023-07-06T12:11:19", Dt.toIsoString(Dt.fromUnixLong(unixLong), 10800000));
	}

	@Test
	public void print_non_iso_formats() {
		long unixLong = 1688634679L;
		Assertions.assertEquals("06.07.2023 12:11:19", Dt.toCustomString(Dt.fromUnixLong(unixLong), 10800000, "dd.MM.yyyy HH:mm:ss"));
	}

}
