package com.example.realnapster.contactapp;

import android.app.LoaderManager;

import android.content.CursorLoader;
import android.content.Loader;
import android.database.Cursor;
import android.net.Uri;
import android.os.PersistableBundle;
import android.provider.ContactsContract;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;


import android.widget.Button;
import java.util.ArrayList;
import java.util.List;



public class MainActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<Cursor> {
    static final  int LOADER_ID = 1;

    RecyclerView recyclerView;
    List<ContactData> contactList;
    MyAdapter myAdapter;
    Button button;
    Uri uri = ContactsContract.CommonDataKinds.Phone.CONTENT_URI;
    String[] projection = {ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME,ContactsContract.CommonDataKinds.Phone.NUMBER};
    String selection = null;
    String[] selectionArgs = null;
    String sortOrder = null;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        button = findViewById(R.id.button);
        button.setOnClickListener(clickListener);

    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        button.setVisibility(View.GONE);
        getLoaderManager().initLoader(LOADER_ID,null,MainActivity.this);
    }


    private View.OnClickListener clickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            loadClicked();
        }
    };

    private void loadClicked(){
        button.setVisibility(View.GONE);
        getLoaderManager().initLoader(LOADER_ID,null,MainActivity.this);
    }


    private List<ContactData> fetchContacts(Cursor cursor){
        List<ContactData> contacts = new ArrayList<>();

            while (cursor.moveToNext()) {
                String name = cursor.getString(cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME));
                String number = cursor.getString(cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER));
                contacts.add(new ContactData(name,number));
                Log.e("Contact", "Name: " + name + "Number: " + number);
            }
        return contacts;

    }

    @Override
    public Loader<Cursor> onCreateLoader(int id, Bundle args) {
        if(id==LOADER_ID)
            return new CursorLoader(this,uri,projection,selection,selectionArgs,sortOrder);
        return  null;
    }

    @Override
    public void onLoadFinished(Loader<Cursor> loader, Cursor data) {
        recyclerView = findViewById(R.id.recyclerView);
        contactList = fetchContacts(data);
        LinearLayoutManager linearLayoutManager= new LinearLayoutManager(this);
        recyclerView.setVisibility(View.VISIBLE);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setHasFixedSize(true);
        myAdapter = new MyAdapter(contactList);
        recyclerView.setAdapter(myAdapter);
        getLoaderManager().destroyLoader(LOADER_ID);
    }

    @Override
    public void onLoaderReset(Loader<Cursor> loader) {

    }

    @Override
    public void onSaveInstanceState(Bundle outState, PersistableBundle outPersistentState) {
        super.onSaveInstanceState(outState, outPersistentState);
    }
}
