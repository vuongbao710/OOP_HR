package oop.entity.Service;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Scanner;
import oop.entity.Enum.*;
import oop.entity.Object.*;

public class ChucNang {
    public static final int GIO_OT_TOI_DA = 40;

    public static void ChamCong(LocalTime time,LocalDate date, NhanVien nv){
        ChiTietChamCong cc = nv.getBcc().timKiem(date);

        if(cc == null){
        System.out.println("không có dòng chấm công ngày " + date
                + " trong bảng " + nv.getBcc().getThang() + "/" + nv.getBcc().getNam());
        cc = new ChiTietChamCong(nv.getBcc().taoMa(), date, nv.getMaNV());
        nv.getBcc().them(cc);
    }
        LocalTime GioVao, GioRa; 
    if(nv.getHinhThucDiLam().equalsIgnoreCase("Full-time")){
        NhanVienFullTime n = (NhanVienFullTime) nv;
        GioVao = n.getGioVaoCa();
        GioRa = n.getGioRaCa();
    } else {
        NhanVienPartTime n = (NhanVienPartTime) nv;
        GioVao = n.getGioVaoCa();
        GioRa = n.getGioRaCa();
    }
    cc.setGioVao(GioVao);
    cc.setGioRa(GioRa);

    cc.checkChamCong(time, date, nv);
}

    public static void XinNghiPhep(LocalDate date,LocalTime time, NhanVien nv, Scanner sc){
    
    if(KiemTraLoi.HuyTacVu(sc)){
        return ;
    }
        String MaNVNopDon = nv.getMaNV();
        String MaNVDuyetDon = nv.getMaQuanLyPhuTrach();
        DonXinPhep don = new DonXinPhep(MaNVNopDon, MaNVDuyetDon, date, time);
        LocalDate NgayBatDau;
        LocalDate NgayKetThuc;
        
        while(true){
            System.out.println("ngày bắt đầu nghỉ phép của bạn là ?\n Định dạng (DD/MM/YYYY)");
            String input = sc.nextLine().trim();
            NgayBatDau = KiemTraLoi.isValidDate(input,"dd/MM/yyyy");
            if(NgayBatDau!=null){
                if(NgayBatDau.isAfter(date) || NgayBatDau.isEqual(date)){
                break;}
                else{
                    System.out.println("Ngày nghỉ phép phải sau ngày hiệnt tại");
                }
            }
        }
            
        while(true){
            System.out.println("ngày kết thúc nghỉ phép của bạn là ?\n Định dạng (DD/MM/YYYY)");
            NgayKetThuc = KiemTraLoi.isValidDate(sc.nextLine(), "dd/MM/yyyy");
            if(NgayKetThuc!=null){
                if(NgayKetThuc.isAfter(date)){
                break;}
                else{
                    System.out.println("Ngày nghỉ phép phải sau ngày hiện tại");
                }
                if(!NgayKetThuc.isBefore(NgayBatDau)){ break; }
                else{ System.out.println("Ngày kết thúc không được trước ngày bắt đầu"); }
                
            }
        }
        System.out.println("lý do xin nghỉ phép của bạn là ?");
        String Message = sc.nextLine().trim();
        int khoangTgian = NgayNghiepVu.TinhNgay(NgayBatDau, NgayKetThuc);
        
        if(khoangTgian <= nv.getSoNgayNghiPhepNam()){
            don.setLoaiNghi(LoaiNghiPhep.NGHI_PHEP_NAM);
        }
        else{
            if(nv.getSoNgayNghiPhepNam()==0)
            System.out.println("Bạn đã sử dụng hết ngày nghỉ phép năm");
            else
            System.out.println("Số ngày nghỉ phép năm của bạn không đủ cho bạn sử dụng lần nghỉ này");
        while(true){
            System.out.println(" Loại nghỉ phép đơn này của bạn là ");
            System.out.println("1. Nghỉ phép bệnh");
            System.out.println("2. Nghỉ phép thai sản");
            int input = sc.nextInt();
            sc.nextLine();
            if(input >= 1 && input<=2){
                PhanLoaiNghiPhep(don,input);
                break;
            }
            
        }
        }
        don.setNgayBatDau(NgayBatDau);
        don.setNgayKetThuc(NgayKetThuc);
        don.setMessage(Message);
        
        
        HomThu.GuiThongBao( MaNVNopDon, MaNVDuyetDon, date, time, Message).setRequest(LoaiRequest.XIN_NGHI_PHEP);
        nv.getDsDon().them(don);
        NhatKyHoatDong.them("Nhân viên mã "+ MaNVNopDon + " nộp đơn nghỉ phép vào "+ time + " ngày " + date +
                "cho quản lý mã " + MaNVDuyetDon);
    }

    public static void DangKyLamOT(NhanVien nv, LocalDate date, LocalTime time, Scanner sc){
        if(KiemTraLoi.HuyTacVu(sc)){
        return;
        }
        String MaNV = nv.getMaNV();
        String MaQuanLyPhuTrach = nv.getMaQuanLyPhuTrach();
        String CaLam = nv.getCaLam();
        LocalDate GuardTime = date.plusDays(3);

        if(nv.getBcc().getSoGioOT() + 2 > GIO_OT_TOI_DA){
        System.out.println("Đã đủ " + GIO_OT_TOI_DA + " giờ OT trong kỳ lương này");
        return;
    }

        System.out.println("Số lượng ngày bạn muốn đăng ký làm OT:");
        int SoLuong = sc.nextInt();
        sc.nextLine();

        while(SoLuong > 0){
        System.out.println("Nhập ngày muốn đăng ký OT (đăng ký trước 3 ngày), định dạng dd/MM/yyyy:");
        String input = sc.nextLine().trim();
        LocalDate NgayDangKy = KiemTraLoi.isValidDate(input, "dd/MM/yyyy");

        if(NgayDangKy == null){
            System.out.println("Ngày tháng không hợp lệ");
            continue;
        }
        if(NgayDangKy.isBefore(GuardTime)){
            System.out.println("Ngày đi làm OT phải đăng ký trước 3 ngày");
            continue;
        }
        ChiTietChamCong ct = nv.getBcc().timKiem(NgayDangKy);
        if(ct == null){
            System.out.println("Chỉ đăng ký OT trong kỳ lương hiện tại (6 đến 5 tháng sau)");
            continue;
        }
        if(ct.isIsDangKyOT()){
            System.out.println("Ngày này bạn đã đăng ký OT rồi");
            continue;
        }

        HomThu.GuiThongBao(MaNV, MaQuanLyPhuTrach, date, time,
                "đăng ký làm OT ngày " + input + " ca " + CaLam, NgayDangKy)
            .setRequest(LoaiRequest.DANG_KY_LAM_OT);

        SoLuong--;
        NhatKyHoatDong.them("Nhân viên mã "+ MaNV + " đăng ký làm OT ngày "+ NgayDangKy + " vào thời điểm "+ time + " ngày " + date +
                " cho quản lý mã " + MaQuanLyPhuTrach);
    }
}

    public static void PhanLoaiNghiPhep (DonXinPhep don, int input){
        switch(input){
                case 1:
                    don.setLoaiNghi(LoaiNghiPhep.NGHI_PHEP_BENH);
                    break;
                case 2:
                    don.setLoaiNghi(LoaiNghiPhep.NGHI_PHEP_THAI_SAN);
                    break;
                default:
                    System.out.println("Khong hop le");
                    break;
        }
        
    }

    public static void LapDayNghiPhep(LocalDate NgayBatDau , LocalDate NgayKetThuc, NhanVien nv){
        for(LocalDate date = NgayBatDau ;!date.isAfter(NgayKetThuc) ; date = date.plusDays(1)){
            ChiTietChamCong cc = nv.getBcc().timKiem(date);
            if(cc == null){
            cc = new ChiTietChamCong(nv.getBcc().taoMa(), date, nv.getMaNV());
            nv.getBcc().them(cc);
        }
            cc.setTrangThai(LoaiTrangThaiChamCong.NGHI_PHEP);
        }
    }

/////////////////////////////////////////////////
    public static void LapDayChamCong(NhanVien nv, LocalDate date){
         //
            String HinhThucDiLam = nv.getHinhThucDiLam();
            LocalTime GioVao=null;
            LocalTime GioRa=null;
            if(HinhThucDiLam.equalsIgnoreCase("Full-time")){
                NhanVienFullTime nvCty = (NhanVienFullTime)nv;
                GioVao = nvCty.getGioVaoCa();
                GioRa = nvCty.getGioRaCa();
            }
            else{
                NhanVienPartTime nvCty = (NhanVienPartTime)nv;
                GioVao = nvCty.getGioVaoCa();
                GioRa = nvCty.getGioRaCa();
            }
            ChamCong(GioVao, date, nv);
            ChamCong(GioRa, date, nv);
        
    }
}

