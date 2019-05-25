package com.wsg.kotlin

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentTransaction
import android.view.View
import com.wsg.kotlin.base.BaseActivity
import android.widget.TextView
import android.widget.RelativeLayout
import com.wsg.kotlin.fragment.FriendFragment
import com.wsg.kotlin.fragment.MineFragment
import com.wsg.kotlin.fragment.MyMessageFragment
import com.wsg.kotlin.fragment.NoteFragment
import org.jetbrains.anko.find
import org.jetbrains.anko.toast


class MainActivity : BaseActivity(), View.OnClickListener {


    //relativelayout
    private lateinit var mNoteLayout: RelativeLayout
    private lateinit var mFriendsLayout: RelativeLayout
    private lateinit var mMessageLayout: RelativeLayout
    private lateinit var mMineLayout: RelativeLayout

    //textview
    private lateinit var mNoteView: TextView
    private lateinit var mFriendsView: TextView
    private lateinit var mMessageView: TextView
    private lateinit var mMineView: TextView

    //fragment
    private lateinit var noteFragment: NoteFragment
    private lateinit var friendFragment: FriendFragment
    private lateinit var myMessageFragment: MyMessageFragment
    private lateinit var mineFragment: MineFragment

    private lateinit var fragmentManager: FragmentManager


    private var isFirst = true
    private var lastTime: Long = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initView()
    }

    private fun initView() {
        mNoteLayout = find(R.id.note_layout_view)
        mNoteLayout.setOnClickListener(this)
        mFriendsLayout = find(R.id.friend_layout_view)
        mFriendsLayout.setOnClickListener(this)
        mMessageLayout = find(R.id.message_layout_view)
        mMessageLayout.setOnClickListener(this)
        mMineLayout = find(R.id.mine_layout_view)
        mMineLayout.setOnClickListener(this)

        mNoteView = find(R.id.home_image_view)
        mFriendsView = find(R.id.home_image_view)
        mMessageView = find(R.id.message_layout_view)
        mMineView = find(R.id.mine_image_view)



    }


    override fun onClick(v: View?) {
        when (v!!.id) {
            R.id.note_layout_view -> {

            }
            R.id.friend_layout_view -> {

            }
            R.id.message_layout_view ->{

            }
            R.id.mine_layout_view ->{

            }
        }
    }

    fun hideFragment(fragment: Fragment , fragmentTransaction: FragmentTransaction){
        if (fragment != null){
            fragmentTransaction.hide(fragment)
        }
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
