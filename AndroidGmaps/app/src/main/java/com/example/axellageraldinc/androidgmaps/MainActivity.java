package com.example.axellageraldinc.androidgmaps;

import android.Manifest;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import com.tbruyelle.rxpermissions.RxPermissions;

import butterknife.ButterKnife;
import butterknife.OnClick;
import rx.functions.Action1;

public class MainActivity extends AppCompatActivity {
    RxPermissions rxPermissions;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        rxPermissions = new RxPermissions(this);
    }

    @OnClick(R.id.bt_plainmap)
    public void loadPlainMap(){
        //Ask permission for android M and above
        rxPermissions
                .request(Manifest.permission.INTERNET,
                        Manifest.permission.ACCESS_COARSE_LOCATION,
                        Manifest.permission.ACCESS_FINE_LOCATION)
                .subscribe(new Action1<Boolean>() {
                    @Override
                    public void call(Boolean result) {
                        //Permission is granted
                        if(result){
                            startActivity(new Intent(MainActivity.this, PlainMapActivity.class));
                        }
                        //Permission not granted
                        else{
                            Toast.makeText(
                                    MainActivity.this
                                    , "You must allow location access permission"
                                    , Toast.LENGTH_SHORT)
                                    .show();
                        }
                    }
                });

    }
}
