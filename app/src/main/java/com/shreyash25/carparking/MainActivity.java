package com.shreyash25.carparking;

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
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity  implements View.OnClickListener{
    private Button login,register;
    FirebaseAuth mAuth;
    EditText reg_email,reg_pass;
    /*public void enter(View view)
    {
        EditText Text1=(EditText)findViewById(R.id.Text1);
        EditText Text3=(EditText)findViewById(R.id.Text3);
        Log.i("ID Entered: " ,Text1.getText().toString());
        Log.i("Password Entered: ",Text3.getText().toString());

        validate(Text1.getText().toString().trim(),Text3.getText().toString().trim());

    }
    private void validate(String name,String password)
    {
       // Toast.makeText(getApplicationContext(),"ID Entered Successfully", Toast.LENGTH_LONG ).show();
        if((name.compareTo("Shreyash")==0||name.compareTo("shreyashtalwekar649@gmail.com")==0)&&(password.compareTo("abcd")==0))
        {
            Toast.makeText(getApplicationContext(),"ID Entered Successfully", Toast.LENGTH_LONG ).show();
            Intent intent=new Intent( this,Parking.class);
            startActivity(intent);
        }
    }

    public void click(View view)
    {
        Intent intent1=new Intent(this,Register.class);
        startActivity(intent1);
    }*/

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        reg_email=(EditText)findViewById(R.id.Text1);
        reg_pass=(EditText)findViewById(R.id.Text3);
        mAuth=FirebaseAuth.getInstance();
        findViewById(R.id.Regbut).setOnClickListener(this);
        findViewById(R.id.Logbut).setOnClickListener(this);
    }

    private void userLogin(){

        final String email=reg_email.getText().toString().trim();
        final String password = reg_pass.getText().toString().trim();
        if(TextUtils.isEmpty(email)||TextUtils.isEmpty(password))
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

        mAuth.signInWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {

               if(task.isSuccessful()){
                    finish();
                    Intent intent=new Intent(MainActivity.this,Parking.class);
                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    startActivity(intent);
               }
               else{
                   Toast.makeText(getApplicationContext(),task.getException().getMessage(),Toast.LENGTH_LONG).show();
               }
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        if(mAuth.getCurrentUser()!=null)
        {
            finish();
            Intent i=new Intent(this,Parking.class);
        }
    }

    @Override
    public void onClick(View view) {
        switch (view.getId())
        {
            case R.id.Regbut:
                finish();
                startActivity(new Intent(this,Register.class));
                break;
            case R.id.Logbut:
              userLogin();
              break;
        }
    }
}
