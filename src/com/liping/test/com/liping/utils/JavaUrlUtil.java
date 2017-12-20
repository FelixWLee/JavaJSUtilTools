package com.liping.test.com.liping.utils;

import java.io.File;
import java.io.IOException;

/**
 * 获取各种路径工具类集合
 *
 * @author LiPing
 * @create 2017-12-20-15:40
 */
public class JavaUrlUtil {


    /**
     *  获取项目根路径 第一种方法
     * @return 项目根路径，例如： D:\IDEAWorkSpace\JavaJSUtilTools
     */
    public static String getProjectRootURL1(){
        File directory = new File("");// 参数为空
        String courseFile = null;
        try {
            courseFile = directory.getCanonicalPath();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return  courseFile;
    }

    /**
     * 获取项目根路径 第二种方法
     * @return 项目根路径，例如： D:\IDEAWorkSpace\JavaJSUtilTools
     */
    public static String getProjectRootURL2(){

        return  System.getProperty("user.dir");
    }

    /**
     *  classpath的获取(在Eclipse或IDEA中为获得src或者classes目录的路径)
     * @return String ,D:/IDEAWorkSpace/JavaJSUtilTools/out/production/JavaJSUtilTools/
     */
    public static String getClassesUrl(){
        return Thread.currentThread().getContextClassLoader().getResource("").getPath();
    }

    /**
     * 获取所有的类路径 包括jar包的路径
     * @return  E:\Java\jdk1.7.0_80\jre\lib\charsets.jar; ...
     */
    public static  String getAllClassJarUrl(){
        return System.getProperty("java.class.path");
    }

    /**
     * 获取当前类文件编译输出的包路径
     * @param object
     * @return  /D:/IDEAWorkSpace/JavaJSUtilTools/out/production/JavaJSUtilTools/com/liping/test/com/liping/utils/
     */
    public static String getClassUrl(Object object) {
        return JavaUrlUtil.class.getResource("").getPath();
    }

    /**
     * 获取当前类文件编译输出的项目路径
     * @param object
     * @return  /D:/IDEAWorkSpace/JavaJSUtilTools/out/production/JavaJSUtilTools/
     */
    public static String getClassProjectUrl(Object object) {
        return JavaUrlUtil.class.getClassLoader().getResource("").getPath();
    }


    public static void main(String[] args){
       //D:\IDEAWorkSpace\JavaJSUtilTools
       // /D:/IDEAWorkSpace/JavaJSUtilTools/out/production/JavaJSUtilTools/
        String p1=JavaUrlUtil.class.getClassLoader().getResource("").getPath();
        System.out.println(p1);


        System.out.println(JavaUrlUtil.class.getResource("").getPath());
    }

}
