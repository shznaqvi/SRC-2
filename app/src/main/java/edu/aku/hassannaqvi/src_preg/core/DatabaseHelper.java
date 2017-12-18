package edu.aku.hassannaqvi.src_preg.core;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.MatrixCursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import edu.aku.hassannaqvi.src_preg.contracts.BLRandomContract;
import edu.aku.hassannaqvi.src_preg.contracts.BLRandomContract.singleChild;
import edu.aku.hassannaqvi.src_preg.contracts.DistrictsContract;
import edu.aku.hassannaqvi.src_preg.contracts.FormsContract;
import edu.aku.hassannaqvi.src_preg.contracts.FormsContract.FormsTable;
import edu.aku.hassannaqvi.src_preg.contracts.TalukasContract;
import edu.aku.hassannaqvi.src_preg.contracts.TalukasContract.TalukasTable;
import edu.aku.hassannaqvi.src_preg.contracts.UCsContract;
import edu.aku.hassannaqvi.src_preg.contracts.UCsContract.UCsTable;
import edu.aku.hassannaqvi.src_preg.contracts.UsersContract;
import edu.aku.hassannaqvi.src_preg.contracts.UsersContract.UsersTable;
import edu.aku.hassannaqvi.src_preg.contracts.VillagesContract;
import edu.aku.hassannaqvi.src_preg.otherClasses.MotherLst;


/**
 * Created by hassan.naqvi on 11/30/2016.
 */

public class DatabaseHelper extends SQLiteOpenHelper {

    public static final String SQL_CREATE_BL_RANDOM = "CREATE TABLE " + singleChild.TABLE_NAME + "("
            + singleChild.COLUMN_ID + " TEXT,"
            + singleChild.COLUMN_SUB_VILLAGE_CODE + " TEXT,"
            + singleChild.COLUMN_LUID + " TEXT,"
            + singleChild.COLUMN_HH + " TEXT,"
            + singleChild.COLUMN_STRUCTURE_NO + " TEXT,"
            + singleChild.COLUMN_FAMILY_EXT_CODE + " TEXT,"
            + singleChild.COLUMN_HH_HEAD + " TEXT,"
            + singleChild.COLUMN_RANDOMDT + " TEXT );";

    public static final String SQL_CREATE_USERS = "CREATE TABLE " + UsersContract.UsersTable.TABLE_NAME + "("
            + UsersTable._ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
            + UsersTable.ROW_USERNAME + " TEXT,"
            + UsersTable.ROW_PASSWORD + " TEXT,"
            + UsersTable.FULL_NAME + " TEXT"
//            + UsersTable.REGION_DSS + " TEXT"
            + " );";
    public static final String DATABASE_NAME = "src_preg.db";
    public static final String DB_NAME = DATABASE_NAME.replace(".", "_copy.");
    public static final String PROJECT_NAME = "DMU-SRCPREG";
    private static final int DATABASE_VERSION = 1;
    private static final String SQL_CREATE_FORMS = "CREATE TABLE "
            + FormsTable.TABLE_NAME + "("
            + FormsTable.COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
            + FormsTable.COLUMN_PROJECT_NAME + " TEXT,"
            + FormsTable.COLUMN_UID + " TEXT," +
            FormsTable.COLUMN_FORMDATE + " TEXT," +
            FormsTable.COLUMN_USER + " TEXT," +
            FormsTable.COLUMN_FORMTYPE + " TEXT," +
            FormsTable.COLUMN_SA + " TEXT," +
            FormsTable.COLUMN_SB + " TEXT," +
            FormsTable.COLUMN_SC + " TEXT," +
            FormsTable.COLUMN_SD + " TEXT," +
            FormsTable.COLUMN_ISTATUS + " TEXT," +
            FormsTable.COLUMN_ISTATUS88x + " TEXT," +
            FormsTable.COLUMN_GPSLAT + " TEXT," +
            FormsTable.COLUMN_GPSLNG + " TEXT," +
            FormsTable.COLUMN_GPSDATE + " TEXT," +
            FormsTable.COLUMN_GPSACC + " TEXT," +
            FormsTable.COLUMN_DEVICEID + " TEXT," +
            FormsTable.COLUMN_DEVICETAGID + " TEXT," +
            FormsTable.COLUMN_APP_VERSION + " TEXT," +
            FormsTable.COLUMN_SYNCED + " TEXT," +
            FormsTable.COLUMN_SYNCED_DATE + " TEXT"
            + " );";
    private static final String SQL_DELETE_BL_RANDOM =
            "DROP TABLE IF EXISTS " + singleChild.TABLE_NAME;
    private static final String SQL_DELETE_USERS =
            "DROP TABLE IF EXISTS " + UsersContract.UsersTable.TABLE_NAME;
    private static final String SQL_DELETE_FORMS =
            "DROP TABLE IF EXISTS " + FormsTable.TABLE_NAME;
    private static final String SQL_SELECT_MOTHER_BY_CHILD =
            "SELECT c.agem cm, c.agey cy, c.aged cd, c.gender, c.serial serial, m.serial serialm, c.name child_name, c.dss_id_member child_id, m.name mother_name, c.dss_id_member mother_id, c.dob date_of_birth FROM census C join census m on c.dss_id_m = m.dss_id_member where c.member_type =? and c.uuid = m.uuid and c.current_status IN ('1', '2') and c.uuid = ? group by mother_id order by substr(c.dob, 7) desc, substr(c.dob, 4,2) desc, substr(c.dob, 1,2) desc;";
    private static final String SQL_SELECT_CHILD =
            "SELECT * from census where member_type =? and dss_id_hh =? and uuid =? and current_status IN ('1', '2')";
    private static final String SQL_SELECT_MWRA =
            "SELECT * from census where member_type =? and dss_id_hh =? and uuid =? and current_status IN ('1', '2')";
    private static final String SQL_DELETE_VILLAGES = "DROP TABLE IF EXISTS " + VillagesContract.singleVillage.TABLE_NAME;
    private static final String SQL_DELETE_TALUKAS = "DROP TABLE IF EXISTS " + TalukasTable.TABLE_NAME;
    private static final String SQL_DELETE_UCS = "DROP TABLE IF EXISTS " + UCsTable.TABLE_NAME;
    final String SQL_CREATE_DISTRICT_TABLE = "CREATE TABLE " + DistrictsContract.singleDistrict.TABLE_NAME + " (" +
            DistrictsContract.singleDistrict._ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
            DistrictsContract.singleDistrict.COLUMN_DISTRICT_CODE + " TEXT, " +
            DistrictsContract.singleDistrict.COLUMN_DISTRICT_NAME + " TEXT " +
            ");";
    final String SQL_CREATE_PSU_TABLE = "CREATE TABLE " + VillagesContract.singleVillage.TABLE_NAME + " (" +
            VillagesContract.singleVillage._ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
            VillagesContract.singleVillage.COLUMN_VILLAGE_CODE + " TEXT, " +
            VillagesContract.singleVillage.COLUMN_VILLAGE_NAME + " TEXT, " +
            VillagesContract.singleVillage.COLUMN_DISTRICT_CODE + " TEXT " +
            ");";
    /* final String SQL_CREATE_VILLAGES = "CREATE TABLE " + VillagesContract.singleVillage.TABLE_NAME + "("
 //                + VillagesTable.COLUMN_ID + " TEXT,"
             + VillagesTable.COLUMN_VILLAGE_NAME + " TEXT,"
             + VillagesTable.COLUMN_AREA_CODE + " TEXT,"
 //                + VillagesTable.COLUMN_TALUKA + " TEXT,"
             + VillagesTable.COLUMN_VILLAGE_CODE + " TEXT );";*/
    final String SQL_CREATE_TALUKAS = "CREATE TABLE " + TalukasTable.TABLE_NAME + "("
            + TalukasTable.COLUMN_TALUKA_CODE + " TEXT,"
            + TalukasTable.COLUMN_TALUKA + " TEXT );";
    final String SQL_CREATE_UCS = "CREATE TABLE " + UCsTable.TABLE_NAME + "("
            + UCsTable.COLUMN_UCCODE + " TEXT,"
            + UCsTable.COLUMN_TALUKA_CODE + " TEXT,"
            + UCsTable.COLUMN_UCS + " TEXT );";

    private final String TAG = "DatabaseHelper";


    public String spDateT = new SimpleDateFormat("dd-MM-yy").format(new Date().getTime());


    public DatabaseHelper(Context context) {

        super(context, DATABASE_NAME, null, DATABASE_VERSION);

    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL(SQL_CREATE_USERS);
        db.execSQL(SQL_CREATE_FORMS);
        db.execSQL(SQL_CREATE_VILLAGES);
        db.execSQL(SQL_CREATE_TALUKAS);
        db.execSQL(SQL_CREATE_UCS);

        db.execSQL(SQL_CREATE_BL_RANDOM);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL(SQL_DELETE_USERS);
        db.execSQL(SQL_DELETE_FORMS);
        db.execSQL(SQL_DELETE_VILLAGES);
        db.execSQL(SQL_DELETE_TALUKAS);
        db.execSQL(SQL_DELETE_UCS);
        db.execSQL(SQL_DELETE_BL_RANDOM);
    }

    /*public void syncVillages(JSONArray Villageslist) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(VillagesTable.TABLE_NAME, null, null);
        try {
            JSONArray jsonArray = Villageslist;
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject jsonObjectCC = jsonArray.getJSONObject(i);

                VillagesContract Vc = new VillagesContract();
                Vc.Sync(jsonObjectCC);

                ContentValues values = new ContentValues();

//                values.put(VillagesTable.COLUMN_ID, Vc.getID());
                values.put(VillagesTable.COLUMN_VILLAGE_NAME, Vc.getVillagename());
                values.put(VillagesTable.COLUMN_AREA_CODE, Vc.getArea_code());
//                values.put(VillagesTable.COLUMN_TALUKA, Vc.getTaluka());
                values.put(VillagesTable.COLUMN_VILLAGE_CODE, Vc.getVillagecode());

                db.insert(VillagesTable.TABLE_NAME, null, values);
            }
        } catch (Exception e) {
        } finally {
            db.close();
        }
    }
*/
    public void syncTalukas(JSONArray Talukaslist) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TalukasTable.TABLE_NAME, null, null);
        try {
            JSONArray jsonArray = Talukaslist;
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject jsonObjectCC = jsonArray.getJSONObject(i);

                TalukasContract Vc = new TalukasContract();
                Vc.Sync(jsonObjectCC);

                ContentValues values = new ContentValues();

                values.put(TalukasTable.COLUMN_TALUKA_CODE, Vc.getTalukacode());
                values.put(TalukasTable.COLUMN_TALUKA, Vc.getTaluka());

                db.insert(TalukasTable.TABLE_NAME, null, values);
            }
        } catch (Exception e) {
        } finally {
            db.close();
        }
    }

    public void syncUCs(JSONArray UCslist) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(UCsTable.TABLE_NAME, null, null);
        try {
            JSONArray jsonArray = UCslist;
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject jsonObjectCC = jsonArray.getJSONObject(i);

                UCsContract Vc = new UCsContract();
                Vc.Sync(jsonObjectCC);

                ContentValues values = new ContentValues();

                values.put(UCsTable.COLUMN_UCCODE, Vc.getUccode());
                values.put(UCsTable.COLUMN_UCS, Vc.getUcs());
                values.put(UCsTable.COLUMN_TALUKA_CODE, Vc.getTaluka_code());

                db.insert(UCsTable.TABLE_NAME, null, values);
            }
        } catch (Exception e) {
        } finally {
            db.close();
        }
    }


    public void syncDistricts(JSONArray dcList) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(DistrictsContract.singleDistrict.TABLE_NAME, null, null);

        try {
            JSONArray jsonArray = dcList;

            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject jsonObjectDistrict = jsonArray.getJSONObject(i);

                DistrictsContract dc = new DistrictsContract();
                dc.sync(jsonObjectDistrict);

                ContentValues values = new ContentValues();

                values.put(DistrictsContract.singleDistrict.COLUMN_DISTRICT_CODE, dc.getDistrictCode());
                values.put(DistrictsContract.singleDistrict.COLUMN_DISTRICT_NAME, dc.getDistrictName());

                db.insert(DistrictsContract.singleDistrict.TABLE_NAME, null, values);
            }
            db.close();

        } catch (Exception e) {

        }
    }

    public Collection<VillagesContract> getAllPSUsByDistrict(String district_code) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = null;
        String[] columns = {
                VillagesContract.singleVillage._ID,
                VillagesContract.singleVillage.COLUMN_VILLAGE_CODE,
                VillagesContract.singleVillage.COLUMN_VILLAGE_NAME,
                VillagesContract.singleVillage.COLUMN_DISTRICT_CODE
        };

        String whereClause = VillagesContract.singleVillage.COLUMN_DISTRICT_CODE + " = ?";
        String[] whereArgs = {district_code};
        String groupBy = null;
        String having = null;

        String orderBy =
                VillagesContract.singleVillage.COLUMN_VILLAGE_CODE + " ASC";

        Collection<VillagesContract> allPC = new ArrayList<VillagesContract>();
        try {
            c = db.query(
                    VillagesContract.singleVillage.TABLE_NAME,  // The table to query
                    columns,                   // The columns to return
                    whereClause,               // The columns for the WHERE clause
                    whereArgs,                 // The values for the WHERE clause
                    groupBy,                   // don't group the rows
                    having,                    // don't filter by row groups
                    orderBy                    // The sort order
            );
            while (c.moveToNext()) {
                VillagesContract pc = new VillagesContract();
                allPC.add(pc.hydrate(c));
            }
        } finally {
            if (c != null) {
                c.close();
            }
            if (db != null) {
                db.close();
            }
        }
        return allPC;
    }


    public void getVillages(JSONArray pcList) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(VillagesContract.singleVillage.TABLE_NAME, null, null);

        try {
            JSONArray jsonArray = pcList;

            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject jsonObjectPSU = jsonArray.getJSONObject(i);

                VillagesContract vc = new VillagesContract();
                vc.sync(jsonObjectPSU);
                Log.i(TAG, "syncVillages: " + jsonObjectPSU.toString());

                ContentValues values = new ContentValues();

                values.put(VillagesContract.singleVillage.COLUMN_VILLAGE_CODE, vc.getVillageCode());
                values.put(VillagesContract.singleVillage.COLUMN_VILLAGE_NAME, vc.getVillageName());
                values.put(VillagesContract.singleVillage.COLUMN_DISTRICT_CODE, vc.getDistrictCode());

                db.insert(VillagesContract.singleVillage.TABLE_NAME, null, values);
            }
            db.close();

        } catch (Exception e) {

        }
    }

    public Collection<TalukasContract> getAllTalukas() {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = null;
        String[] columns = {
                TalukasTable.COLUMN_TALUKA_CODE,
                TalukasTable.COLUMN_TALUKA
        };

        String whereClause = null;
        String[] whereArgs = null;
        String groupBy = null;
        String having = null;

        String orderBy =
                TalukasTable.COLUMN_TALUKA + " ASC";

        Collection<TalukasContract> allDC = new ArrayList<>();
        try {
            c = db.query(
                    TalukasTable.TABLE_NAME,  // The table to query
                    columns,                   // The columns to return
                    whereClause,               // The columns for the WHERE clause
                    whereArgs,                 // The values for the WHERE clause
                    groupBy,                   // don't group the rows
                    having,                    // don't filter by row groups
                    orderBy                    // The sort order
            );
            while (c.moveToNext()) {
                TalukasContract dc = new TalukasContract();
                allDC.add(dc.HydrateTalukas(c));
            }
        } finally {
            if (c != null) {
                c.close();
            }
            if (db != null) {
                db.close();
            }
        }
        return allDC;
    }

    public Collection<UCsContract> getAllUCs(String talukaCode) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = null;
        String[] columns = {
                UCsTable.COLUMN_UCCODE,
                UCsTable.COLUMN_UCS,
                UCsTable.COLUMN_TALUKA_CODE
        };

        String whereClause = UCsTable.COLUMN_TALUKA_CODE + "=?";
        String[] whereArgs = new String[]{talukaCode};
        String groupBy = null;
        String having = null;

        String orderBy =
                UCsTable.COLUMN_UCS + " ASC";

        Collection<UCsContract> allDC = new ArrayList<>();
        try {
            c = db.query(
                    UCsTable.TABLE_NAME,  // The table to query
                    columns,                   // The columns to return
                    whereClause,               // The columns for the WHERE clause
                    whereArgs,                 // The values for the WHERE clause
                    groupBy,                   // don't group the rows
                    having,                    // don't filter by row groups
                    orderBy                    // The sort order
            );
            while (c.moveToNext()) {
                UCsContract dc = new UCsContract();
                allDC.add(dc.HydrateUCs(c));
            }
        } finally {
            if (c != null) {
                c.close();
            }
            if (db != null) {
                db.close();
            }
        }
        return allDC;
    }


    public Collection<BLRandomContract> getAllBLRandom(String subAreaCode, String hh) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = null;
        String[] columns = {
                singleChild.COLUMN_ID,
                singleChild.COLUMN_LUID,
                singleChild.COLUMN_STRUCTURE_NO,
                singleChild.COLUMN_FAMILY_EXT_CODE,
                singleChild.COLUMN_HH,
                singleChild.COLUMN_SUB_VILLAGE_CODE,
                singleChild.COLUMN_RANDOMDT,
                singleChild.COLUMN_HH_HEAD
        };

        String whereClause = singleChild.COLUMN_SUB_VILLAGE_CODE + "=? AND " +
                singleChild.COLUMN_HH + "=?";
        String[] whereArgs = new String[]{subAreaCode, hh};
        String groupBy = null;
        String having = null;

        String orderBy =
                singleChild.COLUMN_ID + " ASC";

        Collection<BLRandomContract> allBL = new ArrayList<>();
        try {
            c = db.query(
                    singleChild.TABLE_NAME,  // The table to query
                    columns,                   // The columns to return
                    whereClause,               // The columns for the WHERE clause
                    whereArgs,                 // The values for the WHERE clause
                    groupBy,                   // don't group the rows
                    having,                    // don't filter by row groups
                    orderBy                    // The sort order
            );
            while (c.moveToNext()) {
                BLRandomContract dc = new BLRandomContract();
                allBL.add(dc.Hydrate(c));
            }
        } finally {
            if (c != null) {
                c.close();
            }
            if (db != null) {
                db.close();
            }
        }
        return allBL;
    }

    public void syncBLRandom(JSONArray Areaslist) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(singleChild.TABLE_NAME, null, null);
        try {
            JSONArray jsonArray = Areaslist;
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject jsonObjectCC = jsonArray.getJSONObject(i);

                BLRandomContract Vc = new BLRandomContract();
                Vc.Sync(jsonObjectCC);

                ContentValues values = new ContentValues();

                values.put(singleChild.COLUMN_ID, Vc.get_ID());
                values.put(singleChild.COLUMN_LUID, Vc.getLUID());
                values.put(singleChild.COLUMN_STRUCTURE_NO, Vc.getStructure());
                values.put(singleChild.COLUMN_FAMILY_EXT_CODE, Vc.getExtension());
                values.put(singleChild.COLUMN_HH, Vc.getHh());
                values.put(singleChild.COLUMN_SUB_VILLAGE_CODE, Vc.getSubVillageCode());
                values.put(singleChild.COLUMN_RANDOMDT, Vc.getRandomDT());
                values.put(singleChild.COLUMN_HH_HEAD, Vc.getHhhead());

                db.insert(singleChild.TABLE_NAME, null, values);
            }
        } catch (Exception e) {
        } finally {
            db.close();
        }
    }

    public void syncUser(JSONArray userlist) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(UsersTable.TABLE_NAME, null, null);
        try {
            JSONArray jsonArray = userlist;
            for (int i = 0; i < jsonArray.length(); i++) {

                JSONObject jsonObjectUser = jsonArray.getJSONObject(i);

                UsersContract user = new UsersContract();
                user.Sync(jsonObjectUser);
                ContentValues values = new ContentValues();

                values.put(UsersContract.UsersTable.ROW_USERNAME, user.getUserName());
                values.put(UsersTable.ROW_PASSWORD, user.getPassword());
                values.put(UsersTable.FULL_NAME, user.getFULL_NAME());
                db.insert(UsersTable.TABLE_NAME, null, values);
            }


        } catch (Exception e) {
            Log.d(TAG, "syncUser(e): " + e);
        } finally {
            db.close();
        }
    }


    public boolean Login(String username, String password) throws SQLException {

        SQLiteDatabase db = this.getReadableDatabase();

// New value for one column
        String[] columns = {
                UsersTable._ID
        };

// Which row to update, based on the ID
        String selection = UsersContract.UsersTable.ROW_USERNAME + " = ?" + " AND " + UsersContract.UsersTable.ROW_PASSWORD + " = ?";
        String[] selectionArgs = {username, password};
        Cursor cursor = db.query(UsersContract.UsersTable.TABLE_NAME, //Table to query
                columns,                    //columns to return
                selection,                  //columns for the WHERE clause
                selectionArgs,              //The values for the WHERE clause
                null,                       //group the rows
                null,                       //filter by row groups
                null);                      //The sort order

        int cursorCount = cursor.getCount();

        cursor.close();
        db.close();
        return cursorCount > 0;
    }


    public List<FormsContract> getFormsByDSS(String dssID) {
        List<FormsContract> formList = new ArrayList<>();
        // Select All Query
        String selectQuery = "SELECT  * FROM " + FormsTable.TABLE_NAME;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor c = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (c.moveToFirst()) {
            do {
                FormsContract fc = new FormsContract();
                formList.add(fc.Hydrate(c));
            } while (c.moveToNext());
        }

        // return contact list
        return formList;
    }

    public Collection<MotherLst> getMotherByUUID(String uuid) {

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = null;
        Collection<MotherLst> memList = new ArrayList<>();
        try {

            c = db.rawQuery(SQL_SELECT_MOTHER_BY_CHILD, new String[]{"c", uuid});

            while (c.moveToNext()) {

                MotherLst mc = new MotherLst();
                memList.add(mc.Hydrate(c));
            }
        } finally {
            if (c != null) {
                c.close();
            }
            if (db != null) {
                db.close();
            }
        }
        return memList;
    }


    public Long addForm(FormsContract fc) {

        // Gets the data repository in write mode
        SQLiteDatabase db = this.getWritableDatabase();

// Create a new map of values, where column names are the keys
        ContentValues values = new ContentValues();
        values.put(FormsTable.COLUMN_PROJECT_NAME, fc.getProjectName());
        values.put(FormsTable.COLUMN_UID, fc.getUID());
        values.put(FormsTable.COLUMN_FORMDATE, fc.getFormDate());
        values.put(FormsTable.COLUMN_USER, fc.getUser());
        values.put(FormsTable.COLUMN_FORMTYPE, fc.getFormtype());
        values.put(FormsTable.COLUMN_ISTATUS, fc.getIstatus());
        values.put(FormsTable.COLUMN_ISTATUS88x, fc.getIstatus88x());
        values.put(FormsTable.COLUMN_SA, fc.getsA());
        values.put(FormsTable.COLUMN_SB, fc.getsB());
        values.put(FormsTable.COLUMN_SC, fc.getsC());
        values.put(FormsTable.COLUMN_SD, fc.getsD());
        values.put(FormsTable.COLUMN_GPSLAT, fc.getGpsLat());
        values.put(FormsTable.COLUMN_GPSLNG, fc.getGpsLng());
        values.put(FormsTable.COLUMN_GPSDATE, fc.getGpsDT());
        values.put(FormsTable.COLUMN_GPSACC, fc.getGpsAcc());
        values.put(FormsTable.COLUMN_DEVICETAGID, fc.getDevicetagID());
        values.put(FormsTable.COLUMN_DEVICEID, fc.getDeviceID());
        values.put(FormsTable.COLUMN_SYNCED, fc.getSynced());
        values.put(FormsTable.COLUMN_SYNCED_DATE, fc.getSynced_date());
        values.put(FormsTable.COLUMN_APP_VERSION, fc.getAppversion());


        // Insert the new row, returning the primary key value of the new row
        long newRowId;
        newRowId = db.insert(
                FormsTable.TABLE_NAME,
                FormsTable.COLUMN_NAME_NULLABLE,
                values);
        return newRowId;
    }


    public void updateSyncedForms(String id) {
        SQLiteDatabase db = this.getReadableDatabase();

// New value for one column
        ContentValues values = new ContentValues();
        values.put(FormsTable.COLUMN_SYNCED, true);
        values.put(FormsTable.COLUMN_SYNCED_DATE, new Date().toString());

// Which row to update, based on the title
        String where = FormsTable.COLUMN_ID + " = ?";
        String[] whereArgs = {id};

        int count = db.update(
                FormsTable.TABLE_NAME,
                values,
                where,
                whereArgs);
    }


    public int updateFormID() {
        SQLiteDatabase db = this.getReadableDatabase();

// New value for one column
        ContentValues values = new ContentValues();
        values.put(FormsTable.COLUMN_UID, MainApp.fc.getUID());

// Which row to update, based on the ID
        String selection = FormsTable._ID + " = ?";
        String[] selectionArgs = {String.valueOf(MainApp.fc.get_ID())};

        int count = db.update(FormsTable.TABLE_NAME,
                values,
                selection,
                selectionArgs);
        return count;
    }

    public Collection<FormsContract> getAllForms() {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = null;
        String[] columns = {
                FormsTable._ID,
                FormsTable.COLUMN_UID,
                FormsTable.COLUMN_FORMDATE,
                FormsTable.COLUMN_USER,
                FormsTable.COLUMN_ISTATUS,
                FormsTable.COLUMN_FORMTYPE,
                FormsTable.COLUMN_SA,
                FormsTable.COLUMN_SB,
                FormsTable.COLUMN_SC,
                FormsTable.COLUMN_SD,
                FormsTable.COLUMN_GPSLAT,
                FormsTable.COLUMN_GPSLNG,
                FormsTable.COLUMN_GPSDATE,
                FormsTable.COLUMN_GPSACC,
                FormsTable.COLUMN_DEVICETAGID,
                FormsTable.COLUMN_DEVICEID,
                FormsTable.COLUMN_SYNCED,
                FormsTable.COLUMN_SYNCED_DATE,
                FormsTable.COLUMN_APP_VERSION

        };
        String whereClause = null;
        String[] whereArgs = null;
        String groupBy = null;
        String having = null;

        String orderBy =
                FormsTable.COLUMN_ID + " ASC";

        Collection<FormsContract> allFC = new ArrayList<FormsContract>();
        try {
            c = db.query(
                    FormsTable.TABLE_NAME,  // The table to query
                    columns,                   // The columns to return
                    whereClause,               // The columns for the WHERE clause
                    whereArgs,                 // The values for the WHERE clause
                    groupBy,                   // don't group the rows
                    having,                    // don't filter by row groups
                    orderBy                    // The sort order
            );
            while (c.moveToNext()) {
                FormsContract fc = new FormsContract();
                allFC.add(fc.Hydrate(c));
            }
        } finally {
            if (c != null) {
                c.close();
            }
            if (db != null) {
                db.close();
            }
        }
        return allFC;
    }


    public Collection<FormsContract> getUnsyncedForms() {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = null;
        String[] columns = {
                FormsTable._ID,
                FormsTable.COLUMN_UID,
                FormsTable.COLUMN_FORMDATE,
                FormsTable.COLUMN_USER,
                FormsTable.COLUMN_ISTATUS,
                FormsTable.COLUMN_FORMTYPE,
                FormsTable.COLUMN_SA,
                FormsTable.COLUMN_SB,
                FormsTable.COLUMN_SC,
                FormsTable.COLUMN_SD,
                FormsTable.COLUMN_GPSLAT,
                FormsTable.COLUMN_GPSLNG,
                FormsTable.COLUMN_GPSDATE,
                FormsTable.COLUMN_GPSACC,
                FormsTable.COLUMN_DEVICETAGID,
                FormsTable.COLUMN_DEVICEID,
                FormsTable.COLUMN_SYNCED,
                FormsTable.COLUMN_SYNCED_DATE,
                FormsTable.COLUMN_APP_VERSION
        };
        String whereClause = FormsTable.COLUMN_SYNCED + " is null";
        String[] whereArgs = null;
        String groupBy = null;
        String having = null;

        String orderBy =
                FormsTable.COLUMN_ID + " ASC";

        Collection<FormsContract> allFC = new ArrayList<FormsContract>();
        try {
            c = db.query(
                    FormsTable.TABLE_NAME,  // The table to query
                    columns,                   // The columns to return
                    whereClause,               // The columns for the WHERE clause
                    whereArgs,                 // The values for the WHERE clause
                    groupBy,                   // don't group the rows
                    having,                    // don't filter by row groups
                    orderBy                    // The sort order
            );
            while (c.moveToNext()) {
                FormsContract fc = new FormsContract();
                allFC.add(fc.Hydrate(c));
            }
        } finally {
            if (c != null) {
                c.close();
            }
            if (db != null) {
                db.close();
            }
        }
        return allFC;
    }

    /*public Collection<FormsContract> getFormsSg() {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = null;
        String[] columns = {
                FormsTable.COLUMN_ID,
                FormsTable.COLUMN_UID,
                FormsTable.COLUMN_FORMDATE,
                FormsTable.COLUMN_USER,
                FormsTable.COLUMN_ISTATUS,
                FormsTable.COLUMN_SA,
                FormsTable.COLUMN_SC,
                FormsTable.COLUMN_SG,
                FormsTable.COLUMN_SHA,
                FormsTable.COLUMN_SHB,
                FormsTable.COLUMN_SB,
                FormsTable.COLUMN_SJ,
                FormsTable.COLUMN_SK,
                FormsTable.COLUMN_SL,
                FormsTable.COLUMN_SM,
                FormsTable.COLUMN_GPSLAT,
                FormsTable.COLUMN_GPSLNG,
                FormsTable.COLUMN_GPSDATE,
                FormsTable.COLUMN_GPSACC,
                FormsTable.COLUMN_DEVICETAGID,
                FormsTable.COLUMN_DEVICEID
        };
        String whereClause = FormsTable.COLUMN_SYNCED + " = ? AND " + FormsTable.COLUMN_SG + " != ?";
        String[] whereArgs = new String[]{"1", ""};
        String groupBy = null;
        String having = null;

        String orderBy =
                FormsTable.COLUMN_ID + " ASC";

        Collection<FormsContract> allFC = new ArrayList<FormsContract>();
        try {
            c = db.query(
                    FormsTable.TABLE_NAME,  // The table to query
                    columns,                   // The columns to return
                    whereClause,               // The columns for the WHERE clause
                    whereArgs,                 // The values for the WHERE clause
                    groupBy,                   // don't group the rows
                    having,                    // don't filter by row groups
                    orderBy                    // The sort order
            );
            while (c.moveToNext()) {
                FormsContract fc = new FormsContract();
                allFC.add(fc.Hydrate(c));
            }
        } finally {
            if (c != null) {
                c.close();
            }
            if (db != null) {
                db.close();
            }
        }
        return allFC;
    }
*/
    public Collection<FormsContract> getTodayForms() {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = null;
        String[] columns = {
                FormsTable._ID,
                //FormsTable.COLUMN_DSSID,
                FormsTable.COLUMN_FORMDATE,
                FormsTable.COLUMN_ISTATUS,
                FormsTable.COLUMN_SYNCED,

        };
        String whereClause = FormsTable.COLUMN_FORMDATE + " Like ? ";
        String[] whereArgs = new String[]{"%" + spDateT.substring(0, 8).trim() + "%"};
        String groupBy = null;
        String having = null;

        String orderBy =
                FormsTable.COLUMN_ID + " ASC";

        Collection<FormsContract> allFC = new ArrayList<>();
        try {
            c = db.query(
                    FormsTable.TABLE_NAME,  // The table to query
                    columns,                   // The columns to return
                    whereClause,               // The columns for the WHERE clause
                    whereArgs,                 // The values for the WHERE clause
                    groupBy,                   // don't group the rows
                    having,                    // don't filter by row groups
                    orderBy                    // The sort order
            );
            while (c.moveToNext()) {
                FormsContract fc = new FormsContract();
                fc.set_ID(c.getString(c.getColumnIndex(FormsTable.COLUMN_ID)));
                fc.setFormDate(c.getString(c.getColumnIndex(FormsTable.COLUMN_FORMDATE)));
                fc.setIstatus(c.getString(c.getColumnIndex(FormsTable.COLUMN_ISTATUS)));
                fc.setSynced(c.getString(c.getColumnIndex(FormsTable.COLUMN_SYNCED)));
                allFC.add(fc);
            }
        } finally {
            if (c != null) {
                c.close();
            }
            if (db != null) {
                db.close();
            }
        }
        return allFC;
    }

    // ANDROID DATABASE MANAGER
    public ArrayList<Cursor> getData(String Query) {
        //get writable database
        SQLiteDatabase sqlDB = this.getWritableDatabase();
        String[] columns = new String[]{"message"};
        //an array list of cursor to save two cursors one has results from the query
        //other cursor stores error message if any errors are triggered
        ArrayList<Cursor> alc = new ArrayList<Cursor>(2);
        MatrixCursor Cursor2 = new MatrixCursor(columns);
        alc.add(null);
        alc.add(null);

        try {
            String maxQuery = Query;
            //execute the query results will be save in Cursor c
            Cursor c = sqlDB.rawQuery(maxQuery, null);

            //add value to cursor2
            Cursor2.addRow(new Object[]{"Success"});

            alc.set(1, Cursor2);
            if (null != c && c.getCount() > 0) {

                alc.set(0, c);
                c.moveToFirst();

                return alc;
            }
            return alc;
        } catch (SQLException sqlEx) {
            Log.d("printing exception", sqlEx.getMessage());
            //if any exceptions are triggered save the error message to cursor an return the arraylist
            Cursor2.addRow(new Object[]{"" + sqlEx.getMessage()});
            alc.set(1, Cursor2);
            return alc;
        } catch (Exception ex) {

            Log.d("printing exception", ex.getMessage());

            //if any exceptions are triggered save the error message to cursor an return the arraylist
            Cursor2.addRow(new Object[]{"" + ex.getMessage()});
            alc.set(1, Cursor2);
            return alc;
        }
    }


    public int updateSA() {
        SQLiteDatabase db = this.getReadableDatabase();

// New value for one column
        ContentValues values = new ContentValues();
        values.put(FormsTable.COLUMN_SA, MainApp.fc.getsA());

// Which row to update, based on the ID
        String selection = FormsTable._ID + " = ?";
        String[] selectionArgs = {String.valueOf(MainApp.fc.get_ID())};

        int count = db.update(FormsTable.TABLE_NAME,
                values,
                selection,
                selectionArgs);
        return count;
    }

    public int updateSB() {
        SQLiteDatabase db = this.getReadableDatabase();

// New value for one column
        ContentValues values = new ContentValues();
        values.put(FormsTable.COLUMN_SB, MainApp.fc.getsB());

// Which row to update, based on the ID
        String selection = FormsTable.COLUMN_ID + " = ?";
        String[] selectionArgs = {String.valueOf(MainApp.fc.get_ID())};

        int count = db.update(FormsTable.TABLE_NAME,
                values,
                selection,
                selectionArgs);
        return count;
    }


    public int updateSC() {
        SQLiteDatabase db = this.getReadableDatabase();

// New value for one column
        ContentValues values = new ContentValues();
        values.put(FormsTable.COLUMN_SC, MainApp.fc.getsC());

// Which row to update, based on the ID
        String selection = FormsTable.COLUMN_ID + " = ?";
        String[] selectionArgs = {String.valueOf(MainApp.fc.get_ID())};

        int count = db.update(FormsTable.TABLE_NAME,
                values,
                selection,
                selectionArgs);
        return count;
    }

    public int updateSD() {
        SQLiteDatabase db = this.getReadableDatabase();

// New value for one column
        ContentValues values = new ContentValues();
        values.put(FormsTable.COLUMN_SD, MainApp.fc.getsD());

// Which row to update, based on the ID
        String selection = FormsTable.COLUMN_ID + " = ?";
        String[] selectionArgs = {String.valueOf(MainApp.fc.get_ID())};

        int count = db.update(FormsTable.TABLE_NAME,
                values,
                selection,
                selectionArgs);
        return count;
    }


    public int updateCount() {
        SQLiteDatabase db = this.getReadableDatabase();

// New value for one column
        ContentValues values = new ContentValues();
        values.put(FormsTable.COLUMN_SB, MainApp.fc.getsB());

// Which row to update, based on the ID
        String selection = FormsTable.COLUMN_ID + " = ?";
        String[] selectionArgs = {String.valueOf(MainApp.fc.get_ID())};

        int count = db.update(FormsTable.TABLE_NAME,
                values,
                selection,
                selectionArgs);
        return count;
    }


    public int updateEnding() {
        SQLiteDatabase db = this.getReadableDatabase();

// New value for one column
        ContentValues values = new ContentValues();
        values.put(FormsTable.COLUMN_ISTATUS, MainApp.fc.getIstatus());
        values.put(FormsTable.COLUMN_ISTATUS88x, MainApp.fc.getIstatus88x());

// Which row to update, based on the ID
        String selection = FormsTable.COLUMN_ID + " =? ";
        String[] selectionArgs = {String.valueOf(MainApp.fc.get_ID())};

        int count = db.update(FormsTable.TABLE_NAME,
                values,
                selection,
                selectionArgs);
        return count;
    }

}