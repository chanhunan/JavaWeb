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
     * 1. ʹ��RequestMappingע����ӳ�������URL
     * 2. ����ֵ��ͨ����ͼ����������Ϊʵ�ʵ�������ͼ, ����InternalResourceViewResolver��ͼ���������������½���
     * ͨ��prefix+returnVal+suffix �����ķ�ʽ�õ�ʵ�ʵ�������ͼ��Ȼ���ת������
     * "/WEB-INF/views/success.jsp"
     * @return
     */
    @RequestMapping("/helloworld")
    public String hello(){
        System.out.println("hello world");
        return "success";
    }
    
    /**
     * ʹ��InitBinder������Date���͵Ĳ���
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
     * ��ǰ̨���ݲ���
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
         * ��ǰ̨ҳ�洫ֵ
         * 
         */
        /**
         * ����1��ʹ��ModelAndView
         * @return
         * https://www.cnblogs.com/caoyc/p/5635782.html
         */
        @RequestMapping("/showPerson")
        public ModelAndView person(){
        	ModelAndView mav=new ModelAndView("person");//personΪjspҳ����
        	mav.addObject("time",new Date());
        	mav.getModel().put("name", "liyang");
        	
        	return mav;
        } 
         /***
          * ����2��ʹ��Map��Model��ModelMap����ǰ̨ҳ�洴��,ʹ�ú���3�ַ�ʽ�������ڷ��������У�ָ��һ�������͵Ĳ�����
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