package com.fenghuang.poetry.herd.common.util;

import lombok.Data;

@Data
public class UUIDGenerator {

    private static final int IP = 0;
    private static final int JVM = (int) (System.currentTimeMillis() >>> 8);
    private static short counter = 0;

    public static String hexUUID() {
        UUIDGenerator uuidGenerator = new UUIDGenerator();
        return uuidGenerator.generate();
    }

    private String generate() {
        return (new StringBuffer(36)).append(this.format(this.getIP())).append("").append(this.format(this.getJVM())).append("").append(this.format(this.getHiTime())).append("").append(this.format(this.getLoTime())).append("").append(this.format(this.getCount())).toString();
    }

    protected short getCount() {
        Class var1 = UUIDGenerator.class;
        synchronized (UUIDGenerator.class) {
            if (counter < 0) {
                counter = 0;
            }

            short var10000 = counter;
            counter = (short) (var10000 + 1);
            return var10000;
        }
    }

    protected String format(int intval) {
        String formatted = Integer.toHexString(intval);
        StringBuffer buf = new StringBuffer("00000000");
        buf.replace(8 - formatted.length(), 8, formatted);
        return buf.toString();
    }

    protected int getIP() {
        return IP;
    }

    protected int getJVM() {
        return JVM;
    }

    protected short getHiTime() {
        return (short) ((int) (System.currentTimeMillis() >>> 32));
    }


    protected int getLoTime() {
        return (int) System.currentTimeMillis();
    }

    protected String format(short shortval) {
        String formatted = Integer.toHexString(shortval);
        StringBuffer buf = new StringBuffer("0000");
        buf.replace(4 - formatted.length(), 4, formatted);
        return buf.toString();
    }
}
