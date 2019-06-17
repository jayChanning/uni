package edu.uni.gradeManagement1.pojo;

import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.metadata.BaseRowModel;

//必须extends BaseRowModel
public class ExcelDemoClass extends BaseRowModel {
    //excel 的列数 从0开始
    //必须有
    @ExcelProperty(index = 0)
    private String name;
    //必须有
    @ExcelProperty(index = 1)
    private int suse;
    //必须有
    public String getName() {
        return name;
    }
    //必须有
    public void setName(String name) {
        this.name = name;
    }
    //必须有
    public int getSuse() {
        return suse;
    }
    //必须有
    public void setSuse(int suse) {
        this.suse = suse;
    }
    //必须有无参构造器
    public ExcelDemoClass() {
    }

    @Override
    public String toString() {
        return "ExcelDemoClass{" +
                "name='" + name + '\'' +
                ", suse=" + suse +
                '}';
    }
}

