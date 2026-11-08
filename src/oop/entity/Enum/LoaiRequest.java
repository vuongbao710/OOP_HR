
package oop.entity.Enum;

public enum LoaiRequest {
    XIN_NGHI_PHEP("xin nghỉ phép"),
    XIN_DOI_CA("xin đổi ca"),
    XIN_NGHI_VIEC("xin nghỉ việc"),
    KHIEN_TRACH_CHAM_CONG("khiển trách chấm công"),
    XIN_TANG_LUONG("đề nghị tăng lương"),;
    
    private String MoTa;

    private LoaiRequest(String MoTa) {
        this.MoTa = MoTa;
    }

    public String getMoTa() {
        return MoTa;
    }

    public void setMoTa(String MoTa) {
        this.MoTa = MoTa;
    }
    
}
