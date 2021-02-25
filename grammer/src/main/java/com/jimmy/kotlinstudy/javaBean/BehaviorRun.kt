package com.jimmy.kotlinstudy.javaBean

/**
 * @Description:
 * @Author:         纯仔
 * @CreateDate:     2020-09-23
 * @Version:        1.0
 */
class BehaviorRun : Behavior {
    override fun fly(): String {
        return "飞不起来"
    }

    override fun swim(): String {
        return "望洋兴叹"
    }

    override fun run(): String {
        return super.run()
    }

    override var skilledSports: String = "奔跑"

}