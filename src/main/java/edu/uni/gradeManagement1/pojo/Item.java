package edu.uni.gradeManagement1.pojo;


import java.util.ArrayList;

/**
* @author 陈少鑫
* @date 19:19 2019-05-12
* @modified 19:19 2019-05-29
 * description TODO 显示给前台显示的数据
*/
public class Item {

    private String name;  //组成项名称
    private int cout;   //组成项次数
   // private LinkedHashMap<String,Object> linkedMap;
    private ArrayList<ItemName> itemNames;

    public Item(int name, int cout) {
        String ss;
        // System.out.println(item);
        switch (name){
            case 1:
                ss = "作业 #";
                break;
            case 2:
                ss = "考勤 #";
                break;
            case 3:
                ss = "期中大题 #";
                break;
            case 4:
                ss = "实验 #";
                break;
            case 5:
                ss = "期末大题 #";
                break;
            default:
                ss = "其他 #";
                break;
        }
        this.name = ss;
        this.cout = cout;
        itemNames = new ArrayList<>(cout+1);
        //linkedMap = new LinkedHashMap<>(cout);
        for (int i = 1; i <= cout; i++) {
           // linkedMap.put( this.name+i, new ItemName(this.name+i, false));
            itemNames.add(new ItemName(this.name+i, false));
        }
    }

    public int getCout() {
        return cout;
    }

    public void setCout(int cout) {
        this.cout = cout;
    }

    public void setItemName(int i){
       // linkedMap.put( name+i, new ItemName(name+i, true));
        itemNames.get(i-1).setIn(true);
    }
    public String getName() {
        return name;
    }

    public void setName(int i) {
        String ss;
        // System.out.println(item);
        switch (i){
            case 1:
                ss = "作业 #";
                break;
            case 2:
                ss = "考勤 #";
                break;
            case 3:
                ss = "期中大题 #";
                break;
            case 4:
                ss = "实验 #";
                break;
            case 5:
                ss = "期末大题 #";
                break;
            default:
                ss = "其他 #";
                break;
        }
        this.name = ss;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ArrayList<ItemName> getItemNames() {
        return itemNames;
    }

    public void setItemNames(ArrayList<ItemName> itemNames) {
        this.itemNames = itemNames;
    }

    @Override
    public String toString() {
        return "Item{" +
                "name='" + name + '\'' +
                ", cout=" + cout +
              //  ", linkedMap=" + linkedMap +
                ", itemNames=" + itemNames +
                '}';
    }
}
