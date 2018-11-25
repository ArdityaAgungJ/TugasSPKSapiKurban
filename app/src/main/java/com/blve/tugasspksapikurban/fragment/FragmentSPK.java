package com.blve.tugasspksapikurban.fragment;


import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import com.blve.tugasspksapikurban.R;


/**
 * A simple {@link Fragment} subclass.
 */
public class FragmentSPK extends Fragment implements View.OnClickListener {
    EditText ETKode;
    Spinner SpnAktivitas, SpnBulu, SpnMata, SpnMulut, SpnCelahKuku, SpnDubur;
    ArrayAdapter<CharSequence> adapterSpn;
    Button btnProses;
    Context mContext;


    public FragmentSPK() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view =  inflater.inflate(R.layout.fragment_fragment_spk, container, false);
        inisialisasi(view);
        // Inflate the layout for this fragment
        return view;
    }

    private void inisialisasi(View view) {
        mContext = getContext();
        ETKode = view.findViewById(R.id.editText);
        SpnAktivitas = view.findViewById(R.id.spinner_act);
        adapterSpn = ArrayAdapter.createFromResource(mContext,
                R.array.list_aktivitas, android.R.layout.simple_spinner_item);
        adapterSpn.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        SpnAktivitas.setAdapter(adapterSpn);
        SpnBulu = view.findViewById(R.id.spinner_bulu);
        adapterSpn = ArrayAdapter.createFromResource(mContext,
                R.array.list_bulu, android.R.layout.simple_spinner_item);
        adapterSpn.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        SpnBulu.setAdapter(adapterSpn);
        SpnCelahKuku = view.findViewById(R.id.spinner_celah_kuku);
        adapterSpn = ArrayAdapter.createFromResource(mContext,
                R.array.list_celah_kuku, android.R.layout.simple_spinner_item);
        adapterSpn.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        SpnCelahKuku.setAdapter(adapterSpn);
        SpnDubur = view.findViewById(R.id.spinner_dubur);
        adapterSpn = ArrayAdapter.createFromResource(mContext,
                R.array.list_dubur, android.R.layout.simple_spinner_item);
        adapterSpn.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        SpnDubur.setAdapter(adapterSpn);
        SpnMata = view.findViewById(R.id.spinner_mata);
        adapterSpn = ArrayAdapter.createFromResource(mContext,
                R.array.list_mata, android.R.layout.simple_spinner_item);
        adapterSpn.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        SpnMata.setAdapter(adapterSpn);
        SpnMulut = view.findViewById(R.id.spinner_mulut);
        adapterSpn = ArrayAdapter.createFromResource(mContext,
                R.array.list_mulut, android.R.layout.simple_spinner_item);
        adapterSpn.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        SpnMulut.setAdapter(adapterSpn);
        btnProses = view.findViewById(R.id.btnProses);
        btnProses.setOnClickListener(this);

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
                AlertDialog.Builder builder = new AlertDialog.Builder(mContext);
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
