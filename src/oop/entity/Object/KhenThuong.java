
package oop.entity.Object;

import oop.entity.Enum.LoaiKhenThuong;
import oop.entity.DanhSach.DanhSachNhanVien;
import java.time.LocalDate;

public class KhenThuong extends KhenThuongKyLuat {
    private LoaiKhenThuong Type;
    private int SoLanThuongThamNien=3;

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
    
    
    public boolean kiemTraThamNien(LocalDate NgayVaoLam){
        LocalDate ThamNien3Nam = NgayVaoLam.plusYears(3);
        LocalDate ThamNien5Nam = NgayVaoLam.plusYears(5);
        LocalDate ThamNien10Nam = NgayVaoLam.plusYears(10);
        
        if(NgayVaoLam.isAfter(ThamNien3Nam)){
            if(SoLanThuongThamNien>=3){
                SoLanThuongThamNien--;
                return true;
            }
            else{
                if(NgayVaoLam.isAfter(ThamNien5Nam)){
                    if(SoLanThuongThamNien>=2){
                    SoLanThuongThamNien--;
                    return true;
                    }
                    else{
                        if(NgayVaoLam.isAfter(ThamNien10Nam)){
                            if(SoLanThuongThamNien>=1){
                            SoLanThuongThamNien--;
                            return true;
                        }
                            else{
                                return false;
                            }
                    }
                }
            }
        }
    }
        return false;
    }
    
    
    public boolean kiemTraLoaiKhenThuong(){
        if(this.Type.equals(LoaiKhenThuong.THUONG_TET)){
            if(NgayBanHanh.getMonthValue()!=11 && NgayBanHanh.getMonthValue()!=12){
                return false;
            }//xem xét nhân viên có biên bản phạm luật, nội quy nữa không?
            return true;
        }
        else if(this.Type.equals(LoaiKhenThuong.THUONG_SANG_KIEN)){
            return true;
        }
        else if(this.Type.equals(LoaiKhenThuong.THUONG_THAM_NIEN)){
            DanhSachNhanVien temp = new DanhSachNhanVien();
            NhanVien nv = temp.timKiem(MaNhanVienNhanQuyetDinh);
            if(nv==null){
                return false;
            }
            else{
                if(kiemTraThamNien(nv.NgayVaoLam)==false){
                    return false;
                }
                return true;
            }
        }
        else{
            return false;
        }
                
    }
    
    
    public double tinhTacDongLuong(String MaNhanVienNhanQuyetDinh){ //MaNV la MaNV nhan qd
        if(kiemTraLoaiKhenThuong() == true){
            if(Type==LoaiKhenThuong.THUONG_TET){
                NhanVien nv = truyXuatNhanVien(MaNhanVienNhanQuyetDinh);
                if(nv!=null){
                    return 2000000;
                }
            }
            if(Type==LoaiKhenThuong.THUONG_SANG_KIEN){
                NhanVien nv = truyXuatNhanVien(MaNhanVienNhanQuyetDinh);
                if(nv!=null){
                    return (nv.getChucVu().getLuongCoBan()*10)/100;
                }
            }
            if(Type==LoaiKhenThuong.THUONG_THAM_NIEN){
                NhanVien nv = truyXuatNhanVien(MaNhanVienNhanQuyetDinh);
                if(nv!=null){
                    return (nv.getChucVu().getLuongCoBan()*20)/100;
                }
            }
        }
        return -1; //try catch*/
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
