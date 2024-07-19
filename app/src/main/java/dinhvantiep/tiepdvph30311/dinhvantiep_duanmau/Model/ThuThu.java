package dinhvantiep.tiepdvph30311.dinhvantiep_duanmau.Model;

public class ThuThu {
    private String maTT;
    private String taiKhoan;
    private String matKhau;

    public ThuThu(String maTT, String taiKhoan, String matKhau) {
        this.maTT = maTT;
        this.taiKhoan = taiKhoan;
        this.matKhau = matKhau;
    }

    public ThuThu() {
    }

    public String getMaTT() {
        return maTT;
    }

    public void setMaTT(String maTT) {
        this.maTT = maTT;
    }

    public String getTaiKhoan() {
        return taiKhoan;
    }

    public void setTaiKhoan(String taiKhoan) {
        this.taiKhoan = taiKhoan;
    }

    public String getMatKhau() {
        return matKhau;
    }

    public void setMatKhau(String matKhau) {
        this.matKhau = matKhau;
    }
}
