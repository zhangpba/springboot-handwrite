package com.study;

import com.handwrite.ZhangpbaSpringApplication;
import com.handwrite.ZhangpbaSpringbootApplication;

/**
 * @author zhangpba
 * @description 测试启动类
 * @date 2023/2/13
 */
@ZhangpbaSpringbootApplication
public class MyApplication {

    public static void main(String[] args) {
        // 扫描 MyApplication 所在路径下的所有包
        ZhangpbaSpringApplication.run(MyApplication.class);
    }
}
