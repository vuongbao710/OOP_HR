
package oop.entity.Enum;

public enum LoaiRequest {
    XIN_NGHI_PHEP("xin nghỉ phép"),
    XIN_DOI_CA("xin đổi ca"),
    XIN_NGHI_VIEC("xin nghỉ việc"),
    DANH_GIA_KPI("đánh giá kpi"),
    KHIEN_TRACH_CHAM_CONG("khiển trách chấm công"),
    THONG_BAO("thông báo"),
    DANG_KY_LAM_OT("đăng ký làm OT"),
    DANG_TAI_DOT_TUYEN_DUNG("đăng tải đợt tuyển dụng "),
    CHAM_DIEM_DAO_TAO("chấm điểm đào tạo"),
    XEM_XET_TANG_LUONG("xem xét tăng lương"),
    GIA_HAN_HOP_DONG("gia hạn hợp đồng"),
    BO_NHIEM("bổ nhiệm");
    
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
