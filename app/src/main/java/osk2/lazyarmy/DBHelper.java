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
        db.execSQL("CREATE TABLE IF NOT EXISTS leader ("
                        + "lid INTEGER PRIMARY KEY,"
                        + "name TEXT,"
                        + "mobile TEXT NOT NULL,"
                        + "line TEXT,"
                        + "description TEXT,"
                        + "timestamp TEXT"
                        + ")"
        );

        db.execSQL("CREATE TABLE IF NOT EXISTS report ("
                        + "rid INTEGER PRIMARY KEY,"
                        + "lid INTEGER,"
                        + "description TEXT,"
                        + "report_mode INTEGER NOT NULL,"
                        + "report_target INTEGER,"
                        + "report_at TEXT,"
                        + "report_from TEXT,"
                        + "report_to TEXT,"
                        + "timestamp TEXT"
                        + ")"
        );

        db.execSQL("CREATE TABLE IF NOT EXISTS report_mode ("
                        + "rmid INTEGER PRIMARY KEY,"
                        + "description TEXT,"
                        + "mode TEXT,"
                        + "timestamp TEXT"
                        + ")"
        );

        db.execSQL("CREATE TABLE IF NOT EXISTS report_target ("
                        + "rtid INTEGER PRIMARY KEY,"
                        + "description TEXT,"
                        + "target TEXT,"
                        + "timestamp TEXT"
                        + ")"
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
