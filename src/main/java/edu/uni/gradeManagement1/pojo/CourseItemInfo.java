package edu.uni.gradeManagement1.pojo;

/**
 * @author 蔡政堂
 * create 2019/5/14
 * modified 2019/5/14
 * description TODO 显示给前台显示的数据
 */
public class CourseItemInfo {

    /**
     * 封装需要显示的POJO
     */
    private String semester;
    private String courseId;
    private String courseName;
    private String courseClass;

    /**
     * 有参constructor
     * @param semester
     * @param courseId
     * @param courseName
     * @param courseClass
     */
    public CourseItemInfo(String semester, String courseId, String courseName, String courseClass) {
        this.semester = semester;
        this.courseId = courseId;
        this.courseName = courseName;
        this.courseClass = courseClass;
    }

    /**
     * getter and setter方法
     * @return
     */
    public String getSemester() {
        return semester;
    }

    public void setSemester(String semester) {
        this.semester = semester;
    }

    public String getCourseId() {
        return courseId;
    }

    public void setCourseId(String courseId) {
        this.courseId = courseId;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public String getCourseClass() {
        return courseClass;
    }

    public void setCourseClass(String courseClass) {
        this.courseClass = courseClass;
    }

    @Override
    public String toString() {
        return "CourseItemInfo{" +
                "semester='" + semester + '\'' +
                ", courseId='" + courseId + '\'' +
                ", courseName='" + courseName + '\'' +
                ", courseClass='" + courseClass + '\'' +
                '}';
    }
}
