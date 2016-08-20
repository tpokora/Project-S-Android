package projects.tpokora.com.project_s_android.activities;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;

/**
 * Created by pokor on 15.08.2016.
 */
public class ActivityDispatcher {

    public static final String ABOUT_ACTIVITY = "AboutActivity";
    public static final String ARTICLES_ACTIVITY = "ArticlesActivity";
    public static final String NEW_ARTICLE_ACTIVITY = "NewArticleActivity";
    public static final String EXPORTER_ACTIVITY = "ExporterActivity";

    private Activity activity;
    private Intent intent;
    private Context context;

    public ActivityDispatcher(AbstractActivity abstractActivity, Context context) {
        this.activity = abstractActivity;
        this.context = context;
    }


    public void redirectNow(String activityName, boolean redirect) {
        setupIntent(activityName);

        if (redirect) {
            this.activity.startActivity(intent);
        }
    }

    public void redirectNowWithExtras(String activityName, String extrasString, Object extras, boolean redirect) {
        setupIntent(activityName);
        intent.putExtra(extrasString, extras.toString());

        if (redirect) {
            this.activity.startActivity(intent);
        }
    }

    private void setupIntent(String activityName) {
        switch (activityName) {
            case ABOUT_ACTIVITY:
                intent = new Intent(context, AboutActivity.class);
                break;
            case ARTICLES_ACTIVITY:
                intent = new Intent(context, ArticlesActivity.class);
                break;
            case NEW_ARTICLE_ACTIVITY:
                intent = new Intent(context, NewArticleActivity.class);
                break;
            case EXPORTER_ACTIVITY:
                intent = new Intent(context, ExporterActivity.class);
                break;
            default:
                break;
        }
    }
}
