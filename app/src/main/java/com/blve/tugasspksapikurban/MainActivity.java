package com.blve.tugasspksapikurban;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.blve.tugasspksapikurban.fragment.FragmentHistory;
import com.blve.tugasspksapikurban.fragment.FragmentSPK;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener, BottomNavigationView.OnNavigationItemSelectedListener {

    Toolbar toolbar;
    public static Context contextOfMainActivity;

    public static Context getContextOfMainActivity()
    {
        return contextOfMainActivity;
    }

    private boolean isFirstFragment=true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        contextOfMainActivity = getApplicationContext();
        toolbar =  findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

//        DrawerLayout drawer =  findViewById(R.id.drawer_layout);
//        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
//                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
//        drawer.addDrawerListener(toggle);
//        toggle.syncState();
//
//        NavigationView navigationView = findViewById(R.id.nav_view);
//        navigationView.setNavigationItemSelectedListener(this);

        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navigation);
        bottomNavigationView.setOnNavigationItemSelectedListener(this);

        toolbar.setTitle("SPK Pemilihan Sapi Kurban");
        loadFragment(new FragmentSPK());

    }

    private void loadFragment(Fragment fragment) {
//        Fragment localFragment= null;
//        if (isFirstFragment) {
//            localFragment=fragment;
            FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
            transaction.replace(R.id.container, fragment);
            transaction.addToBackStack(null);
            transaction.commit();
//            isFirstFragment=false;
//        } else {
//            FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
//            transaction.detach(localFragment);
//            transaction.replace(R.id.container, fragment);
//            transaction.addToBackStack(null);
//            transaction.commit();
//            localFragment=fragment;
//        }

    }


    @Override
    public void onBackPressed() {
        DrawerLayout drawer =  findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        // Inflate the menu; this adds items to the action bar if it is present.
//        getMenuInflater().inflate(R.menu.main, menu);
//        return true;
//    }

//    @Override
//    public boolean onOptionsItemSelected(MenuItem item) {
//        // Handle action bar item clicks here. The action bar will
//        // automatically handle clicks on the Home/Up button, so long
//        // as you specify a parent activity in AndroidManifest.xml.
//        int id = item.getItemId();
//
//        //noinspection SimplifiableIfStatement
//        if (id == R.id.action_settings) {
//            return true;
//        }
//
//
//        return super.onOptionsItemSelected(item);
//    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {

        Fragment fragment;

        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.btm_nav_add) {
            toolbar.setTitle("SPK Pemilihan Sapi Kurban");
            fragment = new FragmentSPK();
            loadFragment(fragment);
            return true;
        } else if (id == R.id.btm_nav_history) {
            toolbar.setTitle("Histori SPK");
            fragment = new FragmentHistory();
            loadFragment(fragment);
            return true;
        }
        if (id == R.id.nav_camera) {
            // Handle the camera action
        } else if (id == R.id.nav_gallery) {

        } else if (id == R.id.nav_slideshow) {

        } else if (id == R.id.nav_manage) {

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        }
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
