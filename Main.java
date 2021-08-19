import com.siyahulhaq.dynamicArray.DArray;
import com.siyahulhaq.dynamicArray.DArrayCallback;

class Main {
    public static void main(String[] args) {
        DArray<String> newArray = new DArray<String>(0);

        newArray.push("1st");
        newArray.push("2nd");
        newArray.push("3rd");
        newArray.unShift("0th");

        DArray<Object> newTypeArray = newArray.map(new DArrayCallback<String>(){
            @Override
            public Object mapCallback(Object obj, int index) {
                NewType newType = new NewType();
                newType.test = (String) obj;
                return newType;
            }
        });

      /*   newTypeArray.forEach(new DArrayCallback<Object>(newTypeArray) {
            @Override
            public void forEachCallback(Object obj, int index) {
                System.out.println(((NewType) newTypeArray.get(index)));
            }
        }); */

        /* System.out.println(newTypeArray.reduce(new DArrayCallback<Object>(newTypeArray) {
            @Override
            public Object reduceCallback(Object prev, Object obj, int index) {
                NewType nT = (NewType) obj;
                return  prev + nT.test;
            }
        }, "")); */

        DArray<Object> newD = newTypeArray.filter(new DArrayCallback<Object>(newTypeArray){
            @Override
            public boolean filterCallback(Object obj, int index) {
                NewType nT = (NewType) obj;
                return nT.test != "2nd";
            }
        });

        newD.forEach(new DArrayCallback<Object>(newD) {
            @Override
            public void forEachCallback(Object obj, int index){
                System.out.println((NewType) obj);
            }
        });
    }
}
