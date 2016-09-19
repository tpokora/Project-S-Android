package projects.tpokora.com.project_s_android;

import android.content.Intent;
import android.support.test.rule.ActivityTestRule;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import projects.tpokora.com.project_s_android.activities.ArticlesActivity;

import static android.support.test.espresso.Espresso.*;
import static android.support.test.espresso.matcher.ViewMatchers.*;
import static android.support.test.espresso.assertion.ViewAssertions.*;
import static android.support.test.espresso.action.ViewActions.*;

/**
 * Created by pokor on 19.09.2016.
 */
public class ArticlesActivityTest extends AbstractActivityTest {

    @Rule
    public ActivityTestRule<ArticlesActivity> articlesActivityActivityTestRule = new ActivityTestRule<>(ArticlesActivity.class, false, false);

    @Before
    public void setupIntent() {
        intent = new Intent();
        intent.putExtra("login", loginString);
    }

    @Test
    public void test_articleListFragmentIsShown_success() {
        articlesActivityActivityTestRule.launchActivity(intent);

        // check if fragment is applied to layout
        onView(withId(R.id.fragment_article_list)).check(matches(isDisplayed()));
    }

    @Test
    public void test_newArticleFragmentIsShownAfterNewArticleBtnClick_success() {
        articlesActivityActivityTestRule.launchActivity(intent);

        // click on New Article button and check if NewArticleFragment is displayed
        onView(withId(R.id.new_article_button)).perform(click());
        onView(withId(R.id.fragment_new_article)).check(matches(isDisplayed()));
    }
}
