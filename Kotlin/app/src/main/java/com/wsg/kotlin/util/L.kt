package com.wsg.kotlin.util

import android.util.Log


/*
 *  项目名:  Kotlin
 *  包名:    com.wsg.kotlin.Util
 *  文件名:   L
 *  创建者:   wsg
 *  创建时间: 2019/5/20 20:10
 *  描述:     log 日志工具类
 */

class L {
    companion object {
        val tag = "WSG"
        val isDebug = true

        fun v(s :String){
            if(isDebug){
                Log.v(tag,s)
            }
        }
        fun d(s :String){
            if(isDebug){
                Log.d(tag,s)
            }
        }
        fun i(s :String){
            if(isDebug){
                Log.i(tag,s)
            }
        }
        fun w(s :String){
            if(isDebug){
                Log.w(tag,s)
            }
        }
        fun e(s :String){
            if(isDebug){
                Log.e(tag,s)
            }
        }
    }
}