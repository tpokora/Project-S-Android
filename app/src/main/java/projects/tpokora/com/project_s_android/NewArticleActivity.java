package projects.tpokora.com.project_s_android;

import android.os.Bundle;

/**
 * Created by pokor on 26.06.2016.
 */
public class NewArticleActivity extends AbstractActivity {

    private String login;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_article);
        setupActivity();

        // Get user
        login = bundle.getString("login");
    }
}
