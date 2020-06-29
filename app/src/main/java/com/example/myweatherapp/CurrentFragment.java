package com.example.myweatherapp;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.w3c.dom.Text;

import java.util.jar.Manifest;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link CurrentFragment#newInstance} factory method to
 * create an instance of this fragment.
 */

public class CurrentFragment extends Fragment {


    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    String mName;
    String mLatitude;
    String mLongitude;
    TextView textView4,textView5,textView6;


    public CurrentFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view=inflater.inflate(R.layout.fragment_current, container, false);
        mName=getArguments().getString("name");
      mLatitude=getArguments().getString("latitude");
        mLongitude=getArguments().getString("longitude");

        RequestQueue queue= Volley.newRequestQueue(getActivity().getApplicationContext());

        TextView textView=(TextView)view.findViewById(R.id.test);
        textView.setText(mName);
        TextView textView2=(TextView)view.findViewById(R.id.lat);
        textView2.setText(mLatitude);
        TextView textView3=(TextView)view.findViewById(R.id.lon);
        textView3.setText(mLongitude);
         textView4=(TextView)view.findViewById(R.id.city);
        textView5=(TextView)view.findViewById(R.id.temp);
         textView6=(TextView)view.findViewById(R.id.humidity);



        String url="http://api.openweathermap.org/data/2.5/weather?lat="+mLatitude+"&lon="+mLongitude+"&appid=873069d275561f80502ee997b550483d";
        Log.i("url",url);
        JsonObjectRequest jor=new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    JSONObject main_object=response.getJSONObject("main");
                    JSONArray array =response.getJSONArray("weather");
                    JSONObject object=array.getJSONObject(0);
                    String temp= String.valueOf(main_object.getDouble("temp"));
                    String description = object.getString("description");
                    String city=response.getString("name");


                    textView4.setText(city);
                    textView5.setText(temp);
                    textView6.setText(description);


                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        }
        );

        queue.add(jor);


        // latEditText.setText(mLatitude);
        //lonEditText.setText(mLongitude);


         Toast.makeText(getContext(), "Latitude="+mLatitude+"\n"+"Longitude="+mLongitude+"\n"+"name:"+mName,Toast.LENGTH_SHORT).show();
        // Inflate the layout for this fragment
        return view;
    }

    private void findWeather() {

    }

    @Override
    public void onResume() {
        super.onResume();
        findWeather();

    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment CurrentFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static CurrentFragment newInstance(String param1, String param2) {
        CurrentFragment fragment = new CurrentFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
           // Toast.makeText(getContext(),"namenamenamename"+mName,Toast.LENGTH_LONG).show();
        }
    }




}