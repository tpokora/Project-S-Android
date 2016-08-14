package projects.tpokora.com.project_s_android.activities;

import android.os.Bundle;
import android.widget.TextView;

import com.google.android.gms.common.GoogleApiAvailability;

import projects.tpokora.com.project_s_android.R;

/**
 * Created by pokor on 14.08.2016.
 */
public class AboutActivity extends AbstractActivity {

    private static final String DEBUG_TAG = "AboutActivity";

    private TextView googleLicencePolicyTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);
        setupActivity();

        googleLicencePolicyTextView = (TextView) findViewById(R.id.about_licence_notes_text);
        googleLicencePolicyTextView.setText(GoogleApiAvailability.getInstance().getOpenSourceSoftwareLicenseInfo(context));
    }
}
