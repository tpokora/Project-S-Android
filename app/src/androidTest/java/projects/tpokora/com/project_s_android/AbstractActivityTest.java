package projects.tpokora.com.project_s_android;

import android.app.Instrumentation;
import android.support.test.filters.LargeTest;
import android.support.test.runner.AndroidJUnit4;

import org.junit.Before;
import org.junit.runner.RunWith;

/**
 * Created by pokor on 18.09.2016.
 */

@RunWith(AndroidJUnit4.class)
@LargeTest
public class AbstractActivityTest {

    protected final int ACTIVITY_TIMEOUT_SHORT = 1000;

    protected Instrumentation.ActivityMonitor activityMonitor;

    // Login Activity
    protected String loginString;
    protected String passwordString;
    protected String wrongPasswordString;

    @Before
    public void initStrings() {
        loginString = "testUser";
        passwordString = "test";
        wrongPasswordString = "wrongPassword";
    }
}
