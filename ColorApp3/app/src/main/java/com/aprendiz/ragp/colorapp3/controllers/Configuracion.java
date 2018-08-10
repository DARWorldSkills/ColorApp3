package com.aprendiz.ragp.colorapp3.controllers;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.RadioButton;
import android.widget.Toast;

import com.aprendiz.ragp.colorapp3.R;

public class Configuracion extends AppCompatActivity {
    RadioButton btnTiempo, btnIntentos;
    ImageButton btnJugar;
    EditText txtTiempoP;
    SharedPreferences juegoC;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_configuracion);
        juegoC = getSharedPreferences("juegoC",MODE_PRIVATE);
        inizialite();
        inputData();
        btnJugar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveData();
            }
        });
    }



    private void inputData() {
        int modo = juegoC.getInt("modo",1);
        int tiempo = juegoC.getInt("tiempo",3);

        if (modo==1){
            btnTiempo.setChecked(true);
        }else {
            btnIntentos.setChecked(true);
        }

        txtTiempoP.setText(Integer.toString(tiempo));


    }

    private void inizialite() {
        btnTiempo = findViewById(R.id.rbtnTiempo);
        btnIntentos = findViewById(R.id.rbtnIntentos);
        txtTiempoP = findViewById(R.id.txtTiempoP);
        btnJugar = findViewById(R.id.btnJugar);

    }

    private void saveData() {
        int modo=1;
        int tiempo=3;
        if (btnTiempo.isChecked()){
            modo=1;
        }

        if (btnIntentos.isChecked()){
            modo=2;
        }

        try {
            int tmp = Integer.parseInt(txtTiempoP.getText().toString());
            if (tiempo>0 && tiempo<11){
                tiempo=tmp;

            }else {
                tiempo = juegoC.getInt("tiempo",3);
                Toast.makeText(this, "El valor del tiempo tiene que ser mayor a 0 y menor a 11. \n No se guardará el tiempo ingresado.", Toast.LENGTH_SHORT).show();
            }


        }catch (Exception e){
            Toast.makeText(this, "Se han ingresado caracteres desconocidos. \n Se jugará de todas formas", Toast.LENGTH_SHORT).show();
        }


        SharedPreferences.Editor editor = juegoC.edit();
        editor.putInt("modo",modo);
        editor.putInt("tiempo",tiempo);
        editor.commit();
    }
}
