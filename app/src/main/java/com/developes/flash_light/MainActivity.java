package com.developes.flash_light;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Camera;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.security.Permission;

public class MainActivity extends AppCompatActivity {

    private Button btn_switch;
    private android.hardware.Camera camera;
    private android.hardware.Camera.Parameters parameters;
    private boolean isFlashOn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btn_switch = (Button)findViewById(R.id.btnSwitch);

        if(checkReadPermisson()){

            functionMain();
        }
        else{

            startActivity(new Intent(this,Main2Activity.class));
        }

    }

    private void functionMain() {

        btn_switch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(isFlashOn){
                    turnOffFlash();
                }
                else {
                    turnOnFlash();
                }
            }
        });

        getCamera();
        toogleImage();

    }

    private void turnOnFlash() {

        if (!isFlashOn) {
            if (camera == null || parameters == null) {
                return;
            }

            parameters = camera.getParameters();
            parameters.setFlashMode(android.hardware.Camera.Parameters.FLASH_MODE_ON);
            camera.setParameters(parameters);
            camera.startPreview();
            isFlashOn = true;
            toogleImage();

        }
    }
    private void turnOffFlash() {

        if(isFlashOn){
            if(camera==null||parameters==null){
                return;
            }

            parameters=camera.getParameters();
            parameters.setFlashMode(android.hardware.Camera.Parameters.FLASH_MODE_OFF);
            camera.setParameters(parameters);
            camera.stopPreview();
            isFlashOn=false;
            toogleImage();

        }
    }

    private void getCamera(){
        if(camera==null){
            try{

                camera= android.hardware.Camera.open();
                parameters=camera.getParameters();

            }
            catch (RuntimeException e){}
        }
    }

    private void toogleImage(){

        if(isFlashOn){
            btn_switch.setBackgroundResource(R.drawable.onbutton);
        }
        else{
            btn_switch.setBackgroundResource(R.drawable.offbutton);
        }
    }


    private boolean checkReadPermisson(){

     int result = ActivityCompat.checkSelfPermission(getApplicationContext(),Manifest.permission.CAMERA);
     return result == PackageManager.PERMISSION_GRANTED;
    }

    private class Parameters {
    }
}
