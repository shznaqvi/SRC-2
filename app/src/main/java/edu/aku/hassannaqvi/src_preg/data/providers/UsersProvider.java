package edu.aku.hassannaqvi.src_preg.data.providers;

import android.content.ContentProvider;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.Context;
import android.content.UriMatcher;
import android.database.Cursor;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import edu.aku.hassannaqvi.src_preg.data.AppDatabase;
import edu.aku.hassannaqvi.src_preg.data.DAOs.UsersDAO;
import edu.aku.hassannaqvi.src_preg.data.contracts.UsersContract;

/**
 * Created by Hassan.naqvi on 11/27/2017.
 */

public class UsersProvider extends ContentProvider {

    public static final String AUTHORITY = "edu.aku.hassannaqvi.src_preg.data.providers";

    public static final Uri URI_USERS = Uri.parse(
            "content://" + AUTHORITY + "/" + UsersContract.TABLE_NAME);

    private static final int CODE_USERS_ALL = 1;
    private static final int CODE_USERS_SINGLE = 2;
    private static final int CODE_USERS_LOGIN = 3;

    private static final UriMatcher MATCHER = new UriMatcher(UriMatcher.NO_MATCH);

    static {
        MATCHER.addURI(AUTHORITY, UsersContract.TABLE_NAME, CODE_USERS_ALL);
        MATCHER.addURI(AUTHORITY, UsersContract.TABLE_NAME + "/*", CODE_USERS_SINGLE);
    }


    @Override
    public boolean onCreate() {
        return true;
    }

    @Nullable
    @Override
    public Cursor query(@NonNull Uri uri, @Nullable String[] strings, @Nullable String s, @Nullable String[] strings1, @Nullable String s1) {

        final int code = MATCHER.match(uri);

        if (code == CODE_USERS_ALL || code == CODE_USERS_SINGLE) {
            final Context context = getContext();
            if (context == null) {
                return null;
            }
            UsersDAO users = AppDatabase.getInstance(context).users();

            final Cursor cursor;

            if (code == CODE_USERS_ALL) {
                cursor = users.selectAll();
            } else {
                cursor = users.selectById(ContentUris.parseId(uri));
            }
            cursor.setNotificationUri(context.getContentResolver(), uri);
            return cursor;

        } else {
            throw new IllegalArgumentException("Unknown URI: " + uri);
        }
    }

    @Nullable
    @Override
    public String getType(@NonNull Uri uri) {
        switch (MATCHER.match(uri)) {
            case CODE_USERS_ALL:
                return "vnd.android.cursor.dir/" + AUTHORITY + "." + UsersContract.TABLE_NAME;
            case CODE_USERS_SINGLE:
                return "vnd.android.cursor.item/" + AUTHORITY + "." + UsersContract.TABLE_NAME;
            default:
                throw new IllegalArgumentException("Unknown URI: " + uri);
        }
    }

    @Nullable
    @Override
    public Uri insert(@NonNull Uri uri, @Nullable ContentValues contentValues) {
        switch (MATCHER.match(uri)) {
            case CODE_USERS_ALL:
                final Context context = getContext();
                if (context == null) {
                    return null;
                }
                final long id = AppDatabase.getInstance(context).users()
                        .insert(UsersContract.fromContentValues(contentValues));
                context.getContentResolver().notifyChange(uri, null);
                return ContentUris.withAppendedId(uri, id);
            case CODE_USERS_SINGLE:
                throw new IllegalArgumentException("Invalid URI, cannot insert with ID: " + uri);
            default:
                throw new IllegalArgumentException("Unknown URI: " + uri);
        }
    }

    @Override
    public int delete(@NonNull Uri uri, @Nullable String s, @Nullable String[] strings) {
        return 0;
    }

    @Override
    public int update(@NonNull Uri uri, @Nullable ContentValues contentValues, @Nullable String s, @Nullable String[] strings) {
        return 0;
    }

    @Override
    public int bulkInsert(@NonNull Uri uri, @NonNull ContentValues[] valuesArray) {
        switch (MATCHER.match(uri)) {
            case CODE_USERS_ALL:
                final Context context = getContext();
                if (context == null) {
                    return 0;
                }
                final AppDatabase database = AppDatabase.getInstance(context);
                final UsersContract[] users = new UsersContract[valuesArray.length];
                for (int i = 0; i < valuesArray.length; i++) {
                    users[i] = UsersContract.fromContentValues(valuesArray[i]);
                }
                return database.users().insertAll(users).length;
            case CODE_USERS_SINGLE:
                throw new IllegalArgumentException("Invalid URI, cannot insert with ID: " + uri);
            default:
                throw new IllegalArgumentException("Unknown URI: " + uri);
        }
    }
}
