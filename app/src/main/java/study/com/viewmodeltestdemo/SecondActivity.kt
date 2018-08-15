package study.com.viewmodeltestdemo

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.CheckBox
import android.widget.TextView

class SecondActivity : AppCompatActivity() {

    private lateinit var tvSecond: TextView
    private lateinit var btnSendSet: Button
    private lateinit var btnSendPost: Button
    private lateinit var btnReceive: Button
    private lateinit var etCheck: CheckBox

    private lateinit var nameViewModel: NameViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_second)
        tvSecond = findViewById(R.id.tv_second)
        btnSendSet = findViewById(R.id.btn_send_set)
        btnSendPost = findViewById(R.id.btn_send_post)
        btnReceive = findViewById(R.id.btn_receive)
        etCheck = findViewById(R.id.et_open_work_thread)

        btnSendPost.setOnClickListener {
            if (etCheck.isChecked) {
                Thread(Runnable {
                    nameViewModel.getmCurrentName().postValue("使用post data 方式在子线程发送数据")
                }).start()
            } else {
                nameViewModel.getmCurrentName().postValue("使用post data 方式")
            }
        }

        btnSendSet.setOnClickListener {
            if (etCheck.isChecked) {
                Thread(Runnable {
                    nameViewModel.getmCurrentName().value = "使用set data 方式在子线程发送数据"
                }).start()
            } else {
                nameViewModel.getmCurrentName().value = "使用set data 方式"
            }
        }

        //1、获取ViewModel
        nameViewModel = ViewModelProviders.of(this).get(NameViewModel::class.java)
        //2、创建观察者
        var nameObserver: Observer<String> = Observer<String> { t -> tvSecond.text = t ?: "不知道" }

        //You must call the setValue(T) method to update the LiveData object from the main thread.
        // If the code is executed in a worker thread, you can use the postValue(T) method instead to update the LiveData object.
        btnReceive.setOnClickListener {
            //3、观察liveData的变化
            nameViewModel.getmCurrentName().observe(this, nameObserver)
        }

    }


}
