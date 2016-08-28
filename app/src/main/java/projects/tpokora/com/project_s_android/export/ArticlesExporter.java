package projects.tpokora.com.project_s_android.export;

import android.app.Activity;
import android.content.Context;
import android.database.Cursor;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import projects.tpokora.com.project_s_android.rest.model.Article;
import projects.tpokora.com.project_s_android.storage.ArticleDBAdapter;
import projects.tpokora.com.project_s_android.utils.DateUtils;

/**
 * Created by pokor on 14.08.2016.
 */
public class ArticlesExporter {

    private static final String DEBUG_TAG = "ArticlesExporter";

    private ArticleDBAdapter articleDBAdapter;
    private Activity activity;

    private String login;

    private ArrayList<Article> articles;

    public ArticlesExporter(Activity activity, Context context, String login) {
        this.activity = activity;
        this.login = login;
        this.articleDBAdapter = new ArticleDBAdapter(context);
        this.articles = new ArrayList<Article>();
        getArticlesFromDB();
    }

    private void getArticlesFromDB() {
        articleDBAdapter.open();
        Cursor cursor = articleDBAdapter.getArticlesByAuthor(login);
        if (cursor != null) {
            this.activity.startManagingCursor(cursor);
            cursor.moveToFirst();
        }

        if (cursor != null && cursor.moveToFirst()) {
            do {
                long id = cursor.getLong(ArticleDBAdapter.ID_COLUMN);
                String title = cursor.getString(ArticleDBAdapter.TITLE_COLUMN);
                String content = cursor.getString(ArticleDBAdapter.CONTENT_COLUMN);
                String createTime = cursor.getString(ArticleDBAdapter.CREATE_TIME_COLUMN);
                String author = cursor.getString(ArticleDBAdapter.AUTHOR_COLUMN);
                Article article = new Article((int) id, title, content, DateUtils.stringToDate(DateUtils.DATE_YEAR_MONTH_DAY_TIME_OFFSET, createTime), author);
                articles.add(article);
            } while (cursor.moveToNext());
        }
    }

    public JSONArray generateJSONArrayFromDB() {
        JSONArray jsonArray = new JSONArray();
        if (articles.size() > 0) {
            for (Article article : articles) {
                try {
                    JSONObject articleElement = new JSONObject();
                    articleElement.put("author", article.getAuthor());
                    articleElement.put("title", article.getTitle());
                    articleElement.put("content", article.getContent());
                    jsonArray.put(articleElement);
                } catch (JSONException e) {
                    Log.e(DEBUG_TAG, "Could not create JSON object");
                }

            }
        }
        return jsonArray;
    }
}
