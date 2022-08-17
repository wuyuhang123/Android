package com.example.myapplication.recycleView.data;

public class PageData {
    private String pageName;
    private Class<?> pageClass;

    public PageData(){

    }

    public PageData(String pageName, Class<?> mClass){
        this.pageName = pageName;
        this.pageClass = mClass;
    }

    public String getPageName() {
        return pageName;
    }

    public void setPageName(String pageName) {
        this.pageName = pageName;
    }

    public Class<?> getPageClass() {
        return pageClass;
    }

    public void setClass(Class<?> mClass) {
        this.pageClass = mClass;
    }
}
