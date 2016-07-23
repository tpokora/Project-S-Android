package projects.tpokora.com.project_s_android.utils;

import android.util.Log;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * Created by pokor on 26.06.2016.
 */
public class DateUtils {

    private static final String DEBUG_TAG = "DATE_UTILS";

    public static final String DATE_YEAR_MONTH_DAY_TIME_OFFSET = "yyyy-MM-dd HH:mm:ss Z";
    public static final String DATE_YEAR_MONTH_DAY = "yyyy-MM-dd";

    public static final Date stringToDate(String format, String dateString) {
        return stringToDate(format, dateString, Locale.ENGLISH);
    }

    public static Date stringToDate(String format, String dateString, Locale locale) {
        SimpleDateFormat formatter = new SimpleDateFormat(format, locale);
        Date date = null;
        try {
            date = formatter.parse(dateString);
        } catch (ParseException e) {
            Log.e(DEBUG_TAG, "Error formatting date string: " + dateString);
        }

        return date;
    }

    public static String dateToString(String format, Date date) {
        DateFormat df = new SimpleDateFormat(format);
        return df.format(date);
    }
}
