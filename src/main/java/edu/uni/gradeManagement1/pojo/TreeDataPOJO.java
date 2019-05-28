package edu.uni.gradeManagement1.pojo;

/**
 * @author 蔡政堂
 * create 2019/5/11
 * modified 2019/5/12
 * description TODO
 */
public class TreeDataPOJO {
    private String name;
    private boolean value;

    public TreeDataPOJO(String name, boolean value) {
        this.name = name;
        this.value = value;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isValue() {
        return value;
    }

    public void setValue(boolean value) {
        this.value = value;
    }
}
