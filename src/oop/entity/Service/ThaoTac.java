
package oop.entity.Service;

import java.time.LocalTime;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Scanner;
import oop.entity.DanhSach.*;
import oop.entity.Enum.LoaiNghiPhep;
import oop.entity.Enum.LoaiRequest;
import oop.entity.Enum.LoaiTrangThaiCuaDon;
import oop.entity.Enum.TrangThaiDocThongBao;
import oop.entity.Object.*;

public class ThaoTac {
    public static TaiKhoan DangNhap(Scanner sc, DanhSachTaiKhoan dstk){
        
        while(true){
            boolean found = false;
            System.out.println("Nhap ten dang nhap");
            System.out.println("Nhap mat khau");
            String tenTest = sc.nextLine();
            for(TaiKhoan tk : dstk.getDstk()){
                if(tk.getTenDangNhap().equalsIgnoreCase(tenTest)){
                    found=true;
                    if(tk.KiemTraTrangThaiHoatDong()==true){
                        if(tk.NhapMatKhau(sc))return tk;
                    }
                    else{
                    System.out.println("Tai khoan cua ban da bi khoa");
                    return null;
                    }
                }
            }
            if(!found){
                System.out.println("Khong tim thay tai khoan tuong ung");
            } 
        }
    }
    
    
    public static void ChamCong(LocalTime time,LocalDate date, NhanVien nv){
        
        if(nv.getBcc().KiemTraTonTai(date)==false){
        
        String MaChiTietChamCong = nv.getBcc().taoMa();
        String MaNV = nv.getMaNV();
        LocalTime GioVao;
        LocalTime GioRa;
        if(nv.getHinhThucDiLam().equalsIgnoreCase("Full-time")){
            NhanVienFullTime nvCty = (NhanVienFullTime) nv;
            GioVao = nvCty.getGioVaoCa();
            GioRa = nvCty.getGioRaCa();
            
        }
        else{
            NhanVienPartTime nvCty = (NhanVienPartTime) nv;
            GioVao = nvCty.getGioVaoCa();
            GioRa = nvCty.getGioRaCa();
        } 
        ChiTietChamCong cc = new ChiTietChamCong(MaChiTietChamCong, date, MaNV, GioVao, GioRa);
        cc.checkChamCong(time,date,nv);
        nv.getBcc().them(cc);
        }
        else{
            ChiTietChamCong cc = nv.getBcc().timKiem(date);
            cc.checkChamCong(time,date,nv);
        }
    }
    
    
    
    public static LocalDate isValidDate(String dateStr, String pattern) {
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern(pattern);
            LocalDate Date = LocalDate.parse(dateStr, formatter);
            return Date; // Hợp lệ
        } catch (DateTimeParseException e) {
            System.out.println("Khong hop le");
            return null; // Không hợp lệ hoặc sai định dạng
        }
    }
    
    
    public static void XinNghiPhep(LocalDate date,LocalTime time, NhanVien nv, Scanner sc, DanhSachNhanVien dsnv){
        String MaDon = nv.getDsDon().taoMa();
        String MaNVNopDon = nv.getMaNV();
        String MaNVDuyetDon = nv.getMaQuanLyPhuTrach();
        DonXinPhep don = new DonXinPhep(MaDon ,MaNVNopDon, MaNVDuyetDon, date, time);
        LocalDate NgayBatDau;
        LocalDate NgayKetThuc;
        
        while(true){
            System.out.println("ngày bắt đầu nghỉ phép của bạn là ?\n Định dạng (DD/MM/YYYY)");
            String input = sc.nextLine();
            NgayBatDau = isValidDate(input,"dd/MM/yyyy");
            if(NgayBatDau!=null){
                if(NgayBatDau.isAfter(date) || NgayBatDau.isEqual(date)){
                break;}
                else{
                    System.out.println("Ngay nghi phep phai sau ngay hien tai");
                }
            }
        }
            
        while(true){
            System.out.println("ngày kết thúc nghỉ phép của bạn là ?\n Định dạng (DD/MM/YYYY)");
            NgayKetThuc = isValidDate(sc.nextLine(), "dd/MM/yyyy");
            if(NgayKetThuc!=null){
                if(NgayKetThuc.isAfter(date)){
                break;}
                else{
                    System.out.println("Ngay nghi phep phai sau ngay hien tai");
                }
            }
        }
        System.out.println("lý do xin nghỉ phép của bạn là ?");
        String Message = sc.nextLine();
        
        while(true){
            System.out.println("Loại nghỉ phép của bạn là ");
            System.out.println("0. Nghỉ phép năm");
            System.out.println("1. Nghỉ phép bệnh");
            System.out.println("2. Nghỉ phép thai sản");
            int input = sc.nextInt();
            sc.nextLine();
            if(input >= 0 && input<2){
                PhanLoaiNghiPhep(don,input);
                break;
            }
            
        }
        
        don.setNgayBatDau(NgayBatDau);
        don.setNgayKetThuc(NgayKetThuc);
        don.setMessage(Message);
        nv.getDsDon().them(don);
        
        GuiThongBao(dsnv, MaNVNopDon, MaNVDuyetDon, date, time, Message).setRequest(LoaiRequest.XIN_NGHI_PHEP);
        
    }
    
    public static ThongBao GuiThongBao (DanhSachNhanVien dsnv, String MaNguoiGui, String MaNguoiNhan, LocalDate date, LocalTime time, String Message){
        NhanVien nv = dsnv.timKiem(MaNguoiNhan);
        String MaThongBao = nv.getDsThongBao().taoMa();
        ThongBao ThongBao = new ThongBao(MaThongBao, MaNguoiGui, date, time, Message);
        nv.getDsThongBao().them(ThongBao);
        return ThongBao;
    }
    
    public static void PhanLoaiNghiPhep (DonXinPhep don, int input){
        switch(input){
                case 0:
                    don.setLoaiNghi(LoaiNghiPhep.NGHI_PHEP_NAM);
                    break;
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
    
    
    public static void DuyetDonXinPhep(String MaNguoiDuyetDon, String MaNguoiGuiDon, DanhSachNhanVien dsnv, ThongBao ThongBao, Scanner sc){
        NhanVien nv = dsnv.timKiem(MaNguoiGuiDon);
        LocalDate date = ThongBao.getDate();
        LocalTime time  = ThongBao.getTime();
        DonXinPhep don = nv.getDsDon().timKiem(date,time);
        
        while(true){
            System.out.println("bạn có duyệt đơn của nhân viên không ? \n0. Không duyệt\n1. Duyệt");
            int input = sc.nextInt();
            sc.nextLine();
            if(input==0 || input==1){
                if(input==0){
                    System.out.println("Nhập lý do không duyệt đơn cho nhân viên của bạn biết: ");
                    String Message = sc.nextLine();
                    don.setTrangThai(LoaiTrangThaiCuaDon.KHONG_DUYET);
                    GuiThongBao(dsnv, MaNguoiDuyetDon, MaNguoiGuiDon, date, time, Message);
                }
                else{
                    don.setTrangThai(LoaiTrangThaiCuaDon.DUYET);
                }
                break;
            }
            else{
                System.out.println("Không hợp lệ, vui lòng nhập lại");
            }
        }
    }
    
    public static void checkInbox(String MaNguoiDuyetDon, DanhSachNhanVien dsnv, Scanner sc, DanhSachThongBao dsThongBao){
        for(ThongBao tb : dsThongBao.getDsThongBao()){
            if(tb.getTrangThai().equals(TrangThaiDocThongBao.CHUA_DOC)){
                System.out.println(tb);
                while(true){
                    System.out.println("Xử lý request này bây giờ không? \n0.không\n1.có");
                    int input = sc.nextInt();
                    sc.nextLine();
                    if(input==0 || input==1){
                        break;
                    }
                }
                String MaNguoiGuiDon = tb.getMaNguoiGui();
                XuLyRequest(MaNguoiDuyetDon,MaNguoiGuiDon,dsnv,tb,sc);
                tb.setTrangThai(TrangThaiDocThongBao.DA_DOC);
            }
        }
    }
    
    
    public static void XuLyRequest(String MaNguoiDuyetDon, String MaNguoiGuiDon, DanhSachNhanVien dsnv, ThongBao ThongBao, Scanner sc){
        if(ThongBao.getRequest().equals(LoaiRequest.XIN_NGHI_PHEP)){
            DuyetDonXinPhep(MaNguoiDuyetDon,MaNguoiGuiDon,dsnv,ThongBao, sc);
        }
    }
}
