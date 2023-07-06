package com.example.navigaiit;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;

import androidx.appcompat.widget.SearchView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class SearchFragment extends Fragment implements RoomAdapter.SelectedRoom{

    SearchView searchView;
    RecyclerView recyclerView;
    String[] first_floor_rooms, second_floor_rooms, third_floor_rooms, fourth_floor_rooms, fifth_floor_rooms;
    String[] first_floor_descriptions, second_floor_descriptions, third_floor_descriptions, fourth_floor_descriptions, fifth_floor_descriptions;
    String[] all_rooms, room_floor, building, all_room_descriptions;

    ArrayList<SearchModel> arrayList;
    ArrayList<SearchModel> searchList;
    Bundle bundle;
    int al, bl, cl ,dl, el, total;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_search, container, false);

        recyclerView = view.findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        searchView = view.findViewById(R.id.searchView);
        arrayList = new ArrayList<>();

        first_floor_rooms = getResources().getStringArray(R.array.first_floor_rooms);
        second_floor_rooms = getResources().getStringArray(R.array.second_floor_rooms);
        third_floor_rooms = getResources().getStringArray(R.array.third_floor_rooms);
        fourth_floor_rooms = getResources().getStringArray(R.array.fourth_floor_rooms);
        fifth_floor_rooms = getResources().getStringArray(R.array.fifth_floor_rooms);

        first_floor_descriptions = getResources().getStringArray(R.array.first_floor_rooms_descriptions);
        second_floor_descriptions = getResources().getStringArray(R.array.second_floor_room_descriptions);
        third_floor_descriptions = getResources().getStringArray(R.array.third_floor_room_descriptions);
        fourth_floor_descriptions = getResources().getStringArray(R.array.fourth_floor_rooms_descriptions);
        fifth_floor_descriptions = getResources().getStringArray(R.array.fifth_floor_rooms_descriptions);

        searchView.clearFocus();

        // add String array
        al = first_floor_rooms.length;
        bl = second_floor_rooms.length;
        cl = third_floor_rooms.length;
        dl = fourth_floor_rooms.length;
        el = fifth_floor_rooms.length;
        total = al + bl + cl + dl + el;
        all_rooms = new String[total];
        building = new String[total];
        room_floor= new String[total];
        all_room_descriptions = new String[total];

        System.arraycopy(first_floor_rooms, 0, all_rooms,0, al);
        System.arraycopy(second_floor_rooms, 0, all_rooms, al, bl);
        System.arraycopy(third_floor_rooms, 0, all_rooms,al+bl, cl);
        System.arraycopy(fourth_floor_rooms, 0, all_rooms,al+bl+cl, dl);
        System.arraycopy(fifth_floor_rooms, 0, all_rooms,al+bl+cl+dl, el);

        System.arraycopy(first_floor_descriptions, 0, all_room_descriptions,0, al);
        System.arraycopy(second_floor_descriptions, 0, all_room_descriptions, al, bl);
        System.arraycopy(third_floor_descriptions, 0, all_room_descriptions,al+bl, cl);
        System.arraycopy(fourth_floor_descriptions, 0, all_room_descriptions,al+bl+cl, dl);
        System.arraycopy(fifth_floor_descriptions, 0, all_room_descriptions,al+bl+cl+dl, el);

        for (int i = 0; i < total; i++) {
            building[i] = "COE";
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

        for(int i = 0; i < all_rooms.length; i++) {
            SearchModel searchModel = new SearchModel();
            searchModel.setRoom(all_rooms[i]);
            searchModel.setFloor(room_floor[i]);
            searchModel.setBuilding(building[i]);
            searchModel.setRoom_descriptions(all_room_descriptions[i]);
            arrayList.add(searchModel);
        }

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(requireContext());
        recyclerView.setLayoutManager(layoutManager);

        RoomAdapter roomAdapter = new RoomAdapter(requireContext(),arrayList, this::selectedRoom);
        recyclerView.setAdapter(roomAdapter);

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {

                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                roomAdapter.getFilter().filter(newText);
                return false;
            }

        });

        return view;
    }

    @Override
    public void selectedRoom(SearchModel searchModel) {
        bundle = new Bundle();
        int index = -1;
        for(int i = 0; i < all_rooms.length; i++) {
            if(all_rooms[i] == searchModel.getRoom() && room_floor[i] == searchModel.getFloor()) {
                index = i;
                break;
            }
        }

        bundle.putInt("path", index);
        bundle.putString("room", searchModel.getRoom());
        bundle.putString("building", searchModel.getBuilding());
        //Toast.makeText(requireContext(), ""+index+", "+all_rooms[index]+", "+searchModel.getRoom()+", "+room_floor[index]+", "+searchModel.getFloor(), Toast.LENGTH_SHORT).show();
        openFragment(new SearchedFragment(), bundle);
        hideKeyboard();
    }

    private void openFragment(Fragment fragment, Bundle bundle) {
        fragment.setArguments(bundle);
        getActivity()
                .getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.search_fragment, fragment,"findThisFragment")
                .addToBackStack(null)
                .commit();
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