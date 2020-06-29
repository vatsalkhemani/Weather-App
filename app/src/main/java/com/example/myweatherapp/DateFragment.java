package com.example.myweatherapp;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.Spinner;
import android.widget.TextView;

import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link DateFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class DateFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    DatePicker picker;

    StringBuilder builder;
    private SupportMapFragment mSupportMapFragment;
    Button displayDate;
    TextView textview1;
    public DateFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment DateFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static DateFragment newInstance(String param1, String param2) {
        DateFragment fragment = new DateFragment();
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
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.fragment_date, container, false);





            mSupportMapFragment = (SupportMapFragment) getChildFragmentManager().findFragmentById(R.id.mapwhere);
            if (mSupportMapFragment == null) {
                FragmentManager fragmentManager = getFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                mSupportMapFragment = SupportMapFragment.newInstance();
                fragmentTransaction.replace(R.id.mapwhere, mSupportMapFragment).commit();
            }

            if (mSupportMapFragment != null) {
                mSupportMapFragment.getMapAsync(new OnMapReadyCallback() {
                    @Override
                    public void onMapReady(GoogleMap googleMap) {
                        if (googleMap != null) {

                            googleMap.getUiSettings().setAllGesturesEnabled(true);

                            // MAKE THIS WHATEVER YOU WANT
                            LatLng sydney = new LatLng(-34, 151);
                            googleMap.addMarker(new MarkerOptions().position(sydney).title("Marker in Sydney"));
                            googleMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));

                            CameraPosition cameraPosition = new CameraPosition.Builder().target(sydney).zoom(15.0f).build();
                            CameraUpdate cameraUpdate = CameraUpdateFactory.newCameraPosition(cameraPosition);
                            googleMap.moveCamera(cameraUpdate);

                        }

                    }
                });
            }


        String [] values =
                {"Delhi","Mumbai","Noida"};
        Spinner spinner = (Spinner) v.findViewById(R.id.spinner);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this.getActivity(), android.R.layout.simple_spinner_item, values);
        adapter.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
        spinner.setAdapter(adapter);


        textview1=(TextView)v.findViewById(R.id.textView1);
        picker=(DatePicker)v.findViewById(R.id.datePicker);
        displayDate=(Button)v.findViewById(R.id.button1);

        textview1.setText("Current Date: "+getCurrentDate());

        displayDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                textview1.setText("Change Date: "+getCurrentDate());

            }
        });


        return v;
    }
    public String getCurrentDate() {
        builder = new StringBuilder();
        builder.append((picker.getMonth() + 1) + "/");
        builder.append(picker.getDayOfMonth() + "/");
        builder.append(picker.getYear());
        return builder.toString();
    }
    }
