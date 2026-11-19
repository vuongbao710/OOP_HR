
package oop.entity.DanhSach;

import java.time.LocalDate;
import java.util.ArrayList;
import oop.entity.Enum.LoaiRequest;
import oop.entity.Enum.TrangThaiDocThongBao;
import oop.entity.Interface.DanhSach;
import oop.entity.Object.ThongBao;

public class DanhSachThongBao implements DanhSach<ThongBao>{
    private ArrayList<ThongBao> dsThongBao;
    private int dem=0;
    private int mocTBXetChuaGiaiQuyet=0;
    private int mocTBXetHetHan=0;
    public DanhSachThongBao() {
        this.dsThongBao = new ArrayList<>();
    }

    public DanhSachThongBao(ArrayList<ThongBao> dsThongBao) {
        this.dsThongBao = dsThongBao;
    }
        public void them(ThongBao tb){
        String MaThongBao = taoMa();
        tb.setMaThongBao(MaThongBao);
        this.dsThongBao.add(tb);
    }
    public void xoa(ThongBao tb){
        this.dsThongBao.remove(tb);
    }
    public ThongBao timKiem(String MaThongBao){
        for(ThongBao ThongBao : dsThongBao){
            if(ThongBao.getMaThongBao().equals(MaThongBao)){
                return ThongBao;
            }
        }
        return null;
    }
    public void xuat(){
        for(ThongBao ThongBao : dsThongBao){
            System.out.println(ThongBao);
        }
    }
    public String taoMa(){
        dem++;
        return String.format("THONGBAO%03d", dem);
    }

    public ArrayList<ThongBao> getDsThongBao() {
        return dsThongBao;
    }

    public void setDsThongBao(ArrayList<ThongBao> dsThongBao) {
        this.dsThongBao = dsThongBao;
    }

    public int getDem() {
        return dem;
    }

    public void setDem(int dem) {
        this.dem = dem;
    }
    
    public ArrayList<ThongBao> timKiemChuaGiaiQuyet(LocalDate date){
        ArrayList<ThongBao> temp = new ArrayList<>();
        for(int i = this.mocTBXetChuaGiaiQuyet ; i<dsThongBao.size() ; i++){
            ThongBao tb = dsThongBao.get(i);
            if(tb!= null && tb.getRequest() != LoaiRequest.THONG_BAO && date.plusDays(1).equals(tb.getNgayHetHan()) 
                    && !(tb.getTrangThai().equals(TrangThaiDocThongBao.DA_GIAI_QUYET))){
                temp.add(tb);
            }
        }
        this.mocTBXetChuaGiaiQuyet = dsThongBao.size();
        return temp;
    }
    
    public void chuyenQuaTrangThaiHetHan(LocalDate date){
        for(int i = this.mocTBXetHetHan ; i<dsThongBao.size() ; i++){
            ThongBao tb = dsThongBao.get(i);
            if(tb!= null && tb.getRequest() != LoaiRequest.THONG_BAO && date.equals(tb.getNgayHetHan())
                    && !(tb.getTrangThai().equals(TrangThaiDocThongBao.DA_GIAI_QUYET))){
                tb.setTrangThai(TrangThaiDocThongBao.HET_HAN);
                
            }
        }
        mocTBXetHetHan = dsThongBao.size();
    }
    
}
