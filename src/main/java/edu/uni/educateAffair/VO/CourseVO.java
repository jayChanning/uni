package edu.uni.educateAffair.VO;

/**
 * @Author:梁俊杰
 * @Description:
 * @Date:Created in 3:02 2019/6/4
 */
public class CourseVO {
    private Long id;
    private String name;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "CourseVO{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
