package com.jimmy.kotlinstudy

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.jimmy.kotlinstudy.javaBean.*
import kotlinx.android.synthetic.main.activity_five.*

/**
 * 类和对象
 */
class FiveActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_five)
        //5.1类的构造
        //5.1.1类的简单定义 (:替代extend关键字，默认是public,AppCompatActivity()带括号)
        //class FiveActivity : AppCompatActivity() {}
        btn_class1.setOnClickListener {
            //因为等号后面的构造函数已经明确知道这个是animal的实例
            //var animal:Animal=Animal()
            var animal = Animal()
        }

        //5.1.2类的构造函数
        /**
         * 二级构造函数:
         * # 二级构造函数没有函数名称
         * # "this(context,name)相当于Java中的super(context,name)
         * # 二级构造函数从属于主构造函数，使用二级构造函数声明该类的实例，则优先调用祝构造函数，再调用二级构造函数
         */

        var count = 0
        btn_class2.setOnClickListener {
            when (count % 2) {
                0 -> {
                    var animal = AnimalMain2(this, "鸭子")
                }
                else -> {
                    var anmal = AnimalMain2(this, "鸭子", 0)
                }
            }
            count++
        }

        //未解决：执行二级构造函数代码都要走主构造函数的的方法，kotlin中允许没有构造函数，可以有多个二级构造函数
        btn_class3.setOnClickListener {
            when (count % 2) {
                0 -> {
                    var animal = AnimalMain3(this, "鸭子")
                }
                else -> {
                    var anmal = AnimalMain3(this, "鸭子", 0)
                }
            }
            count++
        }


        //5.1.3带默认参数的构造函数
        btn_class4.setOnClickListener {
            when (count % 2) {
                0 -> {
                    var animal = AnimalMain4(this, "鸭子")
                }
                else -> {
                    var animal = AnimalMain4(this, "鸭子", 0)
                }
            }
            count++
        }


        //5.2 类的成员（构造函数里面加变量）
        btn_class6.setOnClickListener {
            var animal = when (count % 2) {
                0 -> AnimalMain6("鸭子")
                else -> AnimalMain6("鸭子", 1)
            }
            btn_class6.text = "这只${animal.name}是${if (animal.sex == 0) "公" else "母"}的"
        }

        //类中定义了别的变量得自己声明
        btn_class7.setOnClickListener {
            var animal = when (count % 2) {
                0 -> AnimalMain7("鸭子")
                else -> AnimalMain7("鸭子", 1)
            }
            btn_class7.text = "这只${animal.name}是${animal.sexName}的"

        }


        //5.2.2 成员方法
        btn_class8.setOnClickListener {
            var animal = when (count % 2) {
                0 -> AnimalMain8("鸭子")
                else -> AnimalMain8("鸭子", 1)

            }
            btn_class8.text = animal.getDesc("动物园")
        }

        //5.2.3 半生对象（对比Java中静态成员的概念）
        val sexArray: Array<String> = arrayOf("公", "母", "雄", "雌")
        btn_class9.setOnClickListener {
            var sexName: String = sexArray[count++ % 4]
            //伴生对象的AnimalWild名称可以直接省略掉
//            btn_class9.text="$sexName 对应的类型是${AnimalMain9.AnimalWild.judgeSex(sexName)}"
            btn_class9.text = "$sexName 对应的类型是${AnimalMain9.judgeSex(sexName)}"
        }

        //5.2.4 静态属性


        //5.3 类的继承
        //5.3.1开放性修饰符
        //kotlin 默认每个类都不能被继承，如果想让某个类成为基类，就需要把该类开放出来，也就是添加关键字open作为修饰符
        /**
         * kotlin的开放性修饰符的取值说明
         *
         * public：对所有人开放，kotlin的类，函数，变量不加开放性修饰符的话，默认就是public类型
         * internal:只对本模块内部开放，这是kotlin新增的关键字。对于app开发来说，本模块是指app自身
         * protected:只对自己和子类开放
         * private：只对自己开放，即私有
         */

        //5.3.2 普通类的继承
        btn_class10.setOnClickListener {
            var sexBird = if (count++ % 3 == 0) Bird.MALE else Bird.FEMALE
            var duck = Duck(sex = sexBird)
            btn_class10.text = duck.getDesc("鸟语林")
        }
        //重写父类的方法
        btn_class11.setOnClickListener {
            var sexBird = if (count++ % 3 == 0) Bird.MALE else Bird.FEMALE
            var duck = Duck1(sex = sexBird)
            btn_class11.text = duck.getDesc("鸟语林")
        }

        //5.3.3抽象类
        btn_class12.setOnClickListener {
            when (count % 2) {
                0 -> {
                    btn_class12.text = Cock().callOut(count % 10)
                }
                else -> {
                    btn_class12.text = Hen().callOut(count % 10)
                }
            }
            count++
        }

        //5.3.4 接口
        btn_class13.setOnClickListener {
            btn_class13.text = when (count++ % 3) {
                0 -> Goose().fly()
                1 -> Goose().swim()
                else -> Goose().run()
            }
        }

        //5.3.5接口代理
        /**
         * Kotlin引入了接口代理的技术，即一个类先声明继承自某个接口，但并不直接实现该接口的方法，而是把已经实现该接口的代
         * 理类作为参数传给前面的类，相当于告诉前面的类:“该接口的方法我已经代替你实现理类作为参数传给前面的类，
         * 相当于告诉前面的类:“该接口的方法我已经代替你实现了,你直接拿去用便是"
         *
         */
        btn_class14.setOnClickListener {
            var fowl = when (count++ % 6) {
                0 -> WildFowl("老鹰", Bird.MALE, BehaviorFly())
                1 -> WildFowl("凤凰", behavior = BehaviorFly())
                2 -> WildFowl("大雁", Bird.FEMALE, BehaviorFly())
                3 -> WildFowl("企鹅", behavior = BehaviorSwim())
                4 -> WildFowl("鸵鸟", Bird.MALE, BehaviorRun())
                else -> WildFowl("鸸鹋", behavior = BehaviorRun())
            }
            var action = when (count % 11) {
                in 0..3 -> fowl.fly()
                4, 7, 10 -> fowl.swim()
                else -> fowl.run()
            }
            btn_class14.text = "${fowl.name}:$action"

        }

        //5.4几种特殊类
        //5.4.1 嵌套类（kotlin中嵌套类是不允许访问外部类的成员）

        //注意：调用嵌套类扽时候，得在嵌套类的类名前面添加外部类的类名，相当于把这个嵌套类作为外部类的静态对象使用。
        btn_class15.setOnClickListener {
            val peachBlossom = Tree.Flower("桃花")
            btn_class15.text = peachBlossom.getName()
        }

        //5.4.2 内部类
        //使用关键字inner ，内部类可以访问外部类的成员
        btn_class16.setOnClickListener {
            val peachBlossom = Tree2("桃树").Flower("桃花")
            btn_class16.text = peachBlossom.getName()
        }

        //5.4.3 枚举类
        /**
         * 枚举类内部的枚举变量除了可以直接拿来赋值之外，还可以通过枚举值的几个属性获得对应的信息，例如ordinal属性
         * 用于获取该枚举值的序号，name属性用于获取该枚举值的名称。枚举变量本质上还是该类的一个实例，所以如果枚举类存在构造函数，
         * 枚举变量也必须调用对应的构造函数。这样做的好处是，每个枚举值不但携带唯一的名称，还可以拥有更加个性化的特征描述。
         */


        btn_class17.setOnClickListener {
            if (count % 2 == 0) {
                btn_class17.text = when (count++ % 4) {
                    SeasonType.SPRING.ordinal -> SeasonType.SPRING.name
                    SeasonType.SUMMER.ordinal -> SeasonType.SUMMER.name
                    SeasonType.AUTUMN.ordinal -> SeasonType.AUTUMN.name
                    SeasonType.WINTER.ordinal -> SeasonType.WINTER.name
                    else -> "未知"
                }
            } else {
                btn_class17.text = when (count++ % 4) {
                    SeasonName.SPRING.ordinal -> SeasonName.SPRING.seasonName
                    SeasonName.SUMMER.ordinal -> SeasonName.SUMMER.seasonName
                    SeasonName.AUTUMN.ordinal -> SeasonName.AUTUMN.seasonName
                    SeasonName.WINTER.ordinal -> SeasonName.WINTER.seasonName
                    else -> "未知"
                }
            }
        }

        //5.4.4 密封类
        //为解决枚举值判断的多余分支问题，Kotlin提出 了“密封类”的概念，密封类就像是一种更加严格的枚举类，它内部有且仅有自身的实例对象，所以是一个有限的自身实例集合。
        btn_class18.setOnClickListener {
            var season = when (count++ % 4) {
                0 -> SeasonSealed.Spring("春天")
                1 -> SeasonSealed.Summer("夏天")
                2 -> SeasonSealed.Autumn("秋天")
                else -> SeasonSealed.Winter("冬天")
            }
            btn_class18.text = when (season) {
                is SeasonSealed.Spring -> season.name
                is SeasonSealed.Summer -> season.name
                is SeasonSealed.Autumn -> season.name
                is SeasonSealed.Winter -> season.name
            }
        }

        //5.4.5 数据类（相当于Java中定义的javabean中定义的属性，set/get方法，tostring方法）
        //数据类只要在class前面增加关键字"data"，并声明拥有完整输入参数的构造函数，即可实现以下功能：
        /**
         *(1)自动声明与构造函数入参同名的属性字段。
         *(2)自动实现每个属性字段的get/set方法
         *(3)自动提供equals方法，用于比较两个数据对象是否相等。
         *(4)自动提供copy方法，允许完整复制某个数据对象，也可在复制后单独修改某几个字段的值。
         *(5)自动提供toString方法，用于打印数据对象中保存的所有字段值。
         */
        //⚠️ 数据类必须有主构造函数，且至少又一个输入参数
        //⚠️ 主构造函数扽输入参数前面必须添加关键字val或者var，这保证每个入参都会自动声明同名的属性字段

        var lotus = Plant("莲", "莲藕", "莲叶", "莲花", "莲蓬", "莲子")
        var lotus2 = lotus.copy()
        btn_class19.setOnClickListener {
            lotus2 = when (count++ % 2) {
                //copy方法带参数，表示指定参数另外赋值
                0 -> lotus.copy(flower = "荷花")
                else -> lotus.copy(flower = "莲花")
            }
            var result = if (lotus2.equals(lotus)) "相等" else "不相等"
            btn_class19.text =
                "两个植物的比较结果是${result}\n" + "第一个植物的描述是${lotus.toString()}" + "第二个植物扽描述是${lotus2.toString()}"
        }

        //5.4.6 模板类（泛型类）
        //⚠️外部调用模版类构造函数的时候，要在类名后面补充"<参数类型>",从而动态指定实际的参数类型。
        btn_class20.setOnClickListener {
            var river = when (count++ % 4) {
                0 -> River<Int>("小溪", 100)
                //如果编译器根据输入参数就能知晓参数类型，也可以直接省略<参数类型>"
                1 -> River("瀑布", 99.9f)
                2 -> River<Double>("山涧", 50.5)
                else -> River("小河", "一千")

            }

            btn_class20.text = river.getInfo()
        }
    }
}
