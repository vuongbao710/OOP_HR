package oop.entity.Enum;
public enum LoaiViPham {
    VI_PHAM_CHAM_CONG("vi phạm chấm công"),
    NGHI_KHONG_PHEP("nghỉ không phép"),
    DANG_KY_OT_MA_KHONG_LAM("đăng ký OT mà không làm"),
    VI_PHAM_TRAT_TU_NOI_LAM_VIEC("vi phạm trật tự nơi làm việc"),
    VI_PHAM_AN_TOAN_VE_SINH("vi phạm an toàn vệ sinh"),
    VI_PHAM_TAI_SAN_CONG_TY("vi phạm tài sản công ty"),
    QUAY_ROI("quấy rối");

    private final String moTa;

    LoaiViPham(String moTa) {
        this.moTa = moTa;
    }

    public String getMoTa() {
        return moTa;
    }

    public static LoaiViPham NhanDien(String input) {
        for (LoaiViPham kl : values()) {
            if (kl.moTa.equalsIgnoreCase(input)) {
                return kl;
            }
        }
        return null;
    }
}