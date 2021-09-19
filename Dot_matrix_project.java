package dot_matrix_project;

import java.awt.*;
import java.util.*;
import javax.swing.*;
import javax.swing.table.*;

public class Dot_matrix_project extends JFrame {
    JTable jtbl;
    JScrollPane js;
    
    Dot_matrix_project(){
        super("DOT MATRIX");
        
        String ilk_Dna , ikinci_Dna;

        Scanner al=new Scanner(System.in);
        
        System.out.print("Lutfen ilk Dna'yi giriniz:");
        ilk_Dna=al.nextLine();
        
        System.out.print("Lutfen ikinci Dna'yi giriniz:");
        ikinci_Dna=al.nextLine();
        
        
        char ilk_Dna_harf[]=new char[ilk_Dna.length()+1];
        
        for(int i=0;i<ilk_Dna.length();i++){
            ilk_Dna_harf[i]=ilk_Dna.charAt(i);
        }
        ilk_Dna_harf[ilk_Dna.length()]='+';
        
        
        char ikinci_Dna_harf[]=new char[ikinci_Dna.length()+1];
        
        for(int i=0;i<ikinci_Dna.length();i++){
            ikinci_Dna_harf[i]=ikinci_Dna.charAt(i);
        }
        ikinci_Dna_harf[ikinci_Dna.length()]='/';
        
     
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        this.setLayout(null);
        
        DefaultTableModel model = new DefaultTableModel
        (ikinci_Dna.length()+1, ilk_Dna.length()+1);
        
        jtbl = new JTable(model);
        
        jtbl.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        
        js=new JScrollPane(jtbl);
        
        js.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
        
        js.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS); 
        
        js.setBounds(0, 0, 1920, 1000);
        
        setSize(850,600);
        
        js.setVisible(true);
        
        this.add(js);
        
        this.setExtendedState(JFrame.MAXIMIZED_BOTH);
        
        this.setVisible(true);
        
        jtbl.setRowHeight(35);
        
        jtbl.setFont(new Font("Imprint MT Shadow",Font.BOLD,25));
        
        jtbl.getTableHeader().setFont(new Font("Verona",Font.BOLD,16));
        
        for(int i=0;i<=ilk_Dna.length();i++){
            jtbl.getColumnModel().getColumn(i).setPreferredWidth(35);         
        }
        
        JTableHeader baslik =jtbl.getTableHeader();
        TableColumn tc=baslik.getColumnModel().getColumn(0);
            tc.setHeaderValue("");
        for(int i=0;i<ilk_Dna.length();i++){
            tc=baslik.getColumnModel().getColumn(i+1);
            tc.setHeaderValue(i+1);
        }

        jtbl.setDefaultRenderer(Object.class, new DefaultTableCellRenderer()
        {
         public Component getTableCellRendererComponent(JTable table,
               Object value, boolean isSelected, boolean hasFocus, int row,
               int column) {
                 Component c = super.getTableCellRendererComponent(table, value,
                 isSelected, hasFocus,row, column);
                
                for(int i=0;i<ilk_Dna.length();i++){
                    for(int j=0;j<ikinci_Dna.length();j++){
                        
                        if(ilk_Dna_harf[i]==ikinci_Dna_harf[j] 
                                && ilk_Dna_harf[i+1]==ikinci_Dna_harf[j+1]){
                            if (row == j+1 && column == i+1) {
                                c.setBackground(Color.pink);    
                            } 
                            if (row == j+2 && column == i+2) {
                                c.setBackground(Color.pink);
                            } 
                            else if(column==0 || row==0 ){
                                c.setBackground(null);
                            }   
                        }
                        else if(ilk_Dna_harf[i]!=ikinci_Dna_harf[j] ){
                            if (row == j+1 && column == i+1) {
                                c.setBackground(null);
                            }
                        }
                        if(ilk_Dna_harf[i]!=ikinci_Dna_harf[j]
                                && ilk_Dna_harf[i+1]==ikinci_Dna_harf[j+1] 
                                && ilk_Dna_harf[i+2]!=ikinci_Dna_harf[j+2]){
                              if (row == j+2 && column == i+2) {
                                c.setBackground(null);
                            }
                        }
                        if(ilk_Dna_harf[i]==ikinci_Dna_harf[1]
                                && ilk_Dna_harf[i+1]!=ikinci_Dna_harf[2]){
                            if(row==1 && column== i+2){
                                c.setBackground(null);
                            }
                        }
                    }       
                }
                if(row==1 && column==ilk_Dna.length()){
                    c.setBackground(null);
                }
                c.setForeground(Color.darkGray);
                if(row==0 || column==0){
                    c.setForeground(Color.BLACK);
                }
                return c;
                }
         });
        
        Ilk_DNA_doldur(jtbl,ilk_Dna);
        
        Ikinci_DNA_doldur(jtbl,ikinci_Dna);
        
        Yildiz_doldur(model,jtbl,ilk_Dna_harf,ikinci_Dna_harf);                
    }

    void Ilk_DNA_doldur(JTable a,String x){
        for(int i=1;i<=x.length();i++){
            a.setValueAt(x.charAt(i-1), 0, i);
        }
    }

    void Ikinci_DNA_doldur(JTable a,String x){
        for(int i=1;i<=x.length();i++){
            a.setValueAt(x.charAt(i-1), i, 0);
        }
    } 
    
    void Yildiz_doldur(DefaultTableModel model,JTable k,char[] a,char[] b){ 
        for(int i=1;i<=a.length-1;i++){
                    for(int j=1;j<=b.length-1;j++){
                        if(a[i-1]==b[j-1] && a[i]==b[j]){
                            model.setValueAt("*", j, i);
                        }
                        model.setValueAt("", b.length-1, i);
                        model.setValueAt("", j, a.length-1); 
                    }
        }  
    }
    
    public static void main(String args[]){
        Dot_matrix_project d=new Dot_matrix_project();
    }
}