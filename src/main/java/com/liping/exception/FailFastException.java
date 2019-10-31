package com.liping.exception;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * 快速失败 fail-fast
 * 在系统设计中，快速失效系统一种可以立即报告任何可能表明故障的情况的系统。快速失效系统通常设计用于停止正常操作，
 * 而不是试图继续可能存在缺陷的过程。这种设计通常会在操作中的多个点检查系统的状态，因此可以及早检测到任何故障。
 * 快速失败模块的职责是检测错误，然后让系统的下一个最高级别处理错误
 *
 * @author MG01967
 * @create 2019-07-16-19:35
 */
public class FailFastException {

    public static void main(String[] args){
        
        failFastTest();
    
    
    }
    
    
    
    /**
     * ConcurrentModificationException
     * foreach 循环里对某些集合元素进行元素的 remove/add 操作的时候，就会触发fail-fast机制，进而抛出CMException
     *
     *
     * 之所以会抛出CMException异常，是因为我们的代码中使用了增强for循环，而在增强for循环中，集合遍历是通过iterator进行的，
     * 但是元素的add/remove却是直接使用的集合类自己的方法。这就导致iterator在遍历的时候，会发现有一个元素在自己不知不觉的
     * 情况下就被删除/添加了，就会抛出一个异常，用来提示用户，可能发生了并发修改！
     *
     * 所以，在使用Java的集合类的时候，如果发生CMException，优先考虑fail-fast有关的情况，实际上这里并没有真的发生并发，
     * 只是Iterator使用了fail-fast的保护机制，只要他发现有某一次修改是未经过自己进行的，那么就会抛出异常
     */
    static void failFastTest() {
        List<String> userNames = new ArrayList<String>() {{
            add("Hollis");
            add("hollis");
            add("HollisChuang");
            add("H");
        }};
    
        ////错误的方式
        //for (String userName : userNames) {
        //    if (userName.equals("Hollis")) {
        //        userNames.remove(userName);
        //    }
        //}
        //
        //System.out.println(userNames);
        
        //正确：使用iterator的方法去add/remove
        Iterator it = userNames.iterator();
        while (it.hasNext()) {
            String userName = (String) it.next();
            if (userName.equals("hollis")) {
                it.remove();
            }
        }
    
        System.out.println(userNames);
    }
    
}
