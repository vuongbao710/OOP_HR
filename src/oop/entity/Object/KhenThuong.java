
package oop.entity.Object;

import oop.entity.Enum.LoaiKhenThuong;
import oop.entity.DanhSach.DanhSachNhanVien;
import java.time.LocalDate;

public class KhenThuong extends KhenThuongKyLuat {
    private LoaiKhenThuong Type;

    public KhenThuong(LoaiKhenThuong Type, LocalDate NgayBanHanh, double SoTienTacDong, String MaNhanVienRaQuyetDinh, String MaNhanVienNhanQuyetDinh, String LoaiQuyetDinh) {
        super(NgayBanHanh, MaNhanVienRaQuyetDinh, MaNhanVienNhanQuyetDinh);
        super.LoaiQuyetDinh = "Khen thưởng";
        this.Type = Type;
    }

 
    
    public double tinhTacDongLuong(NhanVien nv){ //MaNV la MaNV nhan qd
        return SoTienTacDong;    
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
