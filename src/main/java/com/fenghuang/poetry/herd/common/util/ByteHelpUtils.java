package com.fenghuang.poetry.herd.common.util;

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
 * @date Created in 2020年05月14日 21:20
 * @since 1.0
 */
public class ByteHelpUtils {
    //原始数组
    byte[] bytes;
    //记录当前写入到多少位
    int index;

    private ByteHelpUtils(int capacity){
        bytes = new byte[capacity];
        index = 0;
    }

    public static ByteHelpUtils CreateBytes(int capacity){
        ByteHelpUtils ByteHelpUtils = new ByteHelpUtils(capacity);
        return ByteHelpUtils;
    }

    //向数组中追加内容
    public ByteHelpUtils AppendNumber(long val){
        byte[] arr = Number2byte(val);
        AppendBytes(arr);
        return this;
    }
    public ByteHelpUtils AppendNumber(int val){
        byte[] arr = Number2byte(val);
        AppendBytes(arr);
        return this;
    }
    public ByteHelpUtils AppendNumber(short val){
        byte[] arr = Number2byte(val);
        AppendBytes(arr);
        return this;
    }
    public ByteHelpUtils AppendNumber(byte val){
        byte[] arr = new byte[]{val};
        AppendBytes(arr);
        return this;
    }

    /**
     * 获取数据的总和
     * @return
     */
    public int GetSum(){
        int ret = 0;
        for(int i = 0 ; i < bytes.length ; i ++){
            ret += bytes[i];
        }
        return ret;
    }

    //追加byte数组
    public ByteHelpUtils AppendBytes(byte[] arr){

        for(byte i = 0 ; i < arr.length ; i ++){
            bytes[index + i] = arr[i];
        }

        index += arr.length;
        return this;
    }

    /**
     * 将数字转换为byte数组
     */
    public static byte[] Number2byte(long val) {

        byte[] arr = new byte[]{
                (byte) ((val >> 56) & 0xFF),
                (byte) ((val >> 48) & 0xFF),
                (byte) ((val >> 40) & 0xFF),
                (byte) ((val >> 32) & 0xFF),
                (byte) ((val >> 24) & 0xFF),
                (byte) ((val >> 16) & 0xFF),
                (byte) ((val >> 8) & 0xFF),
                (byte) (val & 0xFF)
        };

        return arr;
    }
    public static byte[] Number2byte(int val) {

        byte[] arr = new byte[]{
                (byte) ((val >> 24) & 0xFF),
                (byte) ((val >> 16) & 0xFF),
                (byte) ((val >> 8) & 0xFF),
                (byte) (val & 0xFF)
        };

        return arr;
    }
    public static byte[] Number2byte(short val) {

        byte[] arr = new byte[]{
                (byte) ((val >> 8) & 0xFF),
                (byte) (val & 0xFF)
        };

        return arr;
    }
}