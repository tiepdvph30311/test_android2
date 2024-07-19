package dinhvantiep.tiepdvph30311.dinhvantiep_duanmau.DAO;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import dinhvantiep.tiepdvph30311.dinhvantiep_duanmau.DBhelper.DBHelper;
import dinhvantiep.tiepdvph30311.dinhvantiep_duanmau.Model.PhieuMuon;
import dinhvantiep.tiepdvph30311.dinhvantiep_duanmau.Model.Sach;
import dinhvantiep.tiepdvph30311.dinhvantiep_duanmau.Model.Top;

public class PhieuMuonDAO {
    private SQLiteDatabase db;
    private Context context;
    private SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");

    public PhieuMuonDAO(Context context) {
        this.context = context;
        DBHelper dbHelper= new DBHelper(context);
        db=dbHelper.getWritableDatabase();
    }
    public long insert(PhieuMuon phieuMuon){
        ContentValues values= new ContentValues();
        values.put("maTT",phieuMuon.getMaTT());
        values.put("maTV",phieuMuon.getMaTV());
        values.put("maSach",phieuMuon.getMaSach());
        values.put("tienThue",phieuMuon.getTienThue());
        values.put("ngay",sdf.format(phieuMuon.getNgay()));
        values.put("traSach",phieuMuon.getTraSach());
        return db.insert("PhieuMuon",null,values);
    }
    public int update(PhieuMuon phieuMuon){
        ContentValues values= new ContentValues();
        values.put("maTT",phieuMuon.getMaTT());
        values.put("maTV",phieuMuon.getMaTV());
        values.put("maSach",phieuMuon.getMaSach());
        values.put("tienThue",phieuMuon.getTienThue());
        values.put("ngay",sdf.format(phieuMuon.getNgay()));
        values.put("traSach",phieuMuon.getTraSach());
        return db.update("PhieuMuon",values,"maPM=?",new String[]{String.valueOf(phieuMuon.getMaPM())});
    }
    public int delete(String id){
        return db.delete("PhieuMuon","maPM=?",new String[]{id});
    }
    @SuppressLint("Range")
    public List<PhieuMuon> getData(String sql, String...selecitonArgs) {
        List<PhieuMuon> list = new ArrayList<>();
        Cursor c=db.rawQuery(sql,selecitonArgs);
        while(c.moveToNext()) {
            PhieuMuon phieuMuon = new PhieuMuon();
            phieuMuon.setMaTT(c.getString(c.getColumnIndex("maTT")));
            phieuMuon.setMaTV(Integer.parseInt(c.getString(c.getColumnIndex("maTV"))));
            phieuMuon.setMaSach(Integer.parseInt(c.getString(c.getColumnIndex("maSach"))));
            phieuMuon.setTienThue(Integer.parseInt(c.getString(c.getColumnIndex("tienThue"))));
            try {
                phieuMuon.setNgay(sdf.parse(c.getString(c.getColumnIndex("ngay"))));
            }catch(ParseException e){
            e.printStackTrace();
            }
            phieuMuon.setTraSach(Integer.parseInt(c.getString(c.getColumnIndex("traSach"))));
            list.add(phieuMuon);
        }
        return list;
    }
    public List<PhieuMuon> getAllData(){
        String sql="SELECT *FROM PhieuMuon";
        return getData(sql);
    }
    public PhieuMuon getID(String id){
        String sql="SELECT *FROM PhieuMuon WHERE maPM=?";
        List<PhieuMuon> list=getData(sql,id);
        return list.get(0);
    }
    @SuppressLint("Range")
    public List<Top>  getTop(){
        String sqlTop="SELECT maSach,count(maSach) as soluong FROM PhieuMuon GROUP BY maSach ORDER BY soluong DESC LIMIT 10";
        List<Top> list= new ArrayList<>();
        SachDAO sachDAO= new SachDAO(context);
        Cursor c=db.rawQuery(sqlTop,null);
        while(c.moveToNext()){
            Top top= new Top();
            Sach sach=sachDAO.getID(c.getString(c.getColumnIndex("maSach")));
            top.setTenSach(sach.getTenSach());
            top.setSoLuong(Integer.parseInt(c.getString(c.getColumnIndex("soLuong"))));
        }
        return list;
    }
    @SuppressLint("Range")
    public int getDoanhThu(String tuNgay, String denNgay){
        String DoanhThu="SELECT SUM(tienThue) as doanhThu FROM PhieuMuon WHERE ngay BETWEEN ? AND ?";
        List<Integer> list= new ArrayList<Integer>();
        Cursor c=db.rawQuery(DoanhThu,new String[]{tuNgay,denNgay});
        while(c.moveToNext()){
            try{
                list.add(Integer.parseInt(c.getString(c.getColumnIndex("doanhThu"))));
            }catch (Exception e ){
                list.add(0);
            }
        }
        return list.get(0);
        }

}
