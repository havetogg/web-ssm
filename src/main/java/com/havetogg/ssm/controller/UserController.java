package com.havetogg.ssm.controller;

import com.havetogg.ssm.model.User;
import com.havetogg.ssm.result.PageResult;
import com.havetogg.ssm.service.UserService;
import com.havetogg.ssm.utils.CaptchaUtil;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.support.SessionStatus;

import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;


@Controller
@RequestMapping("/user")
@SessionAttributes({"user","errorTimes","randomString"})
public class UserController {
    @ModelAttribute("errorTimes")
    public int getError(){
        return 0;
    }

    private Logger log = Logger.getLogger(UserController.class);
    @Autowired
    private UserService userService;

    @RequestMapping(value = "/login")
    public String login(HttpServletRequest request,@RequestParam(required = false) String name, @RequestParam(required = false) String captcha,@RequestParam(required = false) String pwd,@ModelAttribute("errorTimes") int errorTimes , Model model)throws ServletException, IOException{
        if(model.containsAttribute("user")){
            return "forward:/user/showUser";
        }
        if(errorTimes>=5){
            return "error";
        }
        log.info("用户登录");
        if("".equals(name)||name==null||"".equals(pwd)||pwd==null){
            return "login";
        }
        if(model.containsAttribute("randomString")){
            if(!captchaTest(captcha,(String)request.getSession().getAttribute("randomString"))){
                return "login";
            }
        }
        List<User> userList = userService.getAllUser();
        for(User user:userList){
            if(name.equals(user.getUserName())&&pwd.equals(user.getUserPwd())){
                model.addAttribute("user",name);
                return "forward:/user/showUser";
            }else{
                model.addAttribute("errorTimes", errorTimes+1);
                model.addAttribute("errorMessage", "用户名或密码错误");
                log.info("失败次数"+(errorTimes+1));
            }
        }
        return "login";
    }

    @RequestMapping("/logout")
    public String logout(@ModelAttribute("user") String user, SessionStatus sessionStatus){
        //@ModelAttribute("User")相当于将session中名为"User"的对象注入user对象中
        //sessionStatus中的setComplete方法可以将session中的内容全部清空
        sessionStatus.setComplete();
        return "login";
    }

    //验证码
    @RequestMapping("/captcha")
    @ResponseBody
    public void captcha(HttpServletRequest request, HttpServletResponse response,Model model) throws ServletException, IOException {
        model.addAttribute("randomString",CaptchaUtil.outputCaptcha(request, response));
    }

    //验证码验证
    @RequestMapping("/captchaTest")
    @ResponseBody
    public boolean captchaTest(@RequestParam(required = false) String captcha,@ModelAttribute("randomString") String randomString)
    {
        log.info(captcha);
        log.info(randomString);
        if(captcha.equals(randomString)){
            return true;
        }
        return false;
    }

    @RequestMapping("/showUser")
    public String showUser(Model model){
        log.info("查询所有用户信息");
        List<User> userList = userService.getAllUser();
        model.addAttribute("userList",userList);
        return "showUser";
    }

    @RequestMapping("/showPage")
    public String showPage(Model model,@RequestParam(required = false) Integer pageNum,@RequestParam(required = false) Integer pageSize){
        log.info("查询分页信息");
        if(pageNum==null){
            pageNum=1;
        }
        if(pageSize==null){
            pageSize=1;
        }
        PageResult<User> pageResult= userService.get(pageNum,pageSize);
        model.addAttribute("pageResult",pageResult);
        return "showPage";
    }


    @RequestMapping(value = "/showUser2")
    public @ResponseBody List<User> showUser2(@RequestParam String name){
        log.info("查询所有用户信息2");
        log.info(name);
        List<User> userList = userService.getAllUser();
        return userList;
    }
}
