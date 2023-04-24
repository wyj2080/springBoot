package com.test.springBoot.annotation;

import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.validation.Valid;
import java.util.List;

/**
 * @Description: 各种注解
 * @Author: wangyinjia
 * @Date: 2019/12/19
 * @Version: 1.0
 */
@Controller
@RequestMapping("/annotation")
@Validated
public class AnnotationController {


    @RequestMapping(value = "/valid", method = RequestMethod.GET)
    public void validTest(@RequestBody @Valid ValidDO validDO){
        System.out.println(validDO);
        System.out.println(validDO.toString());

    }

    /**
     * 验证list时，controller类上@Validated
     */
    @RequestMapping(value = "/validList", method = RequestMethod.GET)
    public void validList(@RequestBody @Valid List<ValidDO> list){
        System.out.println(list);
        System.out.println(list.toString());

    }
}
