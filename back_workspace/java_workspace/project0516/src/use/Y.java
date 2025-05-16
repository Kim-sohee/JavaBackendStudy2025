package use;
class X { 
     X() { System.out.print(1); } 
     X(int x) { 
         this(); System.out.print(2); 
     } 
 } 
 public class Y extends X { 
     Y() { super(6); System.out.print(3); } 
     Y(int y) { 
         this(); System.out.println(4); 
     } 
     public static void main(String[] a) { new Y(5); } 
 } 

// result : 1234