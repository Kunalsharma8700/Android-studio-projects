package com.example.vchat;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.navigation.ui.AppBarConfiguration;

import com.example.vchat.Adapter.FragmentAdapter;
import com.example.vchat.databinding.ActivityMain2Binding;
import com.example.vchat.databinding.ActivitySigninpageBinding;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;

public class MainActivity2 extends AppCompatActivity {
ActivityMain2Binding binding;

    private AppBarConfiguration mAppBarConfiguration;
FirebaseAuth mAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

      binding = ActivityMain2Binding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());


        mAuth= FirebaseAuth.getInstance();

        binding.viewPager.setAdapter(new FragmentAdapter(getSupportFragmentManager()));
        binding.tabLayout.setupWithViewPager(binding.viewPager);

    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater=getMenuInflater();
        inflater.inflate(R.menu.menu,menu);
        return super.onCreateOptionsMenu(menu);
    }
@Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item){
        int itemId=item.getItemId();
        if(itemId== R.id.settings){
            Toast.makeText(this, "Settings", Toast.LENGTH_SHORT).show();
        }else if(itemId== R.id.groupChat){
            Toast.makeText(this, "Starting Group Chat", Toast.LENGTH_SHORT).show();
        }else if(itemId==R.id.logout) {
            mAuth.signOut();
            Intent intent=new Intent(MainActivity2.this, signinpage.class);
        startActivity(intent);
        }

        return super.onOptionsItemSelected(item);
    }
}