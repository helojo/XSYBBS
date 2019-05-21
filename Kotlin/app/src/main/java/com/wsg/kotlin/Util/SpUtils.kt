package com.wsg.kotlin.Util

import android.content.Context


/*
 *  项目名:  Kotlin
 *  包名:    com.wsg.kotlin.Util
 *  文件名:   SpUtils
 *  创建者:   wsg
 *  创建时间: 2019/5/20 20:18
 *  描述:     sp常量类
 */

class SpUtils {
    companion object {
        val name = "config"

        val isLogin = "isLogin"
        val userName = "userName"
        val passWord = "passWord"



        fun putString(ctx : Context,key : String,value : String){
            val sp = ctx.getSharedPreferences(name,Context.MODE_PRIVATE);
            sp.edit().putString(key,value).commit()
        }

        fun getString(ctx : Context,key :String,defValue : String):String?{
            val sp = ctx.getSharedPreferences(name,Context.MODE_PRIVATE)
            return sp.getString(key,defValue)
        }

        fun putInt(ctx : Context,key : String,i : Int){
            val sp =ctx.getSharedPreferences(name,Context.MODE_PRIVATE)
            sp.edit().putInt(key,i).commit()
        }

        fun getInt(ctx : Context,key : String ,defValue: Int): Int {
            val sp = ctx.getSharedPreferences(name,Context.MODE_PRIVATE)
            return sp.getInt(key,defValue)
        }

        fun putBoolean(ctx : Context,key : String ,b : Boolean){
            val sp = ctx.getSharedPreferences(name,Context.MODE_PRIVATE)
            sp.edit().putBoolean(key,b).commit()
        }

        fun getBoolean(ctx : Context,key : String ,def : Boolean): Boolean {
            val sp = ctx.getSharedPreferences(name,Context.MODE_PRIVATE)
            return sp.getBoolean(key,def)
        }

        //删除单个
        fun deleShare(ctx : Context,key : String){
            val sp = ctx.getSharedPreferences(name,Context.MODE_PRIVATE)
            sp.edit().remove(key).commit()
        }

        fun deleAll(ctx :Context){
            val sp = ctx.getSharedPreferences(name,Context.MODE_PRIVATE)
            sp.edit().clear().commit()
        }
    }
}