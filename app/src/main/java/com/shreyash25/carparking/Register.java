package com.shreyash25.carparking;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthUserCollisionException;

public class Register extends AppCompatActivity implements View.OnClickListener{
    private Button lnmain;
    private FirebaseAuth mAuth;
    EditText reg_name,reg_email,reg_phone,reg_pass;

    //private DatabaseReference mdatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        reg_name=(EditText)findViewById(R.id.text1);
        reg_email=(EditText)findViewById(R.id.text2);
        reg_phone=(EditText)findViewById(R.id.text3);
        reg_pass=(EditText)findViewById(R.id.text4);
        mAuth = FirebaseAuth.getInstance();
        findViewById(R.id.Regcom).setOnClickListener(this);
        findViewById(R.id.Logcom).setOnClickListener(this);
    }
    /*public void registe(View view)
    {
         reg_name=(EditText)findViewById(R.id.text1);
         reg_email=(EditText)findViewById(R.id.text2);
         reg_phone=(EditText)findViewById(R.id.text3);
         reg_pass=(EditText)findViewById(R.id.text4);
        final String name=reg_name.getText().toString().trim();
        final String email=reg_email.getText().toString().trim();
        final String phone = reg_phone.getText().toString().trim();
        final String password = reg_pass.getText().toString().trim();
        if(TextUtils.isEmpty(email)||TextUtils.isEmpty(name)||TextUtils.isEmpty(phone)||TextUtils.isEmpty(password))
        {
            Toast.makeText(this,"Enter All details",Toast.LENGTH_LONG).show();
            return;
        }
        launchActivity();
    }

    private  void launchActivity()
    {
        Toast.makeText(getApplicationContext(),"Details Entered Successfully", Toast.LENGTH_LONG ).show();
        Intent intent2 =new Intent(this,Parking.class);
        startActivity(intent2);
    }*/

    private void registerUser()
    {

        final String name=reg_name.getText().toString().trim();
        final String email=reg_email.getText().toString().trim();
        final String phone = reg_phone.getText().toString().trim();
        final String password = reg_pass.getText().toString().trim();
        if(TextUtils.isEmpty(email)||TextUtils.isEmpty(name)||TextUtils.isEmpty(phone)||TextUtils.isEmpty(password))
        {
            Toast.makeText(this,"Enter All details",Toast.LENGTH_LONG).show();
            return;
        }
        if(!Patterns.EMAIL_ADDRESS.matcher(email).matches())
        {
            reg_email.setError("Enter A Valid E-mail");
            reg_email.requestFocus();
            return;
        }
        if(password.length()<6)
        {
            reg_pass.setError("Minimum Length of Password Should be 6");
            reg_pass.requestFocus();
            return;
        }

        mAuth.createUserWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {

                if(task.isSuccessful()){
                    finish();
                    Toast.makeText(getApplicationContext(),"Welcome to our database",Toast.LENGTH_LONG).show();
                    Intent intent=new Intent(Register.this,Parking.class);
                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    startActivity(intent);
                }
                else{
                    //Toast.makeText(getApplicationContext(),"Some Error Occured",Toast.LENGTH_LONG).show();
                    if(task.getException() instanceof FirebaseAuthUserCollisionException){
                        Toast.makeText(getApplicationContext(),"You are Already Registered",Toast.LENGTH_LONG).show();
                    }
                    else{
                        Toast.makeText(getApplicationContext(),task.getException().getMessage(),Toast.LENGTH_LONG).show();
                    }
                }
            }
        });
    }
    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.Regcom:
                registerUser();
                break;
            case R.id.Logcom:
                finish();
                startActivity(new Intent(this,MainActivity.class));
                break;
        }
    }
}
