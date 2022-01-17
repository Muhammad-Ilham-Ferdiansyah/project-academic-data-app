package com.ilhamferdx.projectacademicdataapp.database.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Query;

import com.ilhamferdx.projectacademicdataapp.database.entity.Mahasiswa;

import java.util.List;

@Dao
public interface MahasiswaDao {
    @Query("SELECT * FROM mahasiswa")
    List<Mahasiswa> getALl();

    @Query("INSERT INTO mahasiswa (mhsname, npm) VALUES( :mhsname, :npm)")
    void insertAll(String mhsname, int npm);

    @Query("UPDATE mahasiswa SET mhsname=:mhsname, npm=:npm WHERE mhsid=:mhsid")
    void update(int mhsid, String mhsname, int npm);

    @Query("SELECT * FROM mahasiswa WHERE mhsid=:mhsid")
    Mahasiswa get(int mhsid);

    @Delete
    void delete(Mahasiswa mahasiswa);
}