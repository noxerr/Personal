
import java.util.Scanner;

public class Encastables {

    private static int encastables (String a, String b){
        int i=1;
        boolean trobat = false;
        String s1,s2;
        while (i<a.length() && i<b.length() && !trobat){
	    s1=b.substring(0,i);
            s2=a.substring(a.length()-i)
            if (s2.equalsIgnoreCase(s1)){
                trobat = true;
            }
            else{
                i++;
            }
        }
        if (!trobat) {i=0;}
        return i;
    }

    public static void main(String[] args) {
        Scanner teclat = new Scanner(System.in);
        final int  nump=5;
        String[] paraules = new String[nump];
        int k;
                
        for (int i=0; i<nump; i++){
            paraules[i] = teclat.next();
        }
        
        for (int i=0; i<nump; i++){
            for (int j=0; j<nump; j++){
                if (i!=j){
                    k = encastables(paraules[i],paraules[j]);
                    if (k!=0) {
                        System.out.println(paraules[i] + paraules[j].substring(k));
                    }                    
                }
            }
        }
        
    }
}

