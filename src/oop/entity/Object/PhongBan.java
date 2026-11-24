package oop.entity.Object;
public class PhongBan {
    private String MaPhongBan;
    private String TenPhongBan;
    private String MaTruongPhong;

    public PhongBan(String MaPhongBan, String TenPhongBan, String MaTruongPhong) {
        this.MaPhongBan = MaPhongBan;
        this.TenPhongBan = TenPhongBan;
        this.MaTruongPhong = MaTruongPhong;
    }

    public String getMaPhongBan() { return MaPhongBan; }
    public void setMaPhongBan(String MaPhongBan) { this.MaPhongBan = MaPhongBan; }

    public String getTenPhongBan() { return TenPhongBan; }
    public void setTenPhongBan(String TenPhongBan) { this.TenPhongBan = TenPhongBan; }

    public String getMaTruongPhong() { return MaTruongPhong; }
    public void setMaTruongPhong(String MaTruongPhong) { this.MaTruongPhong = MaTruongPhong; }

    @Override
    public String toString() {
        return "Mã phòng: " + MaPhongBan + " | Tên phòng: " + TenPhongBan
                + " | Mã trưởng phòng: " + (MaTruongPhong == null ? "chưa có" : MaTruongPhong);
    }
    
}