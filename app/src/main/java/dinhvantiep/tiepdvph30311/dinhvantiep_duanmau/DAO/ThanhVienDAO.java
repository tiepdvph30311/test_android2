package dinhvantiep.tiepdvph30311.dinhvantiep_duanmau.DAO;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import dinhvantiep.tiepdvph30311.dinhvantiep_duanmau.DBhelper.DBHelper;
import dinhvantiep.tiepdvph30311.dinhvantiep_duanmau.Model.ThanhVien;

public class ThanhVienDAO {
    private SQLiteDatabase db;

    public ThanhVienDAO(Context context) {
        DBHelper dbHelper= new DBHelper(context);
        db=dbHelper.getWritableDatabase();
        db= dbHelper.getReadableDatabase();
    }
    public long insert(ThanhVien thanhVien){
        ContentValues values= new ContentValues();
        values.put("hoTen",thanhVien.getHoTen());
        values.put("namSinh",thanhVien.getNamSinh());
        return db.insert("ThanhVien",null,values);
    }
    public int update(ThanhVien thanhVien){
        ContentValues values= new ContentValues();
        values.put("hoTen",thanhVien.getHoTen());
        values.put("namSinh",thanhVien.getNamSinh());
        return db.update("ThanhVien",values,"maTV=?",new String[]{String.valueOf(thanhVien.getMaTV())});
    }
    public int delete(String id){
    return db.delete("ThanhVien","maTV=?",new String[]{id});
    }
    @SuppressLint("Range")
    public List<ThanhVien> getData(String sql,String...selectionArgs) {
        sql="SELECT *FROM ThanhVien";
        List<ThanhVien> list = new ArrayList<>();
        Cursor c=db.rawQuery(sql,selectionArgs);
        while(c.moveToNext()) {
            ThanhVien thanhVien = new ThanhVien();
            thanhVien.setMaTV(Integer.parseInt(c.getString(c.getColumnIndex("maTV"))));
            thanhVien.setHoTen(c.getString(c.getColumnIndex("hoTen")));
            thanhVien.setNamSinh(c.getString(c.getColumnIndex("namSinh")));
            Log.i("//=======",thanhVien.toString());
            list.add(thanhVien);
        }
        return list;
    }
    public List<ThanhVien> getAllData(){
        String sql="SELECT *FROM ThanhVien";
        return getData(sql);
    }
    public ThanhVien getID(String id){
        String sql="SELECT *FROM ThanhVien WHERE maTV=?";
        List<ThanhVien> list=getData(sql,id);
        return list.get(0);
    }


}
