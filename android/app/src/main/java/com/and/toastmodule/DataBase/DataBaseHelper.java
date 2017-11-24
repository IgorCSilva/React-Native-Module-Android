package com.and.toastmodule.DataBase;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

/**
 * Created by Administrador on 23/11/2017.
 */

public class DataBaseHelper extends SQLiteOpenHelper {

    // Nomes do banco de dados e da tabela.
    public static final String DATABASE_NAME = "Student_db";
    public static final String TABLE_NAME = "Student_table";

    // Definindo nome para as colunas.
    public static final String COL_1 = "ID";
    public static final String COL_2 = "NAME";
    public static final String COL_3 = "SURNAME";
    public static final String COL_4 = "MARKS";

    public DataBaseHelper(Context context){
        super(context, DATABASE_NAME, null, 1);

    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String query = "CREATE TABLE " + TABLE_NAME + "(ID INTEGER PRIMARY KEY AUTOINCREMENT, NAME TEXT, SURNAME TEXT, MARKS TEXT)";
        db.execSQL(query);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String query = "DROP TABLE IF EXISTS " + TABLE_NAME;
        db.execSQL(query);
    }

    public boolean insertData(String name, String surname, String marks){

        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues contentValues = new ContentValues();

        contentValues.put(COL_2, name);
        contentValues.put(COL_3, surname);
        contentValues.put(COL_4, marks);

        long result = db.insert(TABLE_NAME, null, contentValues);
        db.close();

        boolean insertSucess;

        if(result == -1){
            insertSucess = false;
        }else {
            insertSucess = true;

        }

        return insertSucess;
    }

    public Cursor getAllData(){

        SQLiteDatabase db = this.getWritableDatabase();

        Cursor res = db.rawQuery("Select * from " + TABLE_NAME, null);

        return res;
    }

    public boolean updateData(String id, String name, String surname, String marks){

        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues contentValues = new ContentValues();

        contentValues.put(COL_2, name);
        contentValues.put(COL_3, surname);
        contentValues.put(COL_4, marks);

        db.update(TABLE_NAME, contentValues, "ID=?", new String[]{id});
        return true;
    }

    public Integer deleteData(String id){
        SQLiteDatabase db = this.getWritableDatabase();

        int i = db.delete(TABLE_NAME, "ID=?", new String[]{id});
        return i;
    }
}
