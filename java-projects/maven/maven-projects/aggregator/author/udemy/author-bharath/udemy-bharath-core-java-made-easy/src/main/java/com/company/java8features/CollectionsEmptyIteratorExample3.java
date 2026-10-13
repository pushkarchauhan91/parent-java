package com.company.java8features;

import java.util.*;
public class CollectionsEmptyIteratorExample3 {  
    public static void main(String[] args) {          
        List<Integer> ls = new ArrayList<Integer>();  
            ls.add(null);  
            ls.add(2);  
            ls.add(3);  
            ls.add(4);  
            Iterator<Integer> Itr = ls.iterator();  
            while(Itr.hasNext()){  
                 System.out.println(Itr.next());  
            }  
            System.out.println("It is empty Iterator list.");  
          }     
} 