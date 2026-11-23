package oop.entity.Service;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import oop.entity.DanhSach.*;
import oop.entity.Enum.*;
import oop.entity.Object.*;

public class VanHanh {
    
    public static void KiemTraToiNgay(NhanVien nv, LocalDate date, LocalTime time, String MaADMIN){
        if(nv.isDaNghiViec()) return;
        String maQL = nv.getMaQuanLyPhuTrach();
        
        for(ThongBao tb : nv.getDsThongBao().timKiemChuaGiaiQuyet(date)){
        HomThu.GuiThongBao(MaADMIN, nv.getMaNV(), date, time, "Request mã " + tb.getMaThongBao()
                + " (" + tb.getRequest().getMoTa() + ") sẽ hết hạn vào ngày mai, vui lòng giải quyết");
    }
        nv.getDsThongBao().chuyenQuaTrangThaiHetHan(date);

        HopDong hd = nv.getHopDong();
        if(hd != null && hd.getNgayHetHan() != null && date.isAfter(hd.getNgayHetHan())
        && hd.getTrangThaiHopDong() == TRANG_THAI_HOP_DONG.DANG_CO_HIEU_LUC){
        hd.setTrangThaiHopDong(TRANG_THAI_HOP_DONG.HET_HAN);
        HomThu.GuiThongBao(MaADMIN, nv.getMaNV(), date, time, "Hợp đồng " + hd.getMaHopDong() + " của bạn đã hết hạn, đang chờ quản lý quyết định gia hạn");
        if(maQL != null){
        HomThu.GuiYeuCau(MaADMIN, maQL, date, time,
                "Hợp đồng " + hd.getMaHopDong() + " của nhân viên " + nv.getMaNV() + " đã hết hạn, cần gia hạn hoặc từ chối gia hạn",
                LoaiRequest.GIA_HAN_HOP_DONG, nv.getMaNV());
    }
}

        if(nv.ToiNgayTongKetDinhKy(date)){
        nv.ResetQuyTrinhChamCong(date);
        HomThu.GuiThongBao(MaADMIN, nv.getMaNV(), date, time, "Đã reset bảng chấm công, chuyển qua tính theo tháng mới");
    }

        if(nv.ToiNgayXetNangLuong(date) && maQL != null && DangTrongHopDong(nv, date)){
        HomThu.GuiYeuCau(MaADMIN, maQL, date, time, "Tới ngày xét nâng lương của nhân viên có mã " + nv.getMaNV(),
                LoaiRequest.XEM_XET_TANG_LUONG, nv.getMaNV());
    }

        if(date.getMonthValue() == 1 && date.getDayOfMonth() == 1){
        nv.setSoNgayNghiPhepNam(12);
        if(nv.getHinhThucDiLam() != null && nv.getHinhThucDiLam().equalsIgnoreCase("Full-time")
                && DangTrongHopDong(nv, date)){
            NhanVienFullTime nvFull = (NhanVienFullTime) nv;
            double TienThuongTet = nvFull.TinhToanTienThuongTet(LocalDate.of(date.getYear() - 1, 1, 1));
            KhenThuong khen = new KhenThuong(LoaiKhenThuong.THUONG_TET, date, TienThuongTet, MaADMIN, nv.getMaNV());
            nvFull.getDsktkl().them(khen);
            HomThu.GuiThongBao(MaADMIN, nv.getMaNV(), date, time, "thưởng tết " + TienThuongTet + " vnd ").setRequest(LoaiRequest.THONG_BAO);
        }
    }
}

    public static void KiemTraToiNgayHeThong(LocalDate date, LocalTime time, String MaADMIN){
    Boss.getDsThongBao().chuyenQuaTrangThaiHetHan(date);
    if("ngày kết thúc quý".equalsIgnoreCase(NgayNghiepVu.kiemTraToiNgay(date))){
        for(NhanVien NV : DanhSachNhanVien.getDsnv()){
            if(NV.getChucVu() == LoaiChucVu.QUAN_LY && !NV.isDaNghiViec()){
                HomThu.GuiYeuCau(MaADMIN, NV.getMaNV(), date, time,
                        "Đã đến thời hạn chấm KPI của nhân viên bạn phụ trách", LoaiRequest.DANH_GIA_KPI, null);
            }
        }
    }
    for(DotTuyenDung dot : new ArrayList<>(DanhSachDotTuyenDung.getDsDot())){
        if(dot.getTrangThai() == TRANG_THAI_DOT.DANG_TUYEN_DUNG && date.isAfter(dot.getNgayHetHan())){
            dot.setTrangThai(TRANG_THAI_DOT.KET_THUC); 
            HomThu.GuiThongBao(MaADMIN, dot.getMaNguoiTuyen(), date, time,
                    "Đợt tuyển dụng " + dot.getMaDotTuyenDung() + " đã quá hạn và được đóng tự động");
        }
    }
    for(KhoaDaoTao khoa : DanhSachKhoaDaoTao.getDskdt()){ 
        if(khoa.getNgayKetThuc() != null && date.isEqual(khoa.getNgayKetThuc().plusDays(1))){
            HomThu.GuiYeuCau(MaADMIN, khoa.getMaNVDaoTao(), date, time,
                    "Khóa đào tạo " + khoa.getMaKhoaHoc() + " đã kết thúc, vui lòng chấm điểm ứng viên",
                    LoaiRequest.CHAM_DIEM_DAO_TAO, khoa.getMaKhoaHoc());
        }
    }
}

    public static void KiemTraVanHanhLuongNhanSu(LocalDate date, LocalTime time, String MaADMIN, LocalDate NgayKetThuc){
    for(LocalDate d = date; !d.isAfter(NgayKetThuc); d = d.plusDays(1)){
        KiemTraToiNgayHeThong(d, time, MaADMIN);
        for(NhanVien nv : new ArrayList<>(DanhSachNhanVien.getDsnv())){
            KiemTraToiNgay(nv, d, time, MaADMIN);
            if(!nv.isDaNghiViec() && DangTrongHopDong(nv, d)){
                ChucNang.LapDayChamCong(nv, d);
}
        }
    }
}

    private static boolean DangTrongHopDong(NhanVien nv, LocalDate date){
        HopDong hd = nv.getHopDong();
        if(hd == null) return true; 
        if(hd.getNgayBatDauHopDong() != null && date.isBefore(hd.getNgayBatDauHopDong())) return false;
        if(hd.getNgayHetHan() != null && date.isAfter(hd.getNgayHetHan())) return false;
        return true;
}
}
