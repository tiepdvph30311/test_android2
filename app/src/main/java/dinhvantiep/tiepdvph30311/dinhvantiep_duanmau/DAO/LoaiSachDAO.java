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
import dinhvantiep.tiepdvph30311.dinhvantiep_duanmau.Model.ThanhVien;

public class LoaiSachDAO {
    private SQLiteDatabase db;

    public LoaiSachDAO(Context context) {
        DBHelper dbHelper= new DBHelper(context);
        db=dbHelper.getWritableDatabase();
    }
    public  long insert(LoaiSach loaiSach){
        ContentValues values= new ContentValues();
//        values.put("maLoai",loaiSach.getMaLoai());
        values.put("tenLoai",loaiSach.getTenLoai());
        return db.insert("LoaiSach",null,values);
    }
    public int update(LoaiSach loaiSach){
        ContentValues values= new ContentValues();
//        values.put("maLoai",loaiSach.getMaLoai());
        values.put("tenLoai",loaiSach.getTenLoai());
        return db.update("LoaiSach",values,"maLoai=?",new String[]{String.valueOf(loaiSach.getMaLoai())});
    }
    public int delete(String id){
        return db.delete("LoaiSach","maLoai=?",new String[]{id});
    }
    @SuppressLint("Range")
    public List<LoaiSach> getData(String sql, String...selectionArgs) {
        List<LoaiSach> list = new ArrayList<>();
        Cursor c=db.rawQuery(sql,selectionArgs);
        while(c.moveToNext()) {
            LoaiSach loaiSach = new LoaiSach();
            loaiSach.setMaLoai(Integer.parseInt(c.getString(c.getColumnIndex("maLoai"))));
            loaiSach.setTenLoai(c.getString(c.getColumnIndex("tenLoai")));
            list.add(loaiSach);
        }
        return list;
    }
    public List<LoaiSach> getAllData(){
        String sql="SELECT *FROM LoaiSach";
        return getData(sql);
    }
    public LoaiSach getID(String id){
        String sql="SELECT *FROM LoaiSach WHERE maLoai=?";
        List<LoaiSach> list=getData(sql,id);
        return list.get(0);
    }

}
