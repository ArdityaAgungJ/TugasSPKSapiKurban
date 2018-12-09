package com.blve.tugasspksapikurban.adapter;

import android.app.Activity;
import android.database.Cursor;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.blve.tugasspksapikurban.R;
import com.blve.tugasspksapikurban.model.Sapi;
import com.blve.tugasspksapikurban.util.CustomOnItemClickListener;

public class PotensiAdapter extends RecyclerView.Adapter<PotensiAdapter.PotensiViewholder> {
    private Cursor listPotensi;
    private Activity activity;

    public PotensiAdapter(Activity activity) {
        this.activity = activity;
    }

    public void setListPotensi(Cursor cursor) {
        this.listPotensi=cursor;
    }

    @Override
    public PotensiViewholder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_sapi, parent, false);
        return new PotensiViewholder(view);
    }

    @Override
    public void onBindViewHolder(PotensiViewholder holder, int position) {

        final Sapi sapi = getItem(position);
        holder.tvIdentitas.setText(sapi.getIdentitas());
        holder.cvPotensi.setOnClickListener(new CustomOnItemClickListener(position, new CustomOnItemClickListener.OnItemClickCallback() {
            @Override
            public void onItemClicked(View view, int position) {
//                Intent intent = new Intent(activity, FormAddUpdateActivity.class);
//                Uri uri = Uri.parse(CONTENT_URI+"/"+sapi.getId());
//                intent.setData(uri);
//                activity.startActivityForResult(intent, FormAddUpdateActivity.REQUEST_UPDATE);
            }
        }));
    }

    @Override
    public int getItemCount() {
        if (listPotensi == null) return 0;
        return listPotensi.getCount();
    }

    private Sapi getItem(int position){
        if (!listPotensi.moveToPosition(position)) {
            throw new IllegalStateException("Position invalid");
        }
        return new Sapi(listPotensi);
    }

    public class PotensiViewholder extends RecyclerView.ViewHolder{
        TextView tvIdentitas;
        CardView cvPotensi;

        public PotensiViewholder(View itemView) {
            super(itemView);
            tvIdentitas = (TextView)itemView.findViewById(R.id.tv_item_identitas);
            cvPotensi = (CardView)itemView.findViewById(R.id.cv_item_potensi);
        }
    }
}
