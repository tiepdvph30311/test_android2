package dinhvantiep.tiepdvph30311.dinhvantiep_duanmau.DBhelper;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DBHelper extends SQLiteOpenHelper {
    private static final String DB_NAME="QUANLYTHUTHU";
    private static final int DB_VERSION=1;
    static final String CREATE_TABLE_THUTHU="CREATE TABLE ThuThu (\n" +
            "    maTT    TEXT PRIMARY KEY,\n" +
            "    hoTen   TEXT NOT NULL,\n" +
            "    matKhau TEXT NOT NULL\n" +
            ")\n";
    static final String CREATE_TABLE_THANHVIEN="CREATE TABLE ThanhVien (\n" +
            "    maTV    INTEGER PRIMARY KEY AUTOINCREMENT,\n" +
            "    hoTen   TEXT NOT NULL,\n" +
            "    namSinh TEXT NOT NULL\n" +
            ")";
    static final String CREATE_TABLE_SACH="CREATE TABLE Sach (\n" +
            "    maSach  INTEGER PRIMARY KEY AUTOINCREMENT,\n" +
            "    tenSach TEXT    NOT NULL,\n" +
            "    giaThue INTEGER NOT NULL,\n" +
            "    maLoai  INTEGER REFERENCES LoaiSach (maLoai) \n" +
            ")";
    static final String CREATE_TABLE_LOAISACH="CREATE TABLE LoaiSach (\n" +
            "    maLoai  INTEGER PRIMARY KEY AUTOINCREMENT,\n" +
            "    tenLoai TEXT    NOT NULL\n" +
            ")";
    static final String CREATE_TABLE_PHIEUMUON="CREATE TABLE PhieuMuon (\n" +
            "    maPM     INTEGER PRIMARY KEY AUTOINCREMENT,\n" +
            "    maTT     TEXT    REFERENCES ThuThu (maTT),\n" +
            "    maTV     INTEGER    REFERENCES ThanhVien (maTV),\n" +
            "    maSach   INTEGER REFERENCES Sach (maSach),\n" +
            "    tienThue INTEGER NOT NULL,\n" +
            "    ngay     DATE    NOT NULL,\n" +
            "    traSach  INTEGER NOT NULL\n" +
            ")";


    public DBHelper(@Nullable Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        //tao bang thu thu
        //tao data
        db.execSQL(CREATE_TABLE_THUTHU);
        db.execSQL(DB_SQL.INSERT_THU_THU);
        //tao bang thanh vien
        db.execSQL(CREATE_TABLE_THANHVIEN);
        db.execSQL(DB_SQL.INSERT_THANHVIEN);
        //tao bang sach
        db.execSQL(CREATE_TABLE_SACH);
        db.execSQL(DB_SQL.INSERT_SACH);
        //tao bang loai sach
        db.execSQL(CREATE_TABLE_LOAISACH);
        db.execSQL(DB_SQL.INSERT_LOAI_SACH);
        //tao bang phieu muon
        db.execSQL(CREATE_TABLE_PHIEUMUON);
        db.execSQL(DB_SQL.INSERT_PHIEU_MUON);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS ThuThu ");
        db.execSQL("DROP TABLE IF EXISTS ThanhVien ");
        db.execSQL("DROP TABLE IF EXISTS Sach ");
        db.execSQL("DROP TABLE IF EXISTS LoaiSach ");
        db.execSQL("DROP TABLE IF EXISTS PhieuMuon ");
        onCreate(db);


    }
}
