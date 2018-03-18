package com.example.shubhamdubey.capstonep;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import org.w3c.dom.Text;


public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private EditText signemail;
    private EditText signpassword;
    private Button button;
    private ProgressDialog progressDialog;
    private FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        firebaseAuth=FirebaseAuth.getInstance();

        progressDialog = new ProgressDialog(this);

        signemail = (EditText) findViewById(R.id.Text);
        signpassword = (EditText) findViewById(R.id.Text2);
        button = (Button) findViewById(R.id.bt);

        button.setOnClickListener(this);

    }
    public void registerUser(){
        String mail=signemail.getText().toString().trim();
        String pass=signpassword.getText().toString().trim();

        if(TextUtils.isEmpty(mail)){
        // when emailfield is empty
            Toast.makeText(this, "Please Enter Email", Toast.LENGTH_SHORT).show();
            return;
        }
        if(TextUtils.isEmpty(pass)){
            // when Password is empty
            Toast.makeText(this, "Password Field is empty ", Toast.LENGTH_SHORT).show();
            // stopping the function execution further
            return;
        }
        progressDialog.setMessage("Registering User...");
        progressDialog.show();

        firebaseAuth.createUserWithEmailAndPassword(mail,pass)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()){
                            // user login success full
                            progressDialog.dismiss(); //to dismiss the dialog message
                            Toast.makeText(MainActivity.this, "Registered", Toast.LENGTH_SHORT).show();
                    }
                    else {
                            progressDialog.dismiss();
                            Toast.makeText(MainActivity.this, "Could not Register", Toast.LENGTH_SHORT).show();
                        }

                    }
                });
    }
public void onClick(View view){
        if (view==button){
            registerUser();
        }

}



}
