
package oop.entity;

public class NhanVienCapDuoi extends NhanVien {
    private String MaQuanLyPhuTrach;

    public NhanVienCapDuoi() {
        this.MaQuanLyPhuTrach = null;
    }
    
    public NhanVienCapDuoi(String MaQuanLyPhuTrach) {
        this.MaQuanLyPhuTrach = MaQuanLyPhuTrach;
    }
    
    public long TinhLuongCoBan(){
        return 5000000;
    }

    public String getMaQuanLyPhuTrach() {
        return MaQuanLyPhuTrach;
    }

    public void setMaQuanLyPhuTrach(String MaQuanLyPhuTrach) {
        this.MaQuanLyPhuTrach = MaQuanLyPhuTrach;
    }
    
}
