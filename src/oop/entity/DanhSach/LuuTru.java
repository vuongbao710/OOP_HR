
package oop.entity.DanhSach;

public class LuuTru {
    private String[] luutru;
    private int dem=0;

    public LuuTru(String[] luutru) {
        this.luutru = luutru;
    }

    public LuuTru() {
        this.luutru = new String[100];
    }

    public String[] getLuutru() {
        return luutru;
    }

    public void setLuutru(String[] luutru) {
        this.luutru = luutru;
    }
    
    public void swap(int i, int j){
        String temp = luutru[i];
        luutru[i]=luutru[j];
        luutru[j]=temp;
    }
    public void sort(){ //giam dan
        for(int i = 0; i<dem-1; i++){
            for(int j = i+1; j<dem; j++){
                if(luutru[i].compareTo(luutru[j])<0){
                    swap(i,j);
                }
            }
        }
    }
    public void add(String s){
        luutru[dem]=s;
        dem++;
    }
    
    public void remove(int index){
        for(int i = index;i<dem-1;i++){
            luutru[i]=luutru[i+1];
        }
        dem--;
    }
    
}
