package com.aprendiz.ragp.colorapp3.controllers;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.aprendiz.ragp.colorapp3.R;
import com.aprendiz.ragp.colorapp3.models.GestorDB;
import com.aprendiz.ragp.colorapp3.models.Score;

import java.util.ArrayList;
import java.util.List;

public class Puntuacion extends AppCompatActivity {
    TextView txtPrimero, txtSegundo, txtTercero, txtCuarto;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_puntuacion);
        inizialite();
        inputData();
    }


    private void inizialite() {
        txtPrimero = findViewById(R.id.txtOro);
        txtSegundo = findViewById(R.id.txtPlata);
        txtTercero = findViewById(R.id.txtBronce);
        txtCuarto = findViewById(R.id.txtCuarto);
    }


    private void inputData() {
        GestorDB gestorDB = new GestorDB(this);
        List<Score> scores = gestorDB.listScore();

        if (scores.size()>0){
            txtPrimero.setText(scores.get(0).getPuntuacion()+"%");
        }else {
            Toast.makeText(this, "No hay puntuaciones disponibles.", Toast.LENGTH_SHORT).show();
        }

        if (scores.size()>1){
            txtSegundo.setText(scores.get(1).getPuntuacion()+"%");
        }


        if (scores.size()>2){
            txtTercero.setText(scores.get(2).getPuntuacion()+"%");
        }


        if (scores.size()>3){
            txtCuarto.setText(scores.get(3).getPuntuacion()+"%");
        }

    }

    public void salir(View view) {
        finish();
    }
}
