package projects.tpokora.com.project_s_android.activities.fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;

/**
 * Created by pokor on 07.09.2016.
 */
public abstract class AbstractFragment extends Fragment {

    protected Context context;
    protected Bundle bundle;

    protected void setupFragment() {
        context = getContext();
        bundle = getArguments();
    }
}
