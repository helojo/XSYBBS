package com.wsg.xsybbs.fragment;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.text.InputType;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;
import com.wsg.xsybbs.R;
import com.wsg.xsybbs.activity.note.AddNoteActivity;
import com.wsg.xsybbs.activity.note.NoteDetailActivity;
import com.wsg.xsybbs.activity.note.SearchNoteActivity;
import com.wsg.xsybbs.adapter.GlideImageLoader;
import com.wsg.xsybbs.adapter.NoteAdapter;

import com.wsg.xsybbs.bean.Banne;
import com.wsg.xsybbs.bean.Note;
import com.wsg.xsybbs.util.L;
import com.youth.banner.Banner;

import java.util.ArrayList;
import java.util.List;
import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.FindListener;
import es.dmoral.toasty.Toasty;

/**
 * Created by wsg
 * on         2018/6/28.
 * function:  贴吧Fragment
 */
public class NoteFragment extends Fragment implements View.OnClickListener {

    private EditText etSearch;
    private ImageView ivSearch;
    private ImageView ivPost;

    private ListView lvNote;
    private Banner banner;
    private SwipeRefreshLayout swipeRefreshLayout;


    private List<String> listp=new ArrayList<>();

    private List<Note> listNote=new ArrayList<>();
    private NoteAdapter noteadapter;



    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_note, null);

        initView(view);
        return view;
    }

    private void initView(View view) {
        etSearch=(EditText)view.findViewById(R.id.et_note);
        etSearch.setInputType(InputType.TYPE_NULL);
        etSearch.setEnabled(false);
        etSearch.setOnClickListener(this);
        ivSearch=(ImageView)view.findViewById(R.id.iv_search);
        ivSearch.setOnClickListener(this);
        ivPost=(ImageView)view.findViewById(R.id.iv_post);
        ivPost.setOnClickListener(this);



        swipeRefreshLayout=(SwipeRefreshLayout)view.findViewById(R.id.swipe_refresh);
        swipeRefreshLayout.setColorSchemeResources(R.color.colorPrimary);   //设置下拉刷新进度条的颜色
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                initData();   //进行刷新操作
                swipeRefreshLayout.setRefreshing(false);
            }



        });



        //设置轮播图
        listp.add("http://202.200.82.150/u/cms/www/201806/27104449b14a.jpg");
        listp.add("http://202.200.82.150/u/cms/www/201805/16111826e3zf.jpg");
        listp.add("http://202.200.82.150/u/cms/www/201710/30114208slub.jpg");
        listp.add("http://202.200.82.150/u/cms/www/201806/26174701kiz0.png");

        banner = (Banner)view.findViewById(R.id.banner);
        //设置图片加载器
        banner.setImageLoader(new GlideImageLoader());
        //设置图片集合
        banner.setImages(listp);

        //banner设置方法全部调用完毕时最后调用
        banner.start();
        lvNote=(ListView)view.findViewById(R.id.lv_note);

        //设置适配器
        noteadapter=new NoteAdapter(getActivity(),listNote);
        lvNote.setAdapter(noteadapter);



        initBanner();



        lvNote.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent=new Intent(getActivity(), NoteDetailActivity.class);
                intent.putExtra("note",listNote.get(position));
                startActivity(intent);
            }
        });





    }






    //初始化banner
    private void initBanner() {
        BmobQuery<Banne> query = new BmobQuery<Banne>();
        query.setLimit(10);
        //按时间降序
        query.order("-createdAt");
        query.findObjects(new FindListener<Banne>() {
            @Override
            public void done(List<Banne> list, BmobException e) {
                if(e==null){
                    listp.clear();
                    for (int i = 0; i <list.size() ; i++) {
                        listp.add(list.get(i).getPhoto());
                    }

                    //重新设置图片集合
                    banner.setImages(listp);
                    //banner设置方法全部调用完毕时最后调用
                    banner.start();


                }
            }
        });


    }




    @Override
    public void onClick(View v) {
        Intent intent;
        switch (v.getId()){
            case R.id.et_note:
                intent=new Intent(getActivity(),SearchNoteActivity.class);
                startActivity(intent);
                break;
            case R.id.iv_search:
                intent=new Intent(getActivity(),SearchNoteActivity.class);
                startActivity(intent);
                break;
            case R.id.iv_post:
                intent=new Intent(getActivity(),AddNoteActivity.class);
                startActivity(intent);
                break;

        }

    }


    @Override
    public void onResume() {
        super.onResume();
        initData();
    }



    //初始化数据
    private void initData() {
        //获取数据
        BmobQuery<Note> query = new BmobQuery<Note>();
        query.setLimit(50);
        //按时间降序
        query.order("-top");
        query.findObjects(new FindListener<Note>() {
            @Override
            public void done(List<Note> list, BmobException e) {
                if(e==null){
                    listNote.clear();
                    listNote.addAll(list);
                    noteadapter.notifyDataSetChanged();

                }else{
                    L.d(e.toString()+e.getErrorCode()+e.getMessage());
                    Toasty.error(getActivity(),"请求数据失败，请检查网络", Toast.LENGTH_LONG).show();

                }
            }
        });

    }
}
