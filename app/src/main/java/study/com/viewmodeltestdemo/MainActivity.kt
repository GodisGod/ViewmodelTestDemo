package study.com.viewmodeltestdemo

import android.arch.lifecycle.LifecycleObserver
import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView

class MainActivity : AppCompatActivity(), LifecycleObserver {

    //Activity重复创建的时候，持有的是同一个viewModel实例
    // activity销毁的时候回调用viewModel的onCleared方法来释放需要释放的资源
    private lateinit var nameViewModel: NameViewModel

    private lateinit var tvName: TextView
    private lateinit var btnStart: Button
    private lateinit var btnNull: Button

    private lateinit var presenter: MainPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        tvName = findViewById(R.id.tv_name)
        btnStart = findViewById(R.id.btn_start)
        btnNull = findViewById(R.id.btn_null)

        presenter = MainPresenter()
        //将实现了LifecycleObserver接口的presenter传递给lifecycle即可
        lifecycle.addObserver(presenter)
        //是ViewModelProviders而不是ViewModelProvider
        //1、获取ViewModel
        nameViewModel = ViewModelProviders.of(this).get(NameViewModel::class.java)
        //2、创建观察者
        var nameObserver: Observer<String> = Observer<String> { t -> tvName.text = t ?: "不知道" }
        //3、观察liveData的变化
        nameViewModel.getmCurrentName().observe(this, nameObserver)

        btnStart.setOnClickListener {
            nameViewModel.getmCurrentName().value = "我知道了：他叫哦哦哦"
        }
        btnNull.setOnClickListener {
            nameViewModel.getmCurrentName().value = null
        }
        findViewById<Button>(R.id.btn_jump).setOnClickListener {
            startActivity(Intent(this, SecondActivity::class.java))
        }
    }
}
