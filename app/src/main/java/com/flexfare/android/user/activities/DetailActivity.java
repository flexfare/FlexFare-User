package com.flexfare.android.user.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.ImageView;
import android.widget.TextView;

import com.flexfare.android.user.R;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by kodenerd on 10/25/17.
 */

public class DetailActivity extends AppCompatActivity {

    @BindView(R.id.driverName)
    TextView driverName;
    @BindView(R.id.avi)
    ImageView avi;
    @BindView(R.id.license)
    TextView plateNo;
    @BindView(R.id.carType)
    TextView carType;
    @BindView(R.id.cates)
    TextView category;
    @BindView(R.id.routes)
    TextView routes;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.detail_main);
        ButterKnife.bind(this);

        initUI();

        Intent intent = getIntent();
        final String driver = intent.getExtras().getString("driver");
        final String car_type = intent.getExtras().getString("car_type");
        final String route = intent.getExtras().getString("routes");
        final String categories = intent.getExtras().getString("categories");
        final int img = intent.getExtras().getInt("image");

        avi.setImageResource(img);
        carType.setText(car_type);
        category.setText(categories);
        routes.setText(route);
        driverName.setText(driver);
    }
    @Override
    public boolean onNavigateUp() {
        onBackPressed();
        return true;
    }
    public void initUI() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);
        toolbar.setNavigationOnClickListener(v -> onNavigateUp());
    }
}
