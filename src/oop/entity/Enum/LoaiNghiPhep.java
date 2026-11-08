
package oop.entity.Enum;

public enum LoaiNghiPhep {
    NGHI_PHEP_NAM ("nghỉ phép năm"),
    NGHI_PHEP_BENH ("nghỉ phép bệnh"),
    NGHI_PHEP_THAI_SAN ("nghỉ thai sản");
    
    private String MoTa;

    LoaiNghiPhep(String MoTa) {
        this.MoTa = MoTa;
    }

    public String getMoTa() {
        return MoTa;
    }

    public void setMoTa(String MoTa) {
        this.MoTa = MoTa;
    }
    
    
}
