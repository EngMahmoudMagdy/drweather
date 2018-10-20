package com.magdy.drweather.Adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.magdy.drweather.Data.NotifyObject;
import com.magdy.drweather.R;

import java.util.List;

public class NotifyAdapter extends RecyclerView.Adapter<NotifyAdapter.Holder> {
    private Context context ;
    private List<NotifyObject> notifyObjects ;

    public NotifyAdapter(Context context, List<NotifyObject> notifyObjects) {
        this.context = context;
        this.notifyObjects = notifyObjects;
    }

    @NonNull
    @Override
    public Holder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        final View view = LayoutInflater.from(context).inflate(R.layout.notify_item, viewGroup, false);
        return new Holder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Holder holder, int i) {
        NotifyObject object = notifyObjects.get(i);
        holder.name.setText(object.getName());
        holder.desc.setText(object.getDesc());
    }

    @Override
    public int getItemCount() {
        return notifyObjects.size();
    }

    class Holder extends RecyclerView.ViewHolder
    {
        TextView name , desc ;
        Holder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.name);
            desc = itemView.findViewById(R.id.desc);
        }
    }
}
