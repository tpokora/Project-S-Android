package projects.tpokora.com.project_s_android.activities;

import android.os.Bundle;

import projects.tpokora.com.project_s_android.R;

/**
 * Created by pokor on 14.08.2016.
 */
public class AboutActivity extends AbstractActivity {

    private static final String DEBUG_TAG = "AboutActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);
        setupActivity();
    }
}
