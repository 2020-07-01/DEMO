package com.mybatis.bean;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * @ClassName : User
 * @Author : yq
 * @Date: 2020-11-15
 * @Description :
 */

@AllArgsConstructor
@Data
public class User {

    private Integer id;

    private String name;

    private Integer age;
}
