package com.aprendiz.ragp.colorapp3.controllers;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.aprendiz.ragp.colorapp3.R;

public class Resumen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_resumen);



    }

    public void salir(View view) {
        finish();
    }

    public void twi(View view) {

        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.setType("text/plain");
        intent.putExtra(Intent.EXTRA_TEXT, "");
        intent.setPackage("com.twitter.android");

        try {
            startActivity(intent);

        }catch (Exception e){
            Toast.makeText(this, "No cuentas con está aplicación", Toast.LENGTH_SHORT).show();
            e.printStackTrace();
        }
    }

    public void face(View view) {

        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.setType("text/plain");
        intent.putExtra(Intent.EXTRA_TEXT, "");
        intent.setPackage("com.facebook.katana");
        startActivity(intent);
    }
}
