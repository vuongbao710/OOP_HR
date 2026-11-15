
package oop.entity.Service;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;

public class NhatKyHoatDong {
    private static ArrayList<String> NhatKyHeThong;
    
    public static void them(String s){
        NhatKyHeThong.add(s);
    }
    
    public static void xuatMoiNhat(){
        for(int i = NhatKyHeThong.size()-1 ; i>=0 ; i--){
            System.out.println(NhatKyHeThong.get(i));
        }
    }
    
    public static void xuatCuNhat(){
        for(int i = 0 ; i<NhatKyHeThong.size(); i++){
            System.out.println(NhatKyHeThong.get(i));
        }
    }
    
    public static void xuatTheoMaNV (String MaNV){
        for(String s : NhatKyHeThong){
            if(s.contains(MaNV)){
                System.out.println(s);
            }
        }
    }
    
    public static void xuatTheoTime (LocalTime time){
        String timKiem = time.toString();
        for(String s : NhatKyHeThong){
            if(s.contains(timKiem)){
                System.out.println(s);
            }
        }
    }
            
    public static void xuatTheoDate (LocalDate date){
        String timKiem = date.toString();
        for(String s : NhatKyHeThong){
            if(s.contains(timKiem)){
                System.out.println(s);
            }
        }
    }
    
}
