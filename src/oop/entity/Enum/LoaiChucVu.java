
package oop.entity.Enum;

public enum LoaiChucVu {
    GIAM_DOC("Giám đốc"),
    QUAN_LY("Quản lý"),
    NHAN_VIEN("Nhân viên");
    
    private String MoTa;

    private LoaiChucVu(String MoTa) {
        this.MoTa = MoTa;
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
    
}
