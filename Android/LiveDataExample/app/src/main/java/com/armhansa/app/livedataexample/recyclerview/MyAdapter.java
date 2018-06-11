package com.armhansa.app.livedataexample.recyclerview;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.armhansa.app.livedataexample.R;
import com.armhansa.app.livedataexample.database.realm.NumberRealm;

import java.util.ArrayList;

import io.realm.RealmResults;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder> {

    private ArrayList<String> items;

    public MyAdapter(RealmResults<NumberRealm> results) {
        items = new ArrayList<>();
        for(NumberRealm i: results) {
            items.add(i.getNumber());
        }
    }

    // Clean all elements of the recycler
    public void clear() {
        items.clear();
        notifyDataSetChanged();
    }

    // Add a list of items - - change to type used
    public void addAll(RealmResults<NumberRealm> results) {
        items = new ArrayList<>();
        for(NumberRealm i: results) {
            items.add(i.getNumber());
        }
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public MyAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // create a new view
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.number_item, parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyAdapter.ViewHolder holder, int position) {
        holder.itemView.setText(items.get(items.size()-position-1));
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        TextView itemView;

        ViewHolder(View itemView) {
            super(itemView);
            this.itemView = itemView.findViewById(R.id.text_item);
        }

    }
}
