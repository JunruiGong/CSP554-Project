package com.imooc.dao;

import com.imooc.domain.CourseClickCount;
import com.imooc.utils.HBaseUtils;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Database
 */
@Component
public class CourseClickCountDAO {


    /**
     * Select click count with day
     */
    public List<CourseClickCount> query(String day) throws Exception {

        List<CourseClickCount> list = new ArrayList<>();

        // Get page views from HBase
        Map<String, Long> map = HBaseUtils.getInstance().query("imooc_course_clickcount", "20171022");

        for (Map.Entry<String, Long> entry : map.entrySet()) {
            CourseClickCount model = new CourseClickCount();
            model.setName(entry.getKey());
            model.setValue(entry.getValue());

            list.add(model);
        }

        return list;
    }

    public static void main(String[] args) throws Exception {
        CourseClickCountDAO dao = new CourseClickCountDAO();
        List<CourseClickCount> list = dao.query("20171022");
        for (CourseClickCount model : list) {
            System.out.println(model.getName() + " : " + model.getValue());
        }
    }

}
