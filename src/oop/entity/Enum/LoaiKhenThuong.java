
package oop.entity.Enum;

public enum LoaiKhenThuong {
    THUONG_THAM_NIEN ("thưởng thâm niên"),
    THUONG_SANG_KIEN ("thưởng sáng kiến"),
    THUONG_TET ("thưởng tết");
    
    private String MoTa;

     LoaiKhenThuong(String MoTa) {
        this.MoTa = MoTa;
    }

    public String getMoTa() {
        return MoTa;
    }

    public void setMoTa(String MoTa) {
        this.MoTa = MoTa;
    }
    
    
}
