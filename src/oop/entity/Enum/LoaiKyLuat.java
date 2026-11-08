
package oop.entity.Enum;

public enum LoaiKyLuat {
    DI_LAM_TRE ("đi làm trễ"),
    TAN_LAM_SOM ("tan làm sớm"),
    VI_PHAM_QUY_DINH ("vi phạm quy định"),
    NGHI_KHONG_PHEP ("nghỉ không phép");
    
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
