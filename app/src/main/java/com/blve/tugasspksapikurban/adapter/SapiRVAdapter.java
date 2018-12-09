package com.blve.tugasspksapikurban.adapter;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.ContentValues;
import android.database.Cursor;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.blve.tugasspksapikurban.MainActivity;
import com.blve.tugasspksapikurban.R;
import com.blve.tugasspksapikurban.model.Sapi;
import com.blve.tugasspksapikurban.util.CustomOnItemClickListener;

import java.util.List;

import static com.blve.tugasspksapikurban.db.DBContract.TableColumns.AKTIVITAS;
import static com.blve.tugasspksapikurban.db.DBContract.TableColumns.BULU;
import static com.blve.tugasspksapikurban.db.DBContract.TableColumns.CELAH_KUKU;
import static com.blve.tugasspksapikurban.db.DBContract.TableColumns.DUBUR;
import static com.blve.tugasspksapikurban.db.DBContract.TableColumns.IDENTITAS;
import static com.blve.tugasspksapikurban.db.DBContract.TableColumns.MATA;
import static com.blve.tugasspksapikurban.db.DBContract.TableColumns.MULUT;

public class SapiRVAdapter extends RecyclerView.Adapter<SapiRVAdapter.SapiViewholder> implements View.OnClickListener {
    private List<Sapi> listSapi;
    private Activity activity;

    EditText ETKode;
    Spinner SpnAktivitas, SpnBulu, SpnMata, SpnMulut, SpnCelahKuku, SpnDubur;
    ArrayAdapter<CharSequence> adapterSpn;
    Button btnSimpan, btnCancel;
    AlertDialog alertDialog=null;
    private View popupInputDialogView = null;


    public SapiRVAdapter(Activity activity) {
        this.activity = activity;
    }

    public List<Sapi> getListSapi() {
        return listSapi;
    }

    public void setListSapi(List<Sapi> listSapi) {
        this.listSapi = listSapi;
    }

    @Override
    public SapiViewholder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_sapi, parent, false);
        return new SapiViewholder(view);
    }

    @Override
    public void onBindViewHolder(final SapiViewholder holder, final int position) {

        final Sapi sapi = getItem(position);
        holder.tvIdentitas.setText(sapi.getIdentitas());
        if (sapi.isChecked()) {
            holder.checkBox.setChecked(true);
        } else {
            holder.checkBox.setChecked(false);
        }


        holder.cvPotensi.setOnClickListener(new CustomOnItemClickListener(position, new CustomOnItemClickListener.OnItemClickCallback() {
            @Override
            public void onItemClicked(View view, int position) {
                AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(activity);
                // Set title, icon, can not cancel properties.
                alertDialogBuilder.setTitle("Edit Data Sapi");
                alertDialogBuilder.setIcon(R.drawable.ic_launcher_background);
                alertDialogBuilder.setCancelable(false);

                // Init popup dialog view and it's ui controls.
                initPopupViewControls();
                initValues(sapi);

                // Set the inflated layout view object to the AlertDialog builder.
                alertDialogBuilder.setView(popupInputDialogView);

                // Create AlertDialog and show.
                alertDialog = alertDialogBuilder.create();
                alertDialog.show();
                btnSimpan.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {


                        sapi.setIdentitas(ETKode.getText().toString().trim());
                        sapi.setAktivitas(SpnAktivitas.getSelectedItemPosition());
                        sapi.setBulu(SpnBulu.getSelectedItemPosition());
                        sapi.setMata(SpnMata.getSelectedItemPosition());
                        sapi.setMulut(SpnMulut.getSelectedItemPosition());
                        sapi.setCelah_kuku(SpnCelahKuku.getSelectedItemPosition());
                        sapi.setDubur(SpnDubur.getSelectedItemPosition());
                        notifyDataSetChanged();



                    }
                });
            }
        }));

        holder.checkBox.setOnClickListener(new CustomOnItemClickListener(position, new CustomOnItemClickListener.OnItemClickCallback() {
            @Override
            public void onItemClicked(View view, int position) {
                if (sapi.isChecked()) {
                    sapi.setChecked(false);
                    holder.checkBox.setChecked(false);
                } else {
                    sapi.setChecked(true);
                    holder.checkBox.setChecked(true);
                }
            }
        }));

        holder.imageButtonDel.setOnClickListener(new CustomOnItemClickListener(position, new CustomOnItemClickListener.OnItemClickCallback() {
            @Override
            public void onItemClicked(View view, int position) {
                listSapi.remove(position);
                notifyDataSetChanged();
            }
        }));

    }

    private void initValues(Sapi sapi) {
        ETKode.setText(sapi.getIdentitas());
        SpnAktivitas.setSelection(sapi.getAktivitas());
        SpnBulu.setSelection(sapi.getBulu());
        SpnMata.setSelection(sapi.getMata());
        SpnMulut.setSelection(sapi.getMulut());
        SpnCelahKuku.setSelection(sapi.getCelah_kuku());
        SpnDubur.setSelection(sapi.getDubur());
    }

    @Override
    public int getItemCount() {
        if (listSapi == null) return 0;
        return listSapi.size();
    }

    private Sapi getItem(int position) {
        return listSapi.get(position);
    }

    private void initPopupViewControls() {
        // Get layout inflater object.
        LayoutInflater layoutInflater = LayoutInflater.from(activity);

        // Inflate the popup dialog from a layout xml file.
        popupInputDialogView = layoutInflater.inflate(R.layout.popup_input_dialog, null);


        // Get user input edittext and button ui controls in the popup dialog.

        ETKode = popupInputDialogView.findViewById(R.id.editText);
        SpnAktivitas = popupInputDialogView.findViewById(R.id.spinner_act);
        adapterSpn = ArrayAdapter.createFromResource(activity,
                R.array.list_aktivitas, android.R.layout.simple_spinner_item);
        adapterSpn.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        SpnAktivitas.setAdapter(adapterSpn);
        SpnBulu = popupInputDialogView.findViewById(R.id.spinner_bulu);
        adapterSpn = ArrayAdapter.createFromResource(activity,
                R.array.list_bulu, android.R.layout.simple_spinner_item);
        adapterSpn.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        SpnBulu.setAdapter(adapterSpn);
        SpnCelahKuku = popupInputDialogView.findViewById(R.id.spinner_celah_kuku);
        adapterSpn = ArrayAdapter.createFromResource(activity,
                R.array.list_celah_kuku, android.R.layout.simple_spinner_item);
        adapterSpn.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        SpnCelahKuku.setAdapter(adapterSpn);
        SpnDubur = popupInputDialogView.findViewById(R.id.spinner_dubur);
        adapterSpn = ArrayAdapter.createFromResource(activity,
                R.array.list_dubur, android.R.layout.simple_spinner_item);
        adapterSpn.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        SpnDubur.setAdapter(adapterSpn);
        SpnMata = popupInputDialogView.findViewById(R.id.spinner_mata);
        adapterSpn = ArrayAdapter.createFromResource(activity,
                R.array.list_mata, android.R.layout.simple_spinner_item);
        adapterSpn.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        SpnMata.setAdapter(adapterSpn);
        SpnMulut = popupInputDialogView.findViewById(R.id.spinner_mulut);
        adapterSpn = ArrayAdapter.createFromResource(activity,
                R.array.list_mulut, android.R.layout.simple_spinner_item);
        adapterSpn.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        SpnMulut.setAdapter(adapterSpn);
        btnSimpan= popupInputDialogView.findViewById(R.id.btnSimpan);
        btnSimpan.setOnClickListener(this);
        btnCancel= popupInputDialogView.findViewById(R.id.btnCancel);
        btnCancel.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {

            case R.id.btnCancel:
                alertDialog.cancel();
                break;


        }
    }

    public class SapiViewholder extends RecyclerView.ViewHolder {
        TextView tvIdentitas;
        CardView cvPotensi;
        CheckBox checkBox;
        ImageButton imageButtonDel;

        public SapiViewholder(View itemView) {
            super(itemView);
            tvIdentitas = (TextView) itemView.findViewById(R.id.tv_item_identitas);
            cvPotensi = (CardView) itemView.findViewById(R.id.cv_item_potensi);
            checkBox= itemView.findViewById(R.id.checkBox);
            imageButtonDel = itemView.findViewById(R.id.rv_btn_del);
        }
    }
}