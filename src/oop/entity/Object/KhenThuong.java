
package oop.entity.Object;

import oop.entity.Enum.LoaiKhenThuong;
import oop.entity.DanhSach.DanhSachNhanVien;
import java.time.LocalDate;

public class KhenThuong extends KhenThuongKyLuat {
    private LoaiKhenThuong Type;

    public KhenThuong(LoaiKhenThuong Type, String MaQD, LocalDate NgayBanHanh, double SoTienTacDong, String MaNhanVienRaQuyetDinh, String MaNhanVienNhanQuyetDinh) {
        super(MaQD, NgayBanHanh, SoTienTacDong, MaNhanVienRaQuyetDinh, MaNhanVienNhanQuyetDinh);
        this.Type = Type;
        this.LoaiQuyetDinh="Khen Thuong";
    }

    public KhenThuong(LoaiKhenThuong Type, String MaQD, LocalDate NgayBanHanh, String MaNhanVienRaQuyetDinh, String MaNhanVienNhanQuyetDinh) {
        super(MaQD, NgayBanHanh, MaNhanVienRaQuyetDinh, MaNhanVienNhanQuyetDinh);
        this.Type = Type;
        this.LoaiQuyetDinh="Khen Thuong";
    }
    
    
    public double tinhTacDongLuong(NhanVien nv){ //MaNV la MaNV nhan qd
        return 0;    
    }

    public LoaiKhenThuong getLoaiKhenThuong() {
        return Type;
    }

    public void setLoaiKhenThuong(LoaiKhenThuong Type) {
        this.Type = Type;
    }

    @Override
    public String toString() {
        return super.toString()+ "KhenThuong{" + "LoaiKhenThuong=" + Type + '}';
    }
    
    
}
