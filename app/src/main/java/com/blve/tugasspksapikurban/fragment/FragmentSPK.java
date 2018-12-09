package com.blve.tugasspksapikurban.fragment;


import android.app.AlertDialog;
import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.blve.tugasspksapikurban.MainActivity;
import com.blve.tugasspksapikurban.R;
import com.blve.tugasspksapikurban.adapter.PotensiAdapter;
import com.blve.tugasspksapikurban.adapter.SapiRVAdapter;
import com.blve.tugasspksapikurban.model.Sapi;
import com.blve.tugasspksapikurban.util.Sort;
import com.wangjie.rapidfloatingactionbutton.RapidFloatingActionButton;
import com.wangjie.rapidfloatingactionbutton.RapidFloatingActionHelper;
import com.wangjie.rapidfloatingactionbutton.RapidFloatingActionLayout;
import com.wangjie.rapidfloatingactionbutton.contentimpl.labellist.RFACLabelItem;
import com.wangjie.rapidfloatingactionbutton.contentimpl.labellist.RapidFloatingActionContentLabelList;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static com.blve.tugasspksapikurban.db.DBContract.CONTENT_URI;
import static com.blve.tugasspksapikurban.db.DBContract.TableColumns.AKTIVITAS;
import static com.blve.tugasspksapikurban.db.DBContract.TableColumns.BULU;
import static com.blve.tugasspksapikurban.db.DBContract.TableColumns.CELAH_KUKU;
import static com.blve.tugasspksapikurban.db.DBContract.TableColumns.DUBUR;
import static com.blve.tugasspksapikurban.db.DBContract.TableColumns.IDENTITAS;
import static com.blve.tugasspksapikurban.db.DBContract.TableColumns.MATA;
import static com.blve.tugasspksapikurban.db.DBContract.TableColumns.MULUT;


/**
 * A simple {@link Fragment} subclass.
 */
public class FragmentSPK extends Fragment implements View.OnClickListener, RapidFloatingActionContentLabelList.OnRapidFloatingActionContentLabelListListener {

    Context mContext;
    private View popupInputDialogView = null;

    //dialog tambah
    EditText ETKode;
    Spinner SpnAktivitas, SpnBulu, SpnMata, SpnMulut, SpnCelahKuku, SpnDubur;
    ArrayAdapter<CharSequence> adapterSpn;
    Button btnSimpan, btnCancel;
    AlertDialog alertDialog=null;

    RecyclerView recyclerView;
    SapiRVAdapter adapter;
    private List<Sapi> list = new ArrayList<>();

    private RapidFloatingActionLayout rfaLayout;
    private RapidFloatingActionButton rfaBtn;
    private RapidFloatingActionHelper rfabHelper;



    public FragmentSPK() {
        // Required empty public constructor
    }



    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view =  inflater.inflate(R.layout.fragment_fragment_spk, container, false);

        inisialisasi(view);

        setFAB();

        // Inflate the layout for this fragment
        return view;
    }

    private void setFAB() {

        RapidFloatingActionContentLabelList rfaContent = new RapidFloatingActionContentLabelList(mContext);
        rfaContent.setOnRapidFloatingActionContentLabelListListener(this);
        List<RFACLabelItem> items = new ArrayList<>();
        items.add(new RFACLabelItem<Integer>()
                .setLabel("Clear")
                .setResId(R.mipmap.ic_launcher)
                .setIconNormalColor(0xffd84315)
                .setIconPressedColor(0xffbf360c)
                .setWrapper(0)
        );

        items.add(new RFACLabelItem<Integer>()
                .setLabel("Tambah")
                .setResId(R.mipmap.ic_launcher)
                .setIconNormalColor(0xff056f00)
                .setIconPressedColor(0xff0d5302)
                .setLabelColor(0xff056f00)
                .setWrapper(2)
        );
        items.add(new RFACLabelItem<Integer>()
                .setLabel("Proses")
                .setResId(R.mipmap.ic_launcher)
                .setIconNormalColor(0xff283593)
                .setIconPressedColor(0xff1a237e)
                .setLabelColor(0xff283593)
                .setWrapper(3)
        );
        rfaContent
                .setItems(items)
                .setIconShadowRadius(10)
                .setIconShadowColor(0xff888888)
                .setIconShadowDy(10)
        ;
        rfabHelper = new RapidFloatingActionHelper(
                mContext,
                rfaLayout,
                rfaBtn,
                rfaContent
        ).build();
    }

    private void inisialisasi(View view) {
        mContext = getContext();
        rfaLayout = view.findViewById(R.id.activity_main_rfal);
        rfaBtn = view.findViewById(R.id.activity_main_rfab);
        recyclerView= view.findViewById(R.id.rv_sapi);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setHasFixedSize(true);
        adapter = new SapiRVAdapter(getActivity());
        adapter.setListSapi(list);
        recyclerView.setAdapter(adapter);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnSimpan:
                String nilaiIdentitas = ETKode.getText().toString().trim();
                int nilaiAktivitas = SpnAktivitas.getSelectedItemPosition();
                int nilaiBulu= SpnBulu.getSelectedItemPosition();
                int nilaiMata = SpnMata.getSelectedItemPosition();
                int nilaiMulut = SpnMulut.getSelectedItemPosition();
                int nilaiCelahKuku = SpnCelahKuku.getSelectedItemPosition();
                int nilaiDubur= SpnDubur.getSelectedItemPosition();
                final ContentValues values = new ContentValues();
                values.put(IDENTITAS, nilaiIdentitas);
                values.put(AKTIVITAS, nilaiIdentitas);
                values.put(BULU, nilaiBulu);
                values.put(MATA, nilaiMata);
                values.put(MULUT, nilaiMulut);
                values.put(CELAH_KUKU, nilaiCelahKuku);
                values.put(DUBUR, nilaiDubur);

                Sapi sapi = new Sapi(nilaiIdentitas, nilaiAktivitas, nilaiBulu, nilaiMata, nilaiMulut, nilaiCelahKuku, nilaiDubur);
                list.add(sapi);
                adapter.notifyDataSetChanged();



                break;

            case R.id.btnCancel:
                alertDialog.cancel();
                break;

        }
    }

    @Override
    public void onRFACItemLabelClick(int position, RFACLabelItem item) {
        rfabHelper.toggleContent();
    }

    @Override
    public void onRFACItemIconClick(int position, RFACLabelItem item) {
        if (position == 0) {
            list.clear();
            Log.d("LIST", "" + list.size());
            adapter.notifyDataSetChanged();
        } else if (position == 1) {
            // Create a AlertDialog Builder.
            AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(mContext);
            // Set title, icon, can not cancel properties.
            alertDialogBuilder.setTitle("Tambah Data Sapi");
            alertDialogBuilder.setIcon(R.drawable.ic_launcher_background);
            alertDialogBuilder.setCancelable(false);

            // Init popup dialog view and it's ui controls.
            initPopupViewControls();

            // Set the inflated layout view object to the AlertDialog builder.
            alertDialogBuilder.setView(popupInputDialogView);

            // Create AlertDialog and show.
            alertDialog = alertDialogBuilder.create();
            alertDialog.show();
        } else if (position == 2) {

            List<Sapi> listSPK= new ArrayList<>();

            if (list.size() < 2) {
                Toast.makeText(mContext, "data kurang", Toast.LENGTH_SHORT).show();
            } else {
                int count = 0;
                for (Sapi sapi : list) {
                    if (sapi.isChecked()){
                        listSPK.add(sapi);
                        count++;
                    }
                }
                if (count > 1) {
                    dialogKeputusan(prosesModelSPK(listSPK));
                } else {
                    Toast.makeText(mContext, "data kurang", Toast.LENGTH_SHORT).show();
                }
            }
        }
        rfabHelper.toggleContent();
    }

    private void dialogKeputusan(String keputusan) {
        AlertDialog.Builder builder = new AlertDialog.Builder(mContext);
        builder.setTitle(R.string.dialog_title)
                .setMessage(keputusan)
                .setPositiveButton(R.string.simpan, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
//                                Context activityContext = MainActivity.getContextOfMainActivity();
//                                activityContext.getContentResolver().insert(CONTENT_URI, values);
                    }
                })
                .setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
        builder.create().show();

    }

    private String prosesModelSPK(List<Sapi> listSPK) {

        double[] nilaiHasil = new double[listSPK.size()];
        String[] nilaiIdentitas = new String[listSPK.size()];
        int index=0;

        double[] bobotKriteria = new double[]{0.20, 0.15, 0.15, 0.25, 0.05, 0.20};

        for (Sapi sapi : listSPK) {
            int nilaiAktivitas = sapi.getAktivitas();
            int nilaiBulu = sapi.getBulu();
            int nilaiMata = sapi.getMata();
            int nilaiMulut = sapi.getMulut();
            int nilaiCelahKuku = sapi.getCelah_kuku();
            int nilaiDubur = sapi.getDubur();

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


            int[] nilaiKriteria = new int[] { nilaiAktivitas, nilaiBulu, nilaiMata, nilaiMulut, nilaiCelahKuku, nilaiDubur};
            int max = nilaiKriteria[0];
            for(int i = 1; i < nilaiKriteria.length;i++) {
                if(nilaiKriteria[i] > max){
                    max = nilaiKriteria[i];
                }
            }
            Log.d("NILAI_MAX", ""+max);
            double[] nilai_normalisasi = new double[nilaiKriteria.length];
            for (int i = 0; i < nilaiKriteria.length; i++) {
                nilai_normalisasi[i] = (double) nilaiKriteria[i] / max;
                Log.d("NILAI_NORMALISASI"+i, ""+nilai_normalisasi[i]+"="+nilaiKriteria[i]+"/"+max);
            }

            nilaiHasil[index] = bobotKriteria[0] * nilai_normalisasi[0] +
                    bobotKriteria[1] * nilai_normalisasi[1] +
                    bobotKriteria[2] * nilai_normalisasi[2] +
                    bobotKriteria[3] * nilai_normalisasi[3] +
                    bobotKriteria[4] * nilai_normalisasi[4] +
                    bobotKriteria[5] * nilai_normalisasi[5];
            nilaiIdentitas[index] = sapi.getIdentitas();
            index++;
        }

        Sort sort = new Sort(nilaiHasil,nilaiIdentitas);
        nilaiHasil = sort.getTemp_array_num();
        nilaiIdentitas = sort.getTemp_array_label();
        String keputusan = "Tiga sapi terbaik :\n"+
                nilaiIdentitas[nilaiIdentitas.length-1]+"\n"+
                "dengan nilai : "+nilaiHasil[nilaiHasil.length-1]+"\n"+
                nilaiIdentitas[nilaiIdentitas.length-2]+"\n"+
                "dengan nilai : "+nilaiHasil[nilaiHasil.length-2]+"\n"+
                nilaiIdentitas[nilaiIdentitas.length-3]+"\n"+
                "dengan nilai : "+nilaiHasil[nilaiHasil.length-3];


        return keputusan;


    }

    private void initPopupViewControls() {
        // Get layout inflater object.
        LayoutInflater layoutInflater = LayoutInflater.from(mContext);

        // Inflate the popup dialog from a layout xml file.
        popupInputDialogView = layoutInflater.inflate(R.layout.popup_input_dialog, null);


        // Get user input edittext and button ui controls in the popup dialog.

        ETKode = popupInputDialogView.findViewById(R.id.editText);
        SpnAktivitas = popupInputDialogView.findViewById(R.id.spinner_act);
        adapterSpn = ArrayAdapter.createFromResource(mContext,
                R.array.list_aktivitas, android.R.layout.simple_spinner_item);
        adapterSpn.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        SpnAktivitas.setAdapter(adapterSpn);
        SpnBulu = popupInputDialogView.findViewById(R.id.spinner_bulu);
        adapterSpn = ArrayAdapter.createFromResource(mContext,
                R.array.list_bulu, android.R.layout.simple_spinner_item);
        adapterSpn.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        SpnBulu.setAdapter(adapterSpn);
        SpnCelahKuku = popupInputDialogView.findViewById(R.id.spinner_celah_kuku);
        adapterSpn = ArrayAdapter.createFromResource(mContext,
                R.array.list_celah_kuku, android.R.layout.simple_spinner_item);
        adapterSpn.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        SpnCelahKuku.setAdapter(adapterSpn);
        SpnDubur = popupInputDialogView.findViewById(R.id.spinner_dubur);
        adapterSpn = ArrayAdapter.createFromResource(mContext,
                R.array.list_dubur, android.R.layout.simple_spinner_item);
        adapterSpn.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        SpnDubur.setAdapter(adapterSpn);
        SpnMata = popupInputDialogView.findViewById(R.id.spinner_mata);
        adapterSpn = ArrayAdapter.createFromResource(mContext,
                R.array.list_mata, android.R.layout.simple_spinner_item);
        adapterSpn.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        SpnMata.setAdapter(adapterSpn);
        SpnMulut = popupInputDialogView.findViewById(R.id.spinner_mulut);
        adapterSpn = ArrayAdapter.createFromResource(mContext,
                R.array.list_mulut, android.R.layout.simple_spinner_item);
        adapterSpn.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        SpnMulut.setAdapter(adapterSpn);
        btnSimpan= popupInputDialogView.findViewById(R.id.btnSimpan);
        btnSimpan.setOnClickListener(this);
        btnCancel= popupInputDialogView.findViewById(R.id.btnCancel);
        btnCancel.setOnClickListener(this);
    }

    public void updateList() {

        adapter.notifyDataSetChanged();
    }
}
