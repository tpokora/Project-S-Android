package projects.tpokora.com.project_s_android;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

/**
 * Created by pokor on 20.06.2016.
 */
public class ArticleActivity extends AppCompatActivity {

    private Bundle bundle;

    private String loggedUser;

    private Context context;

    private TextView loggedUserBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_article);
        getBundle();

        // Get user
        context = this;
        loggedUser = bundle.getString("login");

        // Initiate components
        loggedUserBar = (TextView) findViewById(R.id.article_user_bar);
        loggedUserBar.setText(loggedUser);
    }

    private void getBundle() {
        bundle = getIntent().getExtras();
    }
}
