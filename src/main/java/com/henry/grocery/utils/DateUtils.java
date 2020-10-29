package com.henry.grocery.utils;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DateUtils {

	public static boolean isValidDate(String promoStartDate, String promoEndDate, Date d) {

		try {

			SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
			Date startDate = sdf.parse(promoStartDate);
			Date endDate = sdf.parse(promoEndDate);

			String currDt = sdf.format(d);

			if ((d.after(startDate) && (d.before(endDate)))
					|| (currDt.equals(sdf.format(startDate)) || currDt.equals(sdf.format(endDate)))) {

				return true;
			} else {
				return false;
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}
    /* This method retrieve the date based on the offset value
     *   If we provide 0 , it gives today's date in dd/MM/yyyy format
     *   If we provide 5 , it gives 5 days later date in dd/MM/yyyy format
     *   If we provide -1 , it gives yesterdays date in dd/MM/yyyy format
     * */
	public static Date getToday(int offset) {

		SimpleDateFormat sdf = null;
		String today = "";
		Date fd = null;
		try {

			Calendar cal = Calendar.getInstance();

			int dayOfYear = cal.get(Calendar.DAY_OF_YEAR); // get today
			offset = dayOfYear + offset; // add any offset to today
			cal.set(Calendar.DAY_OF_YEAR, offset); // set day required using offset

			today = (cal.get(Calendar.DAY_OF_MONTH)) + "/" + // format date for entry to database
					(cal.get(Calendar.MONTH) + 1) + "/" + (cal.get(Calendar.YEAR));

			sdf = new SimpleDateFormat("dd/MM/yyyy");
			fd = sdf.parse(today);
		} catch (Exception e1) {
			e1.printStackTrace();
		}

		return fd;
	}

}
