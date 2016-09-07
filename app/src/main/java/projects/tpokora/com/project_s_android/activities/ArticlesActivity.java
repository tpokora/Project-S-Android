package projects.tpokora.com.project_s_android.activities;

import android.support.v4.app.FragmentTransaction;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import projects.tpokora.com.project_s_android.R;
import projects.tpokora.com.project_s_android.activities.fragments.ArticlesListFragment;

/**
 * Created by pokor on 20.06.2016.
 */
public class ArticlesActivity extends AbstractActivity {

    private static final String DEBUG_TAG = "ArticlesActivity";

    private String loggedUser;

    private ArticlesListFragment articlesListFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_article);
        setupActivity();

        // Get user
        loggedUser = bundle.getString("login");

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        if (findViewById(R.id.articles_fragment_container) != null) {
            if (savedInstanceState != null) {
                return;
            }

            FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();

            articlesListFragment = new ArticlesListFragment();
            articlesListFragment.setArguments(bundle);

            fragmentTransaction.add(R.id.articles_fragment_container, articlesListFragment).commit();

        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_articles, menu);
        return true;
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    /**
     * Click handlers on menu item
     * @param item
     * @return
     */
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_settings_export:
                Log.d(DEBUG_TAG, "Go to Export");
                activityDispatcher.redirectNowWithExtras(ActivityDispatcher.EXPORTER_ACTIVITY, "login", loggedUser, true);
                break;
            case R.id.action_settings_about:
                Log.d(DEBUG_TAG, "Go to About");
                activityDispatcher.redirectNow(ActivityDispatcher.ABOUT_ACTIVITY, true);
                break;
            default:
                return super.onOptionsItemSelected(item);
        }

        return true;
    }

}
