package oop.entity.DanhSach;
import java.time.LocalDate;
import java.util.Arrays;
import oop.entity.Enum.LoaiChucVu;
import oop.entity.Enum.LoaiKyLuat;
import oop.entity.Enum.LoaiViPham;
import oop.entity.Object.NhanVien;
import oop.entity.Object.ViPham;


public class DanhSachViPham {
    private ViPham[] dsViPham = new ViPham[1];
    private int dem = 0;

    public void them(ViPham vp) {
        if (dem == dsViPham.length) {
            dsViPham = Arrays.copyOf(dsViPham, dsViPham.length +1); 
        }
        dsViPham[dem++] = vp;
    }

    private ViPham[] LayViPhamConHieuLuc(LoaiViPham loai, LocalDate date) {
        ViPham[] kq = new ViPham[0];
        for (int i = 0; i < dem; i++) {
            ViPham vp = dsViPham[i];
            boolean conHan = vp.getNgayReset() == null || !vp.getNgayReset().isBefore(date);
            if (vp.getLoai() == loai && conHan) {
                kq = Arrays.copyOf(kq, kq.length + 1);
                kq[kq.length - 1] = vp;
            }
        }
        return kq;
    }
    
    public int SoLanViPham(LoaiViPham loai, LocalDate date){
        int soLanViPham = LayViPhamConHieuLuc(loai, date).length;
        return soLanViPham;
    }

    private LoaiKyLuat XacDinhKyLuat(ViPham[] ds, NhanVien nv) {
        if (ds.length == 0) return null;
        ViPham moiNhat = ds[ds.length - 1];
        LocalDate ngay = moiNhat.getNgayViPham();
        LoaiKyLuat kl = null;
        int soLan = ds.length;          

        switch (soLan) {
            case 1:
            case 2:
                kl = LoaiKyLuat.KHIEN_TRACH;
                moiNhat.setNgayReset(ngay.plusMonths(3));
                break;
            case 3:
                kl = LoaiKyLuat.KEO_DAI_THOI_HAN_NANG_LUONG;
                moiNhat.setNgayReset(ngay.plusMonths(6));
                break;
            case 4:
                if(nv.getChucVu() == LoaiChucVu.NHAN_VIEN){
                kl = LoaiKyLuat.KEO_DAI_THOI_HAN_NANG_LUONG;
                moiNhat.setNgayReset(ngay.plusYears(1));
                }
                else{
                kl = LoaiKyLuat.CACH_CHUC;
                moiNhat.setNgayReset(ngay.plusYears(3));
                }
                break;
            default:
                kl = LoaiKyLuat.SA_THAI;
                break;
        }
        return kl;
    }

    public LoaiKyLuat HoanChinhKyLuat(LoaiViPham loai, LocalDate date, NhanVien nv) {
            LoaiKyLuat kl = null;
            kl = XacDinhKyLuat(LayViPhamConHieuLuc(loai, date), nv);
            return kl;
    }
}