package com.developes.flash_light;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import java.security.Permission;

public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        if(checkReadPermisson()){

        }
        else{

            startActivity(new Intent(this,Main2Activity.class));
        }

    }
    private boolean checkReadPermisson(){

     int result = ActivityCompat.checkSelfPermission(getApplicationContext(),Manifest.permission.CAMERA);
     return result == PackageManager.PERMISSION_GRANTED;
    }
}
