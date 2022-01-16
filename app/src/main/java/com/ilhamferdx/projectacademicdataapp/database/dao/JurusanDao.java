package com.ilhamferdx.projectacademicdataapp.database.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Query;

import com.ilhamferdx.projectacademicdataapp.database.entity.Jurusan;

import java.util.List;

@Dao
public interface JurusanDao {
    @Query("SELECT * FROM jurusan")
    List<Jurusan> getALl();

    @Query("INSERT INTO jurusan (jrsnname,jenjang) VALUES(:jrsnname, :jenjang)")
    void insertAll(String jenjang, String jrsnname);

    @Query("UPDATE jurusan SET jrsnname=:jrsnname, jenjang=:jenjang WHERE jrsnid=:jrsnid")
    void update(int jrsnid, String jenjang, String jrsnname);

    @Query("SELECT * FROM jurusan WHERE jrsnid=:jrsnid")
    Jurusan get(int jrsnid);

    @Delete
    void delete(Jurusan jurusan);

}
