package com.wsg.kotlin.Util

import android.content.Context
import java.lang.Exception


/*
 *  项目名:  Kotlin
 *  包名:    com.wsg.kotlin.Util
 *  文件名:   UtilToos
 *  创建者:   wsg
 *  创建时间: 2019/5/20 20:39
 *  描述:     工具类
 */

class UtilToos {

    companion object {
        //获取版本号
        fun getVersion(ctx : Context): String {
            val p = ctx.packageManager
            try {
                val info = p.getPackageInfo(ctx.packageName,0)
                return info.versionName
            }catch (e : Exception){
                return "未知"
            }
        }
    }
}