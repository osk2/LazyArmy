package osk2.lazyarmy;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBHelper extends SQLiteOpenHelper {
    private static final int VERSION = 1;
    private static final String DB_NAME= "lazyarmy.db";

    public DBHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, DB_NAME, factory, version);
    }

    public DBHelper(Context context) {
        this(context, DB_NAME, null, VERSION);
    }

    public DBHelper(Context context, String name, int version) {
        this(context, DB_NAME, null, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL("CREATE TABLE IF NOT EXISTS report ("
                        + "rid INTEGER PRIMARY KEY,"
                        + "pid INTEGER NOT NULL,"
                        + "rmid INTEGER NOT NULL,"
                        + "description TEXT,"
                        + "report_at TEXT,"
                        + "timestamp DATETIME DEFAULT CURRENT_TIMESTAMP"
                        + ")"
        );

        db.execSQL("CREATE TABLE IF NOT EXISTS people ("
                        + "pid INTEGER PRIMARY KEY,"
                        + "name TEXT NOT NULL,"
                        + "mobile TEXT,"
                        + "line TEXT"
                        + "timestamp DATETIME DEFAULT CURRENT_TIMESTAMP"
                        + ")"
        );

        db.execSQL("CREATE TABLE IF NOT EXISTS report_method ("
                        + "rmid INTEGER PRIMARY KEY,"
                        + "description TEXT,"
                        + "mode TEXT,"
                        + "timestamp DATETIME DEFAULT CURRENT_TIMESTAMP"
                        + ")"
        );

        db.execSQL("INSERT INTO report_method"
                        + "(description, mode)"
                        + "VALUES"
                        + "(`"+ R.string.report_via_sms + "`, `sms`)"
        );

        db.execSQL("INSERT INTO report_method"
                        + "(description, mode)"
                        + "VALUES"
                        + "(`"+ R.string.report_via_line + "`, `line`)"
        );

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    @Override
    public void onOpen(SQLiteDatabase db) {
        super.onOpen(db);
    }

    @Override
    public synchronized void close() {
        super.close();
    }

}
