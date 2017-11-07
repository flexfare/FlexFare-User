package com.flexfare.android.user.fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.flexfare.android.user.R;

/**
 * Created by kodenerd on 10/25/17.
 */

public class Home extends Fragment {
    private View view;
    private Context context;
    private LayoutInflater inflater;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        this.context = getContext();
        this.inflater = inflater;
        this.view = inflater.inflate(R.layout.home_fragment, null);

        //   Toast.makeText(context, "Fragment A", Toast.LENGTH_SHORT).show();

        return this.view;
    }

    public static Home newInstance() {
        Home fragment = new Home();
        return fragment;
    }
}
