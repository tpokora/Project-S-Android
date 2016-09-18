package projects.tpokora.com.project_s_android;

import android.app.Instrumentation;
import android.support.test.filters.LargeTest;
import android.support.test.runner.AndroidJUnit4;

import org.junit.runner.RunWith;

/**
 * Created by pokor on 18.09.2016.
 */

@RunWith(AndroidJUnit4.class)
@LargeTest
public class AbstractActivityTest {

    protected Instrumentation.ActivityMonitor activityMonitor;
}
