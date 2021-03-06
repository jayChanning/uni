package edu.uni.gradeManagement1.pojo;

/**
 * @author 蔡政堂
 * create 2019/5/14
 * modified 2019/6/10
 * description TODO 显示给前台显示的数据
 */
public class ItemGradeInfo {

    /**
     * 封装需要显示的POJO
     */
    private String content;
    private String score;
    private String note;
    private String uploadURL;

    /**
     * 有参constructor
     * @param content 内容说明
     * @param score 成绩
     * @param note 评语
     * @param uploadURL 上传路径
     */
    public ItemGradeInfo(String content, String score, String note, String uploadURL) {
        this.content = content;
        this.score = score;
        this.note = note;
        this.uploadURL = uploadURL;
    }

    /**
     * getter and setter方法
     * @return
     */
    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getScore() {
        return score;
    }

    public void setScore(String score) {
        this.score = score;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public String getUploadURL() {
        return uploadURL;
    }

    public void setUploadURL(String uploadURL) {
        this.uploadURL = uploadURL;
    }

    /**
     * toString()方法，便于输出查看数据
     * @return String data
     */
    @Override
    public String toString() {
        return "ItemGradeInfo{" +
                "content='" + content + '\'' +
                ", score='" + score + '\'' +
                ", note='" + note + '\'' +
                ", uploadURL='" + uploadURL + '\'' +
                '}';
    }
}
