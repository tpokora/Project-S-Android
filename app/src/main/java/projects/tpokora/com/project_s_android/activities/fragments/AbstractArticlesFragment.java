package projects.tpokora.com.project_s_android.activities.fragments;

import projects.tpokora.com.project_s_android.activities.ActivityDispatcher;
import projects.tpokora.com.project_s_android.storage.ArticleDBAdapter;

/**
 * Created by pokor on 07.09.2016.
 */
public class AbstractArticlesFragment extends AbstractFragment {

    protected ArticleDBAdapter articleDBAdapter;

    @Override
    protected void setupFragment() {
        super.setupFragment();
        articleDBAdapter = new ArticleDBAdapter(this.context);
    }
}
