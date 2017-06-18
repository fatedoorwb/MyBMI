package idv.kuchu.mybmi.data;

public class DateObject {
	
	public static String getDate(int year,int month,int day){
		return DateObject.getYear(year)+DateObject.getMonth(month)+DateObject.getDay(day);
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

	public final float height;
	public final float weight;
	final int c;

	public DateObject(double height, double weight, int c) {
		this.height = (float) height;
		this.weight = (float) weight;
		this.c = c;
	}

}
