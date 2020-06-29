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

import org.w3c.dom.Text;

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
        TextView textView=(TextView)view.findViewById(R.id.test);
        textView.setText(mName);
        TextView textView2=(TextView)view.findViewById(R.id.lat);
        textView2.setText(mLatitude);
        TextView textView3=(TextView)view.findViewById(R.id.lon);
        textView3.setText(mLongitude);


        // latEditText.setText(mLatitude);
        //lonEditText.setText(mLongitude);


         Toast.makeText(getContext(), "Latitude="+mLatitude+"\n"+"Longitude="+mLongitude+"\n"+"name:"+mName,Toast.LENGTH_SHORT).show();
        // Inflate the layout for this fragment
        return view;
    }

    @Override
    public void onResume() {
        super.onResume();

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