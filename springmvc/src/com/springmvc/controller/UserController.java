package com.springmvc.controller;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.springmvc.bean.User;
import com.springmvc.dao.UserDao;

@Controller
@RequestMapping("/user")

public class UserController {
//	@RequestMapping(value="/add",method=RequestMethod.POST)
//	public String add(@Valid ){
//		
//	}
	
	@RequestMapping("/login")
	public ModelAndView input(){
		System.out.println("Show the login JSP, and input the user's name and secrect!");
        ModelAndView mav = new ModelAndView("input");  
        return mav;
	}
	
	@RequestMapping("/output")
    public ModelAndView login1(){ 
        System.out.println("login1() input");
        ModelAndView mav = new ModelAndView("login");  
        return mav;
    }
	
	//���ڽ��ղ��������ҷ��ص�outputҳ��
    @RequestMapping("/input")
    public ModelAndView login2( String yonghu, String mima){ 
        System.out.println("Receive the data from form!!");
        
        /**
         * �����ݲ��뵽���ݿ��У��������ݿ����
         */
        ApplicationContext context = new ClassPathXmlApplicationContext("springmvc.xml");
        UserDao userDao = (UserDao) context.getBean("userDao");
        User user=new User();
        user.setId(0);
        user.setName(yonghu);
        user.setPassword(mima);
        System.out.println("usercontroller");
        boolean result=userDao.addUser(user);
        
        System.out.println(result+":result");
        ModelAndView mav = new ModelAndView("output"); //��output.jsp��ʾ���յ����� 
        mav.addObject("yonghu",yonghu);
        mav.addObject("mima",mima);
        if (result)
        System.out.println(yonghu +":"+mima+":"+"SUCCESS");
        else {
        	System.out.println(yonghu +":"+mima+":"+"ERROR");
		}

        return mav;
    }  
}
