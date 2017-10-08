package kr.ac.jejunu.ux.tour.fragment;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import kr.ac.jejunu.ux.tour.R;
import kr.ac.jejunu.ux.tour.TendencyActivity;

/**
 * Created by Osy on 2017-09-16.
 */

public class SettingFragment extends Fragment {
    public static SettingFragment newInstance(){
        SettingFragment Instance = new SettingFragment();
        return Instance;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_setting, container, false);

        return v;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        Button button = (Button)view.findViewById(R.id.tendency_go);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), TendencyActivity.class);
                startActivity(intent);
            }
        });
    }
}
