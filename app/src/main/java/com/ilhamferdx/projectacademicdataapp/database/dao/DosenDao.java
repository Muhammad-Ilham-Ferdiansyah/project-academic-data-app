package com.ilhamferdx.projectacademicdataapp.database.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Query;

import com.ilhamferdx.projectacademicdataapp.database.entity.Dosen;

import java.util.List;

@Dao
public interface DosenDao {
    @Query("SELECT * FROM dosen")
    List<Dosen> getAll();

    @Query("INSERT INTO dosen (dsnname,nip) VALUES(:dsnname, :nip)")
    void insertAll(String dsnname, String nip);

    @Query("UPDATE dosen SET dsnname=:dsnname, nip=:nip WHERE dsnid=:dsnid")
    void update(int dsnid, String dsnname, String nip);

    @Query("SELECT * FROM dosen WHERE dsnid=:dsnid")
    Dosen get(int dsnid);

    @Delete
    void delete(Dosen dosen);
}
