package com.aprendiz.ragp.colorapp3.models;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

public class GestorDB extends SQLiteOpenHelper{
    public GestorDB(Context context) {
        super(context, "score.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE SCORE (PUNTUACION INTEGER, INCORRECTAS INTEGER);");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public void inputData(Score score){
        SQLiteDatabase db = getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("PUNTUACION",score.getPuntuacion());
        values.put("INCORRECTAS",score.getIncorrectas());
        db.insert("SCORE",null,values);
        db.close();

    }

    public List<Score> listScore(){
        List<Score> results = new ArrayList<>();
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM SCORE ORDER BY PUNTACION ASC, INCORRECTAS ASC;",null);
        if (cursor.moveToFirst()){
            do {
                Score score = new Score();

                score.setPuntuacion(cursor.getInt(0));
                score.setIncorrectas(cursor.getInt(1));

                results.add(score);
            }while (cursor.moveToNext());
        }

        cursor.close();
        db.close();


        return results;
    }
}
