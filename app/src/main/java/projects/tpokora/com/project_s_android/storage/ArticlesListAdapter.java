package projects.tpokora.com.project_s_android.storage;

import android.content.Context;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import projects.tpokora.com.project_s_android.R;
import projects.tpokora.com.project_s_android.rest.model.Article;
import projects.tpokora.com.project_s_android.utils.DateUtils;

/**
 * Created by pokor on 26.06.2016.
 */
public class ArticlesListAdapter extends BaseExpandableListAdapter {

    private Context context;
    private List<Article> articles;
    private List<String> titles;
    private HashMap<String, List<String>> titlesContents;

    public ArticlesListAdapter(Context context, List<Article> articles) {
        this.context = context;
        this.articles = articles;

        this.titles = new ArrayList<String>();
        this.titlesContents = new HashMap<>();

        setTitlesAndContents();
    }

    private void setTitlesAndContents() {
        if (articles != null && articles.size() > 0) {
            for (Article article : this.articles) {
                titles.add(article.getTitle());
                List<String> content = new ArrayList<String>();
                content.add(article.getContent());
                content.add(DateUtils.dateToString(DateUtils.DATE_YEAR_MONTH_DAY, article.getCreateTime()));
                titlesContents.put(article.getTitle(), content);
            }
        }
    }

    @Override
    public int getGroupCount() {
        return this.titles.size();
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        return this.titlesContents.get(this.titles.get(groupPosition)).size();
    }

    @Override
    public Object getGroup(int groupPosition) {
        return this.titles.get(groupPosition);
    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {
        return this.titlesContents.get(titles.get(groupPosition)).get(childPosition);
    }

    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
        String articleTitle = (String) getGroup(groupPosition);

        if (convertView == null) {
            LayoutInflater layoutInflater = (LayoutInflater) this.context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = layoutInflater.inflate(R.layout.article_list_layout, null);
        }

        TextView articleListItemTitle = (TextView) convertView.findViewById(R.id.article_list_header);
        articleListItemTitle.setTypeface(null, Typeface.BOLD);
        articleListItemTitle.setText(articleTitle);

        return convertView;
    }

    @Override
    public View getChildView(int groupPosition, final int childPosition,
                             boolean isLastChild, View convertView, ViewGroup parent) {
        final String contentText = (String) getChild(groupPosition, childPosition);

        if (convertView == null) {
            LayoutInflater layoutInflater = (LayoutInflater) this.context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = layoutInflater.inflate(R.layout.article_list_item_content_layout, null);
        }

        TextView articleContentTextView = (TextView) convertView.findViewById(R.id.article_list_item_content);
        articleContentTextView.setText(contentText);

        return convertView;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;
    }
}
