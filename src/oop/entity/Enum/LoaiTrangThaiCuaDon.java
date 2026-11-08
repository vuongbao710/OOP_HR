
package oop.entity.Enum;

public enum LoaiTrangThaiCuaDon {
    DUYET("duyệt",true),
    CHO_DUYET("chờ duyệt",false),
    KHONG_DUYET("không duyệt",false),;
    
    private String MoTa;
    private boolean isDuyet;

    private LoaiTrangThaiCuaDon(String MoTa, boolean isDuyet) {
        this.MoTa = MoTa;
        this.isDuyet = isDuyet;
    }

    public String getMoTa() {
        return MoTa;
    }

    public void setMoTa(String MoTa) {
        this.MoTa = MoTa;
    }

    public boolean isIsDuyet() {
        return isDuyet;
    }

    public void setIsDuyet(boolean isDuyet) {
        this.isDuyet = isDuyet;
    }
    
}
