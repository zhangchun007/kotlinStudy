package com.jimmy.kotlinstudy.javaBean

/**
 * @Description:
 * @Author:         纯仔
 * @CreateDate:     2020-09-23
 * @Version:        1.0
 */
class BehaviorSwim : Behavior {
    override fun fly(): String {
        return "看情况，大雁能展翅高飞，企鹅却欲飞还休"
    }

    override fun swim(): String {
        return "怡然戏水"
    }

    override fun run(): String {
        return "赶鸭子上树"
    }

    override var skilledSports: String = "游泳"

}