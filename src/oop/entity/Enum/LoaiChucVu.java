
package oop.entity.Enum;

public enum LoaiChucVu {
    GIAM_DOC("Giám đốc", 20000000),
    QUAN_LY("Quản lý", 10000000),
    NHAN_VIEN("Nhân viên", 5000000);
    
    private String MoTa;
    private double luongCoBan;

    private LoaiChucVu(String MoTa, double luongCoBan) {
        this.MoTa = MoTa;
        this.luongCoBan = luongCoBan;
    }

    public String getMoTa() {
        return MoTa;
    }

    public void setMoTa(String MoTa) {
        this.MoTa = MoTa;
    }
    
    public static LoaiChucVu NhanDien(String s){
        for(LoaiChucVu l : LoaiChucVu.values()){
            if(l.getMoTa().equalsIgnoreCase(s)){
                return l;
            }
        }
        return null;
    }
    public double getLuongCoBan() {
        return luongCoBan;
    }

    public void setLuongCoBan(double luongCoBan) {
        this.luongCoBan = luongCoBan;
    }
    
}
