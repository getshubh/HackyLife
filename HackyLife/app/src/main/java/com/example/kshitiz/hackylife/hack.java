package com.example.kshitiz.hackylife;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.arch.persistence.room.Query;

import java.util.List;

/**
 * Created by kshitiz on 20/3/18.
 */
@Entity(tableName = "hack")
public class hack {

    @PrimaryKey
    private int id;

    @ColumnInfo(name = "category")
    private int category;

    @ColumnInfo(name = "description")
    private String description;

    @ColumnInfo(name = "status")
    private int status;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCategory() {
        return category;
    }

    public void setCategory(int category) {
        this.category = category;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

}
