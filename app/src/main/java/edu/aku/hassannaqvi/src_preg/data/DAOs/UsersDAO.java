package edu.aku.hassannaqvi.src_preg.data.DAOs;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;
import android.database.Cursor;

import edu.aku.hassannaqvi.src_preg.data.contracts.UsersContract;

/**
 * Created by Hassan.naqvi on 11/27/2017.
 */

@Dao
public interface UsersDAO {

    /**
     * Count All Records
     */
    @Query("SELECT COUNT(*) FROM " + UsersContract.TABLE_NAME)
    int count();

    /**
     * Select All Records
     */
    @Query("SELECT * FROM " + UsersContract.TABLE_NAME)
    Cursor selectAll();

    /**
     * Login
     */
    @Query("SELECT Count(*) FROM " + UsersContract.TABLE_NAME + " WHERE " + UsersContract.COLUMN_USERNAME + " = :username AND " + UsersContract.COLUMN_PASSWORD + " = :password")
    int login(String username, String password);

    /**
     * Insert Single User
     */
    @Insert
    long insert(UsersContract user);

    /**
     * Insert Multiple Users
     */
    @Insert
    long[] insertAll(UsersContract[] users);

    /**
     * Update Single User
     */
    @Update
    int update(UsersContract user);

    Cursor selectById(long l);


}
