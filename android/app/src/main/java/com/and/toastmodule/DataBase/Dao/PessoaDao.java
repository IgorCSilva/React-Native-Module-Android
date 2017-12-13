package com.and.toastmodule.DataBase.Dao;


import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import com.and.toastmodule.DataBase.Entity.Pessoa;

import java.util.List;

/**
 * Created by Administrador on 06/12/2017.
 */

@Dao
public interface PessoaDao {

    @Query("SELECT * FROM Student_table")
    List<Pessoa> getAll();

    @Query("SELECT COUNT(*) FROM Student_table")
    int countUsers();

    @Insert
    void insertAll(Pessoa... pessoas);

    @Delete
    void delete(Pessoa pessoa);
}


