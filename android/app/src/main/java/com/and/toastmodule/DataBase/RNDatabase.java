package com.and.toastmodule.DataBase;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;

import com.and.toastmodule.DataBase.Dao.PessoaDao;
import com.and.toastmodule.DataBase.Entity.Pessoa;

/**
 * Created by Administrador on 06/12/2017.
 */

@Database(entities = {Pessoa.class}, version = 1)
public abstract class RNDatabase extends RoomDatabase {

    private static RNDatabase INSTANCE;
    public abstract PessoaDao pessoaDao();

    public static RNDatabase getRNDatabase(Context context){

        if(INSTANCE == null){
            INSTANCE = Room.databaseBuilder(context.getApplicationContext(), RNDatabase.class, "pessoa-database").allowMainThreadQueries().build();
        }

        return INSTANCE;
    }

    public static void destroyInstance() {
        INSTANCE = null;
    }
}
