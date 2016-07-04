package projects.tpokora.com.project_s_android.activities;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.Date;

import projects.tpokora.com.project_s_android.R;
import projects.tpokora.com.project_s_android.rest.model.Article;
import projects.tpokora.com.project_s_android.storage.ArticleDBAdapter;

/**
 * Created by pokor on 26.06.2016.
 */
public class NewArticleActivity extends AbstractActivity {

    private static final String DEBUG_TAG = "NewArticleActivity";

    private String login;

    private EditText newArticleTitleEditText;
    private EditText newArticleContentEditText;
    private Button newArticleButtonSave;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_article);
        setupActivity();

        // Get user
        login = bundle.getString("login");

        initUIElements();
    }

    private void initUIElements() {
        newArticleTitleEditText = (EditText) findViewById(R.id.new_article_title);
        newArticleContentEditText = (EditText) findViewById(R.id.new_article_content);
        newArticleButtonSave = (Button) findViewById(R.id.new_article_button_save);

        // set button listener
        newArticleButtonSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!newArticleTitleEditText.getText().toString().equals("") &&
                        !newArticleContentEditText.getText().toString().equals("")) {
                    String title = newArticleTitleEditText.getText().toString();
                    String content = newArticleContentEditText.getText().toString();

                    Article newArticle = new Article(title, content, new Date(), login);

                    articleDBAdapter.insertArticle(newArticle);

                } else {
                    Toast.makeText(context, "Please fill all fields", Toast.LENGTH_LONG).show();
                }
            }
        });
    }
}
