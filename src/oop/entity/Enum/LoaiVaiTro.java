
package oop.entity.Enum;

public enum LoaiVaiTro {
    ADMIN("Admin"),
    QUAN_LY("QuanLy"),
    NHAN_VIEN("NhanVien");
    
    private String MoTa;

    LoaiVaiTro(String MoTa) {
        this.MoTa = MoTa;
    }

    public String getMoTa() {
        return MoTa;
    }

    public void setMoTa(String MoTa) {
        this.MoTa = MoTa;
    }
    public static LoaiVaiTro NhanDien(String input){
        for(LoaiVaiTro vaitro : LoaiVaiTro.values()){
            if(vaitro.getMoTa().equalsIgnoreCase(input)){
                return vaitro;
            }
        }
        return null;
                
    }
    
}
