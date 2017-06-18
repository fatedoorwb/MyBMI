package idv.kuchu.mybmi.data;

import java.util.Calendar;

public class DateObject {

	public static int DateGap(String date_start, String date_end) {
		if (date_start == null || date_end == null || date_start.length() != 8 || date_end.length() != 8)
			return -1;
		int year1 = Integer.valueOf(date_start.substring(0, 4));
		int month1 = Integer.valueOf(date_start.substring(4, 6));
		int day1 = Integer.valueOf(date_start.substring(6, 8));
		int year2 = Integer.valueOf(date_end.substring(0, 4));
		int month2 = Integer.valueOf(date_end.substring(4, 6));
		int day2 = Integer.valueOf(date_end.substring(6, 8));
		if (!DateObject.DateThan(year1, month1, day1, year2, month2, day2))
			return -1;
		int result = 0;
		if (year2 - year1 > 1) {
			int d1 = year1;
			int d2 = year2;
			for (int i = 1; i < d2 - d1; i++)
				result += DateObject.getYD(d2 - i);
		}
		int[] cd;
		if (year2 > year1) {
			cd = DateObject.getCD(year2);
			for (int i = 1; i < month2; i++) {
				result += cd[i];
			}
			result += day2;
			year2 = year1;
			month2 = 12;
			day2 = 31;
		}
		cd = DateObject.getCD(year2);
		if (month2 - month1 > 1) {
			int d1 = month1;
			int d2 = month2;
			for (int i = 1; i < d2 - d1; i++)
				result += cd[d2 - i];
		}
		if (month2 > month1) {
			result += cd[month2 - 1];
			year2 = year1;
			month2 = month1;
			day2 = cd[month2 - 1];
		}
		result += day2 - day1 + 1;
		return result;
	}

	private static boolean DateThan(int year1, int month1, int day1, int year2, int month2, int day2) {
		if (year1 > year2)
			return false;
		if (year1 < year2)
			return true;
		if (month1 > month2)
			return false;
		if (month1 < month2)
			return true;
		System.out.println(day1 + ">" + day2);
		if (day1 > day2)
			return false;
		return true;
	}

	public static String getNow() {
		Calendar c = Calendar.getInstance();
		int year = c.get(Calendar.YEAR);
		int month = c.get(Calendar.MONTH) + 1;
		int day = c.get(Calendar.DAY_OF_MONTH);
		return DateObject.getDate(year, month, day);
	}

	public static int getNowYear() {
		Calendar c = Calendar.getInstance();
		return c.get(Calendar.YEAR);
	}

	public static int getNowMonth() {
		Calendar c = Calendar.getInstance();
		return c.get(Calendar.MONTH) + 1;
	}

	public static int getNowDay() {
		Calendar c = Calendar.getInstance();
		return c.get(Calendar.DAY_OF_MONTH);
	}

	public static String getDate(int year, int month, int day) {
		return DateObject.getYear(year) + DateObject.getMonth(month) + DateObject.getDay(day);
	}

	public static String getYear(int year) {
		year = year % 10000;
		if (year < 0)
			return "0000";
		if (year < 10)
			return "000" + year;
		if (year < 100)
			return "00" + year;
		if (year < 1000)
			return "0" + year;
		return "" + year;
	}

	public static String getMonth(int month) {
		return month < 10 ? "0" + month : "" + month;
	}

	public static String getDay(int day) {
		return day < 10 ? "0" + day : "" + day;
	}

	public static int getYD(int year) {
		if (year % 400 == 0 || (year % 4 == 0 && year % 100 != 0)) {
			return 366;
		}
		return 365;
	}

	public static int[] getCD(int year) {
		int[] cd = new int[] { 31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31 };
		if (year % 400 == 0 || (year % 4 == 0 && year % 100 != 0)) {
			cd[1] = 29;
		}
		return cd;
	}

	public final float height;
	public final float weight;
	final int c;

	public DateObject(double height, double weight, int c) {
		this.height = (float) height;
		this.weight = (float) weight;
		this.c = c;
	}

}
