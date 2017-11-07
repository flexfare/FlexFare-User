package com.flexfare.android.user.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.flexfare.android.user.R;
import com.flexfare.android.user.activities.DetailActivity;
import com.flexfare.android.user.views.ItemClickListener;

/**
 * Created by kodenerd on 10/25/17.
 */

public class DriverAdapter extends RecyclerView.Adapter<DriversViewHolder> {

    Context mContext;
    String[] drivers;
    String[] carTypes;
    String[] routes;
    String[] categories;
    int[] images;

    public DriverAdapter(Context mContext, String[] drivers, String[] carTypes, String[] routes, String[] categories, int[] images) {
        this.mContext = mContext;
        this.drivers = drivers;
        this.carTypes = carTypes;
        this.routes = routes;
        this.categories = categories;
        this.images = images;
    }

    @Override
    public DriversViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.main_item, null);

        DriversViewHolder holder = new DriversViewHolder(view);
        return holder;
    }
    @Override
    public void onBindViewHolder(DriversViewHolder holder, final  int position) {
        holder.avi.setImageResource(images[position]);
        holder.name.setText(drivers[position]);
        holder.carType.setText(carTypes[position]);
        holder.route.setText(routes[position]);
        holder.category.setText(categories[position]);

        holder.setItemClickListener(new ItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                Intent intent = new Intent(mContext, DetailActivity.class);
                intent.putExtra("driver", drivers[position]);
                intent.putExtra("car_type",carTypes[position]);
                intent.putExtra("routes", routes[position]);
                intent.putExtra("categories",categories[position]);
                intent.putExtra("image", images[position]);
                mContext.startActivity(intent);
            }
        });
    }
    @Override
    public int getItemCount() {
        return drivers.length;
    }
}
