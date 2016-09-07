package projects.tpokora.com.project_s_android.activities.fragments;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.Date;

import projects.tpokora.com.project_s_android.R;
import projects.tpokora.com.project_s_android.rest.model.Article;

/**
 * Fragment for creating new article
 * @date Created by pokor on 06.09.2016.
 */
public class NewArticleFragment extends AbstractArticlesFragment {

    private static final String DEBUG_TAG = "NewArticleFragment";

    private EditText newArticleTitleEditText;
    private EditText newArticleContentEditText;
    private Button newArticleButtonSave;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle saveInstanceState) {
        return inflater.inflate(R.layout.fragment_articles_new, container, false);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        setupFragment();

        initUIElements();
    }

    private void initUIElements() {
        newArticleTitleEditText = (EditText) getView().findViewById(R.id.new_article_title);
        newArticleContentEditText = (EditText) getView().findViewById(R.id.new_article_content);
        newArticleButtonSave = (Button) getView().findViewById(R.id.new_article_button_save);

        // set button listener
        newArticleButtonSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!newArticleTitleEditText.getText().toString().equals("") &&
                        !newArticleContentEditText.getText().toString().equals("")) {
                    String title = newArticleTitleEditText.getText().toString();
                    String content = newArticleContentEditText.getText().toString();

                    Article newArticle = new Article(title, content, new Date(), loggedUser);

                    long inserted = articleDBAdapter.insertArticle(newArticle);

                    if (inserted > 0) {
                        Log.d(DEBUG_TAG, "Articles inserting results: " + inserted);
                        getActivity().finish();
                    } else {
                        Log.e(DEBUG_TAG, "Error while inserting row to database");
                    }

                } else {
                    Toast.makeText(context, "Please fill all fields", Toast.LENGTH_LONG).show();
                }
            }
        });
    }
}
