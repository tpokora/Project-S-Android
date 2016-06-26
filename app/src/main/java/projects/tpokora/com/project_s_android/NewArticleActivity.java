package projects.tpokora.com.project_s_android;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

/**
 * Created by pokor on 26.06.2016.
 */
public class NewArticleActivity extends AppCompatActivity {

    private Bundle bundle;

    private String login;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_article);
        bundle = getIntent().getExtras();

        // Get user
        login = bundle.getString("login");
    }
}
