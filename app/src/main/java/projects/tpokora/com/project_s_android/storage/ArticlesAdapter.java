package projects.tpokora.com.project_s_android.storage;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

import projects.tpokora.com.project_s_android.R;
import projects.tpokora.com.project_s_android.rest.model.Article;
import projects.tpokora.com.project_s_android.utils.DateUtils;

/**
 * Created by pokor on 26.06.2016.
 */
public class ArticlesAdapter extends ArrayAdapter<Article> {

    private Activity context;
    private List<Article> articles;

    public ArticlesAdapter(Activity context, List<Article> articles) {
        super(context, R.layout.article_list_item, articles);
        this.context = context;
        this.articles = articles;
    }

    static class ViewHolder {
        public TextView article_list_item_title;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;
        View rowView = convertView;
        if (rowView == null) {
            LayoutInflater layoutInflater = context.getLayoutInflater();
            rowView = layoutInflater.inflate(R.layout.article_list_item, null, true);
            viewHolder = new ViewHolder();
            viewHolder.article_list_item_title = (TextView) rowView.findViewById(R.id.article_list_item_title);
            rowView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) rowView.getTag();
        }

        Article article = articles.get(position);
        viewHolder.article_list_item_title.setText(article.getTitle() + " | " + DateUtils.dateToString("HH:mm yyyy-MM-dd", article.getCreateTime()));

        return rowView;
    }
}
