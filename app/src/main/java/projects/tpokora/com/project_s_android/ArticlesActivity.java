package projects.tpokora.com.project_s_android;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

/**
 * Created by pokor on 20.06.2016.
 */
public class ArticlesActivity extends AbstractActivity {

    private String loggedUser;

    private TextView loggedUserBar;
    private Button newArticleButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_article);
        setupActivity();

        // Get user
        loggedUser = bundle.getString("login");

        // Initiate components
        loggedUserBar = (TextView) findViewById(R.id.article_user_bar);
        loggedUserBar.setText(loggedUser);

        newArticleButton = (Button) findViewById(R.id.new_article_button);
        newArticleButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, NewArticleActivity.class);
                intent.putExtra("login", loggedUser);
                startActivity(intent);
            }
        });
    }
}
