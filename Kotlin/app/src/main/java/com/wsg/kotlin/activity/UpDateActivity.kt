package com.wsg.kotlin.activity

import android.os.Bundle
import cn.bmob.v3.update.BmobUpdateAgent
import com.wsg.kotlin.R
import com.wsg.kotlin.base.BaseActivity
import com.wsg.kotlin.util.toast
import kotlinx.android.synthetic.main.activity_update.*

/*
 *  项目名:  Kotlin
 *  包名:    com.wsg.kotlin.activity
 *  文件名:   UpDateActivity
 *  创建者:   wsg
 *  创建时间: 2019/5/28 17:17
 *  描述:     版本更新
 */

class UpDateActivity : BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_update)
        update_bt.setOnClickListener {
            //检查更新
            BmobUpdateAgent.setUpdateOnlyWifi(false);
            BmobUpdateAgent.update(this);
            toast("暂时没新版本，敬请期待哦~")
        }
    }
}