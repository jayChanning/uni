package edu.uni.educateAffair.VO;

/**
 * @Author:梁俊杰
 * @Description:
 * @Date:Created in 2:28 2019/6/4
 */
public class ClassVO {
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
        return "ClassVO{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
