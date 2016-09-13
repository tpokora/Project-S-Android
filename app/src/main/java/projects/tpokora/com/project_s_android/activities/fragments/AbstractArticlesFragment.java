package projects.tpokora.com.project_s_android.activities.fragments;

import projects.tpokora.com.project_s_android.storage.ArticleDBAdapter;

/**
 * Created by pokor on 07.09.2016.
 */
public class AbstractArticlesFragment extends AbstractFragment {

    protected ArticleDBAdapter articleDBAdapter;

    protected String loggedUser;

    @Override
    protected void setupFragment() {
        super.setupFragment();
        articleDBAdapter = new ArticleDBAdapter(this.context);
        loggedUser = bundle.getString("login");
    }
}
