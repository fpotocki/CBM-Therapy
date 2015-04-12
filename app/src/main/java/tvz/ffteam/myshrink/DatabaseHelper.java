package tvz.ffteam.myshrink;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteQueryBuilder;
import android.util.Log;
import android.widget.Toast;

import com.readystatesoftware.sqliteasset.SQLiteAssetHelper;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.sql.DatabaseMetaData;
import java.sql.SQLException;
import java.util.ArrayList;

import static android.util.Log.e;

/**
 * Created by Filip on 7.4.2015..
 */


public class DatabaseHelper extends SQLiteAssetHelper  {
    //PARAMETRI

    private static final String DATABASE_NAME = "CBM.db";
    private static final int DATABASE_VERSION = 1;

    private static String DB_PATH = "";
    private SQLiteDatabase mDataBase;

    public static final String KEY_USERNAME = "Username";
    public static final String KEY_PASSWORD = "Password";
    public static final String KEY_USERS_ADDICTION_TYPE = "AddictionType";
    public static final String KEY_USERS_ADDICTION_TYPE_ID = "AddictionTypeID";
    public static final String KEY_REPETITIONS = "Repetitions";
    public static final String KEY_USERS_SCORE = "Score";
    public static final String KEY_SCORE = "Score";
    public static final String KEY_IMAGEID = "ID";
    public static final String KEY_IMAGE_PATH = "Path";
    public static final String KEY_IMAGE_TYPEID = "TypeID";
    public static final String KEY_ADDICIONTYPE_ID_ = "ID";
    public static final String KEY_ADDICIONTYPE_TYPE_ = "AddictioType";
    public static final String KEY_TYPE_ID = "ID";
    public static final String KEY_TEXT_TEXT = "Text";
    public static final String KEY_TEXT_TYPE = "TypeID";
    public static final String KEY_TEXT_ID = "ID";

    private String TAG="DatabaseHelperClass";
    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null,DATABASE_VERSION );
    }

    public Cursor getPositiveText() {
        Log.d(TAG,"Metoda za dohvaćanje iz Baze");
        SQLiteDatabase db = getReadableDatabase();
        SQLiteQueryBuilder qb = new SQLiteQueryBuilder();
        Log.d(TAG,"Čitanje iz tablice Text");
        qb.setTables("Text");
        String [] sqlSelect = {"Text"};
        Cursor c = qb.query(db, sqlSelect, null, null, null, null, null);
        c.moveToFirst();
        return c;
    }

    public Cursor getNegativeText() {
        Log.d(TAG,"Metoda za dohvaćanje iz Baze");
        SQLiteDatabase db = getReadableDatabase();
        SQLiteQueryBuilder qb = new SQLiteQueryBuilder();
        Log.d(TAG,"Čitanje iz tablice Text");
        qb.setTables("Text");
        String [] sqlSelect = {"Text"};
        Cursor c = qb.query(db, sqlSelect, null, null, null, null, null);
        c.moveToFirst();
        return c;
    }

    public Cursor getPositiveImages() {
        Log.d(TAG,"Metoda za dohvaćanje iz Baze");
        SQLiteDatabase db = getReadableDatabase();
        SQLiteQueryBuilder qb = new SQLiteQueryBuilder();
        Log.d(TAG,"Čitanje iz tablice Text");
        qb.setTables("Text");
        String [] sqlSelect = {"Text"};
        Cursor c = qb.query(db, sqlSelect, null, null, null, null, null);
        c.moveToFirst();
        return c;
    }

    public Cursor getNegativeImages() {
        Log.d(TAG,"Metoda za dohvaćanje iz Baze");
        SQLiteDatabase db = getReadableDatabase();
        SQLiteQueryBuilder qb = new SQLiteQueryBuilder();
        Log.d(TAG,"Čitanje iz tablice Text");
        qb.setTables("Text");
        String [] sqlSelect = {"Text"};
        Cursor c = qb.query(db, sqlSelect, null, null, null, null, null);
        c.moveToFirst();
        return c;
    }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        }

    }
