package projects.tpokora.com.project_s_android.activities;

import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ExpandableListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import projects.tpokora.com.project_s_android.R;
import projects.tpokora.com.project_s_android.rest.model.Article;
import projects.tpokora.com.project_s_android.storage.ArticleDBAdapter;
import projects.tpokora.com.project_s_android.storage.ArticlesListAdapter;
import projects.tpokora.com.project_s_android.utils.DateUtils;

/**
 * Created by pokor on 20.06.2016.
 */
public class ArticlesActivity extends AbstractActivity {

    private static final String DEBUG_TAG = "ArticlesActivity";

    private String loggedUser;

    private TextView loggedUserBar;

    private ExpandableListView articleList;
    private Cursor articleCursor;
    private List<Article> articles;
    private ArticlesListAdapter listAdapter;

    private Button newArticleButton;
    private Button dropDBButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_article);
        setupActivity();

        // Get user
        loggedUser = bundle.getString("login");

        toolbar = (android.support.v7.widget.Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        initUIElements();
        initListView();
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
        initListView();
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

    private void initListView() {
        fillListViewData();
    }

    private void fillListViewData() {
        articleDBAdapter.open();
        getAllArticles();
        listAdapter = new ArticlesListAdapter(this.context, articles);
        articleList.setAdapter(listAdapter);
//        articleList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
//                Article selectedArticle = (Article) adapterView.getItemAtPosition(i);
//            }
//        });
    }

    private void getAllArticles() {
        articles = new ArrayList<Article>();
        articleCursor = articleDBAdapter.getArticlesByAuthor(loggedUser);
        if (articleCursor != null) {
            startManagingCursor(articleCursor);
            articleCursor.moveToFirst();
        }
        updateArticleList();
    }

    private void updateArticleList() {
        if (articleCursor != null && articleCursor.moveToFirst()) {
            do {
                long id = articleCursor.getLong(ArticleDBAdapter.ID_COLUMN);
                String title = articleCursor.getString(ArticleDBAdapter.TITLE_COLUMN);
                String content = articleCursor.getString(ArticleDBAdapter.CONTENT_COLUMN);
                String createTime = articleCursor.getString(ArticleDBAdapter.CREATE_TIME_COLUMN);
                String author = articleCursor.getString(ArticleDBAdapter.AUTHOR_COLUMN);
                Article article = new Article((int) id, title, content, DateUtils.stringToDate(DateUtils.DATE_YEAR_MONTH_DAY_TIME_OFFSET, createTime), author);
                articles.add(article);
            } while (articleCursor.moveToNext());
        }
    }

    /**
     * Method to initiate UI Elements
     */
    private void initUIElements() {
        loggedUserBar = (TextView) findViewById(R.id.article_user_bar);
        loggedUserBar.setText(loggedUser);
        articleList = (ExpandableListView) findViewById(R.id.articles_list_view);
        newArticleButton = (Button) findViewById(R.id.new_article_button);
        newArticleButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                activityDispatcher.redirectNowWithExtras(ActivityDispatcher.NEW_ARTICLE_ACTIVITY, "login", loggedUser, true);
            }
        });

        dropDBButton = (Button) findViewById(R.id.drop_db_button);
        dropDBButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                articleDBAdapter.deleteAllArticles();
                initListView();
            }
        });
    }
}
