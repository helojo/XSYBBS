package com.wsg.kotlin.bean

import cn.bmob.v3.BmobObject


/*
 *  项目名:  Kotlin
 *  包名:    com.wsg.kotlin.bean
 *  文件名:   Banne
 *  创建者:   wsg
 *  创建时间: 2019/5/25 15:08
 *  描述:     Banne 实体
 */

data class Banne(var photo : String, var desc : String) : BmobObject()