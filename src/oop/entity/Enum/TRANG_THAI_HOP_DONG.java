
package oop.entity.Enum;

public enum TRANG_THAI_HOP_DONG {
        CHO_DUYET ("chờ duyệt"),
        DANG_CO_HIEU_LUC ("đang có hiệu lực"),
        HET_HAN ("hết hạn"),
        DA_HUY ("đã huỷ");
        
        private String MoTa;

    private TRANG_THAI_HOP_DONG(String MoTa) {
        this.MoTa = MoTa;
    }

    public String getMoTa() {
        return MoTa;
    }

    public void setMoTa(String MoTa) {
        this.MoTa = MoTa;
    }
        
}
