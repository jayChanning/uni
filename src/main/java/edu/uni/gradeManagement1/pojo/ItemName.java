package edu.uni.gradeManagement1.pojo;

/**
 * @author 陈少鑫
 * create 2019/5/29
 * modified 2019/5/29
 * description TODO 发给前台的数据封装类
 */
public class ItemName {
        private String itemName;
        private boolean isIn;

        ItemName(String itemName, boolean isIn) {
            this.itemName = itemName;
            this.isIn = isIn;
        }

        public String getItemName() {
            return itemName;
        }

        public void setItemName(String itemName) {
            this.itemName = itemName;
        }

        public boolean isIn() {
            return isIn;
        }

        public void setIn(boolean in) {
            isIn = in;
        }

        @Override
        public String toString() {
            return "ItemName{" +
                    "itemName='" + itemName + '\'' +
                    ", isIn=" + isIn +
                    "}\n";
        }

}
