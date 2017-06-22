package com.example.abhi.omconstruction;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

public class productDetails extends AppCompatActivity{
    @Override
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.product_details);
        Intent in=getIntent();
        final int month = in.getIntExtra("month",0);
        // storing string resources into Array
        String[] prdts = new String[]{
                "(none)","Lafarge","Birla Gold","Shree","Mahashakti","Konark","Acc"
        };
        final ListView lv = (ListView)findViewById(R.id.product_list_view);

        // Binding resources Array to ListAdapter
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1,prdts);
        lv.setAdapter(adapter);
        // listening to single list item on click
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                // When clicked, show a toast with the TextView text
                String data=(String) (lv.getItemAtPosition(position));
                Intent in=new Intent(getApplicationContext(),SingleItemSelected.class);
                in.putExtra("product",data);
                in.putExtra("month",month);
                startActivity(in);

            }
        });
    }
}