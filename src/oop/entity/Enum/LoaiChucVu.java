
package oop.entity.Enum;

public enum LoaiChucVu {
    GIAM_DOC("GiamDoc",30000000,150000),
    QUAN_LY("QuanLy",20000000,100000),
    NHAN_SU("NhanSu",5000000,25000),
    NHAN_VIEN("NhanVien",5000000,25000),
    THOI_VU("thời vụ",0,25000);
    
    private String MoTa;
    private double LuongCoBan;
    private double LuongMotGio;
    private LoaiChucVu(String MoTa, double LuongCoBan, double LuongMotGio) {
        this.MoTa = MoTa;
        this.LuongCoBan=LuongCoBan;
        this.LuongMotGio = LuongMotGio;
    }

    public String getMoTa() {
        return MoTa;
    }

    public void setMoTa(String MoTa) {
        this.MoTa = MoTa;
    }

    public double getLuongCoBan() {
        return LuongCoBan;
    }

    public void setLuongCoBan(double LuongCoBan) {
        this.LuongCoBan = LuongCoBan;
    }

    public double getLuongMotGio() {
        return LuongMotGio;
    }

    public void setLuongMotGio(double LuongMotGio) {
        this.LuongMotGio = LuongMotGio;
    }
    
    
    public static LoaiChucVu NhanDien(String input){
        for(LoaiChucVu cv : LoaiChucVu.values()){
            if(cv.getMoTa().equalsIgnoreCase(input)){
                return cv;
            }
        }
        return null;
    }
            
    
}
