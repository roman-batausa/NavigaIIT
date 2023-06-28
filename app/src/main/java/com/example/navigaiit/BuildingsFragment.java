package com.example.navigaiit;

import android.content.res.TypedArray;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.fragment.app.Fragment;


public class BuildingsFragment extends Fragment {

    View view;
    String[] first_floor;
    TypedArray room_path;
    AutoCompleteTextView room_autoCompleteTextView;
    ImageView floorImage;
    TextView building_text;
    Bundle bundle;



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_buildings, container, false);

        building_text = view.findViewById(R.id.building_text);
        first_floor = getResources().getStringArray(R.array.first_floor);
        room_path = getResources().obtainTypedArray(R.array.room_path);
        room_autoCompleteTextView = view.findViewById(R.id.room_autoCompleteTextView);
        floorImage = view.findViewById(R.id.floorImage);
        bundle = this.getArguments();
        building_text.setText(bundle.getString("key"));

        ArrayAdapter<String> adapter2 = new ArrayAdapter<>(requireContext(), R.layout.dropdown_item, first_floor);
        room_autoCompleteTextView.setAdapter(adapter2);

        room_autoCompleteTextView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                floorImage.setImageResource(room_path.getResourceId(i,0));

            }
        });


        return view;
    }
}