package com.example.kshitiz.hackylife;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import java.util.List;

/**
 * Created by kshitiz on 20/3/18.
 */
@Dao
public interface dao {
    @Query("SELECT * FROM hack where category=:cat order by id")
    List<hack> getAllbyCat(int cat);

    @Query("SELECT description FROM hack where status=0 and category=:cat")
    List<String> getAllFavsbyCat(int cat);

    @Query("SELECT * FROM hack where id=:id")
    hack findById(int id);

    @Insert
    void insertAll(List<hack> hacks);

    @Query("UPDATE hack set status=:status where id=:id")
    void updatehack(int status,int id);
}
