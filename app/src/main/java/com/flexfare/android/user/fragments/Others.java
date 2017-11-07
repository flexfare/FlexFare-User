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

public class Others extends Fragment {
    private View view;
    private Context context;
    private LayoutInflater inflater;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        this.context = getContext();
        this.inflater = inflater;
        this.view = inflater.inflate(R.layout.others_fragment, null);

        //Toast.makeText(context, "Fragment C", Toast.LENGTH_SHORT).show();
        //Snackbar.make(view.findViewById(android.R.id.content), "New Fragment", Snackbar.LENGTH_LONG).show();

        return this.view;
    }

    public static Others newInstance() {
        Others fragment = new Others();
        return fragment;
    }
}
