
package oop.entity.Service;

import java.time.LocalTime;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Scanner;
import oop.entity.DanhSach.*;
import oop.entity.Enum.*;
import oop.entity.Object.*;

public class ThaoTac {
//=====================================đăng nhập====================
    public static TaiKhoan DangNhap(Scanner sc){
        
        while(true){
            boolean found = false;
            System.out.println("Nhập tên đăng nhập");
            
            String tenTest = sc.nextLine().trim();
            for(TaiKhoan tk : DanhSachTaiKhoan.getDstk()){
                if(tk.getTenDangNhap().equalsIgnoreCase(tenTest)){
                    found=true;
                    if(tk.KiemTraTrangThaiHoatDong()==true){
                        System.out.println("Nhập mật khẩu");
                        if(tk.NhapMatKhau(sc))return tk;
                    }
                    else{
                    System.out.println("Tài khoản của bạn đã bị khoá");
                    return null;
                    }
                }
            }
            if(!found){
                System.out.println("Không tìm thấy tài khoản tương ứng");
            } 
        }
    }
    
    
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
    
    
    public static void XinNghiPhep(LocalDate date,LocalTime time, NhanVien nv, Scanner sc){
    
    if(HuyTacVu(sc)){
        return ;
    }
        String MaDon = nv.getDsDon().taoMa();
        String MaNVNopDon = nv.getMaNV();
        String MaNVDuyetDon = nv.getMaQuanLyPhuTrach();
        DonXinPhep don = new DonXinPhep(MaDon ,MaNVNopDon, MaNVDuyetDon, date, time);
        LocalDate NgayBatDau;
        LocalDate NgayKetThuc;
        
        while(true){
            System.out.println("ngày bắt đầu nghỉ phép của bạn là ?\n Định dạng (DD/MM/YYYY)");
            String input = sc.nextLine().trim();
            NgayBatDau = isValidDate(input,"dd/MM/yyyy");
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
            NgayKetThuc = isValidDate(sc.nextLine(), "dd/MM/yyyy");
            if(NgayKetThuc!=null){
                if(NgayKetThuc.isAfter(date)){
                break;}
                else{
                    System.out.println("Ngày nghỉ phép phải sau ngày hiện tại");
                }
            }
        }
        System.out.println("lý do xin nghỉ phép của bạn là ?");
        String Message = sc.nextLine().trim();
        
        while(true){
            System.out.println("Loại nghỉ phép của bạn là ");
            System.out.println("0. Nghỉ phép năm");
            System.out.println("1. Nghỉ phép bệnh");
            System.out.println("2. Nghỉ phép thai sản");
            int input = sc.nextInt();
            sc.nextLine();
            if(input >= 0 && input<=2){
                PhanLoaiNghiPhep(don,input);
                break;
            }
            
        }
        
        don.setNgayBatDau(NgayBatDau);
        don.setNgayKetThuc(NgayKetThuc);
        don.setMessage(Message);
        nv.getDsDon().them(don);
        
        GuiThongBao( MaNVNopDon, MaNVDuyetDon, date, time, Message).setRequest(LoaiRequest.XIN_NGHI_PHEP);
        
        NhatKyHoatDong.them("Nhân viên mã "+ MaNVNopDon + " nộp đơn nghỉ phép vào "+ time + " ngày " + date +
                "cho quản lý mã " + MaNVDuyetDon);
    }
    
    public static void DangKyLamOT(NhanVien nv, LocalDate date, LocalTime time, Scanner sc){
        
    if(HuyTacVu(sc)){
        return;
    }
        String MaNV = nv.getMaNV();
        String MaQuanLyPhuTrach = nv.getMaQuanLyPhuTrach();
        String CaLam = nv.getCaLam();
        LocalDate GuardTime = date.plusDays(3);
        if(!nv.getBcc().checkGioiHanGioOT()){
        System.out.println("Quá số giờ làm OT trong một tháng");
        return;
    }

        System.out.println("Số lượng ngày bạn muốn đăng ký làm OT:");
        int SoLuong = sc.nextInt();
        sc.nextLine();

    while(SoLuong > 0){
        System.out.println("Nhập ngày muốn đăng ký OT (đăng ký trước 3 ngày), định dạng dd/MM/yyyy:");
        String input = sc.nextLine().trim();
        LocalDate NgayDangKy = isValidDate(input, "dd/MM/yyyy");

        if(NgayDangKy == null){
            System.out.println("Ngày tháng không hợp lệ");
            continue;
        }
        if(NgayDangKy.isBefore(GuardTime)){
            System.out.println("Ngày đi làm OT phải đăng ký trước 3 ngày");
            continue;
        }
        // chặn đăng ký trùng ngày ngay từ lúc nộp
        ChiTietChamCong ct = nv.getBcc().timKiem(NgayDangKy);
        if(ct != null && ct.isIsDangKyOT()){
            System.out.println("Ngày này bạn đã đăng ký OT rồi");
            continue;
        }

        GuiThongBao(MaNV, MaQuanLyPhuTrach, date, time,
                "đăng ký làm OT ngày " + input + " ca " + CaLam, NgayDangKy)
            .setRequest(LoaiRequest.DANG_KY_LAM_OT);
        
        SoLuong--;
        NhatKyHoatDong.them("Nhân viên mã "+ MaNV + " đăng ký làm OT ngày "+ NgayDangKy + " vào thời điểm "+ time + " ngày " + date +
                "cho quản lý mã " + MaQuanLyPhuTrach);
    }
    }
    
    public static ThongBao GuiThongBao ( String MaNguoiGui, String MaNguoiNhan, LocalDate date, LocalTime time, String Message){
        NhanVien nv = DanhSachNhanVien.timKiem(MaNguoiNhan);
        if(nv==null){
            return GuiThongBao("HETHONG", MaNguoiGui, date, time, "Không tìm thấy nhân viên bạn gửi thông báo");
        }
        else{
        String MaThongBao = nv.getDsThongBao().taoMa();
        ThongBao ThongBao = new ThongBao(MaThongBao, MaNguoiGui, date, time, Message);
        nv.getDsThongBao().them(ThongBao);
        return ThongBao;
        }
    }
    //Thông báo làm OT
    public static ThongBao GuiThongBao (String MaNguoiGui, String MaNguoiNhan, LocalDate date, LocalTime time, String Message, LocalDate NgayDangKy){
        NhanVien nv = DanhSachNhanVien.timKiem(MaNguoiNhan);
        if(nv==null){
            return GuiThongBao("HETHONG", MaNguoiGui, date, time, "Không tìm thấy nhân viên bạn gửi thông báo");
        }
        String MaThongBao = nv.getDsThongBao().taoMa();
        ThongBao ThongBao = new ThongBao(MaThongBao, MaNguoiGui, date, time, Message);
        ThongBao.setNgayDangKy(NgayDangKy);
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
    
    
    public static void DuyetDonXinPhep(String MaNguoiDuyetDon, String MaNguoiGuiDon, ThongBao ThongBao, Scanner sc){
        
    if(HuyTacVu(sc)){
        return;
    }
        NhanVien nv = DanhSachNhanVien.timKiem(MaNguoiGuiDon);
        if(nv==null){
            System.out.println("Không tìm thấy nhân viên");
            return;
        }
        LocalDate date = ThongBao.getDate();
        LocalTime time  = ThongBao.getTime();
        DonXinPhep don = nv.getDsDon().timKiem(date,time);
        System.out.println(don);
        while(true){
            System.out.println("bạn có duyệt đơn của nhân viên không ? \n0. Không duyệt\n1. Duyệt");
            int input = sc.nextInt();
            sc.nextLine();
            if(input==0 || input==1){
                if(input==0){
                    System.out.println("Nhập lý do không duyệt đơn cho nhân viên của bạn biết: ");
                    String Message = sc.nextLine().trim();
                    don.setTrangThai(LoaiTrangThaiCuaDon.KHONG_DUYET);
                    GuiThongBao( MaNguoiDuyetDon, MaNguoiGuiDon, date, time, Message);
                    NhatKyHoatDong.them("Nhân viên mã "+ MaNguoiDuyetDon + " không duyệt đơn nghỉ phép vào "+ time + " ngày " + date +
                "cho nhân viên mã " + MaNguoiGuiDon);
                }
                else{
                    don.setTrangThai(LoaiTrangThaiCuaDon.DUYET);
                    NhatKyHoatDong.them("Nhân viên mã "+ MaNguoiDuyetDon + " duyệt đơn nghỉ phép vào "+ time + " ngày " + date +
                "cho nhân viên mã " + MaNguoiGuiDon);
                }
                break;
            }
            else{
                System.out.println("Không hợp lệ, vui lòng nhập lại");
            }
        }
    }
    //////////////////////////////////////////
    public static void checkInbox(String MaNVCheckInbox, Scanner sc, LocalDate date, LocalTime time,DanhSachThongBao dsThongBao){
        if(DemSoLuongInbox(MaNVCheckInbox)==0){
            return;
        }
        System.out.println("Hòm thư của bạn có " + DemSoLuongInbox(MaNVCheckInbox) +" thông báo");
        if(CoDocKhong(sc)==false){
            return;
        }
        for(ThongBao tb : dsThongBao.getDsThongBao()){
            if(tb.getTrangThai().equals(TrangThaiDocThongBao.CHUA_DOC)){
                System.out.println(tb);
                String MaNguoiGuiDon = tb.getMaNguoiGui();
                if(!tb.getRequest().equals(LoaiRequest.THONG_BAO)){
                    if(CoGiaiQuyetKhong(sc)){
                        XuLyRequest(MaNVCheckInbox,MaNguoiGuiDon,tb,sc, date, time);
                        tb.setTrangThai(TrangThaiDocThongBao.DA_GIAI_QUYET);
                        continue;
                    }
                }
                tb.setTrangThai(TrangThaiDocThongBao.DA_DOC);
                System.out.println();
            }
        }
    }
    
    public static int DemSoLuongInbox(String MaNVCheckInbox){
        NhanVien nv = DanhSachNhanVien.timKiem(MaNVCheckInbox);
        int dem = 0;
        for(ThongBao tb : nv.getDsThongBao().getDsThongBao()){
            if(tb.getTrangThai() == TrangThaiDocThongBao.CHUA_DOC){
                dem++;
            }
        }
        return dem;
    }
    
    public static int DemSoLuongChuaGiaiQuyet(String MaNVCheckInbox){
        NhanVien nv = DanhSachNhanVien.timKiem(MaNVCheckInbox);
        int dem = 0;
        for(ThongBao tb : nv.getDsThongBao().getDsThongBao()){
            if(tb.getRequest() == LoaiRequest.THONG_BAO && tb.getTrangThai() != TrangThaiDocThongBao.DA_GIAI_QUYET){
                dem++;
            }
        }
        return dem;
    }
    
    public static boolean CoDocKhong(Scanner sc){
        while(true){
        System.out.println("đọc thông báo bây giờ không?\0.Không\n1.Có");
        int input = sc.nextInt();
        sc.nextLine();
        if(input==1){
            return true;
        }
        else if(input==0){
            return false;
        }
        System.out.println("Nhập sai định dạng, vui lòng nhập lại");
        }
    }
    
    public static boolean CoGiaiQuyetKhong(Scanner sc){
        while(true){
        System.out.println("giải quyết bây giờ không?\0.Không\n1.Có");
        int input = sc.nextInt();
        sc.nextLine();
        if(input==1){
            return true;
        }
        else if(input==0){
            return false;
        }
        System.out.println("Nhập sai định dạng, vui lòng nhập lại");
        }
    }
    
    
    public static void XuLyRequest(String MaNguoiNhanRequest, String MaNguoiGuiRequest, ThongBao ThongBao, Scanner sc, LocalDate date, LocalTime time){
        if(ThongBao.getRequest().equals(LoaiRequest.XIN_NGHI_PHEP)){
            DuyetDonXinPhep(MaNguoiNhanRequest,MaNguoiGuiRequest,ThongBao, sc);
        }
        if(ThongBao.getRequest().equals(LoaiRequest.DANH_GIA_KPI)){
            DanhGiaKPINhanVienPhuTrach(sc,date, time,MaNguoiNhanRequest);
        }
        if(ThongBao.getRequest().equals(LoaiRequest.XEM_XET_TANG_LUONG)){
            XemXetTangLuong(sc, MaNguoiGuiRequest, MaNguoiNhanRequest, date, time);
        }
        if(ThongBao.getRequest().equals(LoaiRequest.DANG_KY_LAM_OT)){
            DuyetLamOT(sc, MaNguoiNhanRequest, MaNguoiGuiRequest, date, time, ThongBao.getNgayDangKy());
        }
        
    }
    
    public static ArrayList<ThongBao> DanhSachRequestChuaGiaiQuyet(String MaNV){
        ArrayList<ThongBao> temp = new ArrayList<>();
        NhanVien nv = DanhSachNhanVien.timKiem(MaNV);
        if(nv.getDsThongBao().getDsThongBao() == null){
            System.out.println("thông báo rỗng");
            return null;
        }
        else{
            for(ThongBao tb : nv.getDsThongBao().getDsThongBao()){
                temp.add(tb);
            }
            return temp;
        }
        
    }
    
    public static void checkChuaGiaiQuyet(String MaNVCheckInbox, Scanner sc){
        NhanVien nv = DanhSachNhanVien.timKiem(MaNVCheckInbox);
        if(DemSoLuongChuaGiaiQuyet(MaNVCheckInbox)==0){
            System.out.println("Hiện không còn request nào cần bạn xử lý");
            return;
        }
        System.out.println("Hòm thư của bạn có " + DemSoLuongChuaGiaiQuyet(MaNVCheckInbox) +" request chưa giải quyết bao gồm");
        ArrayList<ThongBao> temp = DanhSachRequestChuaGiaiQuyet(MaNVCheckInbox);
        int input=-1;
        while(temp!= null || input!=0){
        for(int i = 0; i< temp.size(); i++){
            System.out.println((i+1) + ". " + temp.get(i));
        }
        System.out.println("Bạn muôn giải quyết mục nào");
        input = sc.nextInt();
        sc.nextLine();
        if(input<0 || input>temp.size()){
          System.out.println("Nhập sai định dạng, vui lòng nhập lại");                                                  /////try - catch
        }
        else{
            ThongBao tb = temp.get(input);
            String MaNVGuiRequest = tb.getMaNguoiGui();
            XuLyRequest(MaNVCheckInbox, MaNVGuiRequest, tb, sc, LocalDate.now(), LocalTime.now());
            temp.remove(input);
        }
        }
    }
    /////////////////////////////////////////////////
    public static void GiaLapChamCong(NhanVien nv, LocalDate date){
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
    
    //////////admin
    
    public static void KiemTraToiNgay(NhanVien nv, LocalDate date, LocalTime time, String MaADMIN){
        ArrayList<ThongBao> temp = nv.getDsThongBao().timKiemChuaGiaiQuyet(date);
        if(temp != null){
            for(int i = 0; i< temp.size();i++){
                System.out.println("có request bạn chưa giải quyết có mã " + temp.get(i).getMaThongBao() 
                        + " loại request là: " + temp.get(i).getRequest() +" đã tới hạn");
            }
        }
        if(nv.ToiNgayTongKetDinhKy(date)){
            nv.ResetQuyTrinhChamCong(date);
            GuiThongBao( MaADMIN, nv.getMaNV(), date, time, "Đã reset bảng chấm công, chuyển qua tính theo tháng mới");

        }
        if(NgayNghiepVu.kiemTraToiNgay(date).equalsIgnoreCase("ngày kết thúc quý")){
            for(NhanVien NV : DanhSachNhanVien.getDsnv()){
                if(NV.getChucVu().equals(LoaiChucVu.QUAN_LY)){
                    GuiThongBao(MaADMIN, NV.getMaNV(), date, time, "Đã đến thời hạn chấm KPI của nhân viên bạn phụ trách" ).setRequest(LoaiRequest.DANH_GIA_KPI);
                }
            }

        }
        if(nv.ToiNgayXetNangLuong(date)){
            String MaQuanLyPhuTrach = nv.getMaQuanLyPhuTrach(); //MaNguoiGui là admin
            String MaNVKiemTra = nv.getMaNV();
            GuiThongBao( MaADMIN, MaQuanLyPhuTrach, date, time, "Tới ngày xét nâng lương của nhân viên có mã " 
                    + MaNVKiemTra).setRequest(LoaiRequest.XEM_XET_TANG_LUONG);
        }
        if(date.isEqual(LocalDate.of(date.getYear(), 1, 1))){
            if(nv.getHinhThucDiLam().equalsIgnoreCase("Full-time")){
                NhanVienFullTime nvFull = (NhanVienFullTime) nv;
                double TienThuongTet = nvFull.TinhToanTienThuongTet(LocalDate.of(date.getYear()-1, 1, 1));
                String MaNV = nvFull.getMaNV();
                KhenThuong khen = new KhenThuong(LoaiKhenThuong.THUONG_TET, null, date, TienThuongTet, MaADMIN, MaNV);
                String MaQD = nvFull.getDsktkl().taoMa();
                khen.setMaQD(MaQD);
                nvFull.getDsktkl().them(khen);
                 GuiThongBao(MaADMIN, MaNV, date, time, "thưởng tết " + TienThuongTet + " vnd ").setRequest(LoaiRequest.THONG_BAO);
                 
            }
        }
    }
    
    public static void KiemTraVanHanhLuongNhanSu(LocalDate date, LocalTime time, String MaADMIN, LocalDate NgayKetThuc){
        for(NhanVien nv : DanhSachNhanVien.getDsnv()){
            for(LocalDate NgayChamCong = date; NgayChamCong.isBefore(NgayKetThuc.plusDays(1));NgayChamCong = NgayChamCong.plusDays(1)){
            KiemTraToiNgay( nv, NgayChamCong,time, MaADMIN);
            GiaLapChamCong(nv, NgayChamCong);
            }
            }
        }
    
    
    
    //quản lý
    
    public static void ThuongSangKien( Scanner sc, LocalDate date,LocalTime time, String MaQuanLy){
        
    if(HuyTacVu(sc)){
        return;
    }
        while(true){
        System.out.println("Nhân viên bạn muốn thưởng sáng kiến có mã nhân viên là :");
        String MaNV = sc.nextLine().trim();
        NhanVien nv = DanhSachNhanVien.timKiem(MaNV);
        if(nv!=null){
            while(true){
            System.out.println("Số tiền thưởng sáng kiến là ?\n Tối thiểu 100.000vnd - Tối đa 500.000vnd");
            double TienThuongSangKien = sc.nextDouble();
            if(TienThuongSangKien>=100000 && TienThuongSangKien<=500000){
                KhenThuong khen = new KhenThuong(LoaiKhenThuong.THUONG_SANG_KIEN, null, date,TienThuongSangKien , MaQuanLy, MaNV);
                String MaQD = nv.getDsktkl().taoMa();
                khen.setMaQD(MaQD);
                nv.getDsktkl().them(khen);
                GuiThongBao( MaQuanLy, MaNV, date, time, "thưởng sáng kiến" + TienThuongSangKien + "vnd");
                NhatKyHoatDong.them("Quản lý mã "+ MaQuanLy + " thưởng sáng kiến có giá trị " + TienThuongSangKien + " vào "+ time + " ngày " + date +
                "cho nhân viên mã " + MaNV);
                break;
            }
            }
            break;
            
        }
        System.out.println("Khong hop le, khong tim thay nhan vien tuong ung");
        }
    }
    
    public static void DanhGiaKPI(Scanner sc, LocalDate date, LocalTime time, String MaQuanLy, String MaNV){
        
    if(HuyTacVu(sc)){
        return;
    }
            NhanVien nv = DanhSachNhanVien.timKiem(MaNV);
                    while(true){
                        System.out.println("Đánh giá điểm KPI của nhân viên " + nv.getMaNV()+" là? ");
                        int DiemKPI = sc.nextInt();
                        sc.nextLine();
                    if(DiemKPI>=0 && DiemKPI<=100){
                    int thang = date.getMonthValue();
                    int nam = date.getYear();
                    DanhGiaKPI kpi = new DanhGiaKPI(null, DiemKPI, thang , nam, MaQuanLy, nv.getMaNV());
                    NhanVienFullTime nvFull = (NhanVienFullTime) nv;
                    
                    kpi.setMaDanhGia(nvFull.getDskpi().taoMa());
                    nvFull.getDskpi().them(kpi);
                    GuiThongBao(MaQuanLy, nv.getMaNV(), date, time, "đã có đánh giá kpi có điểm số là " + DiemKPI +" tháng "+ thang + " nam " +nam).setRequest(LoaiRequest.THONG_BAO);
                    NhatKyHoatDong.them("Quản lý mã "+ MaQuanLy + "đã có đánh giá kpi có điểm số là " + DiemKPI + time + " ngày " + date +
                "cho nhân viên mã " + MaNV);
                    break; 
                    }
                    System.out.println("Điểm KPI phải dưới 100 trên 0 ");
                    }
                
             
    }
    
    public static void DanhGiaKPINhanVienPhuTrach(Scanner sc, LocalDate date, LocalTime time, String MaNVDanhGia){
        ArrayList<NhanVien> temp = DanhSachNhanVien.timKiemTheoMaQuanLyPhuTrach(MaNVDanhGia);
        if(temp == null){
            System.out.println("Danh sách nhân viên cấp dưới trống");
            return;
        }
        for(NhanVien nv : DanhSachNhanVien.timKiemTheoMaQuanLyPhuTrachVaFullTime(MaNVDanhGia)){
            DanhGiaKPI(sc, date, time, MaNVDanhGia, nv.getMaNV());
        }
    }
    

    public static boolean KiemTraIsFullTime(NhanVien nv){
        if(nv.getHinhThucDiLam().equalsIgnoreCase("Full-time")){
            return true;
        }
        return false;
    }
    
    public static NhanVienFullTime DownCastingNhanVienFullTime(NhanVien nv){
        NhanVienFullTime nvFull = (NhanVienFullTime) nv;
        return nvFull;
    }
     public static NhanVienPartTime DownCastingNhanVienPartTime(NhanVien nv){
        NhanVienPartTime nvFull = (NhanVienPartTime) nv;
        return nvFull;
    }

    
    public static void XemXetTangLuong(Scanner sc, String MaNV, String MaQuanLyPhuTrach, LocalDate date, LocalTime time){
        
    if(HuyTacVu(sc)){
        return;
    }
        while(true){
            NhanVien nv = DanhSachNhanVien.timKiem(MaNV);
            System.out.println(nv);
            System.out.println("Đồng ý tăng lương cho nhân viên mã " + nv.getMaNV() + " không?\n0.không\n1.có");
            int input = sc.nextInt();
            sc.nextLine();
            if(input==0 || input ==1){
                if(input==1){
                    if(KiemTraIsFullTime(nv)){
                        double LuongCoBan = DownCastingNhanVienFullTime(nv).getLuongCoSo();
                        System.out.println( " Hiện tại lương cơ bản của nhân viên là" + LuongCoBan + "bạn muốn nâng lương cơ bản lên thành bao nhiêu?");
                        double LuongCoBanMoi = sc.nextDouble();
                        sc.nextLine();
                        
                        HopDong hd = nv.getHopDong();
                        hd.setMucLuongCoSo(LuongCoBanMoi);
                        nv.setHopDong(hd);
                        
                    }
                    else{
                        double LuongMotGio = DownCastingNhanVienPartTime(nv).getLuongCoSo();
                        System.out.println( "Hiện tại lương một giờ của nhân viên là" + LuongMotGio +"bạn muốn nâng lương một giờ công cơ bản lên thành bao nhiêu?");
                        double LuongMotGioMoi = sc.nextDouble();
                        sc.nextLine();
                        
                        HopDong hd = nv.getHopDong();
                        hd.setMucLuongCoSo(LuongMotGioMoi);
                        nv.setHopDong(hd);
                    }
                    GuiThongBao(MaQuanLyPhuTrach, MaNV, date, time, "Quản lý đã đồng ý nâng lương cho bạn").setRequest(LoaiRequest.THONG_BAO);
                    NhatKyHoatDong.them("Quản lý mã "+ MaQuanLyPhuTrach + " nâng lương thành" + 
                            nv.getHopDong().getMucLuongCoSo()+ " vào "+ time + " ngày " + date + "cho nhân viên mã " + MaNV);
                }
                else{
                System.out.println("Nhập lý do tại sao không tăng lương cho ");
                String Message = sc.nextLine().trim();
                GuiThongBao( MaQuanLyPhuTrach, MaNV, date, time, "từ chối nâng lương vì " + Message).setRequest(LoaiRequest.THONG_BAO);
                NhatKyHoatDong.them("Quản lý mã "+ MaQuanLyPhuTrach + " từ chối nâng lương vào "+ time + " ngày " + date +
                "cho nhân viên mã " + MaNV);
                }
                break;
        }
            System.out.println("Nhập sai định dạng, vui lòng nhập lại");
    }
}
    
    
    public static void DuyetLamOT(Scanner sc, String MaQuanLy, String MaNguoiGui, LocalDate date, LocalTime time, LocalDate NgayDangKy){
        
    if(HuyTacVu(sc)){
        return;
    }
        while(true){
            System.out.println("Có duyệt không?\n0.Không\n1.Có");
            int input = sc.nextInt();
            sc.nextLine();
            if(input==0){
                System.out.println("Nhập lý do: ");
                String lydo = sc.nextLine().trim();
                GuiThongBao( MaQuanLy, MaNguoiGui, date,time, "Quản lý của bạn đã từ chối với lý do: " +lydo );
                NhatKyHoatDong.them("Quản lý mã "+ MaQuanLy + " từ chối duyệt làm OT vào "+ time + " ngày " + date +
                "cho nhân viên mã " + MaNguoiGui);
                break;
            }
            if(input==1){
                GuiThongBao( MaQuanLy, MaNguoiGui, date,time, "Quản lý của bạn đã duyệt đăng ký làm OT ngày " + NgayDangKy.toString());
                NhatKyHoatDong.them("Quản lý mã "+ MaQuanLy+ " duyệt ca làm OT vào "+ time + " ngày " + date +
                "cho nhân viên mã " + MaNguoiGui);
                NhanVien nv = DanhSachNhanVien.timKiem(MaNguoiGui);
                ChiTietChamCong cc = nv.getBcc().timKiem(NgayDangKy);
                if(cc!=null){
                    cc.LamOT();
                }
                else{
                    System.out.println("ko tim thay, lỗi cc");
                }
                
                break;
            }
            System.out.println("vui lòng nhập đúng định dạng");
        }
    }
//=================================Tuyển dụng=====================================
    public static DotTuyenDung TaoDotTuyenDung(Scanner sc, String MaNguoiTuyen){
        
    if(HuyTacVu(sc)){
        return null;
    }
        LocalDate NgayBatDau;
        LocalDate NgayKetThuc;
        ViTri vt;
        System.out.println("Nhập số lượng cần tuyển");
        int SoLuong = sc.nextInt();
        sc.nextLine();
        while(true){
        System.out.println("Nhập ngày bắt đầu tuyển dụng");
        NgayBatDau = isValidDate(sc.nextLine(),"dd/MM/yyyy");
        if(NgayBatDau != null && !NgayBatDau.isBefore(LocalDate.now())){
            break;
        }
        }
        while(true){
        System.out.println("Nhập ngày kết thúc tuyển dụng");
        NgayKetThuc = isValidDate(sc.nextLine(),"dd/MM/yyyy");
        if(NgayKetThuc != null && !NgayKetThuc.isBefore(LocalDate.now())){
            break;
        }
        }
        while(true){
        System.out.println("Nhập mã vị trí tuyển dụng");
        String MaViTri = sc.nextLine().trim();
        vt = ViTri.timKiem(MaViTri);
        if(vt!=null){
            break;
        }
        else{
            System.out.println("Không tìm thấy vị trí");
        }
        }
        String MaDotTuyenDung = DanhSachDotTuyenDung.taoMa();
        DotTuyenDung dot = new DotTuyenDung(MaDotTuyenDung, SoLuong,MaNguoiTuyen, NgayBatDau, NgayKetThuc, vt);
        return dot;
    }
    
    public static void DangTaiTuyenDung(Scanner sc, String MaQuanLy, String MaADMIN, LocalDate date, LocalTime time){
        DotTuyenDung dot = TaoDotTuyenDung(sc,MaQuanLy);
        if(dot==null){
            return;
        }
        DanhSachDotTuyenDung.them(dot);
        System.out.println("Nhập lý do");
        String Message = sc.nextLine().trim();
        GuiThongBao(MaQuanLy, MaADMIN, date, time, Message).setRequest(LoaiRequest.DANG_TAI_DOT_TUYEN_DUNG);
        NhatKyHoatDong.them("Quản lý mã "+ MaQuanLy+ " gửi đề nghị tuyển dụng vào "+ time + " ngày " + date +
                "cho giám đốc mã " + MaADMIN);
    }
    
    public static void DuyetDotTuyenDung(Scanner sc, String MaADMIN, String MaNguoiTuyen, LocalDate date, LocalTime time, String MaDotTuyenDung){
        
    if(HuyTacVu(sc)){
        return;
    }
        DotTuyenDung dot = DanhSachDotTuyenDung.timKiem(MaDotTuyenDung);
        System.out.println(dot);
        while(true){
            System.out.println("Có duyệt không?\n0.Không\n1.Có");
            int input = sc.nextInt();
            sc.nextLine();
            if(input==0){
                System.out.println("Nhập lý do: ");
                String lydo = sc.nextLine().trim();
                GuiThongBao(MaADMIN, MaNguoiTuyen, date,time, "Giám đốc của bạn đã từ chối với lý do: " +lydo );
                NhatKyHoatDong.them("Giám đốc mã "+ MaADMIN + " từ chối duyệt làm OT vào "+ time + " ngày " + date +
                "cho nhân viên mã " + MaNguoiTuyen);
                break;
            }
            if(input==1){
                GuiThongBao(MaADMIN, MaNguoiTuyen, date,time, "Quản lý của bạn đã duyệt đợt tuyển dụng mã " + MaDotTuyenDung +" của bạn ");
                NhatKyHoatDong.them("Quản lý mã "+ MaADMIN+ " duyệt đợt tuyển dụng "+ time + " ngày " + date +
                "cho nhân viên mã " + MaNguoiTuyen);
                dot.setTrangThai(TRANG_THAI_DOT.DANG_TUYEN_DUNG);/////
                break;
            }
            System.out.println("vui lòng nhập đúng định dạng");
        }
    }
    
    public static UngVien TaoUngVien(Scanner sc){
    System.out.println("--- NHẬP THÔNG TIN ỨNG VIÊN ---");
    
    if(HuyTacVu(sc)){
        return null;
    }
    
    System.out.print("Nhập CCCD: ");
    String CCCD = sc.nextLine().trim();
    
    System.out.print("Nhập họ tên: ");
    String HoTen = sc.nextLine().trim();
    
    System.out.print("Nhập số điện thoại: ");
    String SDT = sc.nextLine().trim();
    
    LocalDate NgayThangNamSinh = isValidDate(sc.nextLine().trim(), "dd/MM/yyyy");
    
   // Nhập và kiểm tra Mã đợt tuyển dụng
    String MaDotTuyenDung = "";
    while (true) {
        System.out.print("Nhập mã đợt tuyển dụng: ");
        MaDotTuyenDung = sc.nextLine().trim();
        if (DanhSachDotTuyenDung.timKiem(MaDotTuyenDung) != null) {
            break;
        }
        System.out.println("Mã đợt tuyển dụng không tồn tại! Vui lòng nhập lại.");
    }
    
    // Nhập và kiểm tra Vị trí ứng tuyển
    String ViTriUngTuyen = "";
    while (true) {
        System.out.print("Nhập vị trí ứng tuyển: ");
        ViTriUngTuyen = sc.nextLine().trim();
        if (ViTri.timKiem(ViTriUngTuyen) != null) { // Thay 'ViTri' bằng tên lớp quản lý vị trí thực tế của bạn nếu cần
            break;
        }
        System.out.println("Vị trí tuyển dụng không tồn tại! Vui lòng nhập lại.");
    }
    
    // Nhập và kiểm tra Mã khóa đào tạo
    String MaKhoaDaoTao = "";
    while (true) {
        System.out.print("Nhập mã khóa đào tạo: ");
        MaKhoaDaoTao = sc.nextLine().trim();
        if (DanhSachKhoaDaoTao.timKiem(MaKhoaDaoTao) != null) {
            break;
        }
        System.out.println("Mã khóa đào tạo không tồn tại! Vui lòng nhập lại.");
    }
    
    return new UngVien(CCCD, HoTen, SDT, NgayThangNamSinh, MaDotTuyenDung, ViTriUngTuyen, MaKhoaDaoTao);
    }
    
    public static boolean HuyTacVu(Scanner sc){
        System.out.println("Chắc chắn chưa? Hay muốn hoàn tác, thoát khỏi quy trình\n Nhập 'thoát' để thoát");
        if(sc.nextLine().trim().equalsIgnoreCase("thoát")){
            System.out.println("Đã ngắt chương trình");
        return true;
    }
    return false;
    }
    
    public static void ThemUngVienVaoDanhSach(Scanner sc, String MaNguoiThem){
        System.out.println("Nhập số lượng ứng viên muốn thêm: ");
        int SoLuong = sc.nextInt();
        sc.nextLine();
        while(SoLuong!=0){
            UngVien uv = TaoUngVien(sc);
            if(uv==null){
                return;
            }
            DotTuyenDung dot = DanhSachDotTuyenDung.timKiem(uv.getMaDotTuyenDung());
            dot.themUngVien(uv);
            SoLuong--;
            NhatKyHoatDong.them("Nhân viên mã " + MaNguoiThem + " thêm ứng viên có mã " 
                    + uv.getMaUngVien() + " vào " + LocalDate.now() + " , " + LocalTime.now());
        }
    }
    
    public static void ChamDiemUngVien(Scanner sc, String MaNguoiCham, LocalDate date, LocalTime time){
        
    if(HuyTacVu(sc)){
        return;
    }
        final int DIEM_DAT = 50; //từ 50 điểm trở lên là đạt, muốn đổi thì sửa ở đây
        
        DotTuyenDung dot;
        while(true){
            System.out.println("Nhập mã đợt tuyển dụng");
            String MaDot = sc.nextLine().trim();
            dot = DanhSachDotTuyenDung.timKiem(MaDot);
            if(dot != null){
                break;
            }
            System.out.println("Không tìm thấy đợt tuyển dụng");
        }
        
        UngVien uv;
        while(true){
            System.out.println("Nhập mã ứng viên");
            String MaUngVien = sc.nextLine();
            uv = dot.timKiem(MaUngVien); //nếu không thấy thì hàm này tự in thông báo
            if(uv != null){
                break;
            }
        }
        System.out.println("Nhập điểm số");
        int DiemSo = sc.nextInt();
        sc.nextLine();
        KetQuaDaoTao kq = uv.getKq();
        kq.setMaKetQua("KQ" + uv.getMaUngVien());
        kq.setMaUngVien(uv.getMaUngVien());
        kq.setDiemSo(DiemSo);
        if(uv.getKhoa() != null){
            kq.setMaKhoaHoc(uv.getKhoa().getMaKhoaHoc());
        }
        if(DiemSo >= DIEM_DAT){
            kq.setKetQua(LoaiKetQua.DAT);
        }
        else{
            kq.setKetQua(LoaiKetQua.KHONG_DAT);
        }
        
        System.out.println("Ứng viên " + uv.getHoTen() + " có kết quả: " + kq.getKetQua().getMoTa());
        NhatKyHoatDong.them("Nhân viên mã " + MaNguoiCham + " chấm " + DiemSo + " điểm cho ứng viên " + uv.getMaUngVien() 
                + " vào " + time + " ngày " + date + " kết quả " + kq.getKetQua().getMoTa());
    }
/*
    public static NhanVien ChuyenThanhNhanVien(UngVien uv){
        NhanVien nv = new NhanVien();
    }
    
   */ 
}
    
    

