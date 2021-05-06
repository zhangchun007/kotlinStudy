package com.jimmy.kotlinstudy.guolin

/**
 * @Description:扩展函数
 * @Author:         zhangchun
 * @CreateDate:     2021/4/30
 * @Version:        1.0
 */

//在我们将lettersCount()方法定义成了 String类的扩展函数，那么函数中就自动拥有了String实例的上下文。
// 因此lettersCount()函数就不再需要接收一个字符串参数了，而是直接 遍历this即可，因为现在this就代表着字符串本身。
fun String.lettersCount(): Int {
    var count = 0
    for (char in this)//this 指向该字符串本身
    {
        if (char.isLetter())
            count++
    }
    return count
}