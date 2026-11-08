
package oop.entity.Object;
import oop.entity.DanhSach.DanhSachNhanVien;
import java.time.LocalDate;
public abstract class KhenThuongKyLuat {
    protected String MaQD;
    protected LocalDate NgayBanHanh;
    protected double SoTienTacDong;
    protected String MaNhanVienRaQuyetDinh;
    protected String MaNhanVienNhanQuyetDinh;
    protected String LoaiQuyetDinh;

    public KhenThuongKyLuat(String MaQD, LocalDate NgayBanHanh, double SoTienTacDong, String MaNhanVienRaQuyetDinh, String MaNhanVienNhanQuyetDinh) {
        this.MaQD = MaQD;
        this.NgayBanHanh = NgayBanHanh;
        this.SoTienTacDong = SoTienTacDong;
        this.MaNhanVienRaQuyetDinh = MaNhanVienRaQuyetDinh;
        this.MaNhanVienNhanQuyetDinh = MaNhanVienNhanQuyetDinh;
    }

    public KhenThuongKyLuat(String MaQD, LocalDate NgayBanHanh,String MaNhanVienRaQuyetDinh, String MaNhanVienNhanQuyetDinh) {
        this.MaQD = MaQD;
        this.NgayBanHanh = NgayBanHanh;
        this.MaNhanVienRaQuyetDinh = MaNhanVienRaQuyetDinh;
        this.MaNhanVienNhanQuyetDinh = MaNhanVienNhanQuyetDinh;
    }

    public KhenThuongKyLuat(String MaQD, LocalDate NgayBanHanh, String MaNhanVienNhanQuyetDinh) {
        this.MaQD = MaQD;
        this.NgayBanHanh = NgayBanHanh;
        this.MaNhanVienNhanQuyetDinh = MaNhanVienNhanQuyetDinh;
    }
    
    
    
    public abstract double tinhTacDongLuong(String MaNV);
    
    public NhanVien truyXuatNhanVien (String MaNV){ //////////
        DanhSachNhanVien temp = new DanhSachNhanVien();
        NhanVien nv = temp.timKiem(MaNV);
        return nv;
    }

    public String getMaQD() {
        return MaQD;
    }

    public void setMaQD(String MaQD) {
        this.MaQD = MaQD;
    }

    public LocalDate getNgayBanHanh() {
        return NgayBanHanh;
    }

    public void setNgayBanHanh(LocalDate NgayBanHanh) {
        this.NgayBanHanh = NgayBanHanh;
    }

    public double getSoTienTacDong() {
        return SoTienTacDong;
    }

    public void setSoTienTacDong(double SoTienTacDong) {
        this.SoTienTacDong = SoTienTacDong;
    }

    public String getMaNhanVienRaQuyetDinh() {
        return MaNhanVienRaQuyetDinh;
    }

    public void setMaNhanVienRaQuyetDinh(String MaNhanVienRaQuyetDinh) {
        this.MaNhanVienRaQuyetDinh = MaNhanVienRaQuyetDinh;
    }

    public String getMaNhanVienNhanQuyetDinh() {
        return MaNhanVienNhanQuyetDinh;
    }

    public void setMaNhanVienNhanQuyetDinh(String MaNhanVienNhanQuyetDinh) {
        this.MaNhanVienNhanQuyetDinh = MaNhanVienNhanQuyetDinh;
    }

    public String getLoaiQuyetDinh() {
        return LoaiQuyetDinh;
    }

    public void setLoaiQuyetDinh(String LoaiQuyetDinh) {
        this.LoaiQuyetDinh = LoaiQuyetDinh;
    }

    @Override
    public String toString() {
        return "KhenThuongKyLuat{" + "MaQD=" + MaQD + ", NgayBanHanh=" + NgayBanHanh + ", SoTienTacDong=" + SoTienTacDong + ", MaNhanVienRaQuyetDinh=" + MaNhanVienRaQuyetDinh + ", MaNhanVienNhanQuyetDinh=" + MaNhanVienNhanQuyetDinh + ", LoaiQuyetDinh=" + LoaiQuyetDinh + '}';
    }
    
    
}
