package com.example.navigaiit;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;
import com.google.android.material.navigation.NavigationView;

public class HomeActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener{

    DrawerLayout drawerLayout;
    BottomNavigationView bottomNavigationView;
    NavigationView navigationView;
    FragmentManager fragmentManager;
    Toolbar toolbar;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

//        spinner = findViewById(R.id.home_spinner);

        SharedPreferences prefs = getSharedPreferences("prefs", MODE_PRIVATE);
        boolean firstStart = prefs.getBoolean("firstStart", true);

        if (firstStart) {
            showStartDialog();
        }

        toolbar = findViewById(R.id.home_toolbar);
        setSupportActionBar(toolbar);

        drawerLayout = findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.baseline_account_circle_35);

        navigationView = findViewById(R.id.navigation_drawer);
        navigationView.setNavigationItemSelectedListener(this);

        bottomNavigationView = findViewById(R.id.bottom_navigation);
        bottomNavigationView.setBackground(null);

        // BOTTOM NAVIGATION
        bottomNavigationView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int itemId = item.getItemId();

                if(itemId == R.id.bottom_home) {
                    openFragment(new HomeFragment());
                    return true;
                }
                else if(itemId == R.id.bottom_search) {
                    openFragment(new SearchFragment());
                    return true;
                }
                else if(itemId == R.id.bottom_notifications) {
                    openFragment(new NotificationsFragment());
                    return true;
                }
                else if(itemId == R.id.bottom_bookmarks) {
                    openFragment(new BookmarksFragment());
                    return true;
                }

                return false;
            }
        });

        fragmentManager = getSupportFragmentManager();
        openFragment(new HomeFragment());

    }

    // DRAWER NAVIGATION
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {

        int itemId = item.getItemId();
        NavigationView nv = (NavigationView) findViewById(R.id.navigation_drawer);
        Menu menu = nv.getMenu();
        if(itemId == R.id.nav_profile) {
            menu.findItem(R.id.nav_settings_and_privacy).setVisible(false);
            menu.findItem(R.id.nav_display).setVisible(false);
            menu.findItem(R.id.nav_login).setVisible(false);
            menu.findItem(R.id.nav_help).setVisible(false);
            openFragment(new ProfileFragment());
        }
        else if(itemId == R.id.nav_mail) {
            menu.findItem(R.id.nav_settings_and_privacy).setVisible(false);
            menu.findItem(R.id.nav_display).setVisible(false);
            menu.findItem(R.id.nav_login).setVisible(false);
            menu.findItem(R.id.nav_help).setVisible(false);
            openFragment(new MailFragment());
        }
        else if(itemId == R.id.nav_history) {
            menu.findItem(R.id.nav_settings_and_privacy).setVisible(false);
            menu.findItem(R.id.nav_display).setVisible(false);
            menu.findItem(R.id.nav_login).setVisible(false);
            menu.findItem(R.id.nav_help).setVisible(false);
            openFragment(new HistoryFragment());
        }
        else if(itemId == R.id.nav_settings) {
            boolean b = !menu.findItem(R.id.nav_settings_and_privacy).isVisible();
            // setting submenus visible state
            menu.findItem(R.id.nav_settings_and_privacy).setVisible(b);
            menu.findItem(R.id.nav_display).setVisible(b);
            menu.findItem(R.id.nav_login).setVisible(b);
            menu.findItem(R.id.nav_help).setVisible(b);
            return true;
        }
        else if(itemId == R.id.nav_settings_and_privacy) {

        }
        else if(itemId == R.id.nav_display) {
            Intent intent = new Intent(this, DisplaySettingsActivity.class);
            startActivity(intent);
        }
        else if(itemId == R.id.nav_login) {
            Intent intent = new Intent(this,LoginActivity.class);
            startActivity(intent);
        }
        else if(itemId == R.id.nav_help) {

        }

        drawerLayout.closeDrawer(GravityCompat.START);

        return false;
    }

    @Override
    public void onBackPressed() {
        if(drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.close();
        }else {
            super.onBackPressed();
        }
    }

    private void openFragment(Fragment fragment) {
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.replace(R.id.fragment_container, fragment);
        transaction.commit();
    }

    private void showStartDialog() {
        new AlertDialog.Builder(this)
                .setTitle("About the app")
                .setMessage("This is a very early rendition of an ambitious project that may or may not be completed. You may encounter some bugs while using the app.")
                .setPositiveButton("ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                })
                .create().show();

        SharedPreferences prefs = getSharedPreferences("prefs", MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();
        editor.putBoolean("firstStart", true);
        editor.apply();
    }
}