
package oop.entity.Enum;


public enum LOAI_HOP_DONG {
    CHINH_THUC_NGAN_HAN("chính thức ngắn hạn"),
    CHINH_THUC_DAI_HAN("chính thức dài hạn"),
    PART_TIME("part-time");
    
    private String MoTa;

    private LOAI_HOP_DONG(String MoTa) {
        this.MoTa = MoTa;
    }

    public String getMoTa() {
        return MoTa;
    }

    public void setMoTa(String MoTa) {
        this.MoTa = MoTa;
    }
    
}
