package com.aprendiz.ragp.colorapp3.controllers;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.aprendiz.ragp.colorapp3.R;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class JuegoC extends AppCompatActivity implements View.OnClickListener{
    TextView txtCorrectas, txtIncorrectas, txtPorcentaje, txtTiempo, txtPalabra;
    ImageButton btnColor1, btnColor2, btnColor3, btnColor4;
    ProgressBar pbTiempo;
    public static int correctas, incorrectas, porcentaje, intentos;
    int []segundos={0,30};
    List<String> listaPalabra = new ArrayList<>();
    List<Integer> listaColores = new ArrayList<>();
    List<Integer> listaColoresTmp = new ArrayList<>();
    int ipR, icR;
    boolean bandera = true;
    int ab=0, valorcito=0, modo=0, tiempo;
    SharedPreferences juegoC;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_juego_c);
        inizialite();
        inputList();
        inputValues();
        inputData();
        randomizar();
        chronometer();

    }

    private void inizialite() {
        txtCorrectas = findViewById(R.id.txtCorrectas);
        txtIncorrectas = findViewById(R.id.txtIncorrectas);
        txtPorcentaje = findViewById(R.id.txtPorcentaje);
        txtTiempo = findViewById(R.id.txtTiempo);
        txtPalabra = findViewById(R.id.txtPalabra);
        pbTiempo = findViewById(R.id.progressBar);

        btnColor1 = findViewById(R.id.btnColor1);
        btnColor2 = findViewById(R.id.btnColor2);
        btnColor3 = findViewById(R.id.btnColor3);
        btnColor4 = findViewById(R.id.btnColor4);

        btnColor1.setOnClickListener(this);
        btnColor2.setOnClickListener(this);
        btnColor3.setOnClickListener(this);
        btnColor4.setOnClickListener(this);


    }


    private void inputList() {
        listaPalabra = new ArrayList<>();
        listaColores = new ArrayList<>();
        listaPalabra.add("AMARILLO");
        listaColores.add(getColor(R.color.colorAmarillo));
        listaPalabra.add("AZUL");
        listaColores.add(getColor(R.color.colorAzul));
        listaPalabra.add("ROJO");
        listaColores.add(getColor(R.color.colorRojo));
        listaPalabra.add("VERDE");
        listaColores.add(getColor(R.color.colorVerde));

    }

    private void inputValues() {
        correctas=0;
        incorrectas=0;
        porcentaje=0;
        intentos=0;
        juegoC = getSharedPreferences("juegoC",MODE_PRIVATE);
        modo = juegoC.getInt("modo",1);
        tiempo = juegoC.getInt("tiempo",3);
        if (modo==1){
            segundos= new int[]{0,30};
        }else {
            segundos= new int[]{0,0};
        }
        bandera=true;
        pbTiempo.setMax(segundos[1]);
        ab=0;
        valorcito=0;

    }


    private void inputData() {
        if (intentos>0){
            if (incorrectas>0) {
                float tmp1 = intentos, tmo2 = incorrectas;
                double tmpP = (incorrectas/intentos)*100;
                porcentaje= (int) tmpP;
            }else {
                porcentaje=100;
            }
        }else {
            if (incorrectas>0) {

                porcentaje=0;

            }else {
                porcentaje=100;
            }
        }


        txtCorrectas.setText(Integer.toString(correctas));
        txtIncorrectas.setText(Integer.toString(incorrectas));
        txtPorcentaje.setText(porcentaje+"%");


    }

    private void randomizar() {
        listaColoresTmp =listaColores;
        ipR = (int) (Math.random() *4);
        icR = (int) (Math.random() *4);
        Collections.shuffle(listaColoresTmp);

        txtPalabra.setText(listaPalabra.get(ipR));
        txtPalabra.setTextColor(listaColores.get(icR));

        btnColor1.setColorFilter(listaColoresTmp.get(0));
        btnColor2.setColorFilter(listaColoresTmp.get(1));
        btnColor3.setColorFilter(listaColoresTmp.get(2));
        btnColor4.setColorFilter(listaColoresTmp.get(3));

    }


    private void chronometer() {
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                while (bandera){
                    try {
                        Thread.sleep(1000);

                    }catch (Exception e){

                    }

                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            segundos[0]++;
                            if (modo==1) {
                                segundos[1]--;
                            }else {
                                segundos[1]++;
                            }
                            txtTiempo.setText("Tiempo "+segundos[1]);
                            pbTiempo.setProgress(segundos[1]);
                            if (segundos[0]==3){
                                segundos[0]=0;
                                intentos++;
                                incorrectas++;
                                randomizar();
                                inputData();

                            }

                            endGame();

                        }
                    });
                }



            }
        });
        thread.start();
    }

    private void endGame() {
        if (ab==0&& ( (modo==1 &&(segundos[1]==0|| incorrectas==3))) || (modo==2 && incorrectas==3) ){
            ab=1;
            Intent intent = new Intent(JuegoC.this,Resumen.class);
            startActivity(intent);
            finish();
            bandera=false;
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btnColor1:
                valorcito=1;
                validar();
                break;

            case R.id.btnColor2:
                valorcito=2;
                validar();
                break;

            case R.id.btnColor3:
                valorcito=3;
                validar();
                break;

            case R.id.btnColor4:
                valorcito=4;
                validar();
                break;
        }

    }

    private void validar() {
        if (valorcito==1){
            if (icR==0){
                correctas++;
            }else {
                incorrectas++;
            }
        }

        if (valorcito==2){
            if (icR==1){
                correctas++;
            }else {
                incorrectas++;
            }
        }


        if (valorcito==3){
            if (icR==2){
                correctas++;
            }else {
                incorrectas++;
            }
        }


        if (valorcito==4){
            if (icR==3){
                correctas++;
            }else {
                incorrectas++;
            }
        }
        intentos++;
        segundos[0]=0;
        randomizar();
        inputData();

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        bandera=false;
    }


}
