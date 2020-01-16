package com.kit.chia.brvah.bean;

/**
 * @ClassName: CheckBoxBean
 * @Description: 作用描述
 * @Author: CHIA
 * @CreateDate: 2019/12/5
 */
public class CheckBoxBean extends SelectedBean{

    private String name;
    private int age;

    public CheckBoxBean(){}

    public CheckBoxBean(int age,String name,boolean isSelected){
        this.age = age;
        this.name = name;
        setSelected(isSelected);
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
