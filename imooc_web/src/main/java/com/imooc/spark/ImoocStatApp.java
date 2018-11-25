package com.imooc.spark;

import com.imooc.dao.CourseClickCountDAO;
import com.imooc.domain.CourseClickCount;
import net.sf.json.JSONArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * web
 */
@RestController
public class ImoocStatApp {

    private static Map<String, String> courses = new HashMap<>();
    static {
        courses.put("112","Spark SQL Project");
        courses.put("142","Spark Streaming");
        courses.put("156","Introduction of Hadoop");
        courses.put("114","Interview of Big Data");
        courses.put("178","Storm Project");

    }

    @Autowired
    CourseClickCountDAO courseClickCountDAO;


//    @RequestMapping(value = "/course_clickcount_dynamic", method = RequestMethod.GET)
//    public ModelAndView courseClickCount() throws Exception {
//
//        ModelAndView view = new ModelAndView("index");
//
//        List<CourseClickCount> list = courseClickCountDAO.query("20171022");
//        for(CourseClickCount model : list) {
//            model.setName(courses.get(model.getName().substring(9)));
//        }
//        JSONArray json = JSONArray.fromObject(list);
//
//        view.addObject("data_json", json);
//
//        return view;
//    }

    @RequestMapping(value = "/course_clickcount_dynamic", method = RequestMethod.POST)
    @ResponseBody
    public List<CourseClickCount> courseClickCount() throws Exception {

        List<CourseClickCount> list = courseClickCountDAO.query("20171022");
        for(CourseClickCount model : list) {
            model.setName(courses.get(model.getName().substring(9)));
        }

        return list;
    }

    @RequestMapping(value = "/echarts", method = RequestMethod.GET)
    public ModelAndView echarts(){
        return new ModelAndView("echarts");
    }



}
