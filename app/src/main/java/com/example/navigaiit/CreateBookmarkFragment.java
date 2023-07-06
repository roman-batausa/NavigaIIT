package com.example.navigaiit;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import com.google.android.material.textfield.TextInputLayout;

import java.util.ArrayList;


public class CreateBookmarkFragment extends Fragment {

    View view;
    DatabaseHelper databaseHelper;
    BookmarkModel bookmarkModel;

    String[] first_floor_rooms, second_floor_rooms, third_floor_rooms, fourth_floor_rooms, fifth_floor_rooms, floor_number;
    String[] buildings, all_rooms, room_floor;
    int al, bl, cl , dl, el, total;
    ArrayList<String> buildingArrayList, coe_roomArrayList, floorArrayList;
    TextView floor_textView;
    EditText notes_editText;
    Button button;
    AutoCompleteTextView building_AutoCTV, room_AutoCTV, floor_AutoCTV;
    TextInputLayout room_TIL, building_TIL, floor_TIL;

    @Override
    public void onResume() {
        super.onResume();

        room_TIL = view.findViewById(R.id.room_TIL);
        building_TIL = view.findViewById(R.id.building_TIL);
        floor_TIL = view.findViewById(R.id.floor_TIL);
        building_AutoCTV = view.findViewById(R.id.building_ACTV);
        floor_AutoCTV = view.findViewById(R.id.floor_ACTV);
        room_AutoCTV = view.findViewById(R.id.room_ACTV);
        notes_editText = view.findViewById(R.id.notes_et);
        button = view.findViewById(R.id.create_btn);

        first_floor_rooms = getResources().getStringArray(R.array.first_floor_rooms);
        second_floor_rooms = getResources().getStringArray(R.array.second_floor_rooms);
        third_floor_rooms = getResources().getStringArray(R.array.third_floor_rooms);
        fourth_floor_rooms = getResources().getStringArray(R.array.fourth_floor_rooms);
        fifth_floor_rooms = getResources().getStringArray(R.array.fifth_floor_rooms);

        buildingArrayList = new ArrayList<>();
        coe_roomArrayList = new ArrayList<>();
        floorArrayList = new ArrayList<>();

        building_AutoCTV.setText(getArguments().getString("building"));
        floor_AutoCTV.setText(getArguments().getString("floor"));
        room_AutoCTV.setText(getArguments().getString("room"));

        // add String array
        al = first_floor_rooms.length;
        bl = second_floor_rooms.length;
        cl = third_floor_rooms.length;
        dl = fourth_floor_rooms.length;
        el = fifth_floor_rooms.length;
        total = al + bl + cl + dl + el;
        all_rooms = new String[total];
        room_floor = new String[total];
        System.arraycopy(first_floor_rooms, 0, all_rooms,0, al);
        System.arraycopy(second_floor_rooms, 0, all_rooms, al, bl);
        System.arraycopy(third_floor_rooms, 0, all_rooms,al+bl, cl);
        System.arraycopy(fourth_floor_rooms, 0, all_rooms,al+bl+cl, dl);
        System.arraycopy(fifth_floor_rooms, 0, all_rooms,al+bl+cl+dl, el);
        for (int i = 0; i < all_rooms.length; i++) {
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
        for (String s : all_rooms) {
            coe_roomArrayList.add(s);
        }
        for (String s : room_floor) {
            floorArrayList.add(s);
        }

        // building ArrayList
        buildings = getResources().getStringArray(R.array.buildings);
        for(String s : buildings) {
            buildingArrayList.add(s);
        }

        ArrayAdapter<String> first_floor_rooms_adapter = new ArrayAdapter<>(requireContext(), R.layout.dropdown_item, first_floor_rooms);
        ArrayAdapter<String> second_floor_rooms_adapter = new ArrayAdapter<>(requireContext(), R.layout.dropdown_item, second_floor_rooms);
        ArrayAdapter<String> third_floor_rooms_adapter = new ArrayAdapter<>(requireContext(),R.layout.dropdown_item, third_floor_rooms);
        ArrayAdapter<String> fourth_floor_rooms_adapter = new ArrayAdapter<>(requireContext(),R.layout.dropdown_item, fourth_floor_rooms);
        ArrayAdapter<String> fifth_floor_rooms_adapter = new ArrayAdapter<>(requireContext(),R.layout.dropdown_item, fifth_floor_rooms);

        ArrayAdapter<String> buildings_Adapter = new ArrayAdapter<>(requireContext(), R.layout.dropdown_item, buildings);
        building_AutoCTV.setAdapter(buildings_Adapter);
        building_AutoCTV.setThreshold(1);

        ArrayAdapter<String> all_rooms_Adapter = new ArrayAdapter<>(requireContext(), R.layout.dropdown_item, all_rooms);
        room_AutoCTV.setAdapter(first_floor_rooms_adapter);
        room_AutoCTV.setThreshold(1);

        floor_number = getResources().getStringArray(R.array.floor_number);
        ArrayAdapter<String> floor_number_adapter = new ArrayAdapter<>(requireContext(),R.layout.dropdown_item, floor_number);
        floor_AutoCTV.setAdapter(floor_number_adapter);



        floor_AutoCTV.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                switch (i) {
                    case 0:
                        room_AutoCTV.setAdapter(first_floor_rooms_adapter);
                        break;
                    case 1:
                        room_AutoCTV.setAdapter(second_floor_rooms_adapter);
                        break;
                    case 2:
                        room_AutoCTV.setAdapter(third_floor_rooms_adapter);
                        break;
                    case 3:
                        room_AutoCTV.setAdapter(fourth_floor_rooms_adapter);
                        break;
                    case 4:
                        room_AutoCTV.setAdapter(fifth_floor_rooms_adapter);
                        break;
                    default:

                }
            }
        });

        // ====== CREATE BOOKMARK BUTTON =======
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                try {
                    bookmarkModel = new BookmarkModel(-1, building_AutoCTV.getText().toString()
                            , floor_AutoCTV.getText().toString()
                            , room_AutoCTV.getText().toString()
                            , notes_editText.getText().toString());
                }
                catch (Exception e) {
                    Toast.makeText(requireContext(), "Error creating Bookmark"+building_AutoCTV.getText().toString()+floor_AutoCTV.getText().toString()+room_AutoCTV.getText().toString()+notes_editText.getText().toString(), Toast.LENGTH_SHORT).show();
                    bookmarkModel = new BookmarkModel(-1, "error", "error", "error", "error");
                }

                databaseHelper = new DatabaseHelper(requireContext());
                boolean success = databaseHelper.addBookmark(bookmarkModel);

                //Toast.makeText(requireContext(), "Success: " + success, Toast.LENGTH_SHORT).show();
                getActivity()
                        .getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.create_bookmark_fragment, new BookmarksFragment(),"findThisFragment")
                        .addToBackStack(null)
                        .commit();

                hideKeyboard();
            }
        });
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_create_bookmark, container, false);



        return view;
    }

    public void hideKeyboard() {
        // Check if no view has focus:
        View view = getActivity().getCurrentFocus();
        if (view != null) {
            InputMethodManager inputManager = (InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
            inputManager.hideSoftInputFromWindow(view.getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
        }
    }

}