package com.liping.test;

import java.math.BigDecimal;
import java.util.*;

/**
 * @author LiPing
 * @create 2017-12-19-15:32
 */
public class Test1 {


    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
                // 注意 hasNext 和 hasNextLine 的区别
        while (in.hasNextLine()) { // 注意 while 处理多个 case
            List<Integer> inputList = new ArrayList<>();
            String s = in.nextLine();
            String[] s1 = s.split(" ");
            List<String> strings = Arrays.asList(s1);
            for (int i = 0; i < strings.size()&&i<1000; i++) {
                inputList.add(Integer.parseInt(strings.get(i)));
            }

            Map<Integer, Integer> map = new HashMap<>();
            for (int i = 0; i < inputList.size(); i++) {
                int temp = inputList.get(i);
                if (map.containsKey(temp)) {
                    map.put(temp, map.get(temp) + 1);
                } else {
                    map.put(temp, 1);
                }
            }


            int max = 0;
            for (Integer integer : map.keySet()) {
                Integer i = map.get(integer);
                if (i > max) {
                    max = i;
                }
            }
            for (Integer integer : map.keySet()) {
                if (max != (int) map.get(integer)) {
                    inputList.remove((Integer)integer);
                }
            }

            Collections.sort(inputList, new Comparator<Integer>() {
                @Override
                public int compare(Integer o1, Integer o2) {
                    return o1.compareTo(o2);
                }
            });

            int size = inputList.size();
            if (size % 2 == 0) {

                int a = inputList.get(size / 2);
                int b = inputList.get(size / 2 + 1);

                BigDecimal decimal = new BigDecimal(a+b);
                System.out.println(decimal.divide(new BigDecimal(2)));
            } else {
                System.out.println( inputList.get((size + 1)/2));
            }

        }


    }
}
