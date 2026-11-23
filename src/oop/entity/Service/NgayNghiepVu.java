
package oop.entity.Service;

import java.time.LocalDate;

public class NgayNghiepVu {

    public static LocalDate ngayKetThucQuy(LocalDate date) {
        int quy = (date.getMonthValue() - 1) / 3;
        return LocalDate.of(date.getYear(), quy * 3 + 1, 1)
                .plusMonths(3).minusDays(1);
    }

    public static LocalDate ngayThuongTet(LocalDate date) {
        return LocalDate.of(date.getYear(), 1, 1);
    }

    public static String kiemTraToiNgay(LocalDate date) {
        if (date.equals(ngayThuongTet(date))) return "ngày thưởng tết";
        if (date.equals(ngayKetThucQuy(date))) return "ngày kết thúc quý";
        return null;
    }

    public static int TinhNgay(LocalDate NgayBatDau, LocalDate NgayKetThuc){
        int dem = 0;
        for(LocalDate date = NgayBatDau ; !date.isAfter(NgayKetThuc) ; date = date.plusDays(1)){
            dem++;
        }
        return dem;
    }
}
