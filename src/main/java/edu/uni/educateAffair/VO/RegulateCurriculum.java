package edu.uni.educateAffair.VO;

/**
 * @Author:梁俊杰
 * @Description:
 * @Date:Created in 11:07 2019/6/3
 */
public class RegulateCurriculum {
    //要补课的时间
    private Long originCanlendar;
    //补哪一天的课
    private Long compensateCanlendar;
    //描述
    private String describe;

    public String getDescribe() {
        return describe;
    }

    public void setDescribe(String describe) {
        this.describe = describe;
    }

    public Long getOriginCanlendar() {
        return originCanlendar;
    }

    public void setOriginCanlendar(Long originCanlendar) {
        this.originCanlendar = originCanlendar;
    }

    public Long getCompensateCanlendar() {
        return compensateCanlendar;
    }

    public void setCompensateCanlendar(Long compensateCanlendar) {
        this.compensateCanlendar = compensateCanlendar;
    }

    @Override
    public String toString() {
        return "RegulateCurriculum{" +
                "originCanlendar=" + originCanlendar +
                ", compensateCanlendar=" + compensateCanlendar +
                ", describe='" + describe + '\'' +
                '}';
    }
}
