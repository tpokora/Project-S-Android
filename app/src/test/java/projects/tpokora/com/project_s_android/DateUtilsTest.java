package projects.tpokora.com.project_s_android;

import android.util.Log;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

import projects.tpokora.com.project_s_android.utils.DateUtils;

/**
 * Created by pokor on 14.08.2016.
 */

public class DateUtilsTest extends AbstractTest {

    private DateUtils dateUtils;

    @Before
    public void setup() {
        dateUtils = new DateUtils();
    }

    @Test
    public void test_dateToString() {
        Calendar calendar = Calendar.getInstance();
        calendar.set(2016, 7, 14);
        calendar.set(Calendar.HOUR_OF_DAY, 10);
        calendar.set(Calendar.MINUTE, 30);
        calendar.set(Calendar.SECOND, 30);

        Date date = calendar.getTime();

        String dateString = "2016-08-14 10:30:30 +0200";

        Assert.assertEquals(dateString, DateUtils.dateToString(DateUtils.DATE_YEAR_MONTH_DAY_TIME_OFFSET, date));
    }
    
}
