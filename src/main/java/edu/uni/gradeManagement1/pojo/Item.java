package edu.uni.gradeManagement1.pojo;


import java.util.ArrayList;

/**
 * @author 陈少鑫
 * date 19:19 2019-05-12
 * modified 19:19 2019-05-29
 * description TODO 显示给前台显示的数据
*/
public class Item {

    private String name;  //组成项名称
    private int cout;   //组成项次数
    private long itemGradeId;  //对应stu_item_grade表的id
    private long itemDetailId;   //对应course_item_detail表的id
   // private LinkedHashMap<String,Object> linkedMap;
    private ArrayList<ItemName> itemNames;

    public Item(int name, int cout, long itemGradeId, long itemDetailId) {
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
        this.itemDetailId = itemDetailId;
        this.itemGradeId = itemGradeId;
        itemNames = new ArrayList<>(cout+1);
        //linkedMap = new LinkedHashMap<>(cout);
        for (int i = 1; i <= cout; i++) {   //默认所有都已经未录入
           // linkedMap.put( this.name+i, new ItemName(this.name+i, false));
            itemNames.add(new ItemName(this.name + i, false));
        }
    }

    public long getItemGradeId() {
        return itemGradeId;
    }

    public void setItemGradeId(long itemGradeId) {
        this.itemGradeId = itemGradeId;
    }

    public long getItemDetailId() {
        return itemDetailId;
    }

    public void setItemDetailId(long itemDetailId) {
        this.itemDetailId = itemDetailId;
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

    public void setId(int i, long ItemGradeId,long ItemDetailId){
        itemNames.get(i-1).setItemGradeId(ItemGradeId);
        itemNames.get(i-1).setItemDetailId(ItemDetailId);
    }

    @Override
    public String toString() {
        return "Item{" +
                "name='" + name + '\'' +
                ", cout=" + cout +
                ", itemGradeId=" + itemGradeId +
                ", itemDetailId=" + itemDetailId +
                ", itemNames=" + itemNames +
                '}';
    }
}
