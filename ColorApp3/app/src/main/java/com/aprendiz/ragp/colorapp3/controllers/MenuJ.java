package com.aprendiz.ragp.colorapp3.controllers;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.aprendiz.ragp.colorapp3.R;

public class MenuJ extends AppCompatActivity {
    public static int guardar = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_j);
        guardar=0;
    }

    public void jugar(View view) {
        Intent intent = new Intent(MenuJ.this,Juego.class);
        guardar=1;
        startActivity(intent);
    }
}
