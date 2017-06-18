package com.example.abhi.omconstruction;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

/**
 * Created by abhi on 18/6/17.
 */

public class Login extends AppCompatActivity {
    private EditText ePass,eEmail;
    private Button login;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_layout);
        ePass =(EditText)findViewById(R.id.editTextPass);
        eEmail =(EditText)findViewById(R.id.editTextEmail);
        login=(Button)findViewById(R.id.buttonLogin);
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(eEmail.getText().toString().equals("") && ePass.getText().toString().equals(""))
                {
                    Toast.makeText(getApplicationContext(),"Sign in ...",Toast.LENGTH_SHORT).show();
                    Intent in=new Intent(getApplicationContext(),MainActivity.class);
                    startActivity(in);
                }
                else{
                    Intent in=new Intent(getApplicationContext(),Interruption.class);
                    startActivity(in);
                }
            }
        });

    }
}
