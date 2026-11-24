package oop.entity.DanhSach;

import java.util.ArrayList;
import oop.entity.Object.NhanVien;
import oop.entity.Object.PhongBan;

public class DanhSachPhongBan {
    private static ArrayList<PhongBan> dspb = new ArrayList<>();
    private static int dem = 0;

    public static boolean xoaPhongBan(String MaPhongBan) {
        for (PhongBan pb : dspb) {
            if (pb.getMaPhongBan().equals(MaPhongBan)) {
                dspb.remove(pb);
                return true;
            }
        }
        return false;
    }

    public static boolean suaPhongBan(String MaPhongBan, String TenPhongBanMoi, String MaTruongPhongMoi, String TenTruongPhongMoi) {
        for (PhongBan pb : dspb) {
            if (pb.getMaPhongBan().equals(MaPhongBan)) {
                pb.setTenPhongBan(TenPhongBanMoi);
                pb.setMaTruongPhong(MaTruongPhongMoi);
                return true;
            }
        }
        return false;
    }

    public static PhongBan timPhongBan(String MaPhongBan) {
        for (PhongBan pb : dspb) {
            if (pb.getMaPhongBan().equals(MaPhongBan)) {
                return pb;
            }
        }
        return null;
    }

    public static ArrayList<PhongBan> getDspb() {
        return dspb;
    }

    public static void setDspb(ArrayList<PhongBan> dspb) {
        DanhSachPhongBan.dspb = dspb;
    }

    public static void xuat() {
        for (PhongBan pb : dspb) {
            System.out.println(pb);
        }
    }

    public static void them(PhongBan pb) {
        if (pb.getMaPhongBan() == null) {
            pb.setMaPhongBan(taoMa());
        }
        dspb.add(pb);
    }

    public static String taoMa() {
        dem++;
        return String.format("PB%02d", dem);
    }
    
    public static String LayMaPhongBan(NhanVien nv) {
        if (nv.getMaPhongBan() == null) {
            return "null";
        }
        return nv.getMaPhongBan();
    }

    public static String layTenPhongBan(String MaPhongBan) {
        PhongBan pb = timPhongBan(MaPhongBan);
        return pb == null ? "Không rõ" : pb.getTenPhongBan();
    }
   
}
