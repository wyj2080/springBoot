package com.test.springBoot.transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * @Description: 事物：Application里加上@EnableTransactionManagement
 * @Author: wangyinjia
 * @Date: 2020/3/19
 * @Version: 1.0
 */
@Controller
@RequestMapping("/transactional")
@Transactional(readOnly=true)
public class TransactionalController {

    @Autowired
    private TransactionalService transactionalService;

    @RequestMapping(value = "/test", method = RequestMethod.GET)
    @Transactional(rollbackFor = Exception.class)
    public void test(){
//        List<Integer> list = Arrays.asList(1,2,3,4,5,6,7,8,9,0);
//        list.parallelStream().forEach(a -> {
            transactionalService.testA();
//        });
    }

    /**
     * 无事物
     */
    @RequestMapping(value = "/never", method = RequestMethod.GET)
    @Transactional(propagation = Propagation.NEVER)
    public void never(){
//        List<Integer> list = Arrays.asList(1,2,3,4,5,6,7,8,9,0);
//        list.parallelStream().forEach(a -> {
        transactionalService.testA();
//        });
    }

}
