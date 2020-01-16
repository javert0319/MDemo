package com.kit.chia.eventbas;

/**
 * @ClassName: Activation
 * @Description: 用作EventBus数据的传递
 * @Author: CHIA
 * @CreateDate: 2019/12/27
 */
public class Activation {

    public static final int ON_DIMMER_EDIT = 0x001;

    private int what = Integer.MIN_VALUE; //比较的唯一值
    private String arg1;
    private String arg2;
    private Object obj1;
    private Object obj2;

    /**
     * 进行比较 what相同即认为意图相同
     *
     * @param _what
     * @return
     */
    public boolean compare(int _what) {
        if (what == Integer.MIN_VALUE) return false;

        return what == _what;
    }

    public Activation(int what) {
        this.what = what;
    }

    public Activation(int what, String arg1) {
        this.what = what;
        this.arg1 = arg1;
    }

    public Activation(int what, Object obj1) {
        this.what = what;
        this.obj1 = obj1;
    }

    public Activation(int what, String arg1, String arg2) {
        this.what = what;
        this.arg1 = arg1;
        this.arg2 = arg2;
    }

    public Activation(int what, Object obj1, Object obj2) {
        this.what = what;
        this.obj1 = obj1;
        this.obj2 = obj2;
    }

    public Activation(int what, String arg1, String arg2, Object obj1) {
        this.what = what;
        this.arg1 = arg1;
        this.arg2 = arg2;
        this.obj1 = obj1;
    }

    public Activation(int what, String arg1, String arg2, Object obj1, Object obj2) {
        this.what = what;
        this.arg1 = arg1;
        this.arg2 = arg2;
        this.obj1 = obj1;
        this.obj2 = obj2;
    }

    public int getWhat() {
        return what;
    }

    public void setWhat(int what) {
        this.what = what;
    }

    public String getArg1() {
        return arg1;
    }

    public void setArg1(String arg1) {
        this.arg1 = arg1;
    }

    public String getArg2() {
        return arg2;
    }

    public void setArg2(String arg2) {
        this.arg2 = arg2;
    }

    public Object getObj1() {
        return obj1;
    }

    public void setObj1(Object obj1) {
        this.obj1 = obj1;
    }

    public Object getObj2() {
        return obj2;
    }

    public void setObj2(Object obj2) {
        this.obj2 = obj2;
    }


    @Override
    public String toString() {
        return "Activation{" +
                "what=" + what +
                ", arg1='" + arg1 + '\'' +
                ", arg2='" + arg2 + '\'' +
                ", obj1=" + obj1 +
                ", obj2=" + obj2 +
                '}';
    }
}
