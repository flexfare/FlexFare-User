package com.flexfare.android.user.fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.flexfare.android.user.LocationProvider.interfaces.OnFilterInteractionListener;
import com.flexfare.android.user.LocationProvider.model.LocalGovernmentArea;
import com.flexfare.android.user.LocationProvider.model.State;
import com.flexfare.android.user.LocationProvider.ui.AreaPicker;
import com.flexfare.android.user.R;
import com.flexfare.android.user.adapter.DriverAdapter;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by kodenerd on 10/25/17.
 */

public class Drivers extends Fragment implements AreaPicker.OnStateSelected, AreaPicker.OnLGASelected, OnFilterInteractionListener {
    View view;
    Context context;
    LayoutInflater inflater;

    @BindView(R.id.area_picker)
    AreaPicker mAreaPicker;

    @BindView(R.id.item)
    RelativeLayout driverDetail;
    @BindView(R.id.myRecycler)
    RecyclerView recyclerView;


    String[] drivers={"Ander Herera","David De Gea","Michael Carrick","Juan Mata","Diego Costa","Oscar"};
    String[] cartype={"Toyota Camry - 2013","Zuzuki Mini-Bus", "Keke","Hyundai Sonata","Toyota Spyder - 2015","Toyota Avensis"};
    String[] routes = {"Interstate", "State Only", "State Only", "Interstate", "Interstate", "State Only"};
    String[] categories = {"Taxi", "Mini-Bus", "Keke", "Taxi", "Taxi", "Taxi"};
    int[] images={R.drawable.herera,R.drawable.degea,R.drawable.carrick,R.drawable.mata,R.drawable.costa,R.drawable.oscar};

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        this.context = getContext();
        this.inflater = inflater;
        this.view = inflater.inflate(R.layout.drivers_fragment, null);
        ButterKnife.bind(this, view);

        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setItemAnimator(new DefaultItemAnimator());

        DriverAdapter adapter = new DriverAdapter(getActivity(),drivers, cartype, routes, categories,images);
        recyclerView.setAdapter(adapter);

        mAreaPicker.setmOnLGASelected(this);
        mAreaPicker.setmOnStateSelected(this);
        mAreaPicker.setmOnFilterInteractionListener(this);

        return this.view;

    }
    @Override
    public void onFiltersInteraction(State state, LocalGovernmentArea localGovernmentArea){
        Toast.makeText(getActivity(), "STATE: " + state.getName() + " : " + "LGA: " + localGovernmentArea.getName(), Toast.LENGTH_SHORT).show();
    }
    @Override
    public void onStateSelected(int position, State state){

    }
    @Override
    public void onLGASelected(int position, LocalGovernmentArea localGovernmentArea) {

    }

    public static Drivers newInstance() {
        Drivers fragment = new Drivers();
        return fragment;
    }
}
