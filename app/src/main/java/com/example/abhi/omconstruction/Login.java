package com.example.abhi.omconstruction;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.text.method.PasswordTransformationMethod;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
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
       final  CheckBox mCheckBox = (CheckBox) findViewById(R.id.checkBox);
        mCheckBox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mCheckBox.isChecked()){
                    ePass.setTransformationMethod(null);
                }else{
                    ePass.setTransformationMethod(new PasswordTransformationMethod());
                }
            }
        });


        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(eEmail.getText().toString().equals("ankit@omc.com") && ePass.getText().toString().equals("Bittu@123"))
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
    @Override
    public void onBackPressed() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);

        builder.setTitle("Authentication !");
        builder.setMessage("Log in or Exit");
        builder.setNegativeButton("Exit", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                finish();
            }
        });
        builder.setPositiveButton("Log in", null)
                .show();
    }
}
