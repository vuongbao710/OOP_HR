
package oop.entity.Object;

import oop.entity.Enum.LoaiKyLuat;
import java.time.LocalDate;
import oop.entity.Service.ThaoTac;

public class KyLuat extends KhenThuongKyLuat{
    private LoaiKyLuat loaiKyLuat;
    private long SoPhutPhatChamCong;

    public KyLuat(String Loai, String MaQD, LocalDate NgayBanHanh, double SoTienTacDong, String MaNhanVienRaQuyetDinh, String MaNhanVienNhanQuyetDinh) {
        super(MaQD, NgayBanHanh, SoTienTacDong, MaNhanVienRaQuyetDinh, MaNhanVienNhanQuyetDinh);
        this.loaiKyLuat = LoaiKyLuat.NhanDien(Loai);
        this.LoaiQuyetDinh="Ky Luat";
    }

    public KyLuat(String Loai, String MaQD, LocalDate NgayBanHanh,String MaNhanVienRaQuyetDinh, String MaNhanVienNhanQuyetDinh) {
        super(MaQD, NgayBanHanh, MaNhanVienRaQuyetDinh,MaNhanVienNhanQuyetDinh);
        this.loaiKyLuat = LoaiKyLuat.NhanDien(Loai);
        this.LoaiQuyetDinh="Ky Luat";
    }

    public KyLuat(String Loai,String MaQD, LocalDate NgayBanHanh, String MaNhanVienNhanQuyetDinh) {
        super(MaQD, NgayBanHanh, MaNhanVienNhanQuyetDinh);
        this.loaiKyLuat = LoaiKyLuat.NhanDien(Loai);
        this.LoaiQuyetDinh="Ky Luat";
    }
    
    

    public LoaiKyLuat getLoaiKyLuat() {
        return loaiKyLuat;
    }

    public void setLoaiKyLuat(LoaiKyLuat LoaiKyLuat) {
        this.loaiKyLuat = LoaiKyLuat;
    }

    public long getSoPhutPhatChamCong() {
        return SoPhutPhatChamCong;
    }

    public void setSoPhutPhatChamCong(long SoPhutPhatChamCong) {
        this.SoPhutPhatChamCong = SoPhutPhatChamCong;
    }
    
    
    public double tinhTacDongLuong(NhanVien nv){
        if(this.loaiKyLuat==LoaiKyLuat.DI_LAM_TRE || loaiKyLuat==LoaiKyLuat.TAN_LAM_SOM){
            return -1*nv.TinhLuongMotPhut()*SoPhutPhatChamCong;
        }
        return 0;
    }

    @Override
    public String toString() {
        return super.toString()+ "KyLuat{" + "LoaiKyLuat=" + loaiKyLuat + '}';
    }
            
    
    
}
