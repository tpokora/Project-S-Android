package projects.tpokora.com.project_s_android;

import android.support.test.rule.ActivityTestRule;

import junit.framework.Assert;

import org.junit.Rule;
import org.junit.Test;

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

    private ArticlesActivity articlesActivity;

    @Rule
    public ActivityTestRule<LoginActivity> loginActivityActivityTestRule = new ActivityTestRule<>(LoginActivity.class);

    @Test
    public void test_singIn_success() {
        setUpLoginAndPassword(loginString, passwordString);

        // register which activity need to be monitored
        activityMonitor = getInstrumentation().addMonitor(ArticlesActivity.class.getName(), null, false);

        signInAction();

        articlesActivity = (ArticlesActivity) getInstrumentation().waitForMonitorWithTimeout(activityMonitor, ACTIVITY_TIMEOUT_SHORT);
        Assert.assertNotNull(articlesActivity);
        articlesActivity.finish();
    }

    @Test
    public void test_singIn_fail() {
        setUpLoginAndPassword(loginString, wrongPasswordString);

        // register which activity need to be monitored
        activityMonitor = getInstrumentation().addMonitor(ArticlesActivity.class.getName(), null, false);

        signInAction();

        articlesActivity = (ArticlesActivity) getInstrumentation().waitForMonitorWithTimeout(activityMonitor, ACTIVITY_TIMEOUT_SHORT);
        Assert.assertNull(articlesActivity);
    }

    private void setUpLoginAndPassword(String login, String password) {
        // set login and password into editText fields
        onView(withId(R.id.login_editText)).perform(typeText(login));
        onView(withId(R.id.password_editText)).perform(typeText(password));
    }

    private void signInAction() {
        // click Sign in button
        onView(withId(R.id.login_button)).perform(click());
    }

}
