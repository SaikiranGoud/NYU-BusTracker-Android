package com.palsulich.nyubustracker.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.palsulich.nyubustracker.R;
import com.palsulich.nyubustracker.models.Time;

import java.util.ArrayList;

import se.emilsjolander.stickylistheaders.StickyListHeadersAdapter;

public class TimeAdapter extends BaseAdapter implements StickyListHeadersAdapter{

    private LayoutInflater inflater;
    private ArrayList<Time> times;

    public TimeAdapter(Context context, ArrayList<Time> mTimes){
        // Cache the LayoutInflate to avoid asking for a new one each time.
        inflater = LayoutInflater.from(context);
        times = mTimes;
    }

    public int getCount() {
        return times.size();
    }

    public Object getItem(int position) {
        return times.get(position);
    }

    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;
        if (convertView == null){
            viewHolder = new ViewHolder();
            convertView = inflater.inflate(R.layout.time_list_item, null);
            viewHolder.timeText = (TextView) convertView.findViewById(R.id.time_text);
            viewHolder.viaRouteText = (TextView) convertView.findViewById(R.id.route_text);
            convertView.setTag(viewHolder);
        }
        else{
            viewHolder = (ViewHolder) convertView.getTag();
        }
        if (viewHolder.timeText != null) viewHolder.timeText.setText(times.get(position).toString());
        if (viewHolder.viaRouteText != null) viewHolder.viaRouteText.setText("Route " + times.get(position).getRoute());
        return convertView;
    }

    @Override
    public View getHeaderView(int position, View convertView, ViewGroup parent) {
        HeaderViewHolder holder;
        if (convertView == null) {
            holder = new HeaderViewHolder();
            convertView = inflater.inflate(R.layout.time_list_header, parent, false);
            holder.text = (TextView) convertView.findViewById(R.id.time_text);
            convertView.setTag(holder);
        } else {
            holder = (HeaderViewHolder) convertView.getTag();
        }
        // Set the header text to the time of week of this chunk of times.
        String headerText = times.get(position).getTimeOfWeekAsString();
        holder.text.setText(headerText);
        return convertView;
    }

    @Override
    public long getHeaderId(int position) {
        //return the first character of the country as ID because this is what headers are based upon
        return times.get(position).getTimeOfWeek().ordinal();
    }

    class HeaderViewHolder {
        TextView text;
    }

    class ViewHolder {
        TextView timeText;
        TextView viaRouteText;
    }
}