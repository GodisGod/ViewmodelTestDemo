package study.com.viewmodeltestdemo;

import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;

/**
 * Created by  HONGDA on 2018/8/6.
 *  A ViewModel must never reference a view, Lifecycle, or any class that may hold a reference to the activity context.
 *  viewModel不可持有view的引用或者任何持有activity上下文的类的引用
 *  如果需要Application的context的话可以继承AndroidViewModel
 *  Activity重复创建的时候，持有的是同一个viewModel实例
 */
public class NameViewModel extends ViewModel {

    //liveData通常和viewModel一起使用
    private MutableLiveData<String> mCurrentName;

    public MutableLiveData<String> getmCurrentName() {
        if (mCurrentName == null) {
            mCurrentName = new MutableLiveData<String>();
        }
        return mCurrentName;
    }

    //如果需要可以在这里释放资源
    @Override
    protected void onCleared() {
        super.onCleared();

    }

}
