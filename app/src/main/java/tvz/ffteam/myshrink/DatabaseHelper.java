package tvz.ffteam.myshrink;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteQueryBuilder;
import android.util.Log;
import android.widget.Toast;

import com.readystatesoftware.sqliteasset.SQLiteAssetHelper;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.security.Timestamp;
import java.sql.DatabaseMetaData;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

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
    SQLiteDatabase db = getReadableDatabase();
    SQLiteQueryBuilder qb = new SQLiteQueryBuilder();
    public Cursor getPositiveText() {

        Log.d(TAG,"Čitanje iz tablice Text - positive text");
        qb.setTables("Text");
        String sql = "SELECT Text FROM Text WHERE TypeID = '1';";
        Cursor c = db.rawQuery(sql,null);
        c.moveToFirst();
        return c;
    }

    public Cursor getNegativeText() {
        Log.d(TAG,"Čitanje iz tablice Text - negative text");
        qb.setTables("Text");
        String sql = "SELECT Text FROM Text WHERE TypeID = '2';";
        Cursor c = db.rawQuery(sql,null);
        c.moveToFirst();
        return c;
    }

    public Cursor getPositiveImages() {
        Log.d(TAG,"Čitanje iz tablice Images");
        qb.setTables("Images");
        String sql = "SELECT Path FROM Images WHERE TypeID = '1';";
        Cursor c = db.rawQuery(sql,null);
        c.moveToFirst();
        return c;
    }

    public Cursor getNegativeImages() {
        Log.d(TAG,"Čitanje iz tablice Images");
        qb.setTables("Images");
        String sql = "SELECT Path FROM Images WHERE TypeID = '2';";
        Cursor c = db.rawQuery(sql,null);;
        c.moveToFirst();
        return c;
    }
    public void setScoreInDatabase(float score) {
        Log.d(TAG,"Zapisivanje rezultata u bazu");
        qb.setTables("Score");
        SimpleDateFormat dateFormat =new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
        Date myDate = new Date();
        String sql = "INSERT INTO Score (ID,Date,Score) VALUES (1,'"+dateFormat.format(myDate)+"',"+score+");";
        db.execSQL(sql);
        Log.d(TAG,"Rezultati zapisani u bazu!");
    }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        }

    }
