package study.com.viewmodeltestdemo;

import android.arch.lifecycle.Lifecycle;
import android.arch.lifecycle.LifecycleObserver;
import android.arch.lifecycle.OnLifecycleEvent;
import android.util.Log;

/**
 * Created by  HONGDA on 2018/8/6.
 */
public class MainPresenter implements LifecycleObserver {

    public MainPresenter() {
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_RESUME)
    private void onResume(){
        Log.i("LHD", "onResume onResume onResume");
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
    private void onDestroy() {
        Log.i("LHD", "onDestroy onDestroy onDestroy");
    }

}
