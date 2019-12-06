package com.nbhope.chia.brvah.bean;

/**
 * @ClassName: CheckBoxBean
 * @Description: 作用描述
 * @Author: CHIA
 * @CreateDate: 2019/12/5
 */
public class CheckBoxBean {
    private String name;
    private int age;
    private boolean checked;

    public boolean isChecked() {
        return checked;
    }

    public void setChecked(boolean checked) {
        this.checked = checked;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
}
