package com.ilhamferdx.projectacademicdataapp.database;

import android.content.Context;
import android.os.Build;

import androidx.annotation.NonNull;
import androidx.annotation.VisibleForTesting;
import androidx.lifecycle.MutableLiveData;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

import com.ilhamferdx.projectacademicdataapp.database.dao.DosenDao;
import com.ilhamferdx.projectacademicdataapp.database.dao.JurusanDao;
import com.ilhamferdx.projectacademicdataapp.database.dao.MahasiswaDao;
import com.ilhamferdx.projectacademicdataapp.database.dao.MatkulDao;
import com.ilhamferdx.projectacademicdataapp.database.entity.Dosen;
import com.ilhamferdx.projectacademicdataapp.database.entity.Jurusan;
import com.ilhamferdx.projectacademicdataapp.database.entity.Mahasiswa;
import com.ilhamferdx.projectacademicdataapp.database.entity.Matkul;

@Database(entities = {
        Dosen.class,
        Jurusan.class,
        Matkul.class,
        Mahasiswa.class
        }, version = 3)
public abstract class AppDatabase extends RoomDatabase{
    private static AppDatabase sInstance;
    private final MutableLiveData<Boolean> mIsDatabaseCreated = new MutableLiveData<>();

    @VisibleForTesting
    public static final String DATABASE_NAME = "pada_DB";

    public abstract DosenDao dosenDao();
    public abstract JurusanDao jurusanDao();
    public abstract MatkulDao matkulDao();
    public abstract MahasiswaDao mahasiswaDao();

    private void setDatabaseCreated(){
        mIsDatabaseCreated.postValue(true);
    }

    private void updateDatabaseCreated(final Context context) {
        if (context.getDatabasePath(DATABASE_NAME).exists()) {
            setDatabaseCreated();
        }
    }

    public static AppDatabase buildDatabase(final Context context) {
        return Room.databaseBuilder(context, AppDatabase.class, DATABASE_NAME)
                .addCallback(new RoomDatabase.Callback() {
                    @Override
                    public void onCreate(@NonNull SupportSQLiteDatabase db) {
                        super.onCreate(db);
                        AppDatabase database = AppDatabase.getInstance(context);
                        database.setDatabaseCreated();
                    }

                }).allowMainThreadQueries().fallbackToDestructiveMigration().build();
    }
    public static AppDatabase getInstance(final Context context){
        if (sInstance == null){
            synchronized (AppDatabase.class) {
                if (sInstance == null){
                    sInstance = buildDatabase(context);

                    sInstance.updateDatabaseCreated(context.getApplicationContext());
                }
            }
        }
        return sInstance;
    }
}
