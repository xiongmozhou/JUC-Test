package com.atguigu.juc.day02;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;


@NoArgsConstructor
@AllArgsConstructor
@Data
@Accessors(chain = true)
public class Person {
    private String name = "老王";
    private int age = 12;
}
