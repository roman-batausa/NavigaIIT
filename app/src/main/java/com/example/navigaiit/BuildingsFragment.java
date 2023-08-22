package com.example.navigaiit;

import android.content.Context;
import android.content.SharedPreferences;
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

import com.google.android.material.floatingactionbutton.FloatingActionButton;


public class BuildingsFragment extends Fragment {

    View view;
    String[] first_floor_rooms, second_floor_rooms, third_floor_rooms, fourth_floor_rooms, fifth_floor_rooms;
    String[] first_floor_descriptions, second_floor_descriptions, third_floor_descriptions, fourth_floor_descriptions, fifth_floor_descriptions;
    String[] floor_number;
    TypedArray first_floor_room_path, second_floor_room_path, third_floor_room_path, fourth_floor_room_path, fifth_floor_room_path, floor_plan;
    TypedArray first_floor_room_path_no_path, second_floor_room_path_no_path, third_floor_room_path_no_path, fourth_floor_room_path_no_path, fifth_floor_room_path_no_path;
    AutoCompleteTextView room_autoCompleteTextView, floor_autoCompleteTextView;
    ImageView floorImage;
    TextView building_text, room_descriptions, desc_top;
    Bundle bundle;
    int current_floor = 0;
    boolean check;
    FloatingActionButton fab_btn;
    OnSwipeTouchListener onSwipeTouchListener;
    ArrayAdapter<String> first_floor_rooms_adapter, second_floor_rooms_adapter, third_floor_rooms_adapter, fourth_floor_rooms_adapter, fifth_floor_rooms_adapter;

    @Override
    public void onResume() {
        super.onResume();

        room_descriptions = view.findViewById(R.id.room_descriptions_textView);
        desc_top = view.findViewById(R.id.desc_top);
        floorImage = view.findViewById(R.id.floorImage);
        fab_btn = view.findViewById(R.id.fab_btn);

        // checks switch in DisplaySettingsActivity
        SharedPreferences sharedPreferences = getContext().getSharedPreferences("save", Context.MODE_PRIVATE);
        check = sharedPreferences.getBoolean("value",false);

        // floor number
        floor_number = getResources().getStringArray(R.array.floor_number);
        floor_plan = getResources().obtainTypedArray(R.array.floor_plan);
        floor_autoCompleteTextView = view.findViewById(R.id.floor_autoCompleteTextView);
        ArrayAdapter<String> floor_number_adapter = new ArrayAdapter<>(requireContext(),R.layout.dropdown_item, floor_number);
        floor_autoCompleteTextView.setAdapter(floor_number_adapter);
        desc_top.setText("");
        room_descriptions.setText("");
        room_autoCompleteTextView = view.findViewById(R.id.room_autoCompleteTextView);

        // first floor
        first_floor_rooms = getResources().getStringArray(R.array.first_floor_rooms);
        first_floor_descriptions = getResources().getStringArray(R.array.first_floor_rooms_descriptions);
        first_floor_room_path = getResources().obtainTypedArray(R.array.first_floor_room_path); // with path
        first_floor_room_path_no_path = getResources().obtainTypedArray(R.array.first_floor_room_no_path); // no path
        first_floor_rooms_adapter = new ArrayAdapter<>(requireContext(), R.layout.dropdown_item, first_floor_rooms);
        room_autoCompleteTextView.setAdapter(first_floor_rooms_adapter);
        if(check == true){
            room_autoCompleteTextView(first_floor_room_path_no_path, first_floor_descriptions);}
        else{
            room_autoCompleteTextView(first_floor_room_path, first_floor_descriptions);}

        // second floor
        second_floor_rooms = getResources().getStringArray(R.array.second_floor_rooms);
        second_floor_descriptions = getResources().getStringArray(R.array.second_floor_room_descriptions);
        second_floor_room_path = getResources().obtainTypedArray(R.array.second_floor_room_path); // with path
        second_floor_room_path_no_path = getResources().obtainTypedArray(R.array.second_floor_room_no_path); // no path
        second_floor_rooms_adapter = new ArrayAdapter<>(requireContext(), R.layout.dropdown_item, second_floor_rooms);

        // third floor
        third_floor_rooms = getResources().getStringArray(R.array.third_floor_rooms);
        third_floor_descriptions = getResources().getStringArray(R.array.third_floor_room_descriptions);
        third_floor_room_path = getResources().obtainTypedArray(R.array.third_floor_path); // with path
        third_floor_room_path_no_path = getResources().obtainTypedArray(R.array.third_floor_no_path); // no path
        third_floor_rooms_adapter = new ArrayAdapter<>(requireContext(),R.layout.dropdown_item, third_floor_rooms);

        // fourth floor
        fourth_floor_rooms = getResources().getStringArray(R.array.fourth_floor_rooms);
        fourth_floor_descriptions = getResources().getStringArray(R.array.fourth_floor_rooms_descriptions);
        fourth_floor_room_path = getResources().obtainTypedArray(R.array.fourth_floor_path); // with path
        fourth_floor_room_path_no_path = getResources().obtainTypedArray(R.array.fourth_floor_no_path); // no path
        fourth_floor_rooms_adapter = new ArrayAdapter<>(requireContext(),R.layout.dropdown_item, fourth_floor_rooms);

        // fifth floor
        fifth_floor_rooms = getResources().getStringArray(R.array.fifth_floor_rooms);
        fifth_floor_descriptions = getResources().getStringArray(R.array.fifth_floor_rooms_descriptions);
        fifth_floor_room_path = getResources().obtainTypedArray(R.array.fifth_floor_path); // with path
        fifth_floor_room_path_no_path = getResources().obtainTypedArray(R.array.fifth_floor_no_path); // no path
        fifth_floor_rooms_adapter = new ArrayAdapter<>(requireContext(),R.layout.dropdown_item, fifth_floor_rooms);

        floor_autoCompleteTextView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                current_floor = i;
                floorImage.setImageResource(floor_plan.getResourceId(i,0));

                switch (current_floor) {
                    case 0:
                        desc_top.setText("");
                        room_descriptions.setText("");
                        room_autoCompleteTextView.setAdapter(first_floor_rooms_adapter);
                        if(check == true){
                            room_autoCompleteTextView(first_floor_room_path_no_path, first_floor_descriptions);}
                        else{
                            room_autoCompleteTextView(first_floor_room_path, first_floor_descriptions);}
                        break;
                    case 1:
                        desc_top.setText("");
                        room_descriptions.setText("");
                        room_autoCompleteTextView.setAdapter(second_floor_rooms_adapter);
                        if(check == true){
                            room_autoCompleteTextView(second_floor_room_path_no_path, first_floor_descriptions);}
                        else{
                            room_autoCompleteTextView(second_floor_room_path, first_floor_descriptions);}
                        break;
                    case 2:
                        desc_top.setText("");
                        room_descriptions.setText("");
                        room_autoCompleteTextView.setAdapter(third_floor_rooms_adapter);
                        if(check == true){
                            room_autoCompleteTextView(third_floor_room_path_no_path, first_floor_descriptions);}
                        else{
                            room_autoCompleteTextView(third_floor_room_path, first_floor_descriptions);}
                        break;
                    case 3:
                        desc_top.setText("");
                        room_descriptions.setText("");
                        room_autoCompleteTextView.setAdapter(fourth_floor_rooms_adapter);
                        if(check == true){
                            room_autoCompleteTextView(fourth_floor_room_path_no_path, first_floor_descriptions);}
                        else{
                            room_autoCompleteTextView(fourth_floor_room_path, first_floor_descriptions);}
                        break;
                    case 4:
                        desc_top.setText("");
                        room_descriptions.setText("");
                        room_autoCompleteTextView.setAdapter(fifth_floor_rooms_adapter);
                        if(check == true){
                            room_autoCompleteTextView(fifth_floor_room_path_no_path, first_floor_descriptions);}
                        else{
                            room_autoCompleteTextView(fifth_floor_room_path, first_floor_descriptions);}
                        break;
                    default:

                }
            }
        });

        fab_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                bundle.putString("building", getArguments().getString("key"));
                bundle.putString("floor", String.valueOf(floor_autoCompleteTextView.getText()));
                bundle.putString("room", String.valueOf(room_autoCompleteTextView.getText()));

                openFragment(new CreateBookmarkFragment(),bundle);
                fab_btn.setVisibility(View.INVISIBLE);
            }
        });

        //get building String from HomeFragment
        building_text = view.findViewById(R.id.building_text);
        bundle = this.getArguments();
        building_text.setText(bundle.getString("key"));

        floorImage.setOnTouchListener(new OnSwipeTouchListener(requireContext()){
            public void onSwipeTop() {
                if(current_floor == 0) {
                    current_floor = 4;
                }else{
                    --current_floor;
                }

                if(current_floor == 0) {
                    room_autoCompleteTextView.setAdapter(first_floor_rooms_adapter);
                }
                else if(current_floor == 1) {
                    room_autoCompleteTextView.setAdapter(second_floor_rooms_adapter);
                }
                else if(current_floor == 2) {
                    room_autoCompleteTextView.setAdapter(third_floor_rooms_adapter);
                }
                else if(current_floor == 3) {
                    room_autoCompleteTextView.setAdapter(fourth_floor_rooms_adapter);
                }
                else if(current_floor == 4) {
                    room_autoCompleteTextView.setAdapter(fifth_floor_rooms_adapter);
                }
                floor_autoCompleteTextView.setText(floor_number[current_floor]);
                floorImage.setImageResource(floor_plan.getResourceId(current_floor,0));
                ArrayAdapter<String> floor_number_adapter = new ArrayAdapter<>(requireContext(),R.layout.dropdown_item, floor_number);
                for_swipe_motion_room_autoCompleteTextView();
                //floor_autoCompleteTextView.setAdapter(floor_number_adapter);
                floor_autoCompleteTextView.dismissDropDown();

                //Toast.makeText(requireContext(), "top"+room_swipe+current_floor, Toast.LENGTH_SHORT).show();
            }
            public void onSwipeRight() {
            }
            public void onSwipeLeft() {
            }
            public void onSwipeBottom() {
                if(current_floor == 4) {
                    current_floor = 0;
                }
                else {
                    ++current_floor;
                }

                if(current_floor == 0) {
                    room_autoCompleteTextView.setAdapter(first_floor_rooms_adapter);
                }
                else if(current_floor == 1) {
                    room_autoCompleteTextView.setAdapter(second_floor_rooms_adapter);
                }
                else if(current_floor == 2) {
                    room_autoCompleteTextView.setAdapter(third_floor_rooms_adapter);
                }
                else if(current_floor == 3) {
                    room_autoCompleteTextView.setAdapter(fourth_floor_rooms_adapter);
                }
                else if(current_floor == 4) {
                    room_autoCompleteTextView.setAdapter(fifth_floor_rooms_adapter);
                }

                floor_autoCompleteTextView.setText(floor_number[current_floor]);
                floorImage.setImageResource(floor_plan.getResourceId(current_floor,0));
                ArrayAdapter<String> floor_number_adapter = new ArrayAdapter<>(requireContext(),R.layout.dropdown_item, floor_number);
                for_swipe_motion_room_autoCompleteTextView();
                //floor_autoCompleteTextView.setAdapter(floor_number_adapter);
                floor_autoCompleteTextView.dismissDropDown();

                //Toast.makeText(requireContext(), "bottom", Toast.LENGTH_SHORT).show();
            }
        });

    }

    public void for_swipe_motion_room_autoCompleteTextView() {
        switch (current_floor) {
            case 0:
                desc_top.setText("");
                room_descriptions.setText("");
                room_autoCompleteTextView.setAdapter(first_floor_rooms_adapter);
                if (check == true) {
                    room_autoCompleteTextView(first_floor_room_path_no_path, first_floor_descriptions);
                } else {
                    room_autoCompleteTextView(first_floor_room_path, first_floor_descriptions);
                }
                break;
            case 1:
                desc_top.setText("");
                room_descriptions.setText("");
                room_autoCompleteTextView.setAdapter(second_floor_rooms_adapter);
                if (check == true) {
                    room_autoCompleteTextView(second_floor_room_path_no_path, first_floor_descriptions);
                } else {
                    room_autoCompleteTextView(second_floor_room_path, first_floor_descriptions);
                }
                break;
            case 2:
                desc_top.setText("");
                room_descriptions.setText("");
                room_autoCompleteTextView.setAdapter(third_floor_rooms_adapter);
                if (check == true) {
                    room_autoCompleteTextView(third_floor_room_path_no_path, first_floor_descriptions);
                } else {
                    room_autoCompleteTextView(third_floor_room_path, first_floor_descriptions);
                }
                break;
            case 3:
                desc_top.setText("");
                room_descriptions.setText("");
                room_autoCompleteTextView.setAdapter(fourth_floor_rooms_adapter);
                if (check == true) {
                    room_autoCompleteTextView(fourth_floor_room_path_no_path, first_floor_descriptions);
                } else {
                    room_autoCompleteTextView(fourth_floor_room_path, first_floor_descriptions);
                }
                break;
            case 4:
                desc_top.setText("");
                room_descriptions.setText("");
                room_autoCompleteTextView.setAdapter(fifth_floor_rooms_adapter);
                if (check == true) {
                    room_autoCompleteTextView(fifth_floor_room_path_no_path, first_floor_descriptions);
                } else {
                    room_autoCompleteTextView(fifth_floor_room_path, first_floor_descriptions);
                }
                break;
            default:
        }
    }

    public void room_autoCompleteTextView(TypedArray arr, String sarr[]) {
        room_autoCompleteTextView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                floorImage.setImageResource(arr.getResourceId(i,0));
                desc_top.setText("Room descriptions: ");
                room_descriptions.setText(sarr[i]);
                fab_btn.setVisibility(View.VISIBLE);

            }
        });
    }

    private void openFragment(Fragment fragment, Bundle bundle) {
        fragment.setArguments(bundle);
        getActivity()
                .getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.buildings_fragment, fragment,"findThisFragment")
                .addToBackStack(null)
                .commit();
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_buildings, container, false);

        return view;
    }

}