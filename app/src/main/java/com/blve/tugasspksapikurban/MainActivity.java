package com.blve.tugasspksapikurban;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener, View.OnClickListener, BottomNavigationView.OnNavigationItemSelectedListener {

    EditText ETKode;
    Spinner SpnAktivitas, SpnBulu, SpnMata, SpnMulut, SpnCelahKuku, SpnDubur;
    ArrayAdapter<CharSequence> adapterSpn;
    Button btnProses;
    Toolbar toolbar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        toolbar =  findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        inisialisasi();

//        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
////        fab.setOnClickListener(new View.OnClickListener() {
////            @Override
////            public void onClick(View view) {
////                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
////                        .setAction("Action", null).show();
////            }
////        });

        DrawerLayout drawer =  findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navigation);
        bottomNavigationView.setOnNavigationItemSelectedListener(this);

        toolbar.setTitle("SPK Pemilihan Sapi Kurban");
        loadFragment(new FragmentSPK());

    }

    private void loadFragment(FragmentSPK fragmentSPK) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.container, fragmentSPK);
        transaction.addToBackStack(null);
        transaction.commit();
    }

    private void inisialisasi() {
//        ETKode = findViewById(R.id.editText);
//        SpnAktivitas = findViewById(R.id.spinner_act);
//        adapterSpn = ArrayAdapter.createFromResource(this,
//                R.array.list_aktivitas, android.R.layout.simple_spinner_item);
//        adapterSpn.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
//        SpnAktivitas.setAdapter(adapterSpn);
//        SpnBulu = findViewById(R.id.spinner_bulu);
//        adapterSpn = ArrayAdapter.createFromResource(this,
//                R.array.list_bulu, android.R.layout.simple_spinner_item);
//        adapterSpn.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
//        SpnBulu.setAdapter(adapterSpn);
//        SpnCelahKuku = findViewById(R.id.spinner_celah_kuku);
//        adapterSpn = ArrayAdapter.createFromResource(this,
//                R.array.list_celah_kuku, android.R.layout.simple_spinner_item);
//        adapterSpn.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
//        SpnCelahKuku.setAdapter(adapterSpn);
//        SpnDubur = findViewById(R.id.spinner_dubur);
//        adapterSpn = ArrayAdapter.createFromResource(this,
//                R.array.list_dubur, android.R.layout.simple_spinner_item);
//        adapterSpn.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
//        SpnDubur.setAdapter(adapterSpn);
//        SpnMata = findViewById(R.id.spinner_mata);
//        adapterSpn = ArrayAdapter.createFromResource(this,
//                R.array.list_mata, android.R.layout.simple_spinner_item);
//        adapterSpn.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
//        SpnMata.setAdapter(adapterSpn);
//        SpnMulut = findViewById(R.id.spinner_mulut);
//        adapterSpn = ArrayAdapter.createFromResource(this,
//                R.array.list_mulut, android.R.layout.simple_spinner_item);
//        adapterSpn.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
//        SpnMulut.setAdapter(adapterSpn);
//        btnProses = findViewById(R.id.btnProses);
//        btnProses.setOnClickListener(this);

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

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }


        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {

        Fragment fragment;

        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.btm_nav_add) {
            toolbar.setTitle("SPK Pemilihan Sapi Kurban");
            fragment = new FragmentSPK();
            loadFragment((FragmentSPK) fragment);
            return true;
        } else if (id == R.id.btm_nav_history) {
            Toast.makeText(this, "aa", Toast.LENGTH_SHORT).show();
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

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnProses:
                int nilaiAktivitas = SpnAktivitas.getSelectedItemPosition();
                int nilaiBulu= SpnBulu.getSelectedItemPosition();
                int nilaiMata = SpnMata.getSelectedItemPosition();
                int nilaiMulut = SpnMulut.getSelectedItemPosition();
                int nilaiCelahKuku = SpnCelahKuku.getSelectedItemPosition();
                int nilaiDubur= SpnDubur.getSelectedItemPosition();
                if (nilaiAktivitas == 0) {
                    nilaiAktivitas = 90;
                } else if (nilaiAktivitas == 1) {
                    nilaiAktivitas = 60;
                } else if (nilaiAktivitas == 2) {
                    nilaiAktivitas = 40;
                }
                if (nilaiBulu == 0) {
                    nilaiBulu = 90;
                } else if (nilaiBulu == 1) {
                    nilaiBulu = 60;
                } else if (nilaiBulu == 2) {
                    nilaiBulu = 40;
                } else if (nilaiBulu == 3) {
                    nilaiBulu = 20;
                }
                if (nilaiMata == 0) {
                    nilaiMata = 90;
                } else if (nilaiMata == 1) {
                    nilaiMata = 60;
                } else if (nilaiMata == 2) {
                    nilaiMata = 20;
                }
                if (nilaiMulut == 0) {
                    nilaiMulut = 90;
                } else if (nilaiMulut == 1) {
                    nilaiMulut = 40;
                } else if (nilaiMulut == 2) {
                    nilaiMulut = 20;
                }
                if (nilaiCelahKuku == 0) {
                    nilaiCelahKuku = 90;
                } else if (nilaiCelahKuku == 1) {
                    nilaiCelahKuku = 60;
                } else if (nilaiCelahKuku == 2) {
                    nilaiCelahKuku = 20;
                }
                if (nilaiDubur == 0) {
                    nilaiDubur = 90;
                } else if (nilaiDubur == 1) {
                    nilaiDubur = 20;
                }
                int nilai_rata_rata = (nilaiAktivitas + nilaiBulu + nilaiCelahKuku + nilaiDubur + nilaiMata + nilaiMulut) / 6;
                String keputusan;
                if (nilai_rata_rata > 80) {
                    keputusan = "Sapi sangat ideal";
                } else if (nilai_rata_rata > 50) {
                    keputusan = "Sapi boleh untuk kurban namun lebih baik dilakukan pemeriksaan lebih lanjut";
                } else{
                    keputusan = "Sapi tidak direkomendasikan sebagai hewan kurban";
                }
                AlertDialog.Builder builder = new AlertDialog.Builder(this);
                builder.setTitle(R.string.dialog_title)
                        .setMessage(keputusan)
                        .setNegativeButton(R.string.ok, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                            }
                        });
                builder.create().show();


        }
    }
}
