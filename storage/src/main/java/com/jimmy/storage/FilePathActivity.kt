package com.jimmy.storage

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Environment
import kotlinx.android.synthetic.main.activity_file_path.*

/**
 * 手机存储空间包括：
 * 外部存储：
 *      外部存储放的是公共文件，如图片，文档，音视频文件等。
 * 内部存储：
 *      内部存储存放的是手机系统以及各应用的安装目录，
 *
 * 由于内部存储空间有限，因此为了不影响系统的流畅运行，App运行过程中要处理的文件都保存在外部存储空间。
 * 为保证App正常读写外部存储，需在AndroidManifest.xml中增加SD卡的权限配置。6.0版本后需要动态申请。
 *
 * 不过系统默认关闭存储其实只是关闭外部存储的公共空间，外部存储的私有空间依然可以正常读写。这是缘于Android把外部存储分成了两块区域，一
 * 块是所有应用均可访问的公共空间，另一块是只有应用自己才可访问的专享空间。之前说过，内部存储保存着每个应用的安装目录，但是安装目录的空间是很紧张的，
 * 所以Android在SD卡的“Android/data”目录下给每个应用又单独建了一个文件目录，用于给应用保存自己需要处理的临时文件。
 * 这个给每个应用单独建立的文件目录只有当前应用才能够读写文件，其他应用是不允许进行读写的，故而“Android/data" 目录算是外部存储.上的私有空间。
 * 这个私有空间本身已经做了访问权限控制，因此它不受系统禁止访问的影响，应用操作自己的文件目录就不成问题了。
 * 当然，因为私有的文件目录只有属主应用才能访问，所以旦属主应用被用户卸载，那么对应的文件目录也会一起被清理掉。
 *
 *
 * 既然外部存储路径分成了公共空间和私有空间，这两部分空间的路径获取也就有所不同。
 * 公共的存储路径调用的是： Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS).toString()
 * 私有空间的存储路径调用的是：getExternalFilesDir(Environment.DIRECTORY_DOWNLOADS).toString()
 */
class FilePathActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_file_path)
        //获取系统的公共存储路径
        val publicPath =
            Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS)
                .toString()
        //获取当前app的私有存储路径
        val privatePath = getExternalFilesDir(Environment.DIRECTORY_DOWNLOADS).toString()

        tv_file_path.text = "系统的公共存储路径位于${publicPath}" +
                "\n\n当前App的私有存储路径位于${privatePath}" +
                "\n\nAndroid7.0之后默认禁止访问公共存储目录"
    }
}
