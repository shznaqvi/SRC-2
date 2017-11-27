package edu.aku.hassannaqvi.src_preg.data;

import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;

import edu.aku.hassannaqvi.src_preg.data.DAOs.UsersDAO;

/**
 * Created by Hassan.naqvi on 11/27/2017.
 */

public abstract class AppDatabase extends RoomDatabase {

    private static AppDatabase sInstance;

    public static synchronized AppDatabase getInstance(Context context) {
        if (sInstance == null) {
            sInstance = Room
                    .databaseBuilder(context.getApplicationContext(), AppDatabase.class, "src2.db")
                    .build();
        }
        return sInstance;
    }

    @SuppressWarnings("WeakerAccess")
    public abstract UsersDAO users();

}
