package com.springmvc.controller;

import java.lang.reflect.Array;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.Map;

import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.springmvc.bean.Person;

@Controller
public class HelloWorld {

    /**
     * 1. 使用RequestMapping注解来映射请求的URL
     * 2. 返回值会通过视图解析器解析为实际的物理视图, 对于InternalResourceViewResolver视图解析器，会做如下解析
     * 通过prefix+returnVal+suffix 这样的方式得到实际的物理视图，然后会转发操作
     * "/WEB-INF/views/success.jsp"
     * @return
     */
    @RequestMapping("/helloworld")
    public String hello(){
        System.out.println("hello world");
        return "success";
    }
    
    /**
     * 使用InitBinder来处理Date类型的参数
     * @param date
     * @return
     */
  //the parameter was converted in initBinder
    @RequestMapping("/date")
    public String date(Date date){
        System.out.println(date);
        return "hello";
    }
  //At the time of initialization,convert the type "String" to type "date"
    @InitBinder
    public void initBinder(ServletRequestDataBinder binder){
        binder.registerCustomEditor(Date.class, new CustomDateEditor(new SimpleDateFormat("yyyy-MM-dd"),
                true));
    }
    
    
    
  //boxing automatically
    @RequestMapping("/person")
    public String toPerson(Person p){
        System.out.println(p.getName()+" "+p.getAge());
        return "person";
    }
    
    /**
     * 向前台传递参数
     * @param map
     * @return
     */
        //pass the parameters to front-end
        @RequestMapping("/show")
        public String showPerson(Map<String,Object> map){
            Person p =new Person();
            map.put("p", p);
            p.setAge(20);
            p.setName("jayjay");
            return "show";
        }
        
        /***
         * 向前台页面传值
         * 
         */
        /**
         * 方法1：使用ModelAndView
         * @return
         * https://www.cnblogs.com/caoyc/p/5635782.html
         */
        @RequestMapping("/showPerson")
        public ModelAndView person(){
        	ModelAndView mav=new ModelAndView("person");//person为jsp页面名
        	mav.addObject("time",new Date());
        	mav.getModel().put("name", "liyang");
        	
        	return mav;
        } 
         /***
          * 方法2：使用Map、Model和ModelMap来向前台页面创造,使用后面3种方式，都是在方法参数中，指定一个该类型的参数。
          * @param map
          * @param model
          * @param modelMap
          * @return
          * https://www.cnblogs.com/caoyc/p/5635878.html
          */
        @RequestMapping("person1")
        public String person1(Map<String, Object> map,Model model, ModelMap modelMap){
        	map.put("name", Arrays.asList("li","han","chen"));
        	model.addAttribute("date", new Date());
        	modelMap.addAttribute("city","Chengdu");
        	modelMap.put("Gender", "male");
        	
        	return "person1";
        }
}