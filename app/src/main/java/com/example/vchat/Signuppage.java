package com.example.vchat;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.vchat.Models.Users;
import com.example.vchat.databinding.ActivitySignuppageBinding;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.Firebase;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;

public class Signuppage extends AppCompatActivity {

    ActivitySignuppageBinding binding;
    private FirebaseAuth mAuth;
    FirebaseDatabase database;
    ProgressDialog progressDialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding=ActivitySignuppageBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        mAuth = FirebaseAuth.getInstance();
        database=FirebaseDatabase.getInstance();
        getSupportActionBar();

        progressDialog=new ProgressDialog(Signuppage.this);
        progressDialog.setTitle("Creating Account");
        progressDialog.setMessage("We are Creating Your Account");

        binding.btnSignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                progressDialog.show();
                if(!binding.txtUsername.getText().toString().isEmpty() &&!binding.txtEmail.getText().toString().isEmpty() && !binding.txtPassword.getText().toString().isEmpty()){
                    mAuth.createUserWithEmailAndPassword(binding.txtEmail.getText().toString(),binding.txtPassword.getText().toString()).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            progressDialog.dismiss();
                            if(task.isSuccessful()){
                                Users user= new Users(binding.txtUsername.getText().toString(),binding.txtEmail.getText().toString(),binding.txtPassword.getText().toString());
                                Toast.makeText(Signuppage.this, "Sign Up Sucessful", Toast.LENGTH_SHORT).show();
                                String id=task.getResult().getUser().getUid();
                                database.getReference().child("Users").child(id).setValue(user);
                            }
                            else{
                                Toast.makeText(Signuppage.this,task.getException().toString(), Toast.LENGTH_SHORT).show();
                            }
                        }
                    });

                    }
                else{
                    Toast.makeText(Signuppage.this, "Enter Credentials", Toast.LENGTH_SHORT).show();
                }
            }
        });
        binding.txtAlready.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent( Signuppage.this, signinpage.class);
                startActivity(intent);
            }
        });
    }
}
