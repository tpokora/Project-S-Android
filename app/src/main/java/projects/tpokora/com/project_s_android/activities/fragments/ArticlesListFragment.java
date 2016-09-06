package projects.tpokora.com.project_s_android.activities.fragments;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import projects.tpokora.com.project_s_android.R;

/**
 * Fragment for Articles list
 * @date Created by pokor on 06.09.2016.
 */
public class ArticlesListFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle saveInstanceState) {
        return inflater.inflate(R.layout.fragment_articles_list, container, false);
    }
}
