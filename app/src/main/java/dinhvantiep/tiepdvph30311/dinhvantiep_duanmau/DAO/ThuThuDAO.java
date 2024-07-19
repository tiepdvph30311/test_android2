package dinhvantiep.tiepdvph30311.dinhvantiep_duanmau.DAO;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

import dinhvantiep.tiepdvph30311.dinhvantiep_duanmau.DBhelper.DBHelper;
import dinhvantiep.tiepdvph30311.dinhvantiep_duanmau.Model.ThanhVien;
import dinhvantiep.tiepdvph30311.dinhvantiep_duanmau.Model.ThuThu;

public class ThuThuDAO {
    private SQLiteDatabase db;

    public ThuThuDAO(Context context) {
        DBHelper dbHelper = new DBHelper(context);
        db=dbHelper.getWritableDatabase();

    }
    public long insert(ThuThu thuthu){
        ContentValues values= new ContentValues();
        values.put("maTT",thuthu.getMaTT());
        values.put("hoTen",thuthu.getTaiKhoan());
        values.put("matKhau",thuthu.getMatKhau());
        return db.insert("ThuThu",null,values);
    }
    public int update(ThuThu thuthu){
        ContentValues values= new ContentValues();
        values.put("hoTen",thuthu.getTaiKhoan());
        values.put("matKhau",thuthu.getMatKhau());
        return db.update("ThuThu",values,"maTT=?",new String[]{String.valueOf(thuthu.getMaTT())});
    }
    public int delete(String id){
        return db.delete("ThuThu","maTT=?",new String[]{id});
    }
    @SuppressLint("Range")
    public List<ThuThu> getData(String sql, String...selectionArgs) {
        List<ThuThu> list = new ArrayList<>();
        Cursor c=db.rawQuery(sql,selectionArgs);
        while(c.moveToNext()) {
            ThuThu thuthu = new ThuThu();
            thuthu.setMaTT(c.getString(c.getColumnIndex("maTT")));
            thuthu.setTaiKhoan(c.getString(c.getColumnIndex("hoTen")));
            thuthu.setMatKhau(c.getString(c.getColumnIndex("matKhau")));
            list.add(thuthu);
        }
        return list;
    }
    public List<ThuThu> getAllData(){
        String sql="SELECT *FROM ThuThu";
        return getData(sql);
    }
    public ThuThu getID(String id){
        String sql="SELECT *FROM ThuThu WHERE maTT=?";
        List<ThuThu> list=getData(sql,id);
        return list.get(0);
    }
    public int checkLogin(String id,String password){
        String sql="SELECT *FROM ThuThu WHERE maTT=? ẠND matKhau=?";
        List<ThuThu> list=getData(sql,id,password);
        if(list.size()==0)
            return -1;
        return 1;

    }
}
