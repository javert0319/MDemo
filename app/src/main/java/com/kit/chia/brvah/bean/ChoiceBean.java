package com.kit.chia.brvah.bean;

public class ChoiceBean extends SelectedBean {

    private String name;

    public ChoiceBean(String name){
        this.name = name;
    }

    public ChoiceBean(String name,boolean isSelected){
        this.name = name;
        setSelected(isSelected);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
