package com.flexfare.android.user.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.flexfare.android.user.R;
import com.flexfare.android.user.views.ItemClickListener;

/**
 * Created by kodenerd on 10/25/17.
 */

public class DriversViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

    ImageView avi;
    TextView name;
    TextView carType;
    TextView route;
    TextView category;

    private ItemClickListener clickListener;

    public DriversViewHolder(View itemView) {
        super(itemView);
        avi = (ImageView) itemView.findViewById(R.id.avi);
        name = (TextView) itemView.findViewById(R.id.driverName);
        carType = (TextView) itemView.findViewById(R.id.carType);
        route = (TextView) itemView.findViewById(R.id.routes);
        category = (TextView) itemView.findViewById(R.id.category);

        itemView.setOnClickListener(this);
    }
    @Override
    public void onClick(View view) {
        this.clickListener.onItemClick(view, getLayoutPosition());
    }
    public void setItemClickListener(ItemClickListener itemClickListener) {
        this.clickListener = itemClickListener;
    }
}
