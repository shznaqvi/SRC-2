package edu.aku.hassannaqvi.src_preg.data.contracts;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.content.ContentValues;
import android.provider.BaseColumns;

/**
 * Created by hassan.naqvi on 11/30/2016.
 */
@Entity(tableName = UsersContract.TABLE_NAME)
public class UsersContract {

    public static final String TABLE_NAME = "users";
    public static final String COLUMN_ID = BaseColumns._ID;
    public static final String COLUMN_USERNAME = "username";
    public static final String COLUMN_PASSWORD = "password";
    private static final String TAG = "Users_CONTRACT";
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(index = true, name = COLUMN_ID)
    Long id;
    @ColumnInfo(name = COLUMN_USERNAME)
    String username;
    @ColumnInfo(name = COLUMN_PASSWORD)
    String password;

    public static UsersContract fromContentValues(ContentValues values) {
        final UsersContract users = new UsersContract();
        if (values.containsKey(COLUMN_ID)) {
            users.id = values.getAsLong(COLUMN_ID);
        }
        if (values.containsKey(COLUMN_USERNAME)) {
            users.username = values.getAsString(COLUMN_USERNAME);
        }
        if (values.containsKey(COLUMN_PASSWORD)) {
            users.password = values.getAsString(COLUMN_PASSWORD);
        }
        return users;
    }

    public static ContentValues toContentValues(UsersContract usersContract) {
        final ContentValues users = new ContentValues();

        users.put(COLUMN_USERNAME, usersContract.username);
        users.put(COLUMN_PASSWORD, usersContract.password);

        return users;
    }

}