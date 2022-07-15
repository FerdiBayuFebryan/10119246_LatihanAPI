package com.programmer.a10119246_latihanapi;
//NIM : 10119246
//NAMA : FERDI BAYU FEBRYAN
//KELAS : IF6
//TANGGAL PENGERJAAN : 15-07-2022
import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        Thread thread = new Thread() {
            public void run(){

                try
                {
                    sleep(2000);

                }
                catch (Exception e)
                {
                    e.printStackTrace();
                }
                finally
                {
                    Intent intent = new Intent(SplashActivity.this, MainActivity.class);
                    startActivity(intent);

                }

            }
        }; thread.start();
    }
}