package com.jimmy.kotlinstudy.javaBean

/**
 * @Description:
 * @Author:         纯仔
 * @CreateDate:     2020-09-23
 * @Version:        1.0
 */
class WildFowl(name: String, sex: Int = MALE, behavior: Behavior) : Bird(name, sex),Behavior by behavior{
}