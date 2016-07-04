package projects.tpokora.com.project_s_android.utils;

import android.util.Log;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by pokor on 26.06.2016.
 */
public class DateUtils {

    private static final String DEBUG_TAG = "DATE_UTILS";

    public static Date stringToDate(String format, String dateString) {
        SimpleDateFormat formatter = new SimpleDateFormat(format);
        Date date = null;
        try {
            date = formatter.parse(dateString);
        } catch (ParseException e) {
            Log.e(DEBUG_TAG, "Error formatting date string: " + dateString);
        }

        return date;
    }
}
