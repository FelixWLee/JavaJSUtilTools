package com.liping.java.java8;

import java.lang.reflect.Array;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.DoubleStream;
import java.util.stream.IntStream;
import java.util.stream.Stream;

/**
 * java8 Steam流操作
 *
 * @author MG01967
 * @create 2019-07-02-17:26
 */
public class StreamOperation {
    
    public static void main(String[] args) {
        
        //Stream 的获取
        //① 通过集合Collection获取
        List<Integer> list = new ArrayList<Integer>(Arrays.asList(1, 2, 3, 4, 5));
        Stream<Integer> stream1 = list.stream();
        
        //②通过数组获取
        String[] array1 = {"are", "you", "ok"};
        Stream<String> stream2 = Arrays.stream(array1);
       
        //对于基本类型数组的处理
        int[] array = {1, 2, 3, 4, 5};
        Stream<Integer> stream3 = Arrays.stream(array).boxed();
        //Arrays.stream(array)获取的是一个IntStream对象，boxed 方法用于将目前 Stream 中的基本类型装箱
        
        //③ 直接通过值获取
        Stream<String> stream4 = Stream.of("are","you","ok");
    
    //---------------------------------------------------------------------------------
        
        //Stream 常用管道操作
        //1) 筛选 filter
        //filter函数接收一个Lambda表达式作为参数，该表达式返回 boolean，在执行过程中，流将元素逐一输送给filter，并筛选出执行结果为 true 的元素；
        //筛选出列表中的非空项
        List<String> list1 = Arrays.asList("are","you","","ok");
        list1.stream().filter(x -> !x.isEmpty()).collect(Collectors.toList());
    
        //2) 去重 distinct
        ////去除列表中的重复元素
        List<String> list2 = Arrays.asList("are","you","you","ok");
        List<String> distinctList2 = list2.stream().distinct().collect(Collectors.toList());
        //[are, you, ok]
        System.out.println(distinctList2.toString());
    
        //3) 截取 limit
        //截取流的前N个元素：
        ////获取Stream的前3个值
        List<String> list3 = Arrays.asList("are","you","fucking","ok");
        List<String> limitList = list3.stream().limit(3).collect(Collectors.toList());
        //[are, you, fucking]
        System.out.println(limitList.toString());
    
        //4) 跳过 skip
        //跳过流的前n个元素：
        List<String> list4 = Arrays.asList("are","you","fucking","ok");
        List<String> skipList = list4.stream().skip(2).collect(Collectors.toList());
        //[fucking, ok]
        System.out.println(skipList.toString());
    
        //5) 映射 map
        //对流中的每个元素执行一个函数，使得元素转换成另一种类型输出。流会将每一个元素输送给map函数，
        //并执行map中的Lambda表达式，最后将执行结果存入一个新的流中。
        //如：将 list 中每一个 Integer类型元素自增后转化为 String类型
        //将集合中的每一个元素+1，并转为字符串
        List<Integer> list5 = Arrays.asList(1,2,3,4,5);
        List<String> map = list5.stream().map(x -> String.valueOf(++x)).collect(Collectors.toList());
        //统计集合中>3的元素数量
        int num1 = (int) list5.stream().filter(x -> x > 3).count();
        //2[2, 3, 4, 5, 6]
        System.out.println(num1 + map.toString());
    
        //6) 合并多个流 flatMap
        List<List<String>> list6 = Arrays.asList(list1,list3);
        //将list中的list1，list2合并为一个List<String>
        List<String> flatMap = list6.stream().flatMap(List::stream).collect(Collectors.toList());
        System.out.println(flatMap.toString());
    
        //以下一个实际的应用例子：列出 list 中各不相同的单词；
        List<String> list7 = new ArrayList<String>();
        list7.add("I am a boy");
        list7.add("I love the girl");
        list7.add("But the girl loves another girl");
        list7.stream()
                //将每一个项分词，并映射为数组
                .map(x -> x.split(" "))
                //将每一个分项数组组合并到主流中，形成一个包含所有分项数组的总数组流
                .flatMap(Arrays::stream)
                //去重
                .distinct()
                .forEach(System.out::print);
    
        System.out.println();
        // 7）匹配元素
        // ①是否匹配任一元素：anyMatch
        // anyMatch用于判断流中是否存在至少一个元素满足指定的条件，这个判断条件通过Lambda表达式传递给anyMatch，执行结果为boolean类型。 
        //判断流中是否含有>10的项
        List<Integer> list8 = Arrays.asList(1,2,3,4,5,6);
        boolean result1 = list8.stream().allMatch(x -> x > 10);
        //false
        System.out.println(result1);
        
        //③ 是否未匹配所有元素：noneMatch
        //noneMatch与allMatch恰恰相反，它用于判断流中的所有元素是否都不满足指定条件：
        //判断流中是否 全部不满足 >5
        List<Integer> list9 = Arrays.asList(1,2,3,4,5,6);
        ////false
        boolean result2 = list9.stream().noneMatch(x -> x > 5);
        System.out.println(result2);
    
        //8）获取元素
        //①获取任一元素 ：findAny
        //findAny从流中随机选出 一个元素出来，它返回一个Optional类型的元素。
        List<Integer> list10 = Arrays.asList(1,2,3,4,5,6);
        //Optional<Integer> result3 = list10.stream().findAny();
        //if(result3.isPresent()) {
        //    Integer randValue = result3.get();
        //} else {
        //    Integer randValue = result3.orElse(0);
        //}
        //合并的调用方式
        Integer randValue = list10.stream().findAny().orElse(0);
        System.out.println(randValue);
    
        /**
         * Optional 对象介绍
         *
         * Optional是Java8新加入的一个容器，这个容器只存1个或0个元素，它用于防止出现NullpointException，它提供如下方法：
         *
         * isPresent() 
         * 判断容器中是否有值。
         * ifPresent(Consume lambda) 
         * 容器若不为空则执行括号中的Lambda表达式。
         * T get() 
         * 获取容器中的元素，若容器为空则抛出NoSuchElement异常。
         * T orElse(T other) 
         * 获取容器中的元素，若容器为空则返回括号中的默认值。
         */
        //② 获取第一个元素：findFirst
        System.out.println(list10.stream().findFirst());
    
    
        //9） 归约统计
        //归约是将集合中的所有元素经过指定运算，折叠成一个元素输出，如：求最值、平均数等，这些操作都是将一个集合的元素折叠成一个元素输出；
        ////获取一个整型列表的最大值，最小值
        Random random = new Random();
        List<Integer> list11 = random.ints(1, 100).limit(50).boxed().collect(
                Collectors.toCollection(ArrayList::new));
        //最大值
        int max = list11.stream().max(Integer::compareTo).orElse(-1);
        //最小值
        int min = list11.stream().min(Integer::compareTo).orElse(-1);
        System.out.println(max + " " + min +"\n"+ list11.toString());
        
        //使用基于数据流的方式，将流装载相应的 SummaryStatistics 来进行归约计算，可以实现更多的操作；
        IntSummaryStatistics stats = list11.stream().mapToInt(x -> x).summaryStatistics();
        int max1 = stats.getMax();        //获取最大值
        int min1 = stats.getMin();        //获取最小值
        double sum =  stats.getSum();    //获取总值
        double avg = stats.getAverage();  //获取平均值
        long count = stats.getCount();     //获取总数量
    
    
        //10） 遍历流 forEach
        //数据流提供了新的forEach方法遍历该流中的每个元素，方法参数为一个Lambda表达式，用于对每一个遍历的元素执行的操作；
        //输出10个随机数
        random.ints(1,200).limit(10).forEach(System.out::println);
    
        //11） 排序 sorted
        //sorted方法用来流排序，默认升序排布，可以使用一个 Comparator 作为参数来实现自定义排序；
        random.ints().limit(10).sorted().forEach(System.out::println);//升序
    
        //12）parallelStream 并行处理
        //parallelStream是流 进行并行处理的替代方案，parallelStream 的底层实现为 ForkJoin 线程池，JDK8 为为 parallelStream
        // 提供了一个通用的线程池，对于代码实际运行时的 parallelStream 线程数量是不可控的，但是可以通过设置 JVM 运行参数
        //  -Djava.util.concurrent.ForkJoinPool.common.parallelism=N （N为线程数量）来调整 JVM ForkJoinPool 的线程数量；
        List<String> strings = Arrays.asList("abc", "", "bc", "efg", "abcd","", "jkl");
        //get count of empty string
        long count1 = strings.parallelStream().filter(string -> string.isEmpty()).count();
        System.out.println(count1);
    
        
        //---------------------------------------------------------------------------------------------------------
        
        //数值流的使用
        //采用Stream 的 redure 方法 进行数值归约操作会涉及到基本数值类型和引用数值类型之间的装箱、拆箱操作，因此效率较低；
        //此时最好是将当流操作为纯数值操作时，这样使用数值流能获得较高的效率；
        //StreamAPI提供了三种数值流：IntStream、DoubleStream、LongStream；
    
        //1）将 Stream 转换成数值流
        //StreamAPI 提供了将普通流转换成数值流的三种方法：mapToInt、mapToDouble、mapToLong（参数：相应的转化Lambda表达式）；
        //
        //将数值流转化为Stream 的方法：boxed；
    
        List<Double> list12 = Arrays.asList(2.3,2.4,2.5,2.7,2.8);
        //普通Stream转为数值Stream
        DoubleStream doubleStream = list12.stream().mapToDouble(x -> x);  //转化为DoubleStream
        IntStream intStream = list12.stream().mapToInt(x -> Integer.parseInt(String.format("%.0f", x)));//转化为IntStream，同时进行取舍操作
    
        //数值Stream转为普通Stream
        Stream stream = intStream.boxed();
        stream.forEach(System.out::println);
    
        //2）数值流的数值计算
        //每种数值流都提供了数值计算函数，如max、min、sum、avg等。
    
        List<Integer> list13 = Arrays.asList(1,2,3,4,5,6,7);
    
        //OptionalInt max2 = list13.stream().max().orElse(0);
    
        //--------------------------------------------------------------------------------
        
        //Stream 转换为 Collection、Array、String
        //Stream 可以通过 Collector 收集器，将其转化为 Array，Collection，Map，String；
        
        //① Stream -> 数组
        //普通转换
        Stream<String> stream5 = Stream.of("are","you","ok");
        String[] array2 = stream5.toArray(String[]::new);
        System.out.println(Arrays.toString(array2));
        
        //涉及拆箱、装箱操作的转换
        Stream<Integer> stream6 = Stream.of(1,2,3,4,5,6);
        int[] array3 = stream6.mapToInt(x-> x).toArray();
        System.out.println(Arrays.toString(array3));
        
        Stream<Integer> stream7 = Stream.of(1,2,3,4,5,6);
        Integer[] array4 = stream7.toArray(Integer[]::new);
        System.out.println(Arrays.toString(array4));
    
        //将 List<Inetegr> 转化为 String[]
        List<Integer> list14 = Arrays.asList(1,2,3,4,5);
        String[] array5 = list14.stream().map(String::valueOf).toArray(String[]::new);
        System.out.println(Arrays.toString(array5));
        
        //② Stream -> List
        Stream<Integer> stream8 = Stream.of(1,2,3,4,5,6);
        List<Integer> list15 = stream8.collect(Collectors.toList());
        Stream<Integer> stream9 = Stream.of(1,2,3,4,5,6);
        List<Integer> list16 = stream9.collect(Collectors.toCollection(ArrayList::new));
       
        //③ Stream ->Set
        Stream<Integer> stream10 = Stream.of(1,2,3,4,5,6);
        Set<Integer> set = stream10.collect(Collectors.toCollection(TreeSet::new));
        set.forEach(System.out::println);
        
        //④ Stream ->Stack
        Stream<Integer> stream11 = Stream.of(1,2,3,4,5,6);
        Stack<Integer> stack = stream11.collect(Collectors.toCollection(Stack::new));
        stack.forEach(System.out::println);
        
        //⑤ Stream ->Map
        Map<Integer, String> map1 = Stream.of("are","you","ok").collect(Collectors.toMap( s -> s.hashCode(), s -> s));
        System.out.println(map1.toString());
        
        //⑥ Stream -> String
        //Stream 可以很方便转化为 String，利用这一个特性，可以十分方便地将一个 Collection（List，Set等）转化为使用某个标点符号分隔的字符串；
        //将 List 转化为使用 “,” 分隔的字符串
        List<Integer> list17 = Arrays.asList(1,2,3,4,5,6,7);
        String s = list17.stream().map(x -> x.toString()).collect(Collectors.joining(","));
        System.out.println(s);
    
        //对 Map 使用Stream
        //虽然 JDK8 的 Stream API 不直接支持 Map，但是我们可以通过对 Map 的 entrySet，keySet，valueColletion 生成 Stream 来进行曲线救国，如下示例：
        Map<Integer,String> map3 = new HashMap<Integer,String>(){{
            put(1,"are");
            put(2,"you");
            put(3,"ok"); }};
    
        map3.entrySet().forEach(System.out::println);
        
       
    }
    
}
