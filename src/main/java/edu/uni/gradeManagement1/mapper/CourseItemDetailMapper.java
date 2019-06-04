package edu.uni.gradeManagement1.mapper;

import edu.uni.gradeManagement1.bean.CourseItemDetail;
import edu.uni.gradeManagement1.bean.CourseItemDetailExample;

import java.util.HashMap;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import static com.alibaba.druid.sql.parser.Token.INNER;

public interface CourseItemDetailMapper {

    //下面的id是stu_grade_main表的id
    @Select("SELECT course_item_one.`name`,course_item_one.count,course_item_detail_one.number FROM stu_grade_main_one,stu_item_grade_one,course_item_one,course_item_detail_one WHERE course_item_one.id = stu_item_grade_one.course_item_id AND stu_item_grade_one.stu_grade_main_id = stu_grade_main_one.id AND course_item_one.id = course_item_detail_one.course_item_id AND stu_grade_main_id = ${id} ORDER BY course_item_one.`name`,course_item_detail_one.number;")
    List<HashMap> choose(@Param(value = "id") long id);

    int countByExample(CourseItemDetailExample example);

    int deleteByExample(CourseItemDetailExample example);

    int deleteByPrimaryKey(Long id);

    int insert(CourseItemDetail record);

    int insertSelective(CourseItemDetail record);

    List<CourseItemDetail> selectByExample(CourseItemDetailExample example);

    CourseItemDetail selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") CourseItemDetail record, @Param("example") CourseItemDetailExample example);

    int updateByExample(@Param("record") CourseItemDetail record, @Param("example") CourseItemDetailExample example);

    int updateByPrimaryKeySelective(CourseItemDetail record);

    int updateByPrimaryKey(CourseItemDetail record);

    //    父页面
    //    默认当前学期 current Semester
    @Select("SELECT se.`name` AS semester, course.id AS courseId, course.`name` AS courseName,\tclass.`name` AS courseClass,course_species.`name` AS courseType,course_category.`name` AS courseCategory, course.`hour` AS classHour, " +
            "course.credit AS credit, class.id AS classId, `user`.id AS '无用字段，请删，如果你需要添加自己的需要的字段参见上面的写法，有些字段可能在其他表里，参见下面的写法，使用inner join,参数为《user.id》,mapper参见之前树状写法'\n" +
            " FROM\n" +
            " ((((((ea_semester AS se INNER JOIN ea_teaching_task AS task ON se.id = task.semester_id AND NOW() BETWEEN se.`start` AND se.`end` ) INNER JOIN `user` ON task.worker_id = `user`.id AND `user`.id = ${id} )  INNER JOIN course ON task.course_id = course.id)INNER JOIN class ON task.class_id=class.id) INNER JOIN course_species ON course.species_id = course_species.id)INNER JOIN course_category ON course.category_id = course_category.id)")
    List<HashMap> currSemester(@Param(value = "id") Long id);  //user.id 暂使用1941

    //    选择特定的学期 根据学期名称参数为 <semester>
    @Select("SELECT se.`name` AS semester, course.id AS courseId, course.`name` AS courseName,\tclass.`name` AS courseClass,course_species.`name` AS courseType,course_category.`name` AS courseCategory, course.`hour` AS classHour, course.credit AS credit, class.id AS classId, `user`.id AS '无用字段，请删，如果你需要添加自己的需要的字段参见上面的写法，有些字段可能在其他表里，参见下面的写法，使用inner join,参数为《user.id》《se.`name`》,mapper参见之前树状写法'\n" +
            " FROM\n" +
            " ((((((ea_semester AS se INNER JOIN ea_teaching_task AS task ON se.id = task.semester_id) INNER JOIN `user` ON task.worker_id = `user`.id AND `user`.id = 1941 )  INNER JOIN course ON task.course_id = course.id)INNER JOIN class ON task.class_id=class.id) INNER JOIN course_species ON course.species_id = course_species.id)INNER JOIN course_category ON course.category_id = course_category.id) WHERE se.`name` = '2018-2019第二学期'")
    List<HashMap> selectSemester(@Param(value = "semester") String semester);

    //    选择特定的班级 根据班级名称 <courseClass>
    @Select("SELECT se.`name` AS semester, course.id AS courseId, course.`name` AS courseName,\tclass.`name` AS courseClass,course_species.`name` AS courseType,course_category.`name` AS courseCategory, course.`hour` AS classHour, course.credit AS credit, class.id AS classId, `user`.id AS '无用字段，请删，如果你需要添加自己的需要的字段参见上面的写法，有些字段可能在其他表里，参见下面的写法，使用inner join,参数为《user.id》《class.`name`》,mapper参见之前树状写法'\n" +
            " FROM\n" +
            " ((((((ea_semester AS se INNER JOIN ea_teaching_task AS task ON se.id = task.semester_id) INNER JOIN `user` ON task.worker_id = `user`.id AND `user`.id = 1941 )  INNER JOIN course ON task.course_id = course.id)INNER JOIN class ON task.class_id=class.id) INNER JOIN course_species ON course.species_id = course_species.id)INNER JOIN course_category ON course.category_id = course_category.id) WHERE class.`name` = '16网络'")
    List<HashMap> selectClass(@Param(value = "courseClass") String courseClass);

    //    选择特定学期的特定班级 根据学期名称和班级名称
    @Select("SELECT se.`name` AS semester, course.id AS courseId, course.`name` AS courseName,\tclass.`name` AS courseClass,course_species.`name` AS courseType,course_category.`name` AS courseCategory, course.`hour` AS classHour, course.credit AS credit, class.id AS classId, `user`.id AS '无用字段，请删，如果你需要添加自己的需要的字段参见上面的写法，有些字段可能在其他表里，参见下面的写法，使用inner join,参数为《user.id》《se.`name`》《class.`name`》,mapper参见之前树状写法'\n" +
            " FROM\n" +
            " ((((((ea_semester AS se INNER JOIN ea_teaching_task AS task ON se.id = task.semester_id) " +
            "INNER JOIN `user` ON task.worker_id = `user`.id AND `user`.id = 1941 )  INNER JOIN course ON task.course_id = course.id)INNER JOIN class ON task.class_id=class.id) " +
            "INNER JOIN course_species ON course.species_id = course_species.id)INNER JOIN course_category ON course.category_id = course_category.id) WHERE class.`name` = '16网络' AND se.`name` = '2018-2019第二学期'")
    List<HashMap> selectSemesterANDClass(@Param(value = "semester, courseClass") String semester, String courseClass);

    //    子页面
    //    选择某个班级的学生记录
    @Select("SELECT \n" +
            " student.stu_no AS stuNo,`user`.user_name AS stuName, monentClass.`name` AS stuClass, ecomm.content AS contact ,`user`.id " +
            "AS '无用字段，请删，如果你需要添加自己的需要的字段参见上面的写法，有些字段可能在其他表里，参见下面的写法，使用inner join,参数为《class.id》,mapper参见之前树状写法'\n" +
            " FROM ((((classmate INNER JOIN student ON classmate.student_id = student.id  AND classmate.class_id = 26) INNER JOIN `user` ON `user`.id = student.user_id) " +
            "INNER JOIN class AS monentClass ON student.class_id = monentClass.id)LEFT JOIN ecomm ON student.phone_ecomm_id = ecomm.id) ORDER BY monentClass.`name`,student.stu_no")
    List<HashMap> selectClassStu(@Param(value = "stuNo") String stuNo);


}