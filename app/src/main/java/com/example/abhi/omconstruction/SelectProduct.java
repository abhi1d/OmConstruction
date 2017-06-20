package com.example.abhi.omconstruction;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

/**
 * Created by abhi on 12/5/17.
 */

public class SelectProduct extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.select_product);
        final Button go=(Button)findViewById(R.id.button_go);

        Spinner staticSpinner = (Spinner) findViewById(R.id.spinner_select_product);

        // Create an ArrayAdapter using the string array and a default spinner
        ArrayAdapter<CharSequence> staticAdapter = ArrayAdapter.createFromResource(this, R.array.month,
                android.R.layout.simple_spinner_item);

        // Specify the layout to use when the list of choices appears
        staticAdapter
                .setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        // Apply the adapter to the spinner
        staticSpinner.setAdapter(staticAdapter);


        staticSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view,
                                       int position, long id) {
                final int hold=parent.getSelectedItemPosition();
                for(int i=0;i<=13;i++)
                {
                    if(hold==0)
                    {
                        Snackbar.make(view,"Please select any month",Snackbar.LENGTH_INDEFINITE).show();
                    }

                    if(hold==i)
                    {
                        go.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view1) {
                                button(hold);
                            }
                        });
                        break;

                    }

                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // TODO Auto-generated method stub
            }
        });

    }
    public void button(int month)
    {

        Intent intent=new Intent(this,productDetails.class);
        intent.putExtra("month",month);
        startActivity(intent);


    }
}