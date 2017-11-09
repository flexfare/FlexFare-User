package com.flexfare.android.user.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.flexfare.android.user.DriverModel;
import com.flexfare.android.user.R;
import com.flexfare.android.user.activities.DetailActivity;
import com.flexfare.android.user.views.ItemClickListener;
import com.squareup.picasso.MemoryPolicy;
import com.squareup.picasso.NetworkPolicy;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by kodenerd on 10/25/17.
 */

public class DriverAdapter extends RecyclerView.Adapter<DriversViewHolder> {

    Context mContext;
    //String[] drivers;
    String[] carTypes;
    String[] routes;
    String[] categories;
    int[] images;
    List<DriverModel> driverList;
    ArrayList<DriverModel> allDrivers ;

    public DriverAdapter(Context mContext, List<DriverModel> driverList/*String[] drivers, String[] carTypes, String[] routes, String[] categories, int[] images*/) {
        this.mContext = mContext;
        this.driverList = driverList;
        this.allDrivers = new ArrayList<>();
        allDrivers.addAll(driverList);
       /* this.carTypes = carTypes;
        this.routes = routes;
        this.categories = categories;
        this.images = images;*/
    }

    @Override
    public DriversViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.main_item, null);

        DriversViewHolder holder = new DriversViewHolder(view);
        return holder;
    }
    @Override
    public void onBindViewHolder(DriversViewHolder holder, final  int position) {

        DriverModel driverModel = driverList.get(position);

        if(driverModel.getImage_path().length() == 0){
            holder.avi.setImageResource(R.drawable.avatar);
        }else {
            Picasso.with(mContext).load("http://www.flexfare.org/api/images/" + driverModel.getImage_path()).memoryPolicy(MemoryPolicy.NO_CACHE).networkPolicy(NetworkPolicy.NO_CACHE).into(holder.avi);
        }
        //holder.avi.setImageResource(images[position]);
        holder.name.setText(driverModel.getDriver_name());
        holder.carType.setText(driverModel.getCar_name());
        holder.route.setText(driverModel.getState_option());
        holder.category.setText(driverModel.getDriver_type());

        holder.setItemClickListener(new ItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                Intent intent = new Intent(mContext, DetailActivity.class);
                intent.putExtra("driver", driverModel.getDriver_name());
                intent.putExtra("car_type",driverModel.getCar_name());
                intent.putExtra("routes", driverModel.getState_option());
                intent.putExtra("categories",driverModel.getDriver_type());
                intent.putExtra("image", driverModel.getImage_path());
                intent.putExtra("bio", driverModel.getDriver_description());
                intent.putExtra("car_image", driverModel.getCar_image_path());
                intent.putExtra("mobile", driverModel.getDriver_phone_number());

                mContext.startActivity(intent);
            }
        });
    }
    @Override
    public int getItemCount() {
        return driverList.size();
    }


    public void filterBYLGA(String state, String lga){
        Log.d("FlexApp", state +" " + lga);
        if(!driverList.isEmpty()){
            driverList.clear();
            for (int i =0;i< allDrivers.size(); i++) {
                //Log.d("FlexApp", allDrivers.get(i).getDriver_name());
                if(allDrivers.get(i).getState().contains(state) && allDrivers.get(i).getLga().contains(lga)){
                    System.out.print(allDrivers.get(i).getDriver_name());
                    driverList.add(allDrivers.get(i));
                }
            }

        }
        notifyDataSetChanged();

    }
}
