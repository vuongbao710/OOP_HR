
package oop.entity.Enum;

public enum LoaiKyLuat {
    KHIEN_TRACH("khiển trách"),
    KEO_DAI_THOI_HAN_NANG_LUONG("kéo dài thời hạn nâng lương"),
    CACH_CHUC("cách chức"),
    SA_THAI("sa thải");
    
     private String MoTa;

    LoaiKyLuat(String MoTa) {
        this.MoTa = MoTa;
    }

    public String getMoTa() {
        return MoTa;
    }

    public void setMoTa(String MoTa) {
        this.MoTa = MoTa;
    }
    public static LoaiKyLuat NhanDien(String input){
        for(LoaiKyLuat kl : LoaiKyLuat.values()){
            if(kl.getMoTa().equalsIgnoreCase(input)){
                return kl;
            }
        }
        return null;
    }
}
