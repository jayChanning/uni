package edu.uni.gradeManagement1.pojo;

/**
 * @author 蔡政堂
 * create 2019/6/10
 * modified 2019/6/10
 * description TODO 此pojo类用于接收前台传来的成绩项明细对象
 */
public class InsertGradeDetail {
    /* 封装数据 */
    private String content;  //内容描述
    private long courseId;  //课程id
    private long itemGradeId;  //对应stu_item_grade表的id
    private long itemDetailId;  //对应course_item_grade_detail表的id
    private long stuId;    //当前学生的id,对应student表id
    private double score;  //学生成绩分数(百分制)
    private String note;  //教师评语
    private String uploadAddr;  //上传文件的路径
    private String itemDetail;  //作业项名称,eg. 作业#1,作业#2..

    public InsertGradeDetail() {
    }

    /**
     * 有参构造
     * @param content 内容描述
     * @param courseId 课程id
     * @param itemGradeId 对应stu_item_grade表的id
     * @param itemDetailId 对应course_item_grade_detail表的id
     * @param stuId 当前学生的id,对应student表id
     * @param score 学生成绩分数(百分制)
     * @param note 教师评语
     * @param uploadAddr 上传文件的路径
     * @param itemDetail 作业项名称,eg. 作业#1,作业#2..
     */

    public InsertGradeDetail(String content, long courseId, long itemGradeId, long itemDetailId, long stuId, double score, String note, String uploadAddr, String itemDetail) {
        this.content = content;
        this.courseId = courseId;
        this.itemGradeId = itemGradeId;
        this.itemDetailId = itemDetailId;
        this.stuId = stuId;
        this.score = score;
        this.note = note;
        this.uploadAddr = uploadAddr;
        this.itemDetail = itemDetail;
    }

    /**
     * Getter和Setter方法
     */
    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public long getCourseId() {
        return courseId;
    }

    public void setCourseId(long courseId) {
        this.courseId = courseId;
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

    public long getStuId() {
        return stuId;
    }

    public void setStuId(long stuId) {
        this.stuId = stuId;
    }

    public double getScore() {
        return score;
    }

    public void setScore(double score) {
        this.score = score;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public String getUploadAddr() {
        return uploadAddr;
    }

    public void setUploadAddr(String uploadAddr) {
        this.uploadAddr = uploadAddr;
    }

    public String getItemDetail() {
        return itemDetail;
    }

    public void setItemDetail(String itemDetail) {
        this.itemDetail = itemDetail;
    }

    /**
     * toString()方法
     */
    @Override
    public String toString() {
        return "InsertGradeDetail{" +
                "content='" + content + '\'' +
                ", courseId=" + courseId +
                ", itemGradeId=" + itemGradeId +
                ", itemDetailId=" + itemDetailId +
                ", stuId=" + stuId +
                ", score=" + score +
                ", note='" + note + '\'' +
                ", uploadAddr='" + uploadAddr + '\'' +
                ", itemDetail='" + itemDetail + '\'' +
                '}';
    }
}
