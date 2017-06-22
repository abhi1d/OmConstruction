package com.example.abhi.omconstruction;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

/**
 * Created by abhi on 20/6/17.
 */

public class InterStage extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.intermediate_layout);
        final ListView lv = (ListView)findViewById(R.id.month_list_view);

        String []months = new String[]{"none","JANUARY","FEBRUARY","MARCH","APRIL","MAY","JUNE","JULY","AUGUST","SEPTEMBER","OCTOBER","NOVEMBER","DECEMBER",

        };
        // Binding resources Array to ListAdapter
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1,months);
        lv.setAdapter(adapter);
        // listening to single list item on click
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                // When clicked, show a toast with the TextView text
                int pos = parent.getPositionForView(view);
                // Toast.makeText(getApplicationContext(),pos + "",Toast.LENGTH_SHORT).show();
                Intent in=new Intent(getApplicationContext(),productDetails.class);
                in.putExtra("month",pos);
                startActivity(in);

            }
        });
    }
}