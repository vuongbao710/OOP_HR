
package oop.entity.Object;

import java.time.LocalDate;
import java.time.LocalTime;
import oop.entity.Enum.LoaiRequest;
import oop.entity.Enum.TrangThaiDocThongBao;

public class ThongBao {
    private String MaThongBao;
    private String MaNguoiGui;
    private LocalDate date;
    private LocalTime time;
    private String Message;
    private TrangThaiDocThongBao TrangThai;
    private LoaiRequest Request;

    public ThongBao(String MaThongBao,String MaNguoiGui, LocalDate date, LocalTime time, String Message) {
        this.MaThongBao = MaThongBao;
        this.MaNguoiGui = MaNguoiGui;
        this.date = date;
        this.time = time;
        this.Message = Message;
        this.TrangThai = TrangThaiDocThongBao.CHUA_DOC;
    }

    public String getMaThongBao() {
        return MaThongBao;
    }

    public void setMaThongBao(String MaThongBao) {
        this.MaThongBao = MaThongBao;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public LocalTime getTime() {
        return time;
    }

    public void setTime(LocalTime time) {
        this.time = time;
    }

    public String getMessage() {
        return Message;
    }

    public void setMessage(String Message) {
        this.Message = Message;
    }

    public TrangThaiDocThongBao getTrangThai() {
        return TrangThai;
    }

    public void setTrangThai(TrangThaiDocThongBao TrangThai) {
        this.TrangThai = TrangThai;
    }

    public String getMaNguoiGui() {
        return MaNguoiGui;
    }

    public void setMaNguoiGui(String MaNguoiGui) {
        this.MaNguoiGui = MaNguoiGui;
    }

    public LoaiRequest getRequest() {
        return Request;
    }

    public void setRequest(LoaiRequest Request) {
        this.Request = Request;
    }

    @Override
    public String toString() {
        if(Request.equals(LoaiRequest.XIN_NGHI_PHEP)){
            return "Bạn nhận được thông báo xin nghỉ phép có mã " 
                + MaThongBao + "từ người gửi có mã nhân viên " + MaNguoiGui 
                + " được gửi vào ngày " + date + " " + time + " với lý do: " + Message;
        }
        if(Request.equals(LoaiRequest.XIN_DOI_CA)){
            return "Bạn nhận được thông báo xin đổi ca có mã " 
                + MaThongBao + "từ người gửi có mã nhân viên " + MaNguoiGui 
                + " được gửi vào ngày " + date + " " + time + " với lý do: " + Message;
        }
        if(Request.equals(LoaiRequest.XIN_NGHI_VIEC)){
            return "Bạn nhận được thông báo xin nghỉ việc có mã " 
                + MaThongBao + "từ người gửi có mã nhân viên " + MaNguoiGui 
                + " được gửi vào ngày " + date + " " + time + " với lý do: " + Message;
        }
        if(Request.equals(LoaiRequest.KHIEN_TRACH_CHAM_CONG)){
            return "Bạn nhận được thông báo khiển trách về hành vi vi pham chấm công có mã " 
                + MaThongBao + " bỏi hệ thống "  
                + " được gửi vào ngày " + date + " " + time + " với bằng chứng " + Message;
        }
        else{
            return "Bạn nhận được thông báo đề nghị tăng lương có mã " 
                + MaThongBao + "từ người gửi có mã nhân viên " + MaNguoiGui 
               + " được gửi vào ngày " + date + " " + time + " với lý do: " + Message;
        
        }
    }
    
    
    
    
}
