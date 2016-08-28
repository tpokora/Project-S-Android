package projects.tpokora.com.project_s_android.activities;

import android.os.Bundle;
import android.widget.Button;

import projects.tpokora.com.project_s_android.R;
import projects.tpokora.com.project_s_android.export.ArticlesExporter;

/**
 * Created by pokor on 15.08.2016.
 */
public class ExporterActivity extends AbstractActivity {

    private ArticlesExporter articlesExporter;

    private String loggedUser;

    private Button exportJSONButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exporter);
        setupActivity();

        // Get user
        loggedUser = bundle.getString("login");

        //articlesExporter = new ArticlesExporter(this, this.context, loggedUser);

        initUIElement();
    }

    public void initUIElement() {
        exportJSONButton = (Button) findViewById(R.id.export_JSON_button);
    }
}
