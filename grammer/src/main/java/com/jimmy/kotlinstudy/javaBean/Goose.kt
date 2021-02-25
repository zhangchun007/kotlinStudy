package com.jimmy.kotlinstudy.javaBean

/**
 * @Description:
 * @Author:         纯仔
 * @CreateDate:     2020-09-22
 * @Version:        1.0
 */
class Goose(name: String = "鹅", sex: Int = Bird.MALE) : Bird(name, sex), Behavior {
    override fun fly(): String {
        return "鹅能飞一点点，但飞不高,也飞不远"
    }

    override fun swim(): String {
        return "鹅鹅鹅,曲项向天歌。白毛浮绿水，红掌拨清波"
    }

    override fun run(): String {
        return super.run()
    }

    override var skilledSports: String = "游泳"
}