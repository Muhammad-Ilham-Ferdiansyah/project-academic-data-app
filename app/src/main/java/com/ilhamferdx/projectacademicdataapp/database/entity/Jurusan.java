package com.ilhamferdx.projectacademicdataapp.database.entity;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Jurusan {
    @PrimaryKey
    public int jrsnid;

    public String jenjang;

    @ColumnInfo(name = "jrsnname")
    public String nama_jurusan;

}
