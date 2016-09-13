package projects.tpokora.com.project_s_android.activities;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import projects.tpokora.com.project_s_android.R;
import projects.tpokora.com.project_s_android.storage.ArticleDBAdapter;

/**
 * Created by pokor on 26.06.2016.
 */
public abstract class AbstractActivity extends AppCompatActivity {

    protected ActivityDispatcher activityDispatcher;

    protected Context context;
    protected Bundle bundle;

    protected ArticleDBAdapter articleDBAdapter;

    protected Toolbar toolbar;
    protected String loggedUser;

    protected void setupActivity() {
        context = this;
        bundle = getIntent().getExtras();
        activityDispatcher = new ActivityDispatcher(this, this.context);
        articleDBAdapter = new ArticleDBAdapter(this.context);

        // Get user
        if (bundle != null) {
            loggedUser = bundle.getString("login");
        }

        toolbar = (android.support.v7.widget.Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_articles, menu);
        return true;
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
                activityDispatcher.redirectNowWithExtras(ActivityDispatcher.EXPORTER_ACTIVITY, "login", loggedUser, true);
                break;
            case R.id.action_settings_articles:
                activityDispatcher.redirectNowWithExtras(ActivityDispatcher.ARTICLES_ACTIVITY, "login", loggedUser, true);
                break;
            case R.id.action_settings_about:
                activityDispatcher.redirectNowWithExtras(ActivityDispatcher.ABOUT_ACTIVITY, "login", loggedUser, true);
                break;
            default:
                return super.onOptionsItemSelected(item);
        }

        return true;
    }
}
