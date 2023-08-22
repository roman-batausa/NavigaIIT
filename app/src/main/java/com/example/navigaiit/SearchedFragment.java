package com.example.navigaiit;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.res.TypedArray;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;


public class SearchedFragment extends Fragment {

    View view;
    ImageView searched_image;
    TextView room_text, floor_text, building, room_descriptions, desc_top2, note_top2, note2_textView;
    String[] first_floor_rooms, second_floor_rooms, third_floor_rooms, fourth_floor_rooms, fifth_floor_rooms;
    String[] first_floor_descriptions, second_floor_descriptions, third_floor_descriptions, fourth_floor_descriptions, fifth_floor_descriptions;
    String[] floor_number, all_rooms, room_floor;
    int al, bl, cl, dl, el;
    int i;
    TypedArray first_floor_room_path, second_floor_room_path, third_floor_room_path, fourth_floor_room_path, fifth_floor_room_path, floor_plan;
    TypedArray first_floor_room_path_no_path, second_floor_room_path_no_path, third_floor_room_path_no_path, fourth_floor_room_path_no_path, fifth_floor_room_path_no_path;
    ArrayList<BookmarkModel> bookmarkModelArrayList;
    BookmarkAdapter adapter;
    FloatingActionButton fab_bt3;
    Bundle bundle;
    boolean check;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_searched, container, false);

        // checks switch in DisplaySettingsActivity
        SharedPreferences sharedPreferences = getContext().getSharedPreferences("save", Context.MODE_PRIVATE);
        check = sharedPreferences.getBoolean("value",false);

        fab_bt3 = view.findViewById(R.id.fab_bt3);
        searched_image = view.findViewById(R.id.searchedImage);
        room_text = view.findViewById(R.id.searched_textView_room);
        floor_text = view.findViewById((R.id.searched_textView_floor));
        building = view.findViewById(R.id.bding_text);
        room_descriptions = view.findViewById(R.id.room_descriptions2_textView);
        desc_top2 = view.findViewById(R.id.desc_top2);
        note_top2 = view.findViewById(R.id.note_top2);
        note2_textView = view.findViewById(R.id.note2_textView);

        floor_number = getResources().getStringArray(R.array.floor_number);
        floor_plan = getResources().obtainTypedArray(R.array.floor_plan);
        ArrayAdapter<String> floor_number_adapter = new ArrayAdapter<>(requireContext(),R.layout.dropdown_item, floor_number);

        // first floor
        first_floor_rooms = getResources().getStringArray(R.array.first_floor_rooms);
        first_floor_descriptions = getResources().getStringArray(R.array.first_floor_rooms_descriptions);
        first_floor_room_path = getResources().obtainTypedArray(R.array.first_floor_room_path); // with path
        first_floor_room_path_no_path = getResources().obtainTypedArray(R.array.first_floor_room_no_path); // no path
        ArrayAdapter<String> first_floor_rooms_adapter = new ArrayAdapter<>(requireContext(), R.layout.dropdown_item, first_floor_rooms);

        // second floor
        second_floor_rooms = getResources().getStringArray(R.array.second_floor_rooms);
        second_floor_descriptions = getResources().getStringArray(R.array.second_floor_room_descriptions);
        second_floor_room_path = getResources().obtainTypedArray(R.array.second_floor_room_path); // with path
        second_floor_room_path_no_path = getResources().obtainTypedArray(R.array.second_floor_room_no_path); // no path

        // third floor
        third_floor_rooms = getResources().getStringArray(R.array.third_floor_rooms);
        third_floor_descriptions = getResources().getStringArray(R.array.third_floor_room_descriptions);
        third_floor_room_path = getResources().obtainTypedArray(R.array.third_floor_path); // with path
        third_floor_room_path_no_path = getResources().obtainTypedArray(R.array.third_floor_no_path); // no path

        // fourth floor
        fourth_floor_rooms = getResources().getStringArray(R.array.fourth_floor_rooms);
        fourth_floor_descriptions = getResources().getStringArray(R.array.fourth_floor_rooms_descriptions);
        fourth_floor_room_path = getResources().obtainTypedArray(R.array.fourth_floor_path); // with path
        fourth_floor_room_path_no_path = getResources().obtainTypedArray(R.array.fourth_floor_no_path); // no path

        // fifth floor
        fifth_floor_rooms = getResources().getStringArray(R.array.fifth_floor_rooms);
        fifth_floor_descriptions = getResources().getStringArray(R.array.fifth_floor_rooms_descriptions);
        fifth_floor_room_path = getResources().obtainTypedArray(R.array.fifth_floor_path); // with path
        fifth_floor_room_path_no_path = getResources().obtainTypedArray(R.array.fifth_floor_no_path); // no path

        al = first_floor_rooms.length;
        bl = second_floor_rooms.length;
        cl = third_floor_rooms.length;
        dl = fourth_floor_rooms.length;
        el = fifth_floor_rooms.length;
        int total = al+bl+cl+dl+el;
        all_rooms = new String[total];
        room_floor= new String[total];

        System.arraycopy(first_floor_rooms, 0, all_rooms,0, al);
        System.arraycopy(second_floor_rooms, 0, all_rooms, al, bl);
        System.arraycopy(third_floor_rooms, 0, all_rooms,al+bl, cl);
        System.arraycopy(fourth_floor_rooms, 0, all_rooms,al+bl+cl, dl);
        System.arraycopy(fifth_floor_rooms, 0, all_rooms,al+bl+cl+dl, el);

        for (int i = 0; i < total; i++) {
            if(i >= al+bl+cl+dl) {
                room_floor[i] = "Fifth floor";
            }
            else if(i >= al+bl+cl) {
                room_floor[i] = "Fourth floor";
            }
            else if(i >= al+bl) {
                room_floor[i] = "Third floor";
            }
            else if(i >= al) {
                room_floor[i] = "Second floor";
            }
            else if(i < al) {
                room_floor[i] = "First floor";
            }
        }

        i = getArguments().getInt("path");

        //Toast.makeText(requireContext(), "" + i + "", Toast.LENGTH_SHORT).show();
        searchedImage(i);



        if(getArguments().getBoolean("check") == true) {
            fab_bt3.setVisibility(View.VISIBLE);
        }
        fab_bt3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                bundle = new Bundle();

                bundle.putString("building", String.valueOf(building.getText()));
                bundle.putString("floor", String.valueOf(floor_text.getText()));
                bundle.putString("room", String.valueOf(room_text.getText()));

                openFragment(new CreateBookmarkFragment(),bundle);
                fab_bt3.setVisibility(View.INVISIBLE);
            }
        });


        return view;
    }

    private void searchedImage(int i) {
        if(i >= al+bl+cl+dl) {
            i -= al+bl+cl+dl;
            if(check == true){
                searched_image.setImageResource(fifth_floor_room_path_no_path.getResourceId(i,0));}
            else{
                searched_image.setImageResource(fifth_floor_room_path.getResourceId(i,0));}
            note_top2.setText(getArguments().getString("notes_top"));
            note2_textView.setText(getArguments().getString("notes"));
            desc_top2.setText("Room Descriptions: ");
            room_descriptions.setText(fifth_floor_descriptions[i]);
            room_text.setText(getArguments().getString("room"));
            building.setText(getArguments().getString("building"));
            floor_text.setText("Fifth floor");
        }
        else if(i >= al+bl+cl) {
            i -= al+bl+cl;
            if(check == true){
                searched_image.setImageResource(fourth_floor_room_path_no_path.getResourceId(i,0));}
            else{
                searched_image.setImageResource(fourth_floor_room_path.getResourceId(i,0));}
            note_top2.setText(getArguments().getString("notes_top"));
            note2_textView.setText(getArguments().getString("notes"));
            desc_top2.setText("Room Descriptions: ");
            room_descriptions.setText(fourth_floor_descriptions[i]);
            room_text.setText(getArguments().getString("room"));
            building.setText(getArguments().getString("building"));
            floor_text.setText("Fourth floor");
        }
        else if(i >= al+bl) {
            i -= al+bl;
            if(check == true){
                searched_image.setImageResource(third_floor_room_path_no_path.getResourceId(i,0));}
            else{
                searched_image.setImageResource(third_floor_room_path.getResourceId(i,0));}
            note_top2.setText(getArguments().getString("notes_top"));
            note2_textView.setText(getArguments().getString("notes"));
            desc_top2.setText("Room Descriptions: ");
            room_descriptions.setText(third_floor_descriptions[i]);
            room_text.setText(getArguments().getString("room"));
            building.setText(getArguments().getString("building"));
            floor_text.setText("Third floor");
        }
        else if(i >= al) {
            i -= al;
            if(check == true){
                searched_image.setImageResource(second_floor_room_path_no_path.getResourceId(i,0));}
            else{
                searched_image.setImageResource(second_floor_room_path.getResourceId(i,0));}
            note_top2.setText(getArguments().getString("notes_top"));
            note2_textView.setText(getArguments().getString("notes"));
            desc_top2.setText("Room Descriptions: ");
            room_descriptions.setText(second_floor_descriptions[i]);
            room_text.setText(getArguments().getString("room"));
            building.setText(getArguments().getString("building"));
            floor_text.setText("Second floor");
        }
        else if(i < al) {
            if(check == true){
                searched_image.setImageResource(first_floor_room_path_no_path.getResourceId(i,0));}
            else{
                searched_image.setImageResource(first_floor_room_path.getResourceId(i,0));}
            note_top2.setText(getArguments().getString("notes_top"));
            note2_textView.setText(getArguments().getString("notes"));
            desc_top2.setText("Room Descriptions: ");
            room_descriptions.setText(first_floor_descriptions[i]);
            room_text.setText(getArguments().getString("room"));
            building.setText(getArguments().getString("building"));
            floor_text.setText("First floor");
        }
    }

    private void openFragment(Fragment fragment, Bundle bundle) {
        fragment.setArguments(bundle);
        getActivity()
                .getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.searched_fragment, fragment,"findThisFragment")
                .addToBackStack(null)
                .commit();
    }

}
