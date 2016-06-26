package projects.tpokora.com.project_s_android;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by pokor on 20.06.2016.
 */
public class ArticleActivity extends AppCompatActivity {

    private Bundle bundle;

    private String loggedUser;

    private Context context;

    private TextView loggedUserBar;
    private Button newArticleButton;

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

        newArticleButton = (Button) findViewById(R.id.new_article_button);
        newArticleButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(context, "This will open new article menu", Toast.LENGTH_LONG).show();
            }
        });
    }

    private void getBundle() {
        bundle = getIntent().getExtras();
    }
}
