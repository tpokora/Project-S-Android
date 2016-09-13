package projects.tpokora.com.project_s_android.activities.fragments;

import android.support.v4.app.FragmentTransaction;
import android.database.Cursor;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ExpandableListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import projects.tpokora.com.project_s_android.R;
import projects.tpokora.com.project_s_android.rest.model.Article;
import projects.tpokora.com.project_s_android.storage.ArticleDBAdapter;
import projects.tpokora.com.project_s_android.storage.ArticlesExpandableListAdapter;
import projects.tpokora.com.project_s_android.utils.DateUtils;

/**
 * Fragment for Articles list
 * @date Created by pokor on 06.09.2016.
 */
public class ArticlesListFragment extends AbstractArticlesFragment {

    private TextView loggedUserBar;

    private ExpandableListView articleList;
    private Cursor articleCursor;
    private List<Article> articles;
    private ArticlesExpandableListAdapter listAdapter;

    private Button newArticleButton;
    private Button dropDBButton;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle saveInstanceState) {
        return inflater.inflate(R.layout.fragment_articles_list, container, false);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        setupFragment();

        initUIElements();
        initListView();
    }

    private void initListView() {
        fillListViewData();
    }

    private void fillListViewData() {
        articleDBAdapter.open();
        getAllArticles();
        listAdapter = new ArticlesExpandableListAdapter(this.context, articles);
        articleList.setAdapter(listAdapter);
        articleList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Article selectedArticle = (Article) adapterView.getItemAtPosition(i);
            }
        });
    }

    private void getAllArticles() {
        articles = new ArrayList<Article>();
        articleCursor = articleDBAdapter.getArticlesByAuthor(loggedUser);
        if (articleCursor != null) {
            getActivity().startManagingCursor(articleCursor);
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
        loggedUserBar = (TextView) getView().findViewById(R.id.article_user_bar);
        loggedUserBar.setText(loggedUser);
        articleList = (ExpandableListView) getView().findViewById(R.id.articles_expandable_list_view);
        newArticleButton = (Button) getView().findViewById(R.id.new_article_button);
        newArticleButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentTransaction fragmentTransaction = getActivity().getSupportFragmentManager().beginTransaction();

                NewArticleFragment newArticleFragment = new NewArticleFragment();
                newArticleFragment.setArguments(bundle);

                fragmentTransaction.replace(R.id.articles_fragment_container, newArticleFragment).addToBackStack(null).commit();
            }
        });

        dropDBButton = (Button) getView().findViewById(R.id.drop_db_button);
        dropDBButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                articleDBAdapter.deleteAllArticles();
                initListView();
            }
        });
    }
}
