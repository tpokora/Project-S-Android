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
//        loggedUser = bundle.getString("login");

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
    public void onResume() {
        super.onResume();
    }
}
