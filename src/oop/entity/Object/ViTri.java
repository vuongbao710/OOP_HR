package oop.entity.Object;

import oop.entity.DanhSach.DanhSachNhanVien;
import oop.entity.Enum.LoaiChucVu;
import oop.entity.Enum.LoaiVaiTro;

public enum ViTri {
    // --- KINH DOANH (7 vị trí) ---
    NHAN_VIEN_KINH_DOANH("VT01", "Nhân viên kinh doanh", "PB01", true),
    NHAN_VIEN_KINH_DOANH_THI_TRUONG("VT02", "Nhân viên kinh doanh thị trường", "PB01", true),
    CHUYEN_VIEN_PHAT_TRIEN_DOI_TAC("VT03", "Chuyên viên phát triển đối tác", "PB01", true),
    CHUYEN_VIEN_KINH_DOANH_ONLINE("VT04", "Chuyên viên kinh doanh online", "PB01", true),
    NHAN_VIEN_HO_TRO_KINH_DOANH("VT05", "Nhân viên hỗ trợ kinh doanh", "PB01", true),
    CHUYEN_VIEN_PHAN_TICH_DOANH_SO("VT06", "Chuyên viên phân tích doanh số", "PB01", true),
    CONG_TAC_VIEN_BAN_HANG("VT07", "Cộng tác viên bán hàng (Part-Time)", "PB01", false),

    // --- SẢN XUẤT (8 vị trí) ---
    CONG_NHAN_VAN_HANH_MAY("VT08", "Công nhân vận hành máy", "PB02", true),
    CONG_NHAN_LAP_RAP("VT09", "Công nhân lắp ráp", "PB02", true),
    KY_THUAT_VIEN_BAO_TRI_MAY_MOC("VT10", "Kỹ thuật viên bảo trì máy móc", "PB02", true),
    NHAN_VIEN_KIEM_TRA_CHAT_LUONG("VT11", "Nhân viên kiểm tra chất lượng", "PB02", true),
    KY_SU_QUY_TRINH_SAN_XUAT("VT12", "Kỹ sư quy trình sản xuất", "PB02", true),
    NHAN_VIEN_AN_TOAN_LAO_DONG("VT13", "Nhân viên an toàn lao động", "PB02", true),
    NHAN_VIEN_KE_HOACH_SAN_XUAT("VT14", "Nhân viên kế hoạch sản xuất", "PB02", true),
    CONG_NHAN_THOI_VU("VT15", "Công nhân thời vụ (Part-time)", "PB02", false),

    // --- LOGISTIC (8 vị trí) ---
    NHAN_VIEN_KHO("VT16", "Nhân viên kho", "Logistic", true),
    NHAN_VIEN_DIEU_PHOI_VAN_CHUYEN("VT17", "Nhân viên điều phối vận chuyển", "Logistic", true),
    TAI_XE_GIAO_HANG("VT18", "Tài xế giao hàng", "Logistic", true),
    NHAN_VIEN_XUAT_NHAP_HANG("VT19", "Nhân viên xuất nhập hàng", "Logistic", true),
    NHAN_VIEN_QUAN_LY_TON_KHO("VT20", "Nhân viên quản lý tồn kho", "Logistic", true),
    NHAN_VIEN_XU_LY_DON_HANG("VT21", "Nhân viên xử lý đơn hàng", "Logistic", true),
    CHUYEN_VIEN_THU_MUA("VT22", "Chuyên viên thu mua", "Logistic", true),
    NHAN_VIEN_DONG_GOI("VT23", "Nhân viên đóng gói (Part-time)", "Logistic", false),

    // --- NHÂN SỰ (7 vị trí) ---
    CHUYEN_VIEN_TUYEN_DUNG("VT24", "Chuyên viên tuyển dụng", "PB03", true),
    CHUYEN_VIEN_TIEN_LUONG_VA_PHUC_LOI("VT25", "Chuyên viên tiền lương và phúc lợi (C&B)", "PB03", true),
    CHUYEN_VIEN_DAO_TAO("VT26", "Chuyên viên đào tạo", "PB03", true),
    CHUYEN_VIEN_QUAN_HE_LAO_DONG("VT27", "Chuyên viên quan hệ lao động", "PB03", true),
    NHAN_VIEN_HANH_CHINH_NHAN_SU("VT28", "Nhân viên hành chính nhân sự", "PB03", true),
    CHUYEN_VIEN_DANH_GIA_KPI("VT29", "Chuyên viên đánh giá KPI", "PB03", true),
    THUC_TAP_SINH_NHAN_SU("VT30", "Thực tập sinh nhân sự (Part-time)", "PB03", false),

    // --- CHĂM SÓC KHÁCH HÀNG (7 vị trí) ---
    NHAN_VIEN_TONG_DAI("VT31", "Nhân viên tổng đài", "PB04", true),
    NHAN_VIEN_CSKH_QUA_CHAT_EMAIL("VT32", "Nhân viên CSKH qua chat / email", "PB04", true),
    NHAN_VIEN_XU_LY_KHIEU_NAI("VT33", "Nhân viên xử lý khiếu nại", "PB04", true),
    NHAN_VIEN_CHAM_SOC_KHACH_HANG_THAN_THIET("VT34", "Nhân viên chăm sóc khách hàng thân thiết", "PB04", true),
    NHAN_VIEN_HO_TRO_KY_THUAT("VT35", "Nhân viên hỗ trợ kỹ thuật", "PB04", true),
    NHAN_VIEN_CSKH_CA_TOI("VT36", "Nhân viên CSKH ca tối (Part-time)", "PB04", false),
    NHAN_VIEN_KHAO_SAT_KHACH_HANG("VT37", "Nhân viên khảo sát khách hàng (Part-time)", "PB04", false),

    // --- MARKETING (7 vị trí) ---
    CHUYEN_VIEN_DIGITAL_MARKETING("VT38", "Chuyên viên digital marketing", "PB05", true),
    CHUYEN_VIEN_SEO_SEM("VT39", "Chuyên viên SEO / SEM", "PB05", true),
    CHUYEN_VIEN_CONTENT("VT40", "Chuyên viên content", "PB05", true),
    THIET_KE_DO_HOA("VT41", "Thiết kế đồ họa", "PB05", true),
    CHUYEN_VIEN_QUAN_LY_MANG_XA_HOI("VT42", "Chuyên viên quản lý mạng xã hội", "PB05", true),
    CHUYEN_VIEN_NGHIEN_CUU_THI_TRUONG("VT43", "Chuyên viên nghiên cứu thị trường", "PB05", true),
    THUC_TAP_SINH_CONTENT("VT44", "Thực tập sinh content (Part-time)", "PB05", false),

    // --- KẾ TOÁN (7 vị trí) ---
    KE_TOAN_TONG_HOP("VT45", "Kế toán tổng hợp", "PB06", true),
    KE_TOAN_THANH_TOAN("VT46", "Kế toán thanh toán", "PB06", true),
    KE_TOAN_CONG_NO("VT47", "Kế toán công nợ", "PB06", true),
    KE_TOAN_KHO("VT48", "Kế toán kho", "PB06", true),
    KE_TOAN_THUE("VT49", "Kế toán thuế", "PB06", true),
    KE_TOAN_GIA_THANH("VT50", "Kế toán giá thành", "PB06", true),
    NHAN_VIEN_NHAP_LIEU_KE_TOAN("VT51", "Nhân viên nhập liệu kế toán (Part-time)", "PB06", false),
    // --- QUẢN LÝ ----
    QUAN_LY_KINH_DOANH("VT58", "Quản lý kinh doanh", "PB01", true),
    QUAN_LY_SAN_XUAT("VT52", "Quản lý sản xuất", "PB02", true),
    QUAN_LY_LOGISTIC("VT53", "Quản lý logistic", "Logistic", true),
    QUAN_LY_NHAN_SU("VT54", "Quản lý nhân sự", "PB03", true),
    QUAN_LY_CSKH("VT55", "Quản lý chăm sóc khách hàng", "PB04", true),
    QUAN_LY_MARKETING("VT56", "Quản lý marketing", "PB05", true),
    QUAN_LY_KE_TOAN("VT57", "Quản lý kế toán", "PB06", true),
    GIAM_DOC_DIEU_HANH("VT59", "Giám đốc điều hành", "null", true);

    private String MaViTri;
    private String MoTa;
    private String PhongBan;
    private boolean isFullTime; // true: Full-Time, false: Part-Time

    private ViTri(String MaViTri, String MoTa, String PhongBan, boolean isFullTime) {
        this.MaViTri = MaViTri;
        this.MoTa = MoTa;
        this.PhongBan = PhongBan;
        this.isFullTime = isFullTime;
    }

    public String getMaViTri() {
        return MaViTri;
    }

    public void setMaViTri(String MaViTri) {
        this.MaViTri = MaViTri;
    }

    public String getMoTa() {
        return MoTa;
    }

    public void setMoTa(String MoTa) {
        this.MoTa = MoTa;
    }

    public String getPhongBan() {
        return PhongBan;
    }

    public void setPhongBan(String PhongBan) {
        this.PhongBan = PhongBan;
    }

    public boolean isFullTime() {
        return isFullTime;
    }

    public void setFullTime(boolean isFullTime) {
        this.isFullTime = isFullTime;
    }

    public static ViTri NhanDien(String input) {
        if (input == null) return null;
        for (ViTri vt : ViTri.values()) {
            if (vt.getMaViTri().equalsIgnoreCase(input.trim()) || vt.getMoTa().equalsIgnoreCase(input.trim())) {
                return vt;
            }
        }
        return null;
    }
    
    public static ViTri timKiem(String MaViTri){
        for(ViTri vt : ViTri.values()){
            if(vt.getMaViTri().equalsIgnoreCase(MaViTri)){
                return vt;
            }
        }
        return null;
    }
    
    public static LoaiVaiTro XacDinhQuyen(String MaViTri){
        if(MaViTri.compareTo("VT52")>=0 && MaViTri.compareTo("VT58")<=0){
            return LoaiVaiTro.QUAN_LY;
        }
        if(MaViTri.compareTo("VT24")>=0 && MaViTri.compareTo("VT30")<=0){
            return LoaiVaiTro.HR_ADMIN;
        }
        if(MaViTri.compareTo("VT59") == 0){
            return LoaiVaiTro.DIRECTOR;
        }
        else{
            return LoaiVaiTro.NHAN_VIEN;
        }
    }
    
    public static LoaiChucVu XacDinhChucVu(String MaViTri){
        if(MaViTri.compareTo("VT52")>=0 && MaViTri.compareTo("VT58")<=0){
            return LoaiChucVu.QUAN_LY;
        }
        if(MaViTri.compareTo("VT59") == 0){
            return LoaiChucVu.GIAM_DOC;
        }
        else{
            return LoaiChucVu.NHAN_VIEN;
        }
    }

}

