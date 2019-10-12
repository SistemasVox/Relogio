package com.example.relogio;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.view.TextureView;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Date;

public class MainActivity extends AppCompatActivity {

    private TextView relVie, relTU;
    long ss = 0;
    private boolean continuar = true;
    private Handler handler = new Handler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        relVie = (TextView) findViewById(R.id.txtRel);
        relTU = (TextView) findViewById(R.id.txtTU);
        attTU();
    }

    public void agora(View v) {
        //System.out.println(new SimpleDateFormat("HH:mm:ss").format(new Date()));
        relVie.setText(new SimpleDateFormat("HH:mm:ss").format(new Date()));
        //Toast.makeText(this, "Oi", Toast.LENGTH_SHORT).show();
    }

    public void attTU() {

        new Thread() {
            public void run() {
                while (true) {
                    handler.post(new Runnable() {
                        @Override
                        public void run() {
                            relTU.setText("Tempo de Uso: " + zero(ss / 3600) + ":" + zero(ss / 60) + ":" + zero(ss % 60)+".");
                            ss ++;
                        }
                    });
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }.start();
    }

    public String zero(Long l) {
        if (l < 10) {
            return "0" + String.valueOf(l);
        } else {
            return String.valueOf(l);
        }
    }

    public void oi(View v) {
        //System.out.println(new SimpleDateFormat("HH:mm:ss").format(new Date()));
        //relVie.setText(new SimpleDateFormat("HH:mm:ss").format(new Date()));
        Toast.makeText(this, "Parando...", Toast.LENGTH_SHORT).show();
        continuar = false;
    }

    public void attThread(final View v) {
        continuar = true;
        new Thread() {
            public void run() {
                while (continuar) {
                    handler.post(new Runnable() {
                        @Override
                        public void run() {
                            agora(v);
                        }
                    });
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }.start();
    }
}
