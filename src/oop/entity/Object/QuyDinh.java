package oop.entity.Object;

public class QuyDinh {

    // =========================
    // 1. ĐIỂM NỀN
    // =========================
    public static final double DIEM_NEN = 35;


    // =========================
    // 2. ĐIỂM KẾT QUẢ CÔNG VIỆC
    // =========================
    public static final double DIEM_KET_QUA_CO_BAN = 30;
    public static final double DIEM_KET_QUA_TOI_DA = 50;

    // Trọng số chỉ tiêu chung
    public static final double TRONG_SO_CHI_TIEU_CHUNG = 0.4;

    // Trọng số chỉ tiêu phòng ban
    public static final double TRONG_SO_CHI_TIEU_PHONG_BAN = 0.6;

    // Mốc đạt 100% chỉ tiêu
    public static final double MOC_DAT_CHI_TIEU = 100;

    // Mốc đạt tối đa 50 điểm
    public static final double MOC_TOI_DA = 125;

    // Hệ số tăng điểm khi vượt chỉ tiêu
    public static final double HE_SO_VUOT_CHI_TIEU = 0.8;


    // =========================
    // 3. ĐIỂM CỘNG
    // =========================

    // Không đi trễ, không về sớm cả quý
    public static final double DIEM_KHONG_TRE = 3;

    // Mỗi 10 giờ OT hoàn thành
    public static final double DIEM_OT_MOI_10_GIO = 1;

    // Điểm OT tối đa
    public static final double DIEM_OT_TOI_DA = 8;

    // Tổng điểm cộng của nhân viên
    public static final double DIEM_CONG_TOI_DA = 12;


    // =========================
    // 4. ĐIỂM TRỪ
    // =========================

    // Đi trễ 3 lần đầu
    public static final double DI_TRE_LAN_1_3 = 1;

    // Đi trễ từ lần thứ 4
    public static final double DI_TRE_TU_LAN_4 = 2;

    // Nghỉ không phép
    public static final double NGHI_KHONG_PHEP = 5;

    // Đăng ký OT nhưng không thực hiện
    public static final double OT_KHONG_THUC_HIEN = 2;

    // Vi phạm an toàn vệ sinh
    public static final double VI_PHAM_AN_TOAN = 5;

    // Vi phạm an toàn tại sản xuất và logistics
    public static final double VI_PHAM_AN_TOAN_NHAN_DOI = 10;

    // Vi phạm tài sản công ty
    public static final double VI_PHAM_TAI_SAN = 10;

    // Quấy rối: KyLuatNang KPI
    public static final double VETO_QUAY_ROI = 0;


    // =========================
    // 5. KPI QUẢN LÝ
    // =========================

    // Trọng số kết quả phòng ban
    public static final double TRONG_SO_KPI_PHONG = 0.5;

    // Trọng số tỷ lệ cấp dưới đạt
    public static final double TRONG_SO_CAP_DUOI_DAT = 0.25;

    // Trọng số KPI trung bình cấp dưới
    public static final double TRONG_SO_KPI_TRUNG_BINH = 0.25;

    // Mốc tỷ lệ cấp dưới đạt
    public static final double MOC_CAP_DUOI_DAT = 90;

    // Mốc KPI trung bình cấp dưới
    public static final double MOC_KPI_TRUNG_BINH = 65;


    // =========================
    // 6. REQUEST QUẢN LÝ
    // =========================


    // Không có request hết hạn
    public static final double DIEM_KHONG_REQUEST_HET_HAN = 3;
    // Request hết hạn
    public static final double TRU_REQUEST_HET_HAN = 2;



    // =========================
    // 7. XẾP LOẠI KPI
    // =========================

    public static final double DIEM_XUAT_SAC = 90;
    public static final double DIEM_TOT = 70;
    public static final double DIEM_DAT = 50;

    public static final double HE_SO_XUAT_SAC = 2.0;
    public static final double HE_SO_TOT = 1.5;
    public static final double HE_SO_DAT = 1.2;
    public static final double HE_SO_KHONG_DAT = 1.0;


    // =========================
    // 8. HÀM TÍNH ĐIỂM KẾT QUẢ
    // =========================

    public static double tinhDiemKetQua(double phanTramDat) {

        if (phanTramDat < 100) {
            return DIEM_KET_QUA_CO_BAN * phanTramDat / 100;
        }

        if (phanTramDat < 125) {
            return DIEM_KET_QUA_CO_BAN
                    + (phanTramDat - 100) * HE_SO_VUOT_CHI_TIEU;
        }

        return DIEM_KET_QUA_TOI_DA;
    }


    // =========================
    // 9. HÀM TÍNH ĐIỂM CỘNG OT
    // =========================

    public static double tinhDiemOT(double soGioOT) {

        double diemOT = (int) (soGioOT / 10)
                * DIEM_OT_MOI_10_GIO;

        if (diemOT > DIEM_OT_TOI_DA) {
            diemOT = DIEM_OT_TOI_DA;
        }

        return diemOT;
    }


    // =========================
    // 10. HÀM TÍNH ĐIỂM TRỪ ĐI TRỄ
    // =========================

    public static double tinhDiemTruDiTre(int soLanDiTre) {

        double diemTru = 0;

        if (soLanDiTre <= 3) {
            diemTru = soLanDiTre * DI_TRE_LAN_1_3;
        } else {
            diemTru = 3 * DI_TRE_LAN_1_3
                    + (soLanDiTre - 3) * DI_TRE_TU_LAN_4;
        }

        return diemTru;
    }


    // =========================
    // 11. HÀM XẾP LOẠI
    // =========================

    public static String phanXepLoai(double diemKPI) {

        if (diemKPI > DIEM_XUAT_SAC) {
            return "Xuất sắc";
        } else if (diemKPI > DIEM_TOT) {
            return "Tốt";
        } else if (diemKPI > DIEM_DAT) {
            return "Đạt";
        } else {
            return "Không đạt";
        }
    }


    // =========================
    // 12. HÀM LẤY HỆ SỐ KPI
    // =========================

    public static double tinhHeSoKPI(double diemKPI) {

        if (diemKPI > DIEM_XUAT_SAC) {
            return HE_SO_XUAT_SAC;
        } else if (diemKPI > DIEM_TOT) {
            return HE_SO_TOT;
        } else if (diemKPI > DIEM_DAT) {
            return HE_SO_DAT;
        } else {
            return HE_SO_KHONG_DAT;
        }
    }


    // =========================
    // 13. HÀM TÍNH ĐIỂM KPI CUỐI
    // =========================

    public static double tinhTongDiemKPI(
            double diemNen,
            double diemKetQua,
            double diemCong,
            double diemTru,
            boolean biKyLuatNang) {

        // Nếu bị veto thì KPI bằng 0
        if (biKyLuatNang) {
            return 0;
        }

        double tongDiem = diemNen
                + diemKetQua
                + diemCong
                - diemTru;

        // KPI không được thấp hơn 0
        if (tongDiem < 0) {
            tongDiem = 0;
        }

        // KPI không được vượt quá 100
        if (tongDiem > 100) {
            tongDiem = 100;
        }

        // Làm tròn 1 chữ số thập phân
        return Math.round(tongDiem * 10.0) / 10.0;
    }
}
