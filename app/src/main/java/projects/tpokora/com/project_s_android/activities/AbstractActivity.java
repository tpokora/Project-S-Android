package projects.tpokora.com.project_s_android.activities;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import projects.tpokora.com.project_s_android.storage.ArticleDBAdapter;

/**
 * Created by pokor on 26.06.2016.
 */
public abstract class AbstractActivity extends AppCompatActivity {

    protected Context context;
    protected Bundle bundle;

    protected ArticleDBAdapter articleDBAdapter;

    protected void setupActivity() {
        context = this;
        bundle = getIntent().getExtras();
        articleDBAdapter = new ArticleDBAdapter(this.context);
    }
}
