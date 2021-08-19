package com.siyahulhaq.dynamicArray;
public class DArray <T> {
    private Object[] arr;
    public int length;

    public static boolean callback () { return false; }

    public DArray(int length) {
        this.arr = new Object[length];
        this.length = length;
    }

    public T get(int index) {
        final T t = (T) arr[index];
        return t;
    }

    public void set(int index, T value) {
        arr[index] = value;
    }

    public void push(T item) {
        Object[] newArray;
        if (arr.length == this.length) {
            newArray = new Object[this.length + 1];
        } else {
            newArray = new Object[this.length];
        }
        for (int i = 0; i < this.length; i++) {
            newArray[i] = this.arr[i];
        }
        newArray[this.length] = item;
        this.arr = newArray;
        this.length = this.length + 1;
    }

    public void pop() {
        Object[] newArray = new Object[this.length - 1];
        for (int i = 0; i < newArray.length; i++) {
            newArray[i] = this.arr[i];
        }

        this.arr = newArray;
        this.length = newArray.length;
    }

    public void unShift(T value) {
        Object[] newArray = new Object[this.length + 1];
        newArray[0] = value;
        for (int i = 1; i < newArray.length; i++) {
            newArray[i] = this.arr[i - 1];
        }
        this.arr = newArray;
        this.length = newArray.length;
    }

    public void shift () {
        Object[] newArray = new Object[this.length - 1];
        for (int i = 1; i < this.length; i++) {
            newArray[i - 1] = this.arr[i];
        }
        this.arr = newArray;
        this.length = newArray.length;
    }

    public Object[] getArray() {
        return this.arr;
    }

    public int indexOf(T data){
        for (int i = 0; i < this.length; i++) {
            if (arr[i] == data) return i;
        }

        return -1;
    }

    public int findIndex(DArrayCallback<T> call) {
        for (int i = 0; i < this.length; i++) {
            if (call.findIndexCallback(arr[i], i)){
                return i;
            }
        }
        return -1;
    }

    public T find(DArrayCallback<T> call) {
        for (int i = 0; i < this.length; i++) {
            if (call.findCallback(arr[i], i)){
                return (T) this.arr[i];
            }
        }
        return null;
    }

    public DArray<Object> map(DArrayCallback<T> call) {
        DArray<Object> result = new DArray<Object>(this.length);
        for (int i = 0; i < result.length; i++) {
                result.set(i,call.mapCallback(arr[i], i));
        }
        return result;
    }

    public void forEach(DArrayCallback<T> call) {
        for (int i = 0; i < this.length; i++) {
            call.forEachCallback(arr[i], i);
        }
    }
    private Object reduced;
    public Object reduce(DArrayCallback<T> call, Object firstValue) {
        reduced = firstValue;
        this.forEach(new DArrayCallback<T>(this) {
            @Override
            public void forEachCallback(Object item, int index) {
                this.dArray.reduced = call.reduceCallback(this.dArray.reduced, item, index);
            }
        });
        return reduced;
    }

    public DArray<T> filter(DArrayCallback<T> call) {
        DArray<T> newDArray = new DArray<T>(0);
        this.forEach(new DArrayCallback<T>(this) {
            @Override
            public void forEachCallback(Object item, int index) {
                if(call.filterCallback(item, index)) {
                    newDArray.push((T) item);
                }
            }
        });
        return newDArray;
    }
}
