
package oop.entity.Service;

import java.time.LocalDate;
import oop.entity.DanhSach.DanhSachNhanVien;
import oop.entity.Object.NhanVien;

public class ADMIN {
    public static void KiemTraChamCongCacNhanVien(DanhSachNhanVien dsnv, LocalDate date){
        for(NhanVien nv : dsnv.getDsnv()){
            if(!nv.getBcc().KiemTraTonTai(date)){
                int SoLanViPham = nv.getSoLanViPhamNghiKhongPhep();
                nv.setSoLanViPhamNghiKhongPhep(SoLanViPham+1);
            }
        }
    }
}
