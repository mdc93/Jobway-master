package cripel.jobway.utilities;

import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

/**
 *
 * The Utilities class to convert the date format
 */
public class DateUtil {

	private DateUtil() {
	}

	/**
	 * Method to convert a date to a local date format
	 *
	 * @param date
	 * @return
	 */
	public static LocalDate convertToLocalDate(Date date) {

		if (date != null) {
			return Instant.ofEpochMilli(date.getTime()).atZone(ZoneId.systemDefault()).toLocalDate();
		}

		return null;
	}

	/**
	 * Method to convert a local date to a date format
	 *
	 * @param date
	 * @return
	 */
	public static Date convertToDate(LocalDate date) {

		if (date != null) {
			return Date.from(date.atStartOfDay().atZone(ZoneId.systemDefault()).toInstant());
		}
		return null;
	}

	/**
	 * Method to convert a localDateTime to a date format
	 *
	 * @param date
	 * @return
	 */
	public static Date convertToDate(LocalDateTime date) {

		if (date != null) {
			return Date.from(date.atZone(ZoneId.systemDefault()).toInstant());

		}
		return null;
	}

	/**
	 * Format and convert to {@link String} current time
	 *
	 * @return
	 */
	public static String dateTimeFile() {
		DateTimeFormatter format = DateTimeFormatter.ofPattern("yyyy-MM-dd_HH-mm-ss");
		return ZonedDateTime.now(ZoneId.systemDefault()).format(format);
	}

}
