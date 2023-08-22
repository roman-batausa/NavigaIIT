package com.example.navigaiit;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.Arrays;

public class BookmarksFragment extends Fragment {


    View view;
    RecyclerView recyclerView;
    BookmarkAdapter adapter;
    RecyclerView.LayoutManager layoutManager;
    ArrayList<BookmarkModel> bookmarkModelArrayList;
    FloatingActionButton fab_btn;
    Bundle bundle;
    String[] all_rooms, room_floor, building;
    String[] first_floor_rooms, second_floor_rooms, third_floor_rooms, fourth_floor_rooms, fifth_floor_rooms;
    int al, bl, cl, dl, el, total;
    TextView bookmark_blank_tv;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_bookmarks, container, false);

        bookmark_blank_tv = view.findViewById(R.id.bookmark_blank_tv);
        fab_btn = view.findViewById(R.id.fab_btn);

        fab_btn.setVisibility(View.VISIBLE);

        // add String array
        first_floor_rooms = getResources().getStringArray(R.array.first_floor_rooms);
        second_floor_rooms = getResources().getStringArray(R.array.second_floor_rooms);
        third_floor_rooms = getResources().getStringArray(R.array.third_floor_rooms);
        fourth_floor_rooms = getResources().getStringArray(R.array.fourth_floor_rooms);
        fifth_floor_rooms = getResources().getStringArray(R.array.fifth_floor_rooms);
        al = first_floor_rooms.length;
        bl = second_floor_rooms.length;
        cl = third_floor_rooms.length;
        dl = fourth_floor_rooms.length;
        el = fifth_floor_rooms.length;
        total = al + bl + cl + dl + el;
        all_rooms = new String[total];
        building = new String[total];
        room_floor= new String[total];

        System.arraycopy(first_floor_rooms, 0, all_rooms,0, al);
        System.arraycopy(second_floor_rooms, 0, all_rooms, al, bl);
        System.arraycopy(third_floor_rooms, 0, all_rooms,al+bl, cl);
        System.arraycopy(fourth_floor_rooms, 0, all_rooms,al+bl+cl, dl);
        System.arraycopy(fifth_floor_rooms, 0, all_rooms,al+bl+cl+dl, el);
        for (int i = 0; i < all_rooms.length; i++) {
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


        recyclerView = view.findViewById(R.id.recyclerView2);

        createBookmark();
        buildRecyclerView();
        fab_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                bundle = new Bundle();
                bundle.putString("building", "select building");
                bundle.putString("room", "select room");
                bundle.putString("floor", "First floor");
                openFragment(new CreateBookmarkFragment(), bundle);
                fab_btn.setVisibility(View.INVISIBLE);
            }
        });

        return view;
    }

    public void addBookmark(int position) {
        //bookmarkModelArrayList.add(position, new BookmarkModel(-1, "COE", "first floor", "101", "class 1"));
        adapter.notifyItemInserted(position);
    }
    public void deleteBookmark(int position) {
        DatabaseHelper mdatabaseHelper = new DatabaseHelper(requireContext());
        //mdatabaseHelper.deleteALL();
        BookmarkModel bm = adapter.getItemAtPosition(position);
        mdatabaseHelper.deleteBookmark(bm);
        bookmarkModelArrayList.remove(position);
        adapter.notifyItemRemoved(position);
        if(adapter.getItemCount() <= 0) {
            bookmark_blank_tv.setVisibility(View.VISIBLE);
        }
        Toast.makeText(requireContext(), "Bookmark deleted", Toast.LENGTH_SHORT).show();

    }

    public void createBookmark() {
        DatabaseHelper databaseHelper = new DatabaseHelper(requireContext());
        bookmarkModelArrayList = databaseHelper.getAll();
        //bookmarkModelArrayList.add(new BookmarkModel("COE", "first floor", "101", "class 1"));
        //bookmarkModelArrayList.add(new BookmarkModel("COE", "first floor", "101", "class 1"));

    }
    public void buildRecyclerView() {
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(requireContext());
        adapter = new BookmarkAdapter(bookmarkModelArrayList);

        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);

        if(adapter.getItemCount() <= 0) {
            bookmark_blank_tv.setVisibility(View.VISIBLE);
        }

        adapter.setOnItemClickListener(new BookmarkAdapter.onItemClickListener() {

            @Override
            public void onItemClicked(int position) {
                bundle = new Bundle();
                BookmarkModel bookmarkModel = adapter.getItemAtPosition(position);
                try {
                    int q = Arrays.asList(all_rooms).indexOf(bookmarkModel.getRoom());
                    int z = Arrays.asList(room_floor).indexOf(bookmarkModel.getFloor());

                    String a = all_rooms[q];
                    String b = room_floor[z];

                    int index = -1;

                    for(int i = 0; i < all_rooms.length; i++) {
                        if(all_rooms[i] == a && room_floor[i] == b) {
                            index = i;
                            break;
                        }
                    }

                    // HOLY HARD CODED YAWAA
                    if(index == -1) {
                        if(z == 28 && q == 25) { // second floor elevator
                            index = 45;
                        }
                        else if(z == 28 && q == 26) { // second floor female cr
                            index = 46;
                        }
                        else if(z == 28 && q == 27) { // second floor male cr
                            index = 47;
                        }
                        else if(z == 48 && q == 25) { // third floor elevator
                            index = 73;
                        }
                        else if(z == 48 && q == 26) { // third floor female cr
                            index = 74;
                        }
                        else if(z == 48 && q == 27) { // third floor male cr
                            index = 75;
                        }
                        else if(z == 76 && q == 25) { // fourth floor elevator
                            index = 79;
                        }
                        else if(z == 76 && q == 26) { // fourth floor female cr
                            index = 80;
                        }
                        else if(z == 76 && q == 27) { // fourth floor male cr
                            index = 81;
                        }
                        else if(z == 76 && q == 82) { // fourth floor pwd cr
                            index = 82;
                        }
                        else if(z == 83 && q == 25) { // fifth floor elevator
                            index = 89;
                        }
                        else if(z == 83 && q == 26) { // fifth floor female cr
                            index = 90;
                        }
                        else if(z == 83 && q == 27) { // fifth floor male cr
                            index = 91;
                        }
                        else if(z == 83 && q == 82) { // fifth floor male cr
                            index = 92;
                        }
                    }


                    bundle.putBoolean("check", false);
                    bundle.putString("notes_top", "Notes: ");
                    bundle.putString("notes", bookmarkModel.getNotes());
                    bundle.putInt("path", index);
                    bundle.putString("room", bookmarkModel.getRoom());
                    bundle.putString("building", bookmarkModel.getBuilding());
                    //Toast.makeText(requireContext(), ""+index+", "+all_rooms[q]+", "+a+", "+room_floor[z]+", "+b+", "+q+", "+z+"", Toast.LENGTH_SHORT).show();
                    openFragment(new SearchedFragment(), bundle);
                    fab_btn.setVisibility(View.INVISIBLE);
                }catch (Exception e) {
                    Toast.makeText(requireContext(), "Error: Make sure the room, floor, and building are set accordingly", Toast.LENGTH_LONG).show();
                    e.getCause();
                }
            }

            @Override
            public void onDeleteClicked(int position) {
                deleteBookmark(position);
            }
        });


    }

    private void openFragment(Fragment fragment, Bundle bundle) {
        fragment.setArguments(bundle);
        getActivity()
                .getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.bookmarks_fragment, fragment,"findThisFragment")
                .addToBackStack(null)
                .commit();
    }


}