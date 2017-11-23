package edu.aku.hassannaqvi.src_preg.data.providers;

import android.content.ContentProvider;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;

import edu.aku.hassannaqvi.src_preg.contracts.FormsContract;
import edu.aku.hassannaqvi.src_preg.core.DatabaseHelper;

/**
 * Created by Hassan.naqvi on 11/23/2017.
 */

public class FormsProvider extends ContentProvider {

    public static final String AUTHORITY = "edu.aku.hassannaqvi.src_preg.data.providers";
    /**
     * Tag for the log messages
     */
    public static final String TAG = FormsProvider.class.getSimpleName();
    /**
     * UriMatcher declaration, matches incoming URIs with appropriate constants
     */
    public static final UriMatcher MATCHER = new UriMatcher(UriMatcher.NO_MATCH);
    /**
     * The match code for some items in the Forms table.
     */
    private static final int CODE_FORMS_ALL = 1;
    /**
     * The match code for an item in the Forms table.
     */
    private static final int CODE_FORMS_SINGLE = 2;

    /** Add possible URIs to handle by the matcher */
    static {
        MATCHER.addURI(AUTHORITY, FormsContract.FormsTable.TABLE_NAME, CODE_FORMS_ALL);
        MATCHER.addURI(AUTHORITY, FormsContract.FormsTable.TABLE_NAME + "/#", CODE_FORMS_SINGLE);
    }

    /**
     * FormsDbHelper - database helper object
     */
    private DatabaseHelper db;

    /**
     * Initialize the provider and the database helper object.
     */
    @Override
    public boolean onCreate() {
        db = new DatabaseHelper(getContext());
        return true;
    }

    /**
     * Perform the query for the given URI. Use the given projection, selection, selection arguments, and sort order.
     */
    @Override
    public Cursor query(Uri uri, String[] projection, String selection, String[] selectionArgs,
                        String sortOrder) {
        // Get readable database
        SQLiteDatabase database = db.getReadableDatabase();

        // This cursor will hold the result of the query
        Cursor cursor;

        // Figure out if the URI matcher can match the URI to a specific code
        int match = MATCHER.match(uri);
        switch (match) {
            case CODE_FORMS_ALL:
                cursor = database.query(FormsContract.FormsTable.TABLE_NAME, projection, null, null,
                        null, null, sortOrder);
                break;
            case CODE_FORMS_SINGLE:
                // Where
                selection = FormsContract.FormsTable._ID + "=?";
                // What
                selectionArgs = new String[]{String.valueOf(ContentUris.parseId(uri))};
                // Form query to the database
                cursor = database.query(FormsContract.FormsTable.TABLE_NAME, projection, selection, selectionArgs,
                        null, null, sortOrder);

                break;
            default:
                throw new IllegalArgumentException("Cannot query unknown URI " + uri);
        }

        return cursor;
    }

    /**
     * Insert new data into the provider with the given ContentValues.
     */
    @Override
    public Uri insert(Uri uri, ContentValues contentValues) {
        return null;
    }

    /**
     * Updates the data at the given selection and selection arguments, with the new ContentValues.
     */
    @Override
    public int update(Uri uri, ContentValues contentValues, String selection, String[] selectionArgs) {
        return 0;
    }

    /**
     * Delete the data at the given selection and selection arguments.
     */
    @Override
    public int delete(Uri uri, String selection, String[] selectionArgs) {
        return 0;
    }

    /**
     * Returns the MIME type of data for the content URI.
     */
    @Override
    public String getType(Uri uri) {
        return null;
    }
}
