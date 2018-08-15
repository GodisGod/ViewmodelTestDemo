package study.com.viewmodeltestdemo;

import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

/**
 * Created by  HONGDA on 2018/8/6.
 */
public class FragmentOne extends Fragment {

    private EditText edContent;
    private Button btnSend;
    private NameViewModel model;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_one, container, false);
        edContent = view.findViewById(R.id.ed_content);
        btnSend = view.findViewById(R.id.btn_send);
        model = ViewModelProviders.of(getActivity()).get(NameViewModel.class);
        btnSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //获取到liveData后设置liveData的值
                model.getmCurrentName().setValue(edContent.getText().toString());
            }
        });
        return view;
    }
}
