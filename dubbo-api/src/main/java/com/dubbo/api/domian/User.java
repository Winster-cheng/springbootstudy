package com.dubbo.api.domian;




import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;

@Getter
@Setter
@ToString
public class User implements Serializable {
    private Integer id;
    private String name;
    private String sex;
    private int age;
    private String address;

    public User(Integer id, String name, String sex, int age, String address) {
        this.id = id;
        this.name = name;
        this.sex = sex;
        this.age = age;
        this.address = address;
    }


}
