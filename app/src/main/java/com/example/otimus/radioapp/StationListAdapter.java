package com.example.otimus.radioapp;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

/**
 * Created by Otimus on 10/25/2016.
 */
public class StationListAdapter extends RecyclerView.Adapter<StationListAdapter.Holder> {

    List<ItemStation> stationList;
    private final OnItemClickListener listener;
    public interface OnItemClickListener {
        void onItemClick(ItemStation item);
    }
    public StationListAdapter(List<ItemStation> stationList, OnItemClickListener listener) {
        this.stationList = stationList;
        this.listener=listener;
    }


    @Override
    public StationListAdapter.Holder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView= LayoutInflater.from(parent.getContext()).inflate(R.layout.item_list,parent,false);

        return new Holder(itemView);    }

    @Override
    public void onBindViewHolder(StationListAdapter.Holder holder, int position) {
        final ItemStation station =stationList.get(position);
        holder.bind(stationList.get(position),listener);
        holder.image.setImageResource(station.getImage());
        holder.id.setText(String.valueOf(station.getId()));
        holder.name.setText(station.getName());
        holder.frequency.setText(station.getFrequency());


    }

    @Override
    public int getItemCount() {
        return stationList==null?0:stationList.size();
    }

    public class Holder extends RecyclerView.ViewHolder {
        TextView id,name,frequency;
        ImageView image;



        public Holder(View itemView) {
            super(itemView);

            id=(TextView)itemView.findViewById(R.id.list_id);
            name=(TextView)itemView.findViewById(R.id.list_name);
            frequency=(TextView)itemView.findViewById(R.id.list_frequency);
            image=(ImageView) itemView.findViewById(R.id.list_logo);
        }


        public void bind(final ItemStation item, final OnItemClickListener listener) {


            itemView.setOnClickListener(new View.OnClickListener() {
                @Override public void onClick(View v) {
                    listener.onItemClick(item);
                }
            });
        }
    }
}
