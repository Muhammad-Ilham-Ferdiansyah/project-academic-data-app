package com.ilhamferdx.projectacademicdataapp.database.entity;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Dosen {
    @PrimaryKey
    public int dsnid;

    @ColumnInfo(name = "dsnname")
    public String nama_dosen;

    public String nip;
}
