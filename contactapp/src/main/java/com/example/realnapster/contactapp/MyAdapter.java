package com.example.realnapster.contactapp;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;


import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Realnapster on 3/3/2018.
 */

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MainHolder> {

    private List<ContactData> contacts = Collections.emptyList();


    // MyAdapter Constructor to initialize the Contact Data

    public MyAdapter(List<ContactData> contacts) {

        this.contacts = contacts;
    }

    @Override
    public MainHolder onCreateViewHolder(ViewGroup parent, int viewType) {
       View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.custom_row, parent, false);
        return new MainHolder(view);
    }

    @Override
    public void onBindViewHolder(MainHolder holder, int position) {
        final ContactData contactData = contacts.get(position);
        holder.contactName.setText(contactData.getName());
        holder.number.setText(contactData.getNumber());
    }

    @Override
    public int getItemCount() {
        return contacts.size() ;
    }

    public class MainHolder extends RecyclerView.ViewHolder{

        @BindView(R.id.contactName)
        TextView contactName;

        @BindView(R.id.number)
        TextView number;


        public MainHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }
    }
}
