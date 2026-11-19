
package oop.entity.Object;

import oop.entity.Enum.LoaiKyLuat;
import java.time.LocalDate;
import oop.entity.Enum.LoaiViPham;

public class KyLuat extends KhenThuongKyLuat{
    private LoaiKyLuat loaiKyLuat;
    private LoaiViPham loaiViPham;
    private long SoPhutPhatChamCong=0;

    public KyLuat(LoaiKyLuat loaiKyLuat, LoaiViPham loaiViPham, LocalDate NgayBanHanh, String MaNhanVienRaQuyetDinh, String MaNhanVienNhanQuyetDinh) {
        super(NgayBanHanh, MaNhanVienRaQuyetDinh, MaNhanVienNhanQuyetDinh);
        super.LoaiQuyetDinh = "Kỷ luật";
        this.loaiKyLuat = loaiKyLuat;
        this.loaiViPham = loaiViPham;
    }

    public LoaiViPham getLoaiViPham() {
        return loaiViPham;
    }

    public void setLoaiViPham(LoaiViPham loaiViPham) {
        this.loaiViPham = loaiViPham;
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
        if(this.loaiViPham==LoaiViPham.VI_PHAM_CHAM_CONG){
            SoTienTacDong = -1*nv.TinhLuongMotPhut()*SoPhutPhatChamCong;
            return SoTienTacDong;
        }
        if(this.loaiViPham==LoaiViPham.NGHI_KHONG_PHEP){
            SoTienTacDong =  -1*nv.TinhLuongMotGio();
            return SoTienTacDong;
        }
        return 0;
    }

    @Override
    public String toString() {
        return super.toString()+ "KyLuat{" + "LoaiKyLuat=" + loaiKyLuat + '}';
    }
            
    
    
}
