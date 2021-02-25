package com.jimmy.simple.javabean

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

/**
 * @Description:
 * @Author:         纯仔
 * @CreateDate:     2020-09-29
 * @Version:        1.0
 */
@Parcelize
class MessageInfo(var content: String, val send_time: String) : Parcelable {
}