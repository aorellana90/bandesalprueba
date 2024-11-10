package com.bandesalprueba.sv.component.util;

import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

public class DateUtils {

	private DateUtils() {
		throw new IllegalStateException("DateUtils class component util");
	}

	public static String formaterDate() {

		ZoneId zonaElSalvador = ZoneId.of("America/El_Salvador");

		ZonedDateTime timestamp = ZonedDateTime.now(zonaElSalvador);

		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss:SSS");

		return timestamp.format(formatter);
	}

}
