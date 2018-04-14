package com.example.kshitiz.hackylife;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;

/**
 * Created by kshitiz on 20/3/18.
 */
@Database(entities = {hack.class}, version = 1)
public abstract class AppDb extends RoomDatabase {
    private static AppDb INSTANCE;

    public abstract dao UDao();

    public static AppDb getAppDb(Context context) {
        if (INSTANCE == null) {
            INSTANCE = Room.databaseBuilder(context, AppDb.class,"hack.db").build();
        }
        return INSTANCE;
    }

    public static void destroyInstance() {
        INSTANCE = null;
    }
}
