package com.test.springBoot.liteFlow;

import com.yomahub.liteflow.entity.data.AbsSlot;
import lombok.Data;

/**
 * @author wangyinjia
 * @description
 * @date 2021/11/9
 */
@Data
public class MySlot extends AbsSlot {
    private Integer age;
    private String name;
}
