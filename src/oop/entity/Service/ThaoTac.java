
package oop.entity.Service;

import java.time.Duration;
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
    private static boolean daHuyTacVu = false;
    
    // Đọc số nguyên trong [min, max], nhập sai thì hỏi lại (thay cho nextInt)
private static int NhapSoNguyen(Scanner sc, String thongBao, int min, int max){
    while(true){
        System.out.println(thongBao);
        try{
            int v = Integer.parseInt(sc.nextLine().trim());
            if(v >= min && v <= max) return v;
        }catch(NumberFormatException e){ }
        System.out.println("Vui lòng nhập số từ " + min + " đến " + max);
    }
}

public static ThongBao GuiYeuCau(String gui, String nhan, LocalDate date, LocalTime time,
                                 String msg, LoaiRequest loai, String maLienQuan){
    boolean tonTai = nhan != null && (nhan.equalsIgnoreCase("BOSS") || DanhSachNhanVien.timKiem(nhan) != null);
    if(!tonTai){
        System.out.println("Không tìm thấy người nhận request: " + nhan);
        return null;
    }
    ThongBao tb = GuiThongBao(gui, nhan, date, time, msg);
    tb.setMaLienQuan(maLienQuan);
    tb.setRequest(loai);
    return tb;
}
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
    
    public static LocalDate isValidDate(String dateStr, String pattern,LocalDate Moc) {
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern(pattern);
            LocalDate Date = LocalDate.parse(dateStr, formatter);
            if(Date.isBefore(Moc)){
                return null;
            }
            return Date;          // Hợp lệ
        } catch (DateTimeParseException e) {
            System.out.println("Khong hop le");
            return null; // Không hợp lệ hoặc sai định dạng
        }
    }
    
    public static int TinhNgay(LocalDate NgayBatDau, LocalDate NgayKetThuc){
        int dem = 0;
        for(LocalDate date = NgayBatDau ; !date.isAfter(NgayKetThuc) ; date = date.plusDays(1)){
            dem++;
        }
        return dem;
    }
    
    
    public static void XinNghiPhep(LocalDate date,LocalTime time, NhanVien nv, Scanner sc){
    
    if(HuyTacVu(sc)){
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
                if(!NgayKetThuc.isBefore(NgayBatDau)){ break; }
                else{ System.out.println("Ngày kết thúc không được trước ngày bắt đầu"); }
                
            }
        }
        System.out.println("lý do xin nghỉ phép của bạn là ?");
        String Message = sc.nextLine().trim();
        int khoangTgian = TinhNgay(NgayBatDau, NgayKetThuc);
        
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
        
        
        GuiThongBao( MaNVNopDon, MaNVDuyetDon, date, time, Message).setRequest(LoaiRequest.XIN_NGHI_PHEP);
        nv.getDsDon().them(don);
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
        if(MaNguoiNhan.equalsIgnoreCase("BOSS")){
            ThongBao tb = new ThongBao(MaNguoiGui, date, time, Message);
            Boss.getDsThongBao().them(tb);
            return tb;
        }
        NhanVien nv = DanhSachNhanVien.timKiem(MaNguoiNhan);
        if(nv==null){
            return GuiThongBao("HETHONG", MaNguoiGui, date, time, "Không tìm thấy nhân viên bạn gửi thông báo");
        }
        else{
        ThongBao ThongBao = new ThongBao(MaNguoiGui, date, time, Message);
        nv.getDsThongBao().them(ThongBao);
        return ThongBao;
        }
    }
    //Thông báo làm OT
    public static ThongBao GuiThongBao (String MaNguoiGui, String MaNguoiNhan, LocalDate date, LocalTime time, String Message, LocalDate NgayDangKy){
        if(MaNguoiNhan.equalsIgnoreCase("BOSS")){
            ThongBao tb = new ThongBao(MaNguoiGui, date, time, Message);
            tb.setNgayDangKy(NgayDangKy);
            Boss.getDsThongBao().them(tb);
            return tb;
        }
        NhanVien nv = DanhSachNhanVien.timKiem(MaNguoiNhan);
        if(nv==null){
            return GuiThongBao("HETHONG", MaNguoiGui, date, time, "Không tìm thấy nhân viên bạn gửi thông báo");
        }
        ThongBao ThongBao = new ThongBao(MaNguoiGui, date, time, Message);
        ThongBao.setNgayDangKy(NgayDangKy);
        nv.getDsThongBao().them(ThongBao);
        return ThongBao;
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
    
    
    public static void DuyetDonXinPhep(String MaNguoiDuyetDon, String MaNguoiGuiDon, ThongBao tb, Scanner sc){
        if(HuyTacVu(sc)) return;
        NhanVien nv = DanhSachNhanVien.timKiem(MaNguoiGuiDon);
        if(nv == null){
        System.out.println("Không tìm thấy nhân viên");
        return;
    }
        LocalDate date = tb.getDate();
        LocalTime time = tb.getTime();
        DonXinPhep don = nv.getDsDon().timKiem(date, time);
        if(don == null){
        System.out.println("Không tìm thấy đơn xin nghỉ phép tương ứng");
        return;
    }
        LocalDate ngayBatDau = don.getNgayBatDau();
        LocalDate ngayKetThuc = don.getNgayKetThuc();
        int khoangTgian = TinhNgay(ngayBatDau, ngayKetThuc);
        System.out.println(don);

        int chon = NhapSoNguyen(sc, "bạn có duyệt đơn của nhân viên không ? \n0. Không duyệt\n1. Duyệt", 0, 1);
        LocalDate homNay = HeThong.date;
        LocalTime bayGio = LocalTime.now();

    // Ngày phép có thể đã bị dùng bởi đơn khác từ lúc nộp đơn
        boolean khongDuPhep = chon == 1 && don.getLoaiNghi() == LoaiNghiPhep.NGHI_PHEP_NAM
            && nv.getSoNgayNghiPhepNam() < khoangTgian;
        if(chon == 0 || khongDuPhep){
        String lyDo;
        if(khongDuPhep){
            lyDo = "bạn không còn đủ ngày nghỉ phép năm";
            System.out.println("Nhân viên không còn đủ ngày phép năm, đơn sẽ bị từ chối");
        }else{
            System.out.println("Nhập lý do không duyệt đơn cho nhân viên của bạn biết: ");
            lyDo = sc.nextLine().trim();
        }
        don.setTrangThai(LoaiTrangThaiCuaDon.KHONG_DUYET);
        GuiThongBao(MaNguoiDuyetDon, MaNguoiGuiDon, homNay, bayGio, "Đơn nghỉ phép của bạn không được duyệt vì: " + lyDo);
        NhatKyHoatDong.them("Nhân viên mã " + MaNguoiDuyetDon + " không duyệt đơn nghỉ phép vào " + bayGio + " ngày " + homNay
                + " cho nhân viên mã " + MaNguoiGuiDon);
        return;
    }

        don.setTrangThai(LoaiTrangThaiCuaDon.DUYET);
        if(don.getLoaiNghi() == LoaiNghiPhep.NGHI_PHEP_NAM){
        nv.setSoNgayNghiPhepNam(nv.getSoNgayNghiPhepNam() - khoangTgian);
        // chỉ lập chấm công cho đúng nhân viên này (code cũ lập cho cả công ty)
        for(LocalDate d = ngayBatDau; !d.isAfter(ngayKetThuc); d = d.plusDays(1)){
            LapDayChamCong(nv, d);
        }
        }   else{
        LapDayNghiPhep(ngayBatDau, ngayKetThuc, nv);
    }
    // đơn đã nằm trong dsDon từ lúc nộp, không them() lần hai
        GuiThongBao(MaNguoiDuyetDon, MaNguoiGuiDon, homNay, bayGio, "đã duyệt đơn nghỉ phép có mã " + don.getMaDon());
        NhatKyHoatDong.them("Nhân viên mã " + MaNguoiDuyetDon + " duyệt đơn nghỉ phép vào " + bayGio + " ngày " + homNay
            + " cho nhân viên mã " + MaNguoiGuiDon);
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
    //////////////////////////////////////////
public static void checkInbox(String MaNVCheckInbox, Scanner sc, LocalDate date, LocalTime time, DanhSachThongBao dsThongBao){
    int soLuong = DemSoLuongInbox(MaNVCheckInbox);
    if(soLuong == 0) return;
    System.out.println("Hòm thư của bạn có " + soLuong + " thông báo");
    if(!CoDocKhong(sc)) return;

    // duyệt trên bản sao vì lúc xử lý có thể phát sinh thông báo mới
    for(ThongBao tb : new ArrayList<>(dsThongBao.getDsThongBao())){
        if(tb.getTrangThai() != TrangThaiDocThongBao.CHUA_DOC) continue;
        System.out.println(tb);
        if(tb.getRequest() != LoaiRequest.THONG_BAO && CoGiaiQuyetKhong(sc)){
            if(XuLyRequest(MaNVCheckInbox, tb.getMaNguoiGui(), tb, sc, date, time)){
                tb.setTrangThai(TrangThaiDocThongBao.DA_GIAI_QUYET);
                continue;
            }
        }
        tb.setTrangThai(TrangThaiDocThongBao.DA_DOC);
        System.out.println();
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
            if(tb.getRequest() != LoaiRequest.THONG_BAO && tb.getTrangThai() != TrangThaiDocThongBao.DA_GIAI_QUYET){
                dem++;
            }
        }
        return dem;
    }
    
    public static boolean CoDocKhong(Scanner sc){
        while(true){
        System.out.println("đọc thông báo bây giờ không?\n0.Không\n1.Có");
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
        System.out.println("giải quyết bây giờ không?\n0.Không\n1.Có");
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
    
    
public static boolean XuLyRequest(String MaNguoiNhan, String MaNguoiGui, ThongBao tb, Scanner sc, LocalDate date, LocalTime time){
    daHuyTacVu = false;
    switch(tb.getRequest()){
        case XIN_NGHI_PHEP:
            DuyetDonXinPhep(MaNguoiNhan, MaNguoiGui, tb, sc);
            break;
        case DANH_GIA_KPI:
            DanhGiaKPINhanVienPhuTrach(sc, date, time, MaNguoiNhan);
            break;
        case XEM_XET_TANG_LUONG:
            // MaNguoiGui ở đây là admin, nhân viên cần xét lương nằm trong MaLienQuan
            XemXetTangLuong(sc, tb.getMaLienQuan(), MaNguoiNhan, date, time);
            break;
        case DANG_KY_LAM_OT:
            DuyetLamOT(sc, MaNguoiNhan, MaNguoiGui, date, time, tb.getNgayDangKy());
            break;
        case DANG_TAI_DOT_TUYEN_DUNG:
            DuyetDotTuyenDung(sc, MaNguoiNhan, MaNguoiGui, date, time, tb.getMaLienQuan());
            break;
        case BO_NHIEM: {
            NhanVien nguoiNhan = DanhSachNhanVien.timKiem(MaNguoiNhan);
            if(nguoiNhan == null || tb.getMaLienQuan() == null){
                System.out.println("Request bổ nhiệm thiếu thông tin người bị cách chức");
                break;
            }
            ChonRaNguoiThayThe(date, time, tb.getMaLienQuan(), sc, nguoiNhan);
            break;
        }
        case CHAM_DIEM_DAO_TAO:
            do{
                ChamDiemUngVien(sc, MaNguoiNhan, date, time);
            }while(!daHuyTacVu && NhapSoNguyen(sc, "Chấm điểm ứng viên khác không?\n0.Không\n1.Có", 0, 1) == 1);
            break;
        case KHIEN_TRACH_CHAM_CONG:
            break; // chỉ cần xác nhận đã xem
        case GIA_HAN_HOP_DONG:
            DuyetGiaHanHopDong(sc, MaNguoiNhan, tb.getMaLienQuan(), date, time);
            break;
        default:
            System.out.println("Loại request " + tb.getRequest().getMoTa() + " chưa có chức năng xử lý");
            return false;
    }
    return !daHuyTacVu;
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
                if(tb.getRequest() != LoaiRequest.THONG_BAO
                && (tb.getTrangThai() != TrangThaiDocThongBao.DA_GIAI_QUYET)&& (tb.getTrangThai()!= TrangThaiDocThongBao.HET_HAN))
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
    

    
    //////////admin
    
    public static void KiemTraToiNgay(NhanVien nv, LocalDate date, LocalTime time, String MaADMIN){
        if(nv.isDaNghiViec()) return;
        String maQL = nv.getMaQuanLyPhuTrach();
        
    // 1. Request sắp hết hạn (ngày mai) thì nhắc, tới hạn thì chuyển HET_HAN
        for(ThongBao tb : nv.getDsThongBao().timKiemChuaGiaiQuyet(date)){
        GuiThongBao(MaADMIN, nv.getMaNV(), date, time, "Request mã " + tb.getMaThongBao()
                + " (" + tb.getRequest().getMoTa() + ") sẽ hết hạn vào ngày mai, vui lòng giải quyết");
    }
        nv.getDsThongBao().chuyenQuaTrangThaiHetHan(date);

    // 2. Hợp đồng hết hạn
        HopDong hd = nv.getHopDong();
        if(hd != null && hd.getNgayHetHan() != null && date.isAfter(hd.getNgayHetHan())
        && hd.getTrangThaiHopDong() == TRANG_THAI_HOP_DONG.DANG_CO_HIEU_LUC){
        hd.setTrangThaiHopDong(TRANG_THAI_HOP_DONG.HET_HAN);
        GuiThongBao(MaADMIN, nv.getMaNV(), date, time, "Hợp đồng " + hd.getMaHopDong() + " của bạn đã hết hạn, đang chờ quản lý quyết định gia hạn");
        if(maQL != null){
        GuiYeuCau(MaADMIN, maQL, date, time,
                "Hợp đồng " + hd.getMaHopDong() + " của nhân viên " + nv.getMaNV() + " đã hết hạn, cần gia hạn hoặc từ chối gia hạn",
                LoaiRequest.GIA_HAN_HOP_DONG, nv.getMaNV());
    }
}

    // 3. Tổng kết kỳ lương
        if(nv.ToiNgayTongKetDinhKy(date)){
        nv.ResetQuyTrinhChamCong(date);
        GuiThongBao(MaADMIN, nv.getMaNV(), date, time, "Đã reset bảng chấm công, chuyển qua tính theo tháng mới");
    }

    // 4. Tới ngày xét nâng lương: gửi request cho quản lý, kèm mã nhân viên
        if(nv.ToiNgayXetNangLuong(date) && maQL != null && DangTrongHopDong(nv, date)){
        GuiYeuCau(MaADMIN, maQL, date, time, "Tới ngày xét nâng lương của nhân viên có mã " + nv.getMaNV(),
                LoaiRequest.XEM_XET_TANG_LUONG, nv.getMaNV());
    }

    // 5. Đầu năm: reset phép năm, thưởng tết cho full-time
        if(date.getMonthValue() == 1 && date.getDayOfMonth() == 1){
        nv.setSoNgayNghiPhepNam(12);
        if(nv.getHinhThucDiLam() != null && nv.getHinhThucDiLam().equalsIgnoreCase("Full-time")
                && DangTrongHopDong(nv, date)){
            NhanVienFullTime nvFull = (NhanVienFullTime) nv;
            double TienThuongTet = nvFull.TinhToanTienThuongTet(LocalDate.of(date.getYear() - 1, 1, 1));
            KhenThuong khen = new KhenThuong(LoaiKhenThuong.THUONG_TET, null, date, TienThuongTet, MaADMIN, nv.getMaNV());
            nvFull.getDsktkl().them(khen);
            GuiThongBao(MaADMIN, nv.getMaNV(), date, time, "thưởng tết " + TienThuongTet + " vnd ").setRequest(LoaiRequest.THONG_BAO);
        }
    }
}
    
    public static void KiemTraToiNgayHeThong(LocalDate date, LocalTime time, String MaADMIN){
    // Cuối quý: yêu cầu mỗi quản lý chấm KPI
    if("ngày kết thúc quý".equalsIgnoreCase(NgayNghiepVu.kiemTraToiNgay(date))){
        for(NhanVien NV : DanhSachNhanVien.getDsnv()){
            if(NV.getChucVu() == LoaiChucVu.QUAN_LY && !NV.isDaNghiViec()){
                GuiYeuCau(MaADMIN, NV.getMaNV(), date, time,
                        "Đã đến thời hạn chấm KPI của nhân viên bạn phụ trách", LoaiRequest.DANH_GIA_KPI, null);
            }
        }
    }
    // Đợt tuyển dụng quá hạn thì tự đóng
    for(DotTuyenDung dot : new ArrayList<>(DanhSachDotTuyenDung.getDsDot())){
        if(dot.getTrangThai() == TRANG_THAI_DOT.DANG_TUYEN_DUNG && date.isAfter(dot.getNgayHetHan())){
            dot.setTrangThai(TRANG_THAI_DOT.KET_THUC); // ⚠ 2
            GuiThongBao(MaADMIN, dot.getMaNguoiTuyen(), date, time,
                    "Đợt tuyển dụng " + dot.getMaDotTuyenDung() + " đã quá hạn và được đóng tự động");
        }
    }
    // Khóa đào tạo kết thúc hôm qua thì báo người phụ trách chấm điểm
    for(KhoaDaoTao khoa : DanhSachKhoaDaoTao.getDskdt()){ // ⚠ 3
        if(khoa.getNgayKetThuc() != null && date.isEqual(khoa.getNgayKetThuc().plusDays(1))){
            GuiYeuCau(MaADMIN, khoa.getMaNVDaoTao(), date, time,
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
                LapDayChamCong(nv, d);
}
        }
    }
}
    
    
    
    //quản lý
    
    public static void ThuongSangKien(Scanner sc, LocalDate date, LocalTime time, String MaQuanLy){
        if(HuyTacVu(sc)) return;
        NhanVien nv;
        while(true){
        System.out.println("Nhân viên bạn muốn thưởng sáng kiến có mã nhân viên là :");
        nv = DanhSachNhanVien.timKiem(sc.nextLine().trim());
        if(nv != null) break;
        System.out.println("Không tìm thấy nhân viên tương ứng");
    }
        double tien;
        while(true){
        tien = NhapSo(sc, "Số tiền thưởng sáng kiến là ?\n Tối thiểu 100.000vnd - Tối đa 500.000vnd");
        if(tien >= 100000 && tien <= 500000) break;
        System.out.println("Số tiền phải trong khoảng 100.000 đến 500.000 vnd");
    }
        nv.getDsktkl().them(new KhenThuong(LoaiKhenThuong.THUONG_SANG_KIEN, null, date, tien, MaQuanLy, nv.getMaNV()));
        GuiThongBao(MaQuanLy, nv.getMaNV(), date, time, "thưởng sáng kiến " + tien + " vnd");
        NhatKyHoatDong.them("Quản lý mã " + MaQuanLy + " thưởng sáng kiến có giá trị " + tien + " vào " + time
            + " ngày " + date + " cho nhân viên mã " + nv.getMaNV());
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
                    DanhGiaKPI kpi = new DanhGiaKPI(DiemKPI, thang , nam, MaQuanLy, nv.getMaNV());
                    NhanVienFullTime nvFull = (NhanVienFullTime) nv;
                    
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
    ArrayList<NhanVien> ds = DanhSachNhanVien.timKiemTheoMaQuanLyPhuTrachVaFullTime(MaNVDanhGia);
    if(ds.isEmpty()){ // code cũ kiểm tra == null nên không bao giờ chạy
        System.out.println("Danh sách nhân viên full-time cấp dưới trống");
        return;
    }
    for(NhanVien nv : ds){
        DanhGiaKPI(sc, date, time, MaNVDanhGia, nv.getMaNV());
        if(daHuyTacVu) return;
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
        NhanVien nv = DanhSachNhanVien.timKiem(MaNV);
    if(nv == null){
        System.out.println("Không tìm thấy nhân viên cần xét lương");
        return;
    }
    System.out.println(nv);
    int chon = NhapSoNguyen(sc, "Đồng ý tăng lương cho nhân viên mã " + MaNV + " không?\n0.Không\n1.Có", 0, 1);
    if(chon == 1){
        boolean full = KiemTraIsFullTime(nv);
        double hienTai = nv.getLuongCoSo();
        double moi;
        while(true){
            moi = NhapSo(sc, "Hiện tại " + (full ? "lương cơ bản" : "lương một giờ") + " là " + hienTai
                    + ", bạn muốn nâng lên thành bao nhiêu?");
            if(moi > hienTai) break;
            System.out.println("Mức mới phải lớn hơn mức hiện tại");
        }
        HopDong hd = nv.getHopDong();
        hd.setMucLuongCoSo(moi);
        nv.setHopDong(hd);
        nv.setNgayXetNangLuong(date.plusYears(1));
        GuiThongBao(MaQuanLyPhuTrach, MaNV, date, time, "Quản lý đã đồng ý nâng lương cho bạn").setRequest(LoaiRequest.THONG_BAO);
        NhatKyHoatDong.them("Quản lý mã " + MaQuanLyPhuTrach + " nâng lương thành " + moi
                + " vào " + time + " ngày " + date + " cho nhân viên mã " + MaNV);
    }else{
        System.out.println("Nhập lý do tại sao không tăng lương: ");
        String Message = sc.nextLine().trim();
        nv.setNgayXetNangLuong(date.plusMonths(6)); 
        GuiThongBao(MaQuanLyPhuTrach, MaNV, date, time, "từ chối nâng lương vì " + Message).setRequest(LoaiRequest.THONG_BAO);
        NhatKyHoatDong.them("Quản lý mã " + MaQuanLyPhuTrach + " từ chối nâng lương vào " + time + " ngày " + date
                + " cho nhân viên mã " + MaNV);
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
        DotTuyenDung dot = new DotTuyenDung(SoLuong,MaNguoiTuyen, NgayBatDau, NgayKetThuc, vt);
        return dot;
    }
    
    public static void DangTaiTuyenDung(Scanner sc, String MaQuanLy, String MaCapTren, LocalDate date, LocalTime time){
        DotTuyenDung dot = TaoDotTuyenDung(sc,MaQuanLy);
        if(dot==null){
            return;
        }
        
        System.out.println("Nhập lý do");
        String Message = sc.nextLine().trim();
        DanhSachDotTuyenDung.them(dot);
        GuiThongBao(MaQuanLy, MaCapTren, date, time, Message).setRequest(LoaiRequest.DANG_TAI_DOT_TUYEN_DUNG);
        NhatKyHoatDong.them("Quản lý mã "+ MaQuanLy+ " gửi đề nghị tuyển dụng vào "+ time + " ngày " + date +
                "cho giám đốc mã " + MaCapTren);
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
                DanhSachDotTuyenDung.xoa(MaDotTuyenDung);
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
        System.out.println("Chắc chắn chưa? Hay muốn hoàn tác, thoát khỏi quy trình\n Nhập 'thoát' để thoát hoặc nhập bất kỳ để tiếp tục");
        String input = sc.nextLine().trim();
        if(input.equalsIgnoreCase("thoát")){
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
    
public static KhoaDaoTao TaoKhoaDaoTao(Scanner sc, String MaNguoiTao, LocalDate date, LocalTime time) {
    if (HuyTacVu(sc)) return null;

    System.out.println("Nhập tên khóa đào tạo");
    String ten = sc.nextLine().trim();

    // Vị trí tuyển dụng mà khóa này đào tạo cho
    ViTri vt;
    NhanVien quanLy;
    while (true) {
        System.out.println("Nhập mã vị trí tuyển dụng của khóa");
        vt = ViTri.timKiem(sc.nextLine().trim());
        if (vt == null) {
            System.out.println("Không tìm thấy vị trí");
            continue;
        }
        if (vt == ViTri.GIAM_DOC_DIEU_HANH) {
            System.out.println("Vị trí này không thuộc phòng ban nào, không thể mở khóa đào tạo");
            continue;
        }
        // Người đào tạo là quản lý của phòng ban chứa vị trí đó
        quanLy = DanhSachNhanVien.TimQuanLyTheoViTri(vt);
        if (quanLy == null) {
            System.out.println("Phòng ban " + vt.getPhongBan() + " chưa có quản lý, hãy chọn vị trí khác");
            continue;
        }
        break;
    }
    System.out.println("Người đào tạo: " + quanLy.getHoTen() + " (mã " + quanLy.getMaNV()
            + ", phòng ban " + quanLy.getMaPhongBan() + ")");

    LocalDate ngayBatDau;
    while (true) {
        ngayBatDau = NhapNgay(sc, "Nhập ngày bắt đầu khóa");
        if (!ngayBatDau.isBefore(date)) break;
        System.out.println("Ngày bắt đầu không được trước hôm nay");
    }
    LocalDate ngayKetThuc;
    while (true) {
        ngayKetThuc = NhapNgay(sc, "Nhập ngày kết thúc khóa");
        if (ngayKetThuc.isAfter(ngayBatDau)) break;
        System.out.println("Ngày kết thúc phải sau ngày bắt đầu");
    }

    KhoaDaoTao khoa = new KhoaDaoTao(ten, ngayBatDau, ngayKetThuc, vt, quanLy.getMaNV());
    // ⚠ Nếu DanhSachKhoaDaoTao.them không tự sinh mã thì phải gọi khoa.setMaKhoaHoc(...) trước dòng này
    DanhSachKhoaDaoTao.them(khoa);

    NhatKyHoatDong.them("Người dùng mã " + MaNguoiTao + " tạo khóa đào tạo " + khoa.getMaKhoaHoc()
            + " cho vị trí " + vt.getMaViTri() + ", người đào tạo là quản lý mã " + quanLy.getMaNV()
            + " vào " + time + " ngày " + date);
    System.out.println("Đã tạo khóa đào tạo mã " + khoa.getMaKhoaHoc());
    return khoa;
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
    
    private static LocalDate NhapNgay(Scanner sc, String thongBao) {
    while (true) {
        System.out.println(thongBao + " (dd/MM/yyyy)");
        LocalDate d = isValidDate(sc.nextLine().trim(), "dd/MM/yyyy");
        if (d != null) return d;
        System.out.println("Ngày không hợp lệ, vui lòng nhập lại");
    }
}

// Đọc số thực >= 0, nhập sai thì hỏi lại
private static double NhapSo(Scanner sc, String thongBao) {
    while (true) {
        System.out.println(thongBao);
        try {
            double v = Double.parseDouble(sc.nextLine().trim());
            if (v >= 0) return v;
        } catch (NumberFormatException e) { }
        System.out.println("Vui lòng nhập số hợp lệ (>= 0)");
    }
}

// Nhập ca làm, lặp đến khi hợp lệ với loại nhân viên
private static String NhapCaLam(Scanner sc, boolean fullTime) {
    while (true) {
        System.out.println("Nhập ca làm");
        String ca = sc.nextLine().trim();
        boolean hopLe = fullTime ? LoaiCaLamFullTime.NhanDien(ca) != null
                                 : LoaiCaLamPartTime.NhanDien(ca) != null;
        if (hopLe) return ca;
        System.out.println("Ca làm không hợp lệ, vui lòng nhập lại");
    }
}

// Tạo đúng class con của NhanVien (NhanVien là abstract)
private static NhanVien TaoNhanVien(String maNV, UngVien uv, String maQL,
                                    String caLam, boolean fullTime) {
    if (fullTime) {
        return new NhanVienFullTime(maNV, uv.getCCCD(), uv.getHoTen(), uv.getSDT(), maQL,
                uv.getNgayThangNamSinh(), caLam);
    }
    return new NhanVienPartTime(maNV, uv.getCCCD(), uv.getHoTen(), uv.getSDT(), maQL,
            uv.getNgayThangNamSinh(), caLam);
}

public static HopDong KyHopDong(Scanner sc, ViTri vt, LocalDate ngayKy) {
    LOAI_HOP_DONG loai;
    if (!vt.isFullTime()) {
        loai = LOAI_HOP_DONG.PART_TIME;
        System.out.println("Loại hợp đồng: " + loai.getMoTa());
    } else {
        while (true) {
            System.out.println("Chọn loại hợp đồng:\n1." + LOAI_HOP_DONG.CHINH_THUC_NGAN_HAN.getMoTa()
                    + "\n2." + LOAI_HOP_DONG.CHINH_THUC_DAI_HAN.getMoTa());
            String chon = sc.nextLine().trim();
            if (chon.equals("1")) { loai = LOAI_HOP_DONG.CHINH_THUC_NGAN_HAN; break; }
            if (chon.equals("2")) { loai = LOAI_HOP_DONG.CHINH_THUC_DAI_HAN; break; }
            System.out.println("Vui lòng nhập 1 hoặc 2");
        }
    }

    LocalDate ngayBatDau;
    while (true) {
        ngayBatDau = NhapNgay(sc, "Nhập ngày bắt đầu hợp đồng");
        if (!ngayBatDau.isBefore(ngayKy)) break;
        System.out.println("Ngày bắt đầu không được trước ngày ký");
    }
    LocalDate ngayHetHan;
    while (true) {
        ngayHetHan = NhapNgay(sc, "Nhập ngày hết hạn hợp đồng");
        if (ngayHetHan.isAfter(ngayBatDau)) break;
        System.out.println("Ngày hết hạn phải sau ngày bắt đầu");
    }

    double luongCoBan = 0, luongTheoGio = 0;
    if (loai == LOAI_HOP_DONG.PART_TIME) {
        luongTheoGio = NhapSo(sc, "Nhập lương theo giờ");
    } else {
        luongCoBan = NhapSo(sc, "Nhập lương cơ bản");
    }
    double phuCap = NhapSo(sc, "Nhập phụ cấp");

    System.out.println("Nhập ghi chú (có thể để trống)");
    String ghiChu = sc.nextLine().trim();

    return new HopDong(null, null, ngayKy, ngayBatDau, ngayHetHan, vt.getMaViTri(),
            luongCoBan, luongTheoGio, phuCap, loai,
            TRANG_THAI_HOP_DONG.DANG_CO_HIEU_LUC, ghiChu);
}

public static void TiepNhanUngVien(Scanner sc, String MaNguoiThucHien, LocalDate date, LocalTime time) {
    if (HuyTacVu(sc)) return;
    // 1. Tìm đợt tuyển dụng
DotTuyenDung dot;
while (true) {
    System.out.println("Nhập mã đợt tuyển dụng");
    dot = DanhSachDotTuyenDung.timKiem(sc.nextLine().trim());
    if (dot != null) break;
    System.out.println("Không tìm thấy đợt tuyển dụng");
}
if (dot.getTrangThai() != TRANG_THAI_DOT.DANG_TUYEN_DUNG) {
    System.out.println("Đợt tuyển dụng này chưa được duyệt hoặc đã đóng, không thể tiếp nhận");
    return;
}

// 2. Tìm ứng viên (dot.timKiem tự in thông báo nếu không thấy)
UngVien uv;
while (true) {
    System.out.println("Nhập mã ứng viên");
    uv = dot.timKiem(sc.nextLine().trim());
    if (uv != null) break;
}

// 3. Kiểm tra điều kiện
if (uv.getKq() == null || uv.getKq().getKetQua() != LoaiKetQua.DAT) {
    System.out.println("Ứng viên chưa đạt hoặc chưa được chấm điểm, không thể tiếp nhận");
    return;
}
if (uv.isDaLaNhanVien()) {
    System.out.println("Ứng viên này đã được chuyển thành nhân viên rồi");
    return;
}
for (NhanVien n : DanhSachNhanVien.getDsnv()) {
    if (n.getCCCD() != null && n.getCCCD().equals(uv.getCCCD())) {
        System.out.println("CCCD này đã tồn tại trong danh sách nhân viên (mã " + n.getMaNV() + ")");
        return;
    }
}

// 4. Quản lý phụ trách và phòng ban lấy từ người mở đợt tuyển dụng
NhanVien quanLy = DanhSachNhanVien.timKiem(dot.getMaNguoiTuyen());
if (quanLy == null) {
    System.out.println("Không tìm thấy quản lý phụ trách của đợt tuyển dụng này");
    return;
}
ViTri vt = dot.getViTri(); // lấy vị trí từ chính đợt tuyển dụng cho chắc chắn khớp
String caLam = NhapCaLam(sc, vt.isFullTime());
 // 5. Nhập hợp đồng và xác nhận trước khi tạo nhân viên
    System.out.println("--- KÝ HỢP ĐỒNG CHO " + uv.getHoTen() + " ---");
    HopDong hd = KyHopDong(sc, vt, date);
    System.out.println(hd);
    while (true) {
        System.out.println("Xác nhận tiếp nhận và ký hợp đồng?\n0.Không\n1.Có");
        String c = sc.nextLine().trim();
        if (c.equals("0")) {
            System.out.println("Đã hủy, chưa tạo nhân viên");
            return;
        }
        if (c.equals("1")) break;
        System.out.println("Vui lòng nhập đúng định dạng");
    }

    // 6. Tạo nhân viên. Sinh mã sau khi đã xác nhận để không bị "đốt" mã
    String maNV = DanhSachNhanVien.taoMa();
    NhanVien nv = TaoNhanVien(maNV, uv, quanLy.getMaNV(), caLam, vt.isFullTime());

    hd.setMaNV(maNV);
    hd.setMaHopDong("HD" + maNV);
    nv.setHopDong(hd); // gán lương, phụ cấp, ngày vào làm, vị trí, chức vụ, phòng ban, quyền tài khoản

    DanhSachNhanVien.them(nv);
    // ⚠ 3: nếu tài khoản được lưu ở DanhSachTaiKhoan thì thêm dòng dưới (đổi tên hàm cho đúng)
    DanhSachTaiKhoan.them(nv.getTaiKhoan());
    uv.setDaLaNhanVien(true);

    NhatKyHoatDong.them("Người dùng mã " + MaNguoiThucHien + " tiếp nhận ứng viên " + uv.getMaUngVien()
            + " thành nhân viên " + maNV + " và ký hợp đồng " + hd.getMaHopDong()
            + " vào " + time + " ngày " + date);
    System.out.println("Đã tạo nhân viên " + maNV + " và ký hợp đồng " + hd.getMaHopDong());
    System.out.println("Tài khoản đăng nhập: " + maNV); // ⚠ chỉnh nếu TaiKhoan dùng username/mật khẩu khác
}
    
    public static void PhanQuyen(TaiKhoan tk, Scanner sc){
        if(tk.getRole()==LoaiVaiTro.HR_ADMIN){
            PhanQuyen.ADMIN(sc, tk.getMaNV());
        }
        if(tk.getRole()==LoaiVaiTro.NHAN_VIEN){
            PhanQuyen.NHAN_VIEN(sc, tk.timKiemNhanVien());
        }
        if(tk.getRole()==LoaiVaiTro.QUAN_LY){
            PhanQuyen.QUAN_LY(sc, tk.timKiemNhanVien());
        }
        if(tk.getRole()==LoaiVaiTro.DIRECTOR){
            PhanQuyen.DIRECTOR(sc, tk.timKiemNhanVien());
        }
        if(tk.getRole()==LoaiVaiTro.BOSS){
            PhanQuyen.BOSS(sc, tk.timKiemNhanVien());
        }
    }
    
    private static String ThongBaoKyLuat(LoaiKyLuat kl, LoaiViPham vp, LocalDate date, NhanVien nv){
        String Message = null;
        int SoLanViPham = nv.getDsViPham().SoLanViPham(vp, date);
        switch(kl){
            case LoaiKyLuat.KHIEN_TRACH:
                Message = "Bạn bị khiển trách vì " + vp.getMoTa() + " lần thứ " + SoLanViPham;
                break;
            case LoaiKyLuat.KEO_DAI_THOI_HAN_NANG_LUONG:
                Message = "Bạn bị kéo dài thời hạn nâng lương vì " + vp.getMoTa() + " lần thứ " + SoLanViPham;
                break;
            case LoaiKyLuat.CACH_CHUC:
                Message = "Bạn bị cách chức vì " + vp.getMoTa()+ " lần thứ " + SoLanViPham;
                break;
            case LoaiKyLuat.SA_THAI:
                Message = "Bạn bị sa thải vì "+ vp.getMoTa() + " lần thứ " + SoLanViPham;
                break;
            default:
                System.out.println(" Không có trong danh sách hình thức kỷ luật");
                break;
        }
        return Message;
    }
    
    public static void XuLyKyLuat(LoaiKyLuat kl, LoaiViPham vp, LocalDate date, LocalTime time, NhanVien nv){
        if(nv.isDaNghiViec()) return;
        String Message = ThongBaoKyLuat(kl, vp, date, nv);
        if(Message != null){
        GuiThongBao("HETHONG", nv.getMaNV(), date, time, Message);
    }
        switch(kl){
        case KHIEN_TRACH:
            break;
        case KEO_DAI_THOI_HAN_NANG_LUONG:
            if(nv.getNgayXetNangLuong() != null){
                nv.setNgayXetNangLuong(nv.getNgayXetNangLuong().plusMonths(6));
            }
            break;
        case CACH_CHUC:
            // nhân viên thường không có chức để cách, chỉ quản lý và giám đốc mới cần người thay
            YeuCauBoNhiemThayThe(nv, date, time);
            break;
        case SA_THAI:
            ChamDutNhanVien(nv, date, date, time, "HETHONG", "bị sa thải");
            break;
        default:
            System.out.println(" Không có trong danh sách hình thức kỷ luật");
            break;
    }
        NhatKyHoatDong.them("Hệ thống áp dụng kỷ luật " + kl.getMoTa() + " cho nhân viên mã " + nv.getMaNV()
            + " vào " + time + " ngày " + date);
}
    
        public static boolean KiemTraMaViTriTonTai(String MaViTri){
        for(ViTri vt : ViTri.values()){
            if(MaViTri.equalsIgnoreCase(vt.getMaViTri())){
                return true;
            }
        }
        return false;
        }
        
private static void DoiViTri(NhanVien nv, ViTri vt) {
    HopDong hd = nv.getHopDong();
    hd.setLoaiViTri(vt);
    // Lương theo chức vụ mới. Tính từ vị trí vì ChucVu của nv chỉ đổi sau setHopDong.
    hd.setMucLuongCoSo(ViTri.XacDinhChucVu(vt.getMaViTri()).getLuongCoBan());
    nv.setHopDong(hd);
}

// Hỏi vị trí mới cho người bị cách chức. phongBanBatBuoc = null nghĩa là không ràng buộc phòng ban.
private static ViTri NhapViTriMoi(Scanner sc, NhanVien nvXuong, String phongBanBatBuoc) {
    while (true) {
        System.out.println("Nhập mã vị trí mới cho " + nvXuong.getHoTen() + " sau khi bị cách chức");
        ViTri vt = ViTri.timKiem(sc.nextLine().trim());
        if (vt == null) {
            System.out.println("Không tìm thấy vị trí");
            continue;
        }
        if (!vt.isFullTime() || ViTri.XacDinhChucVu(vt.getMaViTri()) != LoaiChucVu.NHAN_VIEN) {
            System.out.println("Phải là vị trí nhân viên full-time ");
            continue;
        }
        if (phongBanBatBuoc != null && !vt.getPhongBan().equalsIgnoreCase(phongBanBatBuoc)) {
            System.out.println("Vị trí phải thuộc phòng ban " + phongBanBatBuoc);
            continue;
        }
        return vt;
    }
}

private static ViTri LayViTriMoi(Scanner sc, NhanVien nvXuong, String phongBanBatBuoc){
    if(!nvXuong.isDaNghiViec()){
        return NhapViTriMoi(sc, nvXuong, phongBanBatBuoc);
    }
    for(ViTri vt : ViTri.values()){
        if(vt.isFullTime() && ViTri.XacDinhChucVu(vt.getMaViTri()) == LoaiChucVu.NHAN_VIEN
                && vt.getPhongBan() != null && vt.getPhongBan().equalsIgnoreCase(nvXuong.getMaPhongBan())){
            return vt;
        }
    }
    return ViTri.NHAN_VIEN_KINH_DOANH; // giám đốc không thuộc phòng ban nào
}

public static void ThayChuc(NhanVien nvXuong, NhanVien nvLen, ViTri viTriMoiCuaNvXuong) {
    ViTri viTriNvLenNhan = nvXuong.getHopDong().getLoaiViTri(); // vị trí nvLen sẽ nhận

    // 1. Đổi cấp báo cáo (thứ tự giữ như code cũ)
    nvLen.setMaQuanLyPhuTrach(nvXuong.getMaQuanLyPhuTrach());
    nvXuong.setMaQuanLyPhuTrach(nvLen.getMaNV());
    for (NhanVien n : DanhSachNhanVien.timKiemTheoMaQuanLyPhuTrach(nvXuong.getMaNV())) {
        n.setMaQuanLyPhuTrach(nvLen.getMaNV());
    }

    // 2. Đổi vị trí (chức vụ, phòng ban, quyền, lương tự cập nhật)
    DoiViTri(nvLen, viTriNvLenNhan);
    DoiViTri(nvXuong, viTriMoiCuaNvXuong);
}

    public static void BoNhiem(LocalDate date, LocalTime time, String MaNVCachChuc, String MaNVBoNhiem, Scanner sc, NhanVien nv) {
    NhanVien nvXuong = DanhSachNhanVien.timKiem(MaNVCachChuc);
    NhanVien nvLen = DanhSachNhanVien.timKiem(MaNVBoNhiem);
    if (nvXuong == null || nvLen == null) {
        System.out.println("Không tìm thấy nhân viên");
        return;
    }
    if (nvXuong == nvLen) {
        System.out.println("Không thể bổ nhiệm chính người bị cách chức");
        return;
    }
    if (nvLen instanceof NhanVienPartTime) {
        System.out.println("Không thể bổ nhiệm người làm part-time");
        return;
    }

    if (nvLen.getChucVu() == LoaiChucVu.NHAN_VIEN && nvXuong.getChucVu() == LoaiChucVu.QUAN_LY) {
        // Vị trí quản lý thuộc về một phòng ban, nên người được bổ nhiệm phải cùng phòng ban
        if (!nvLen.getMaPhongBan().equalsIgnoreCase(nvXuong.getMaPhongBan())) {
            System.out.println("Người được bổ nhiệm phải thuộc cùng phòng ban với quản lý bị cách chức");
            return;
        }
        ViTri viTriMoi = LayViTriMoi(sc, nvXuong, nvXuong.getMaPhongBan());
        ThayChuc(nvXuong, nvLen, viTriMoi);
        NhatKyHoatDong.them("Người dùng mã " + nv.getMaNV() + " bổ nhiệm nhân viên " + nvLen.getMaNV()
                + " thay quản lý " + nvXuong.getMaNV() + " vào " + time + " ngày " + date);
    }
    else if (nvLen.getChucVu() == LoaiChucVu.QUAN_LY && nvXuong.getChucVu() == LoaiChucVu.GIAM_DOC) {
        ViTri viTriMoi = LayViTriMoi(sc, nvXuong, null);
        ThayChuc(nvXuong, nvLen, viTriMoi);
        NhatKyHoatDong.them("Người dùng mã " + nv.getMaNV() + " bổ nhiệm quản lý " + nvLen.getMaNV()
                + " lên giám đốc thay " + nvXuong.getMaNV() + " vào " + time + " ngày " + date);
        // TODO: ghế quản lý của nvLen đang trống, cần chọn người thay thế (xem ghi chú bên dưới)
        System.out.println("Ghế quản lý phòng ban " + viTriMoi.getPhongBan() + " cũ của "
                + nvLen.getHoTen() + " đang trống, cần bổ nhiệm người thay thế");
    }
    else {
        System.out.println("Chỉ bổ nhiệm được: nhân viên lên quản lý (thay quản lý) hoặc quản lý lên giám đốc (thay giám đốc)");
    }
}

        
        public static String NhapMaNV(Scanner sc){
            String input = sc.nextLine();//////////////////////
            if(input==null){
                System.out.println("không hợp lệ");
                return null;
            }
            NhanVien nv = DanhSachNhanVien.timKiem(input);
            if(nv==null){
                System.out.println("Không tìm thấy nhân viên");
                return null;
            }
            return input;
        }
        
        public static void ChonRaNguoiThayThe(LocalDate date, LocalTime time, String MaNVCachChuc, Scanner sc, NhanVien nv){
            DanhSachNhanVien.SortTheoThamNien();
            System.out.println("Chọn người thay thế cho nhân viên mã "+ MaNVCachChuc);
            int input = NhapSoNguyen(sc, "\n0.Thoát\n1.Bổ nhiệm nội bộ\n2.Tuyển ngoài", 0, 2);
            if(input == 0){
            daHuyTacVu = true; // thoát thì request bổ nhiệm vẫn còn mở
            return;
            }
            while(input ==-1 || input<1 || input >2 ){
                System.out.println("Không hơp lệ, nhập lại");
                input = sc.nextInt();
                sc.nextLine();
            }
            if(input == 1){
                System.out.println("Nhập mã nhân viên bạn muốn bổ nhiệm");
                String nhap = NhapMaNV(sc);
                while(nhap==null){
                    nhap = NhapMaNV(sc);
                }
                BoNhiem(date, time, MaNVCachChuc, nhap, sc, nv);
            }
            else{
                if(!nv.getMaNV().equalsIgnoreCase("Boss"))
                DangTaiTuyenDung(sc, nv.getMaNV(), nv.getMaQuanLyPhuTrach(), date, time);
                else {
                DotTuyenDung dot = TaoDotTuyenDung(sc, nv.getMaNV());
                if (dot == null) {
                return; // người dùng chọn thoát
    }
                dot.setTrangThai(TRANG_THAI_DOT.DANG_TUYEN_DUNG);
                DanhSachDotTuyenDung.them(dot);
                ArrayList<NhanVien> temp = DanhSachNhanVien.timKiemTheoMaQuanLyPhuTrach(MaNVCachChuc);
                NhanVien nvXuong = DanhSachNhanVien.timKiem(MaNVCachChuc);
                for (NhanVien NV : temp) {
                NV.setMaQuanLyPhuTrach(nvXuong.getMaQuanLyPhuTrach());
                        }
                    }
                }
            
        }
        
        private static boolean DangTrongHopDong(NhanVien nv, LocalDate date){
        HopDong hd = nv.getHopDong();
        if(hd == null) return true; // dữ liệu cũ chưa có hợp đồng thì giữ hành vi cũ
        if(hd.getNgayBatDauHopDong() != null && date.isBefore(hd.getNgayBatDauHopDong())) return false;
        if(hd.getNgayHetHan() != null && date.isAfter(hd.getNgayHetHan())) return false;
        return true;
}
        private static void YeuCauBoNhiemThayThe(NhanVien nv, LocalDate date, LocalTime time){
        if(nv.getChucVu() != LoaiChucVu.QUAN_LY && nv.getChucVu() != LoaiChucVu.GIAM_DOC) return;
        if(nv.getMaQuanLyPhuTrach() == null) return;
            GuiYeuCau("HETHONG", nv.getMaQuanLyPhuTrach(), date, time,
            "Nhân viên mã " + nv.getMaNV() + " mất chức vụ " + nv.getChucVu() + ", cần bổ nhiệm người thay thế",
            LoaiRequest.BO_NHIEM, nv.getMaNV());
}
        // Chấm dứt quan hệ lao động (dùng chung cho sa thải và từ chối gia hạn):
// xuất phiếu lương cuối, hủy hợp đồng, khóa tài khoản, báo người liên quan.
// ngayKetThuc: ngày hợp đồng chấm dứt (sa thải = hôm nay, hết hạn = ngày hết hạn cũ)
        public static void ChamDutNhanVien(NhanVien nv, LocalDate ngayKetThuc, LocalDate date, LocalTime time,
                                   String MaNguoiThucHien, String lyDo){
        if(nv.isDaNghiViec()) return;

    // 1. Phiếu lương cuối: chốt bảng chấm công hiện tại rồi tính như cuối kỳ
        nv.ThemBangChamCong();
        BangChamCong bang = nv.getBcc();
        PhieuLuong pl = nv.TaoPhieuLuong(bang.getThang(), bang.getNam());
        pl.setMaPhieu(nv.getDspl().taoMa());
        nv.ThemPhieuLuong(pl);

    // 2. Hợp đồng: hủy và chốt ngày kết thúc (DangTrongHopDong tự dừng chấm công sau ngày này)
        HopDong hd = nv.getHopDong();
        if(hd != null){
        hd.setTrangThaiHopDong(TRANG_THAI_HOP_DONG.DA_HUY);
        hd.setNgayHetHan(ngayKetThuc);
        String cu = hd.getGhiChu() == null || hd.getGhiChu().isEmpty() ? "" : hd.getGhiChu() + "; ";
        hd.setGhiChu(cu + "Chấm dứt ngày " + ngayKetThuc + ": " + lyDo);
    }

    // 3. Khóa tài khoản (giữ lại trong danh sách để còn tra cứu)
        if(nv.getTaiKhoan() != null){
        nv.getTaiKhoan().setTrangThai(TaiKhoan.TrangThaiHoatDong.BI_KHOA);
    }
        for(TaiKhoan tk : DanhSachTaiKhoan.getDstk()){
        if(tk.getMaNV() != null && tk.getMaNV().equalsIgnoreCase(nv.getMaNV())){
            tk.setTrangThai(TaiKhoan.TrangThaiHoatDong.BI_KHOA);
        }
    }

    // 4. Báo quản lý và yêu cầu người thay thế nếu người này đang giữ chức
        if(nv.getMaQuanLyPhuTrach() != null){
        GuiThongBao("HETHONG", nv.getMaQuanLyPhuTrach(), date, time,
                "Nhân viên " + nv.getMaNV() + " (" + nv.getHoTen() + ") đã chấm dứt hợp đồng: " + lyDo
                + ". Phiếu lương cuối đã được xuất.");
    }
        YeuCauBoNhiemThayThe(nv, date, time);

        NhatKyHoatDong.them("Người dùng mã " + MaNguoiThucHien + " chấm dứt hợp đồng nhân viên " + nv.getMaNV()
            + " (" + lyDo + "), phiếu lương cuối " + pl.getMaPhieu() + " vào " + time + " ngày " + date);
        System.out.println("Đã chấm dứt hợp đồng, xuất phiếu lương cuối và khóa tài khoản của " + nv.getHoTen());
}
        
        
        public static void DuyetGiaHanHopDong(Scanner sc, String MaNguoiDuyet, String MaNV, LocalDate date, LocalTime time){
        if(HuyTacVu(sc)) return;
        NhanVien nv = DanhSachNhanVien.timKiem(MaNV);
        if(nv == null){
        System.out.println("Không tìm thấy nhân viên cần gia hạn hợp đồng");
        return;
    }
        HopDong hd = nv.getHopDong();
        if(hd == null || hd.getTrangThaiHopDong() != TRANG_THAI_HOP_DONG.HET_HAN){
        System.out.println("Hợp đồng này không còn chờ gia hạn (đã được xử lý rồi)");
        return;
    }
        System.out.println("Nhân viên " + nv.getHoTen() + " (mã " + MaNV + ")");
        System.out.println(hd);

        int chon = NhapSoNguyen(sc, "Quyết định?\n0.Từ chối gia hạn (chấm dứt hợp đồng)\n1.Gia hạn", 0, 1);
        if(chon == 0){
        System.out.println("Nhập lý do: ");
        String lyDo = sc.nextLine().trim();
        // ngày chấm dứt là ngày hết hạn cũ, không phải hôm nay
        ChamDutNhanVien(nv, hd.getNgayHetHan(), date, time, MaNguoiDuyet, "không gia hạn hợp đồng, lý do: " + lyDo);
        return;
    }

        LOAI_HOP_DONG loai = hd.getLoaiHopDong();
        if(loai != LOAI_HOP_DONG.PART_TIME){
        int c = NhapSoNguyen(sc, "Loại hợp đồng mới:\n1." + LOAI_HOP_DONG.CHINH_THUC_NGAN_HAN.getMoTa()
                + "\n2." + LOAI_HOP_DONG.CHINH_THUC_DAI_HAN.getMoTa(), 1, 2);
        loai = (c == 1) ? LOAI_HOP_DONG.CHINH_THUC_NGAN_HAN : LOAI_HOP_DONG.CHINH_THUC_DAI_HAN;
    }
        LocalDate ngayHetHanMoi;
        while(true){
        ngayHetHanMoi = NhapNgay(sc, "Nhập ngày hết hạn mới của hợp đồng");
        if(ngayHetHanMoi.isAfter(date)) break;
        System.out.println("Ngày hết hạn mới phải sau hôm nay (" + date + ")");
    }

        LocalDate ngayHetHanCu = hd.getNgayHetHan();
        hd.setLoaiHopDong(loai);
        hd.setNgayHetHan(ngayHetHanMoi);
        hd.setTrangThaiHopDong(TRANG_THAI_HOP_DONG.DANG_CO_HIEU_LUC);
        String cu = hd.getGhiChu() == null || hd.getGhiChu().isEmpty() ? "" : hd.getGhiChu() + "; ";
        hd.setGhiChu(cu + "Gia hạn ngày " + date + ", hạn cũ " + ngayHetHanCu);

        GuiThongBao(MaNguoiDuyet, MaNV, date, time, "Hợp đồng " + hd.getMaHopDong() + " của bạn đã được gia hạn đến ngày " + ngayHetHanMoi);
        NhatKyHoatDong.them("Người dùng mã " + MaNguoiDuyet + " gia hạn hợp đồng " + hd.getMaHopDong()
            + " của nhân viên " + MaNV + " đến " + ngayHetHanMoi + " vào " + time + " ngày " + date);
        System.out.println("Đã gia hạn hợp đồng đến " + ngayHetHanMoi);
}
        
}
    
    

