
package oop.entity.Enum;

public enum LoaiTrangThaiChamCong {
    DUNG_GIO("dung gio",true),
    DI_TRE("di tre", false),
    VE_SOM("ve som", false),
    KHONG_CHAM_CONG_GIO_RA("khong cham cong gio ra",false); //không chấm công giờ ra - quá 4h từ giờ vào không chấm công - đi làm về sớm mà không có phép
    
    private String MoTa;
    private boolean IsPhat;

    private LoaiTrangThaiChamCong(String MoTa, boolean IsPhat) {
        this.MoTa = MoTa;
        this.IsPhat = IsPhat;
    }

    public String getMoTa() {
        return MoTa;
    }

    public void setMoTa(String MoTa) {
        this.MoTa = MoTa;
    }

    public boolean isIsPhat() {
        return IsPhat;
    }

    public void setIsPhat(boolean IsPhat) {
        this.IsPhat = IsPhat;
    }
    
}
