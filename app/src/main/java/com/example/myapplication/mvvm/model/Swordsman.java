package com.example.myapplication.mvvm.model;

/**
 * @Author wuyuhang
 * @Date 2023/11/27 16:19
 * @Describe
 */
public class Swordsman {
    private String name;
    private String level;

    public Swordsman(String name, String level) {
        this.name = name;
        this.level = level;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }
}
