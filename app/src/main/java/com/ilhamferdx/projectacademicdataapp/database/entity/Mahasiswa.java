package com.ilhamferdx.projectacademicdataapp.database.entity;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Mahasiswa {
    @PrimaryKey
    public int mhsid;

    @ColumnInfo(name = "mhsname")
    public String nama_mahasiswa;

    public int npm;
}
