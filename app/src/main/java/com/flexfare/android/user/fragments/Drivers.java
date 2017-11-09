package com.flexfare.android.user.fragments;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.flexfare.android.user.DriverModel;
import com.flexfare.android.user.LocationProvider.interfaces.OnFilterInteractionListener;
import com.flexfare.android.user.LocationProvider.model.LocalGovernmentArea;
import com.flexfare.android.user.LocationProvider.model.State;
import com.flexfare.android.user.LocationProvider.ui.AreaPicker;
import com.flexfare.android.user.R;
import com.flexfare.android.user.activities.DetailActivity;
import com.flexfare.android.user.adapter.DriverAdapter;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

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
    DriverAdapter adapter;
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

        new AlldriversAsyncTask(getActivity()).execute();

        //DriverAdapter adapter = new DriverAdapter(getActivity(),drivers, cartype, routes, categories,images);


        mAreaPicker.setmOnLGASelected(this);
        mAreaPicker.setmOnStateSelected(this);
        mAreaPicker.setmOnFilterInteractionListener(this);

        return this.view;

    }
    @Override
    public void onFiltersInteraction(State state, LocalGovernmentArea localGovernmentArea){
       // Toast.makeText(getActivity(), "STATE: " + state.getName() + " : " + "LGA: " + localGovernmentArea.getName(), Toast.LENGTH_SHORT).show();
       //new SearchDriversAsyncTask(getActivity(), state.getName(),localGovernmentArea.getName()).execute();
        adapter.filterBYLGA(state.getName(),localGovernmentArea.getName());
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

    private class AlldriversAsyncTask extends AsyncTask<String, Void, String>{
        ProgressDialog progressDialog;
        Context context;

        public  AlldriversAsyncTask(Context context){
         this.context = context;
        }
        @Override
        protected void onPreExecute() {


            //progressDialog.setTitle();
            progressDialog = new ProgressDialog(getActivity());
            progressDialog.setMessage("Please wait...");
            progressDialog.show();
            super.onPreExecute();
        }

        @Override
        protected String doInBackground(String... strings) {


            HttpURLConnection urlConnection = null;
            BufferedReader reader = null;
            DataOutputStream dataout = null;

            // Will contain the raw JSON response as a string.
            String result = null;

            try {

                URL url = new URL("http://www.flexfare.org/api/getdrivers.php");


                urlConnection = (HttpURLConnection) url.openConnection();
                urlConnection.setRequestMethod("POST");
                urlConnection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
                urlConnection.connect();

                /*Map<String, Object> params = new LinkedHashMap<>();
                params.put("user_id", user_id);
                params.put("driver_name", name);
                // params.put("car_name", carName);
                params.put("car_name", car_type);
                params.put("car_registration",reg_number);
                params.put("phone_number", phone_number);
                params.put("driver_descr", about);
                params.put("route", route);
                params.put("driver_type", driver_type);

                StringBuilder postData = new StringBuilder();
                for (Map.Entry<String, Object> param : params.entrySet()) {
                    if (postData.length() != 0) {
                        postData.append('&');
                    }
                    postData.append(URLEncoder.encode(param.getKey(), "UTF-8"));
                    postData.append('=');
                    postData.append(URLEncoder.encode(String.valueOf(param.getValue()), "UTF-8"));
                }
                String urlParameters = postData.toString();*/

                dataout = new DataOutputStream(urlConnection.getOutputStream());
                // perform POST operation
               // dataout.writeBytes(urlParameters);

                // Read the input stream into a String
                InputStream inputStream = urlConnection.getInputStream();
                StringBuffer buffer = new StringBuffer();
                if (inputStream == null) {
                    // Nothing to do.

                }
                reader = new BufferedReader(new InputStreamReader(inputStream));

                String line;
                while ((line = reader.readLine()) != null) {
                    // Since it's JSON, adding a newline isn't necessary (it won't affect parsing)
                    // But it does make debugging a *lot* easier if you print out the completed
                    // buffer for debugging.
                    buffer.append(line + "\n");
                }

                if (buffer.length() == 0) {
                    // Stream was empty.  No point in parsing.
                    Log.i("FlexApp", "Buffer Empty");
                }
                result = buffer.toString();

            } catch (Exception e) {
                Log.e("FlexApp", "Error ", e);
                // If the code didn't successfully get the weather data, there's no point in attemping
                // to parse it.

            } finally {
                if (urlConnection != null) {
                    urlConnection.disconnect();
                }
                if (reader != null) {
                    try {
                        reader.close();
                    } catch (final IOException e) {
                        Log.e("FlexApp", "Error closing stream", e);
                    }
                }
            }
            //Log.i("FlexApp", result);
            return result;

        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            progressDialog.dismiss();
            //Toast.makeText(getContext(), s, Toast.LENGTH_SHORT).show();
            List<DriverModel> driver = new ArrayList();

            try {
                JSONObject jsonObject = new JSONObject(s);
                JSONArray jsonArray = jsonObject.getJSONArray("message");
                for(int i = 0; i < jsonArray.length(); i++){
                    JSONObject object = jsonArray.getJSONObject(i);
                    DriverModel driverModel = new DriverModel();
                    if(object != null){

                        driverModel.setUser_id(object.getString("users_id"));
                        driverModel.setDriver_name(object.getString("driver_name"));
                        driverModel.setCar_name(object.getString("car_name"));
                        driverModel.setCar_plate_number(object.getString("car_plate_number"));
                        driverModel.setImage_path(object.getString("image_path"));
                        driverModel.setDriver_type(object.getString("driver_type"));
                        driverModel.setState_option(object.getString("state_option"));
                        driverModel.setDriver_description(object.getString("driver_description"));
                        driverModel.setCar_image_path(object.getString("car_image_path"));
                        driverModel.setDriver_phone_number(object.getString("driver_phone_number"));
                        driverModel.setState(object.getString("state"));
                        driverModel.setLga(object.getString("lga"));
                        driver.add(driverModel);

                    }


                }
                 adapter = new DriverAdapter(context, driver);
                recyclerView.setAdapter(adapter);
                adapter.notifyDataSetChanged();

            } catch (JSONException e) {
                e.printStackTrace();
            }

        }
    }


    private class SearchDriversAsyncTask extends AsyncTask<String, Void, String>{
        ProgressDialog progressDialog;
        Context context;
        String state;
        String lga;

        public  SearchDriversAsyncTask(Context context, String state, String lga){
            this.context = context;
            this.state = state;
            this.lga = lga;
        }
        @Override
        protected void onPreExecute() {


            //progressDialog.setTitle();
            progressDialog = new ProgressDialog(getActivity());
            progressDialog.setMessage("Please wait...");
            progressDialog.show();
            super.onPreExecute();
        }

        @Override
        protected String doInBackground(String... strings) {


            HttpURLConnection urlConnection = null;
            BufferedReader reader = null;
            DataOutputStream dataout = null;

            // Will contain the raw JSON response as a string.
            String result = null;

            try {

                URL url = new URL("http://www.flexfare.org/api/getdrivers.php");


                urlConnection = (HttpURLConnection) url.openConnection();
                urlConnection.setRequestMethod("POST");
                urlConnection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
                urlConnection.connect();

                Map<String, Object> params = new LinkedHashMap<>();
                params.put("state", state);
                params.put("lga", lga);
                // params.put("car_name", carName);


                StringBuilder postData = new StringBuilder();
                for (Map.Entry<String, Object> param : params.entrySet()) {
                    if (postData.length() != 0) {
                        postData.append('&');
                    }
                    postData.append(URLEncoder.encode(param.getKey(), "UTF-8"));
                    postData.append('=');
                    postData.append(URLEncoder.encode(String.valueOf(param.getValue()), "UTF-8"));
                }
                String urlParameters = postData.toString();

                dataout = new DataOutputStream(urlConnection.getOutputStream());
                // perform POST operation
                dataout.writeBytes(urlParameters);

                // Read the input stream into a String
                InputStream inputStream = urlConnection.getInputStream();
                StringBuffer buffer = new StringBuffer();
                if (inputStream == null) {
                    // Nothing to do.

                }
                reader = new BufferedReader(new InputStreamReader(inputStream));

                String line;
                while ((line = reader.readLine()) != null) {
                    // Since it's JSON, adding a newline isn't necessary (it won't affect parsing)
                    // But it does make debugging a *lot* easier if you print out the completed
                    // buffer for debugging.
                    buffer.append(line + "\n");
                }

                if (buffer.length() == 0) {
                    // Stream was empty.  No point in parsing.
                    Log.i("FlexApp", "Buffer Empty");
                }
                result = buffer.toString();

            } catch (Exception e) {
                Log.e("FlexApp", "Error ", e);
                // If the code didn't successfully get the weather data, there's no point in attemping
                // to parse it.

            } finally {
                if (urlConnection != null) {
                    urlConnection.disconnect();
                }
                if (reader != null) {
                    try {
                        reader.close();
                    } catch (final IOException e) {
                        Log.e("FlexApp", "Error closing stream", e);
                    }
                }
            }
            Log.i("FlexApp", result);
            return result;

        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            progressDialog.dismiss();
            //Toast.makeText(getContext(), s, Toast.LENGTH_SHORT).show();
            List<DriverModel> driver = new ArrayList();

            try {
                JSONObject jsonObject = new JSONObject(s);
                JSONArray jsonArray = jsonObject.getJSONArray("message");
                for(int i = 0; i < jsonArray.length(); i++){
                    JSONObject object = jsonArray.getJSONObject(i);
                    DriverModel driverModel = new DriverModel();
                    if(object != null){

                        driverModel.setUser_id(object.getString("users_id"));
                        driverModel.setDriver_name(object.getString("driver_name"));
                        driverModel.setCar_name(object.getString("car_name"));
                        driverModel.setCar_plate_number(object.getString("car_plate_number"));
                        driverModel.setImage_path(object.getString("image_path"));
                        driverModel.setDriver_type(object.getString("driver_type"));
                        driverModel.setState_option(object.getString("state_option"));
                        driverModel.setDriver_description(object.getString("driver_description"));
                        driverModel.setCar_image_path(object.getString("car_image_path"));
                        driverModel.setDriver_phone_number(object.getString("driver_phone_number"));
                        driver.add(driverModel);

                    }


                }
                DriverAdapter adapter = new DriverAdapter(context, driver);
                recyclerView.setAdapter(adapter);
                adapter.notifyDataSetChanged();

            } catch (JSONException e) {
                e.printStackTrace();
            }

        }
    }

}
