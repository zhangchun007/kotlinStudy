package com.jimmy.kotlinstudy.javaBean

/**
 * @Description:
 * @Author:         纯仔
 * @CreateDate:     2020-09-23
 * @Version:        1.0
 */
class BehaviorFly : Behavior {
    override fun fly(): String {
        return "翱翔天空"
    }

    override fun swim(): String {
        return "落水凤凰不如鸡"
    }

    override fun run(): String {
        return "能飞干嘛还要走"
    }

    override var skilledSports: String = "飞翔"

}