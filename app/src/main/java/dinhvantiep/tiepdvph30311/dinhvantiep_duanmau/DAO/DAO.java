package dinhvantiep.tiepdvph30311.dinhvantiep_duanmau.DAO;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import dinhvantiep.tiepdvph30311.dinhvantiep_duanmau.DBhelper.DBHelper;

public class DAO {
    private SQLiteDatabase db;

    public DAO(Context context) {
        DBHelper helper= new DBHelper(context);
        db=helper.getWritableDatabase();
    }
}
