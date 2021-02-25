package com.jimmy.kotlinstudy.javaBean

/**
 * @Description:
 * @Author:         纯仔
 * @CreateDate:     2020-09-24
 * @Version:        1.0
 */
class Tree(var treeName: String) {
    //在类内部在定义一个类称为嵌套类
    class Flower(var flowerName: String) {
        fun getName(): String {
            return "这是一朵$flowerName"
        }
    }
}