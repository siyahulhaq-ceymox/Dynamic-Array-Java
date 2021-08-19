package com.siyahulhaq.dynamicArray;
public class DArrayCallback <T>{
    protected DArray<T> dArray;

    public DArrayCallback() {}
    public DArrayCallback(DArray<T> dArray) {
        this.dArray = dArray;
    }

    public boolean findCallback(Object obj, int index) {
        return false;
    }
    
    public boolean findIndexCallback(Object obj, int index) {
        return false;
    }

    public Object mapCallback(Object obj, int index) {
        return obj;
    }
    
    public void forEachCallback(Object obj, int index) {
        return;
    }
    public Object reduceCallback(Object reduced, Object obj, int index) {
        return obj;
    }

    public boolean filterCallback(Object obj, int index){
        return false;
    }
}
