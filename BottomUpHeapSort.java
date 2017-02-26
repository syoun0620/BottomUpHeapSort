//Sukwhan Youn

import java.util.*;

public class BottomUpHeapSort {
  
  private BottomUpHeapSort () {
  }
  public static void sort(Comparable[] pq) { 
    int n = pq.length - 1;
        
    for (int k = n / 2; k >= 1; k--) {
      bottomUpRearrange(pq, n, k, 1);
    }
        
    while (n > 1) {
      exch(pq, 1, n--);
      bottomUpRearrange(pq, n, 1, 1);
    }
  }
 
  private static void bottomUpRearrange(Comparable[] pq, int n, int i, int j) {
    j = leafSearch(pq, n, i, j);    
    j = bottomUpSearch(pq, i, j);
    interchange(pq, n, i, j);
  }
  
  private static int leafSearch(Comparable[] pq, int n, int i, int j) {
    j = i;
    
    while ((2 * j) <= n) {
      if ((2 * j + 1) <= n && less(pq, 2 * j, 2 * j + 1)) {
        j = 2 * j + 1;
      } else {
        j = 2 * j;
      }
    }
    
    return j;
  }
  
  private static int bottomUpSearch(Comparable[] pq, int i, int j) {
    while (less(pq, j, i)) {
      j = j / 2;
    }
    
    return j;
  }
  
  private static void interchange(Comparable[] pq, int n, int i, int j) {
    Comparable x = pq[j];
    
    pq[j] = pq[i];
    
    while (j > i) {
      Comparable t = x;
      x = pq[j / 2];
      pq[j / 2] = t;
      j = j / 2;
    }
  }

  private static void exch(Comparable[] pq, int i, int j) {
    Comparable swap = pq[i];
    pq[i] = pq[j];
    pq[j] = swap;
  }
  

  private static boolean less(Comparable[] pq, int i, int j) {
    return pq[i].compareTo(pq[j]) < 0;
  }
 

  private static boolean less(Comparable v, Comparable w) {
    return v.compareTo(w) < 0;
  }  
  
  private static void show(Comparable[] a) {
    for (int i = 0; i < a.length; i++) {
      StdOut.println(a[i]);
    }
  }
  
  public static void main(String[] args) {
    In in = new In("largeW.txt");
    
    String[] a = in.readAllStrings();
    
    in.close();
    
    Comparable[] b = new Comparable[a.length + 1];
    
    for (int i = 1; i <= a.length; i++) {
      b[i] = a[i - 1];
    }
    
    BottomUpHeapSort.sort(b);
    
    Out out = new Out("largeWresult.txt");
    
    for(int i = 1; i < b.length; i++) {
      out.print(b[i] + " ");
    }
    
    out.close();
  }
}
