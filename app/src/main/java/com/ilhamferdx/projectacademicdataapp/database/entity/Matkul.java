package com.ilhamferdx.projectacademicdataapp.database.entity;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Matkul {
    @PrimaryKey
    public int matkulid;

    @ColumnInfo(name = "kode")
    public String kode_matkul;

    @ColumnInfo(name = "matkulname")
    public String nama_matkul;

    public int sks;


}
