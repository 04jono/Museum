package org.dhfrederick.museum;

import android.widget.BaseAdapter;
import android.content.Context;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class ExhibitListAdapter extends BaseAdapter {
    Context context;
    private final String [] values;
    private final String [] numbers;
    private final int [] images;

    public ExhibitListAdapter(Context context, String [] values, String [] numbers, int [] images){
        //super(context, R.layout.single_list_app_item, utilsArrayList);
        this.context = context;
        this.values = values;
        this.numbers = numbers;
        this.images = images;
    }

    @Override
    public int getCount() {
        return values.length;
    }

    @Override
    public Object getItem(int i) {
        return i;
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {


        ViewHolder viewHolder;

        final View result;

        if (convertView == null) {

            viewHolder = new ViewHolder();
            LayoutInflater inflater = LayoutInflater.from(context);
            convertView = inflater.inflate(R.layout.exhibit_list_item, parent, false);
            viewHolder.txtTitle = (TextView) convertView.findViewById(R.id.exhibitTitleText);
            viewHolder.txtDescription = (TextView) convertView.findViewById(R.id.exhibitDescriptionText);
            viewHolder.icon = (ImageView) convertView.findViewById(R.id.exhibitIcon);

            result=convertView;

            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
            result=convertView;
        }

        viewHolder.txtTitle.setText(values[position]);
        viewHolder.txtDescription.setText(numbers[position]);
        viewHolder.icon.setImageResource(images[position]);

        return convertView;
    }

    private static class ViewHolder {

        TextView txtTitle;
        TextView txtDescription;
        ImageView icon;

    }

}
