package projects.tpokora.com.project_s_android;

import android.app.Instrumentation.ActivityMonitor;
import android.support.test.InstrumentationRegistry;
import android.support.test.filters.LargeTest;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import junit.framework.Assert;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import projects.tpokora.com.project_s_android.activities.ArticlesActivity;
import projects.tpokora.com.project_s_android.activities.LoginActivity;

import static android.support.test.InstrumentationRegistry.*;
import static android.support.test.espresso.Espresso.*;
import static android.support.test.espresso.action.ViewActions.*;
import static android.support.test.espresso.matcher.ViewMatchers.*;

/**
 * Created by pokor on 18.09.2016.
 */

public class LoginActivityTest extends AbstractActivityTest {

    private String loginString;
    private String passwordString;

    private ArticlesActivity articlesActivity;

    @Rule
    public ActivityTestRule<LoginActivity> loginActivityRule = new ActivityTestRule<LoginActivity>(LoginActivity.class);

    @Before
    public void initStrings() {
        loginString = "testUser";
        passwordString = "test";
    }

    @Test
    public void singIn_test() {
        // set login and password into editText fields
        onView(withId(R.id.login_editText)).perform(typeText(loginString));
        onView(withId(R.id.password_editText)).perform(typeText(passwordString));

        // register which activity need to be monitored
        activityMonitor = getInstrumentation().addMonitor(ArticlesActivity.class.getName(), null, false);

        // click Sign in button
        onView(withId(R.id.login_button)).perform(click());

        articlesActivity = (ArticlesActivity) getInstrumentation().waitForMonitorWithTimeout(activityMonitor, 5000);
        Assert.assertNotNull(articlesActivity);
        articlesActivity.finish();
    }
}
