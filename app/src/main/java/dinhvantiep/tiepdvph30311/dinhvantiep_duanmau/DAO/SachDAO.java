package dinhvantiep.tiepdvph30311.dinhvantiep_duanmau.DAO;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

import dinhvantiep.tiepdvph30311.dinhvantiep_duanmau.DBhelper.DBHelper;
import dinhvantiep.tiepdvph30311.dinhvantiep_duanmau.Model.LoaiSach;
import dinhvantiep.tiepdvph30311.dinhvantiep_duanmau.Model.Sach;

public class SachDAO {
    private SQLiteDatabase db;

    public SachDAO(Context context) {
        DBHelper dbHelper= new DBHelper(context);
        db=dbHelper.getWritableDatabase();
    }
    public  long insert(Sach sach){
        ContentValues values= new ContentValues();
        values.put("tenSach",sach.getTenSach());
        values.put("giaThue",sach.getGiaThue());
        values.put("maLoai",sach.getMaLoai());
        return db.insert("Sach",null,values);
    }
    public int update(Sach sach){
        ContentValues values= new ContentValues();
        values.put("tenSach",sach.getTenSach());
        values.put("giaThue",sach.getGiaThue());
        values.put("maLoai",sach.getMaLoai());
        return db.update("Sach",values,"maSach=?",new String[]{String.valueOf(sach.getMaSach())});
    }
    public int delete(String id){
        return db.delete("Sach","maSach=?",new String[]{id});
    }
    @SuppressLint("Range")
    public List<Sach> getData(String sql, String...selectionArgs) {
        List<Sach> list = new ArrayList<>();
        Cursor c=db.rawQuery(sql,selectionArgs);
        while(c.moveToNext()) {
            Sach sach = new Sach();
            sach.setMaSach(Integer.parseInt(c.getString(c.getColumnIndex("maSach"))));
            sach.setTenSach(c.getString(c.getColumnIndex("tenSach")));
            sach.setGiaThue(Integer.parseInt(c.getString(c.getColumnIndex("giaThue"))));
            sach.setMaLoai(Integer.parseInt(c.getString(c.getColumnIndex("maLoai"))));
            list.add(sach);
        }
        return list;
    }
    public List<Sach> getAllData(){
        String sql="SELECT *FROM Sach";
        return getData(sql);
    }
    public Sach getID(String id){
        String sql="SELECT *FROM Sach WHERE maSach=?";
        List<Sach> list=getData(sql,id);
        return list.get(0);
    }
}
