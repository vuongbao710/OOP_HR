
package oop.entity.Enum;

public enum LoaiPhongBan {
    KY_THUAT("kỹ thuật", "P01", true),
    KE_TOAN("kế toán", "P02", false),
    NHAN_SU("nhân sự", "P03", false),
    MARKETING("marketing", "P04", true),
    KINH_DOANH("kinh doanh", "P05", true), // Gợi ý bổ sung
    KE_HOACH("kế hoạch", "P06", false),
    SAN_XUAT("sản xuất", "P07", true),
    LOGISTICS("logistics", "P08", true), // Gợi ý bổ sung
    QC("kiểm tra chất lượng", "P09", false),
    CSKH("chăm sóc khách hàng", "P10", true);
    
    private String MoTa;
    private String MaPhongBan;
    private boolean isPart;

    private LoaiPhongBan(String MoTa, String MaPhongBan, boolean isPart) {
        this.MoTa = MoTa;
        this.MaPhongBan=MaPhongBan;
        this.isPart = isPart;
    }

    public String getMoTa() {
        return MoTa;
    }

    public void setMoTa(String MoTa) {
        this.MoTa = MoTa;
    }

    public String getMaPhongBan() {
        return MaPhongBan;
    }

    public void setMaPhongBan(String MaPhongBan) {
        this.MaPhongBan = MaPhongBan;
    }
    public static LoaiPhongBan NhanDien(String MaPhongBan){
        for(LoaiPhongBan pb : LoaiPhongBan.values()){
            if(pb.getMaPhongBan().equalsIgnoreCase(MaPhongBan)){
                return pb;
            }
        }
        return null;
    }

    public boolean isIsPart() {
        return isPart;
    }

    public void setIsPart(boolean isPart) {
        this.isPart = isPart;
    }
    
}
