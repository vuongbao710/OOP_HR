
package oop.entity.Enum;

public enum LoaiKetQua {
    DAT("đạt", true),
    KHONG_DAT("không đạt", false);
    
    private String MoTa;
    private boolean isDat;

    LoaiKetQua(String MoTa, boolean isDat) {
        this.MoTa = MoTa;
        this.isDat= isDat;
    }

    public boolean isIsDat() {
        return isDat;
    }

    public void setIsDat(boolean isDat) {
        this.isDat = isDat;
    }

    public String getMoTa() {
        return MoTa;
    }

    public void setMoTa(String MoTa) {
        this.MoTa = MoTa;
    }
    
            
}
