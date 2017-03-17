package com.havetogg.ssm.controller;

import com.havetogg.ssm.model.User;
import com.havetogg.ssm.result.CommonResult;
import com.havetogg.ssm.utils.ShaUtil;
import com.havetogg.ssm.utils.StringUtil;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.ui.Model;

import java.util.Arrays;

/**
 * Created by admin on 2017/3/17.
 */
@Controller
@RequestMapping("/encryption")
@SessionAttributes("user")
public class EncryptionController {

    private Logger log = Logger.getLogger(EncryptionController.class);
    //获取加密网址
    @RequestMapping()
    public String encryption(@ModelAttribute("user") User user, Model model){
        System.out.println(user);
        return "encryption";
    }

    @RequestMapping("/getAssessToken")
    @ResponseBody
    public CommonResult getAssessToken(@ModelAttribute("user") User user, Model model, @RequestParam("uId") String uid,@RequestParam("randomString") String randomString,@RequestParam("sha") String sha){
        CommonResult<String> commonResult = new CommonResult<>();
        if(!String.valueOf(user.getId()).equals(uid)){
            commonResult.setType(0);
            commonResult.setMessage("用户名不匹配请重新登录！");
            return commonResult;
        }
        String[] strings = new String[2];
        strings[0] = uid;
        strings[1] = randomString;
        Arrays.sort(strings);
        String sortString = strings[0]+strings[1];
        String shaString = ShaUtil.getSha1(sortString);
        if(sha.equals(shaString)){
            String assessToken = StringUtil.getRandomString(8);

        }
        return null;
    }

}
