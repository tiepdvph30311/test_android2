package dinhvantiep.tiepdvph30311.dinhvantiep_duanmau.Model;

public class LoaiSach {
    private int maLoai;
    private String tenLoai;

    public LoaiSach() {
    }

    public int getMaLoai() {
        return maLoai;
    }

    public String getTenLoai() {
        return tenLoai;
    }

    public void setMaLoai(int maLoai) {
        this.maLoai = maLoai;
    }

    public void setTenLoai(String tenLoai) {
        this.tenLoai = tenLoai;
    }

    public LoaiSach(int maLoai, String tenLoai) {
        this.maLoai = maLoai;
        this.tenLoai = tenLoai;
    }
}
