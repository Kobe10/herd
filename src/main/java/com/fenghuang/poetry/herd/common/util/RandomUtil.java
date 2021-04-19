package com.fenghuang.poetry.herd.common.util;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

/**
 * <p></p>
 * <p>
 * <PRE>
 * <BR>    修改记录
 * <BR>-----------------------------------------------
 * <BR>    修改日期         修改人          修改内容
 * </PRE>
 *
 * @author fuzq
 * @version 1.0
 * @Desc
 * @date Created in 2020年07月21日 23:01
 * @since 1.0
 */
public class RandomUtil {
    public static List<Integer> randomArray(int min,int max,int n){
        int len = max-min+1;

        if(max < min || n > len){
            return null;
        }

        //初始化给定范围的待选数组
        int[] source = new int[len];
        for (int i = min; i < min+len; i++){
            source[i-min] = i;
        }

        int[] result = new int[n];
        Random rd = new Random();
        int index = 0;
        for (int i = 0; i < result.length; i++) {
            //待选数组0到(len-2)随机一个下标
            index = Math.abs(rd.nextInt() % len--);
            //将随机到的数放入结果集
            result[i] = source[index];
            //将待选数组中被随机到的数，用待选数组(len-1)下标对应的数替换
            source[index] = source[len];
        }
        List<Integer> list2 = Arrays.stream(result).boxed().collect(Collectors.toList());

        return list2;
    }

    public static void main(String[] args) throws InterruptedException {


        for (int i =1 ; i< 1000;i++) {
            long l = System.currentTimeMillis();
            List<Integer> reult2 = randomArray(0,800,20);
            System.out.println(System.currentTimeMillis() - l);
            Thread.sleep(50);
        }

//        for (int i : reult2) {
////            System.out.println(i);
//        }
    }
}
