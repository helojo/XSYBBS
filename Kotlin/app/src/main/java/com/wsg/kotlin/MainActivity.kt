package com.wsg.kotlin

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import android.view.View
import com.wsg.kotlin.base.BaseActivity
import com.wsg.kotlin.fragment.FriendFragment
import com.wsg.kotlin.fragment.MineFragment
import com.wsg.kotlin.fragment.MyMessageFragment
import com.wsg.kotlin.fragment.NoteFragment
import kotlinx.android.synthetic.main.activity_main.*
import org.jetbrains.anko.toast
import com.hyphenate.easeui.EaseConstant
import com.hyphenate.easeui.domain.EaseUser
import com.wsg.kotlin.activity.ChatActivity
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.intentFor
import com.hyphenate.exceptions.HyphenateException
import com.hyphenate.chat.EMClient


class MainActivity : BaseActivity(), View.OnClickListener {


    //fragment
    private lateinit var noteFragment: NoteFragment
    private lateinit var friendFragment: FriendFragment
    private lateinit var myMessageFragment: MyMessageFragment
    private lateinit var mineFragment: MineFragment
    private lateinit var fm: androidx.fragment.app.FragmentManager

    fun isFriendInit() = ::friendFragment.isInitialized
    fun isMessageInit() = ::myMessageFragment.isInitialized
    fun isMineInit() = ::mineFragment.isInitialized

    private var isFirst = true
    private var lastTime: Long = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initView()
    }

    private fun initView() {
        note_layout_view.setOnClickListener(this)
        friend_layout_view.setOnClickListener(this)
        message_layout_view.setOnClickListener(this)
        mine_layout_view.setOnClickListener(this)
        home_image_view.setBackgroundResource(R.drawable.note_pressed)

        //默认显示第一个
        noteFragment = NoteFragment()
        fm = this.supportFragmentManager
        fm.beginTransaction().replace(R.id.content_layout,noteFragment).commit()
    }

    override fun onClick(v: View?) {
        val fmt = fm.beginTransaction()
       when(v!!.id){
           R.id.note_layout_view -> {
               home_image_view.setBackgroundResource(R.drawable.note_pressed)
               fish_image_view.setBackgroundResource(R.drawable.friends)
               message_image_view.setBackgroundResource(R.drawable.message)
               mine_image_view.setBackgroundResource(R.drawable.mine)
               if (isFriendInit()){
                   hideFragment(friendFragment,fmt)
               }
               if(isMessageInit()){
                   hideFragment(myMessageFragment,fmt)
               }
               if(isMineInit()){
                   hideFragment(mineFragment,fmt)
               }
               if(noteFragment == null){
                   noteFragment = NoteFragment()
                   fmt.add(R.id.content_layout,noteFragment)
               }else{
                   fmt.show(noteFragment)
               }
           }
           R.id.friend_layout_view -> {
               home_image_view.setBackgroundResource(R.drawable.note)
               fish_image_view.setBackgroundResource(R.drawable.friends_pressed)
               message_image_view.setBackgroundResource(R.drawable.message)
               mine_image_view.setBackgroundResource(R.drawable.mine)
               hideFragment(noteFragment,fmt)
               if(isMessageInit()){
                   hideFragment(myMessageFragment,fmt)
               }
               if(isMineInit()){
                   hideFragment(mineFragment,fmt)
               }
               if(!isFriendInit()){
                   friendFragment = FriendFragment()
                   doAsync {
                       friendFragment.setContactsMap(getContact());
                   }
                   //设置item点击事件
                   friendFragment.setContactListItemClickListener{ user -> startActivity(intentFor<ChatActivity>(EaseConstant.EXTRA_USER_ID to user.username))}
                   fmt.add(R.id.content_layout,friendFragment)
               }else{
                   fmt.show(friendFragment)
               }
           }
           R.id.message_layout_view -> {
               home_image_view.setBackgroundResource(R.drawable.note)
               fish_image_view.setBackgroundResource(R.drawable.friends)
               message_image_view.setBackgroundResource(R.drawable.message_pressed)
               mine_image_view.setBackgroundResource(R.drawable.mine)
               hideFragment(noteFragment,fmt)
               if(isFriendInit()){
                   hideFragment(friendFragment,fmt)
               }
               if(isMineInit()){
                   hideFragment(mineFragment,fmt)
               }
               if(!isMessageInit()){
                   myMessageFragment = MyMessageFragment()
                   myMessageFragment.setConversationListItemClickListener { conversation ->startActivity(intentFor<ChatActivity>(EaseConstant.EXTRA_USER_ID to conversation.conversationId())) }
                   fmt.add(R.id.content_layout,myMessageFragment)
               }else{
                   fmt.show(myMessageFragment)
               }
           }
           R.id.mine_layout_view -> {
               home_image_view.setBackgroundResource(R.drawable.note)
               fish_image_view.setBackgroundResource(R.drawable.friends)
               message_image_view.setBackgroundResource(R.drawable.message)
               mine_image_view.setBackgroundResource(R.drawable.mine_pressed)
               hideFragment(noteFragment,fmt)
               if(isFriendInit()){
                   hideFragment(friendFragment,fmt)
               }
               if(isMessageInit()){
                   hideFragment(myMessageFragment,fmt)
               }
               if(!isMineInit()){
                   mineFragment = MineFragment()
                   fmt.add(R.id.content_layout,mineFragment)
               }else{
                   fmt.show(mineFragment)
               }
           }
       }
        fmt.commit()
    }

    private fun getContact(): MutableMap<String, EaseUser> {
        val map = HashMap<String,EaseUser>()
        try {
            val userNames = EMClient.getInstance().contactManager().allContactsFromServer
            for (userId in userNames) {
                map.put(userId, EaseUser(userId))
            }
        } catch (e: HyphenateException) {
            e.printStackTrace()
        }
        return map
    }

    private fun hideFragment(fragment: androidx.fragment.app.Fragment, ft : androidx.fragment.app.FragmentTransaction){

        /*
        懒加载的变量是在没初始化之前是不允许做判空操作的，要先判断是否初始化
        kotlin 真是蛋疼
         */
        ft.hide(fragment)
    }

    override fun onBackPressed() {
        super.onBackPressed()
        if(isFirst){
            toast("再按一次退出")
            lastTime = System.currentTimeMillis()
            isFirst = false
        }else{
            if(System.currentTimeMillis() - lastTime < 2000){
                this.finish()
            }else{
                toast("再按一次退出")
                lastTime = System.currentTimeMillis()
            }
        }
    }
}
