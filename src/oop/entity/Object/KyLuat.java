
package oop.entity.Object;

import oop.entity.Enum.LoaiKyLuat;
import java.time.LocalDate;

public class KyLuat extends KhenThuongKyLuat{
    private LoaiKyLuat LoaiKyLuat;

    public KyLuat(String Loai, String MaQD, LocalDate NgayBanHanh, double SoTienTacDong, String MaNhanVienRaQuyetDinh, String MaNhanVienNhanQuyetDinh) {
        super(MaQD, NgayBanHanh, SoTienTacDong, MaNhanVienRaQuyetDinh, MaNhanVienNhanQuyetDinh);
        this.LoaiKyLuat = LoaiKyLuat.NhanDien(Loai);
        this.LoaiQuyetDinh="Ky Luat";
    }

    public KyLuat(String Loai, String MaQD, LocalDate NgayBanHanh,String MaNhanVienRaQuyetDinh, String MaNhanVienNhanQuyetDinh) {
        super(MaQD, NgayBanHanh, MaNhanVienRaQuyetDinh,MaNhanVienNhanQuyetDinh);
        this.LoaiKyLuat = LoaiKyLuat.NhanDien(Loai);
        this.LoaiQuyetDinh="Ky Luat";
    }

    public KyLuat(String Loai,String MaQD, LocalDate NgayBanHanh, String MaNhanVienNhanQuyetDinh) {
        super(MaQD, NgayBanHanh, MaNhanVienNhanQuyetDinh);
        this.LoaiKyLuat = LoaiKyLuat.NhanDien(Loai);
        this.LoaiQuyetDinh="Ky Luat";
    }
    
    

    public LoaiKyLuat getLoaiKyLuat() {
        return LoaiKyLuat;
    }

    public void setLoaiKyLuat(LoaiKyLuat LoaiKyLuat) {
        this.LoaiKyLuat = LoaiKyLuat;
    }
    public double tinhTacDongLuong(String MaNV){
        return 0;
    }
    
    public double tinhTacDongLuong(long min, NhanVien nv){
        if(LoaiKyLuat.equals(LoaiKyLuat.DI_LAM_TRE) || LoaiKyLuat.equals(LoaiKyLuat.TAN_LAM_SOM)){
            double SoLuongMotGio = nv.getChucVu().getLuongMotGio();
            return -1*min*SoLuongMotGio;
        }
        else{
            return 0;
        }
    }

    @Override
    public String toString() {
        return super.toString()+ "KyLuat{" + "LoaiKyLuat=" + LoaiKyLuat + '}';
    }
            
    
    
}
