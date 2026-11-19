
package oop.entity.Enum;

public enum LoaiVaiTro {
    BOARD_MEMBER("Hội đồng quản trị"),
    DIRECTOR("Giám đốc"),
    HR_ADMIN("Admin"),
    QUAN_LY("Quản lý"),
    NHAN_VIEN("Nhân viên");
    
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
