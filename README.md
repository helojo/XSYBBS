此分支为毕业设计的kotlin版本，用于学习kotlin，目前还在不断完善


使用到的第三方库：

Kotlin Anko : https://github.com/Kotlin/anko

Rxjava :   https://github.com/ReactiveX/RxJava

RxAndroid :   https://github.com/ReactiveX/RxAndroid

一些简化操作：
1、使用RxJava 和 扩展函数 代替 Handler 实现消息转发
```

open class BaseActivity : AppCompatActivity(){


    ...

    //处理异步消息
    open fun msgManagement(message: Int){

    }
}
```

```
//扩展函数

//使用rxjava 代替Handler 立刻发送消息
fun BaseActivity.sendMessage(m : Int){
    Observable
        .empty<Any>()
        .observeOn(AndroidSchedulers.mainThread())
        .doOnComplete({ msgManagement(m) })
        .subscribe()
}
//使用rxjava 代替Handler 延迟发送消息
fun BaseActivity.sendMessageDelayed(m :Int ,d : Long){
    Observable
        .timer(d, TimeUnit.MILLISECONDS)
        .observeOn(AndroidSchedulers.mainThread())
        .doOnComplete { msgManagement(m) }
        .subscribe()
}
```
2、使用Kotlin Anko 协程  实现后台线程和主线程之前的转换：
```
        doAsync {
                    ...................
                    // 这部分执行在后台线程
                    uiThread {
                    ...................
                    //这部分执行在UI线程
                    }
                }
            })
        }

```
3、使用kotlin android extensions省略findViewById()模板代码：
```
import kotlinx.android.synthetic.main.activity_feedback.*

class FeedBackActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_feedback)
        bt_back.setOnClickListener { saveFeed() }
    }


    ......
}


```
