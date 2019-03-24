package other.study;

import java.util.*;

public class lession {
    public static void main(String []agrs){
//        List<Integer> list = new ArrayList<>();
//        for(int i=0;i<5;i++){
//            list.add(i);
//        }
//        List<Integer> list2 = new ArrayList<>();
//        for(int i=0;i<5;i++){
//            list2.add(i*i);
//        }
//
//        list.addAll(list2);
//        print(1,list.toString());
//
//        Collections.sort(list);
//        print(2,list.toString());
//
//        Collections.sort(list, new Comparator<Integer>() {
//            @Override
//            public int compare(Integer o1, Integer o2) {
//                return o2.compareTo(o1);
//            }
//        });
//        print(3,list.toString());
        demoRandom();
}
    public static void demoControllerFlow(){
        int a = 2;
        int target = a==2?1:2;
    }
    public static void print(int index,String content){
        System.out.println(String.format("[%d] %s",index,content));
    }
    public static void print(int index,int content){
        System.out.println(String.format("[%d] %d",index,content));
    }
    public static void print(int index,float content){
        System.out.println(String.format("[%d] %f",index,content));
    }

    public static void demoRandom(){
        Random random = new Random();
        //random.setSeed(1L);
        print(1,random.nextInt(100));
        print(1,random.nextFloat());
    }
}
