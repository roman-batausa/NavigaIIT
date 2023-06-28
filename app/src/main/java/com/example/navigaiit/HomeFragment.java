package com.example.navigaiit;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;

import androidx.fragment.app.Fragment;


public class HomeFragment extends Fragment {

    View view;
    String[] buildings;
    AutoCompleteTextView autoCompleteTextView;
    String building_name;
    Bundle bundle;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_home, container, false);

        buildings = getResources().getStringArray(R.array.buildings);
        autoCompleteTextView = view.findViewById(R.id.buildings_autoCompleteTextView);
        bundle = new Bundle();

        ArrayAdapter<String> adapter = new ArrayAdapter<>(requireContext(), R.layout.dropdown_item, buildings);
        autoCompleteTextView.setAdapter(adapter);

        autoCompleteTextView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                bundle.putString("key",buildings[i]);
                openFragment(new BuildingsFragment());
            }
        });

        return view;
    }

    private void openFragment(Fragment fragment) {
        fragment.setArguments(bundle);
        getActivity()
                .getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.home_fragment, fragment,"findThisFragment")
                .addToBackStack(null)
                .commit();
    }
    public String getBuilding_name() {
        return building_name;
    }
    
}