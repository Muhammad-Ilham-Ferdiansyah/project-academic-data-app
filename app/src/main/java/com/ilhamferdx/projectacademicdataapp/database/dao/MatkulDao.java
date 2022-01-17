package com.ilhamferdx.projectacademicdataapp.database.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Query;

import com.ilhamferdx.projectacademicdataapp.database.entity.Matkul;

import java.util.List;

@Dao
public interface MatkulDao {
    @Query("SELECT * FROM matkul")
    List<Matkul> getALl();

    @Query("INSERT INTO matkul (kode, matkulname, sks) VALUES(:kode, :matkulname, :sks)")
    void insertAll(String kode, String matkulname, String sks);

    @Query("UPDATE matkul SET kode=:kode, matkulname=:matkulname, sks=:sks WHERE matkulid=:matkulid")
    void update(int matkulid, String kode, String matkulname, String sks);

    @Query("SELECT * FROM matkul WHERE matkulid=:matkulid")
    Matkul get(int matkulid);

    @Delete
    void delete(Matkul matkul);
}
