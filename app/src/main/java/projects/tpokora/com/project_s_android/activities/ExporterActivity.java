package projects.tpokora.com.project_s_android.activities;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import java.io.IOException;
import java.io.OutputStreamWriter;

import projects.tpokora.com.project_s_android.R;
import projects.tpokora.com.project_s_android.export.ArticlesExporter;

/**
 * Created by pokor on 15.08.2016.
 */
public class ExporterActivity extends AbstractActivity {

    private final String DEBUG_TAG = "ExporterActivity";

    private ArticlesExporter articlesExporter;

    private String loggedUser;

    private Button exportJSONButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exporter);
        setupActivity();

        // Get user
//        loggedUser = bundle.getString("login");

        articlesExporter = new ArticlesExporter(this, this.context, loggedUser);

        initUIElement();
    }

    public void initUIElement() {
        exportJSONButton = (Button) findViewById(R.id.export_JSON_button);
        exportJSONButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                writeToFile(articlesExporter.generateJSONArrayFromDB().toString());
            }
        });
    }

    private void writeToFile(String json) {
        try {
            OutputStreamWriter outputStreamWriter = new OutputStreamWriter(context.openFileOutput("articles.json", Context.MODE_PRIVATE));
            outputStreamWriter.write(json);
            outputStreamWriter.close();
        } catch (IOException e) {
            Log.e(DEBUG_TAG, "Could not write JSON to file.");
        }
    }
}
