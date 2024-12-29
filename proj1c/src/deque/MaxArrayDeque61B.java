package deque;

import java.util.Comparator;

public class MaxArrayDeque61B<T> extends ArrayDeque61B<T>{
    private final Comparator<T> Max_Comparator;

    public MaxArrayDeque61B(Comparator<T> c){
        Max_Comparator = c;
    }

    public T max(){
        if(isEmpty()){
            return null;
        }
        T max = this.get(0);
        for (T x : this) {
           // System.out.println("Comparing: " + x + " with " + max);
            if (Max_Comparator.compare(x, max) > 0) {
            //    System.out.println("Updating max to: " + x);
                max = x;
            }
        }
        return max;
    }

    public T max(Comparator<T> c){
        if(isEmpty()){
            return null;
        }
        T max = this.get(0);
        for(T x:this){
            if(c.compare(x,max) > 0){
                max = x;
            }
        }
        return max;
    }
}
