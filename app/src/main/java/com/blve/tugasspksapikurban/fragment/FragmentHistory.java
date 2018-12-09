package com.blve.tugasspksapikurban.fragment;


import android.content.Context;
import android.database.Cursor;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import com.blve.tugasspksapikurban.MainActivity;
import com.blve.tugasspksapikurban.R;
import com.blve.tugasspksapikurban.adapter.PotensiAdapter;

import static com.blve.tugasspksapikurban.db.DBContract.CONTENT_URI;

/**
 * A simple {@link Fragment} subclass.
 */
public class FragmentHistory extends Fragment {

    RecyclerView recyclerView;
    ProgressBar progressBar;
    PotensiAdapter adapter;

    private Cursor list;



    public FragmentHistory() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_fragment_history, container, false);
        recyclerView = view.findViewById(R.id.rv_history);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setHasFixedSize(true);
        progressBar = view.findViewById(R.id.progressBar);

        adapter = new PotensiAdapter(getActivity());
        adapter.setListPotensi(list);
        recyclerView.setAdapter(adapter);

        new LoadAsync().execute();

        return view;
    }

    private class LoadAsync extends AsyncTask<Void, Void, Cursor> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            progressBar.setVisibility(View.VISIBLE);

        }

        @Override
        protected Cursor doInBackground(Void... voids) {
            Context activityContext = MainActivity.getContextOfMainActivity();
            return activityContext.getContentResolver().query(CONTENT_URI,null,null,null,null);
        }

        @Override
        protected void onPostExecute(final Cursor list) {
            super.onPostExecute(list);
            adapter.setListPotensi(list);
            new CountDownTimer(1000, 1000) {
                public void onFinish() {
                    progressBar.setVisibility(View.GONE);
                    adapter.notifyDataSetChanged();
                    try {

                        if (list.getCount()==0) {
                            showSnackbarMessage("Tidak ada data saat ini");
                        }
                    } catch (Exception e) {
                    }

                }

                public void onTick(long millisUntilFinished) {
                }
            }.start();



        }

        private void showSnackbarMessage(String message){
            Snackbar.make(recyclerView, message, Snackbar.LENGTH_SHORT).show();
        }
    }
}
