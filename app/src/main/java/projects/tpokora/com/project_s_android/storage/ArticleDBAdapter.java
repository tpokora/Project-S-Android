package projects.tpokora.com.project_s_android.storage;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.text.SimpleDateFormat;

import projects.tpokora.com.project_s_android.rest.model.Article;
import projects.tpokora.com.project_s_android.utils.DateUtils;

/**
 * Created by pokor on 26.06.2016.
 */
public class ArticleDBAdapter {
    private static final String DEBUG_TAG = "ARTICLE_DB_STORAGE";

    private static final int DB_VERSION = 1;
    private static final String DB_NAME = "database.db";
    private static final String DB_ARTICLE_TABLE = "article";

    public static final String KEY_ID = "_id";
    public static final String ID_OPTIONS = "INTEGER PRIMARY KEY AUTOINCREMENT";
    public static final int ID_COLUMN = 0;

    public static final String KEY_TITLE = "title";
    public static final String TITLE_OPTIONS = "TEXT NOT NULL";
    public static final int TITLE_COLUMN = 1;

    public static final String KEY_CONTENT = "content";
    public static final String CONTENT_OPTIONS = "TEXT NOT NULL";
    public static final int CONTENT_COLUMN = 2;

    public static final String KEY_CREATE_TIME = "createTime";
    public static final String CREATE_TIME_OPTIONS = "TEXT NOT NULL";
    public static final int CREATE_TIME_COLUMN = 3;

    public static final String KEY_AUTHOR = "author";
    public static final String AUTHOR_OPTIONS = "TEXT NOT NULL";
    public static final int AUTHOR_COLUMN = 4;

    // SQL QUERY
    private static final String DB_CREATE_ARTICLE_TABLE =
            "CREATE TABLE " + DB_ARTICLE_TABLE + "( " +
                    KEY_ID + " " + ID_OPTIONS + ", " +
                    KEY_TITLE + " " + TITLE_OPTIONS + ", " +
                    KEY_CONTENT + " " + CONTENT_OPTIONS + ", " +
                    KEY_CREATE_TIME + " " + CREATE_TIME_OPTIONS + ", " +
                    KEY_AUTHOR + " " + AUTHOR_OPTIONS +
                    ");";

    private static final String DROP_ARTICLE_TABLE =
            "DROP TABLE IF EXISTS " + DB_ARTICLE_TABLE;

    private SQLiteDatabase sqldb;
    private Context context;
    private DatabaseHelper dbHelper;

    private static class DatabaseHelper extends SQLiteOpenHelper {

        public DatabaseHelper(Context context, String name, SQLiteDatabase.CursorFactory cursor,
                              int version) {
            super(context, name, cursor, version);
        }

        @Override
        public void onCreate(SQLiteDatabase sqLiteDatabase) {
            sqLiteDatabase.execSQL(DB_CREATE_ARTICLE_TABLE);

            Log.d(DEBUG_TAG, "Database creating...");
            Log.d(DEBUG_TAG, "Table " + DB_ARTICLE_TABLE + " ver." + DB_VERSION + " created");
        }

        @Override
        public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldVersion, int newVersion) {
            sqLiteDatabase.execSQL(DROP_ARTICLE_TABLE);

            Log.d(DEBUG_TAG, "Database updating...");
            Log.d(DEBUG_TAG, "Table " + DB_ARTICLE_TABLE + " updated from ver." + oldVersion + " to ver." + newVersion);
            Log.d(DEBUG_TAG, "All data is lost.");

            onCreate(sqLiteDatabase);
        }
    }

    public ArticleDBAdapter(Context context) {
        this.context = context;
    }

    public ArticleDBAdapter open() {
        dbHelper = new DatabaseHelper(context, DB_NAME, null, DB_VERSION);

        try {
            sqldb = dbHelper.getWritableDatabase();
        } catch (SQLException e) {
            sqldb = dbHelper.getReadableDatabase();
        }

        return this;
    }

    public long insertArticle(Article newArticle) {
        this.open();
        ContentValues newArticleValues = new ContentValues();
        newArticleValues.put(KEY_TITLE, newArticle.getTitle());
        newArticleValues.put(KEY_CONTENT, newArticle.getContent());
        newArticleValues.put(KEY_CREATE_TIME, newArticle.getCreateTime().toString());
        newArticleValues.put(KEY_AUTHOR, newArticle.getAuthor());
        Log.d(DEBUG_TAG, "Inserting new article to database...");
        return sqldb.insertOrThrow(DB_ARTICLE_TABLE, null, newArticleValues);
    }

    public boolean deleteArticle(long id){
        String where = KEY_ID + "=" + id;
        return sqldb.delete(DB_ARTICLE_TABLE, where, null) > 0;
    }

    public Cursor getAllArticles() {
        String[] columns = {KEY_ID, KEY_TITLE, KEY_CONTENT, KEY_CREATE_TIME, KEY_AUTHOR};
        return sqldb.query(DB_ARTICLE_TABLE, columns, null, null, null, null, null);
    }

    public Article getTodo(long id) {
        String[] columns = {KEY_ID, KEY_TITLE, KEY_CONTENT, KEY_CREATE_TIME, KEY_AUTHOR};
        String where = KEY_ID + "=" + id;
        Cursor cursor = sqldb.query(DB_ARTICLE_TABLE, columns, where, null, null, null, null);
        Article article = null;
        if (cursor != null && cursor.moveToFirst()) {
            String format = "YYYY-MM-DD HH:MM:SS.SSS";
            String dateString = cursor.getString(CREATE_TIME_COLUMN);
            SimpleDateFormat formater = new SimpleDateFormat(format);
            article = new Article((int) id, cursor.getString(TITLE_COLUMN), cursor.getString(CONTENT_COLUMN), DateUtils.stringToDate(format, dateString), cursor.getString(AUTHOR_COLUMN));


        }

        return article;
    }

}
