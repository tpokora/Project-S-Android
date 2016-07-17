package projects.tpokora.com.project_s_android.storage;

import android.app.Activity;
import android.content.Context;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseExpandableListAdapter;
import android.widget.TextView;

import java.util.HashMap;
import java.util.List;

import projects.tpokora.com.project_s_android.R;
import projects.tpokora.com.project_s_android.rest.model.Article;
import projects.tpokora.com.project_s_android.utils.DateUtils;

/**
 * Created by pokor on 26.06.2016.
 */
public class ArticlesAdapter extends BaseExpandableListAdapter {

    private Context context;
    private List<String> titles;
    private HashMap<String, List<String>> titlesContents;

    public ArticlesAdapter(Context context, List<String> titles, HashMap<String, List<String>> titlesContents) {
        this.context = context;
        this.titles = titles;
        this.titlesContents = titlesContents;
    }

    @Override
    public int getGroupCount() {
        return 0;
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
            convertView = layoutInflater.inflate(R.layout.article_list_item, null);
        }

        TextView articleListItemTitle = (TextView) convertView.findViewById(R.id.article_list_item_title);
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
            convertView = layoutInflater.inflate(R.layout.article_list_item_content, null);
        }

        TextView articleContetTextView = (TextView) convertView.findViewById(R.id.article_list_item_content);
        articleContetTextView.setText(contentText);

        return convertView;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;
    }

    // ListView Adapter
//    private Activity context;
//    private List<Article> articles;
//
//    public ArticlesAdapter(Activity context, List<Article> articles) {
//        super(context, R.layout.article_list_item, articles);
//        this.context = context;
//        this.articles = articles;
//    }
//
//    static class ViewHolder {
//        public TextView article_list_item_title;
//    }
//
//    @Override
//    public View getView(int position, View convertView, ViewGroup parent) {
//        ViewHolder viewHolder;
//        View rowView = convertView;
//        if (rowView == null) {
//            LayoutInflater layoutInflater = context.getLayoutInflater();
//            rowView = layoutInflater.inflate(R.layout.article_list_item, null, true);
//            viewHolder = new ViewHolder();
//            viewHolder.article_list_item_title = (TextView) rowView.findViewById(R.id.article_list_item_title);
//            rowView.setTag(viewHolder);
//        } else {
//            viewHolder = (ViewHolder) rowView.getTag();
//        }
//
//        Article article = articles.get(position);
//        viewHolder.article_list_item_title.setText(article.getTitle() + " | " + DateUtils.dateToString("HH:mm yyyy-MM-dd", article.getCreateTime()));
//
//        return rowView;
//    }
}
