package study.com.viewmodeltestdemo;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

/**
 * Created by  HONGDA on 2018/8/6.
 */
public class FragmentTwo extends Fragment {

    private TextView textName;
    private NameViewModel model;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_two, container, false);
        textName = view.findViewById(R.id.tv_fragment2_name);
        model = ViewModelProviders.of(getActivity()).get(NameViewModel.class);
        model.getmCurrentName().observe(getActivity(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                if (TextUtils.isEmpty(s)) s = "哦，是个null";
                textName.setText(s);
            }
        });
        return view;
    }
}
