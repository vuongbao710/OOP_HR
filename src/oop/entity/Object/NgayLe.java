package oop.entity.Object;

import java.time.LocalDate;
import java.util.ArrayList;

public class NgayLe {
    private static ArrayList<LocalDate> dsNgayLe = new ArrayList<>();

    public static ArrayList<LocalDate> getDsNgayLe() {
        return dsNgayLe;
    }

    public static void them(LocalDate date) {
        if (!dsNgayLe.contains(date)) {
            dsNgayLe.add(date);
        }
    }

    public static boolean isNgayLe(LocalDate date) {
        return dsNgayLe.contains(date);
    }
}
