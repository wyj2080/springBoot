package com.test.springBoot.annotation;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang3.time.DateUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.Valid;
import java.util.Date;

/**
 * @Description: 各种注解
 * @Author: wangyinjia
 * @Date: 2019/12/19
 * @Version: 1.0
 */
@Controller
@RequestMapping("/annotation")
public class AnnotationController {


    @RequestMapping(value = "/valid", method = RequestMethod.GET)
    public void validTest(@RequestBody @Valid ValidDO validDO){
        System.out.println(validDO);
        System.out.println(validDO.toString());

    }

}
