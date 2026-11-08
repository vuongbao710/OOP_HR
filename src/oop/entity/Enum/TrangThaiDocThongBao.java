
package oop.entity.Enum;
public enum TrangThaiDocThongBao {
    CHUA_DOC("chưa đọc"),
    DA_DOC("đã đọc"),;
    
    private String MoTa;

    private TrangThaiDocThongBao(String MoTa) {
        this.MoTa = MoTa;
    }

    public String getMoTa() {
        return MoTa;
    }

    public void setMoTa(String MoTa) {
        this.MoTa = MoTa;
    }
    
}
