/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package smarttransport;

import java.util.*;
import java.text.*;
/**
 *
 * @author mursalat
 */
public class dateMan {
    Date d;
    DateFormat df;
    public int[] count;
    public int[] size;
    @SuppressWarnings("empty-statement")
    public dateMan()
    {
        count = new int[12];
        size = new int[]{31,28,31,30,31,31,30,31,30,31,30,31};
        int i  = 1;
        count[0] = 0;
        count[1] = 31;
        while(++i<12)
        {
            count[i] = count[i-1]+ size[i-1];
        }
    }
    public String numToDate(String in)
    {
        int d = 0;
        
        d=Integer.parseInt(in);
        System.out.println(d);
        System.out.println(numToDate(d));
        return ""+numToDate(d);
    }
    public int today()
    {
        d = new Date();
        df = new SimpleDateFormat("dd/MM");
        return dateToNum(df.format(d));
    }
    
    public int dateToNum(String d)
    {
        int dd = (d.charAt(0)-'0')*10 + (d.charAt(1)-'0');
        int mm = (d.codePointAt(3)-'0')*10 + (d.codePointAt(4)-'0');
        
        return dd+count[mm-1];
    }
    
    public int dateToNum(int d, int m)
    {
        int c = 0;
        
        c = d + count[m-1];
        
        return c;
    }
    
    public String numToDate(int d)
    {
        String out = "";
        
        int i = -1;
        
        while(i++<12)
        {
            if(count[i]>d)
            {
                break;
            }
        }
        
        out += (d-count[i-1])+"/";
        
        if(i<10)
        {
            out+= '0';
        }
        out += i;
        return out;
    }
    public int[] numToDateArray(int d)
    {
        int[] out = new int[2];
        
        int i = -1;
        
        while(i++<12)
        {
            if(count[i]>d)
            {
                break;
            }
        }
        
        out[0] = d-count[i-1];
        out[1] = i;
        return out;
    }
}
