package kr.ac.jejunu.ux.tour.fragment;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by Osy on 2017-09-16.
 */

public class MoneyFragment extends Fragment {
    public static MoneyFragment newInstance(){
        MoneyFragment Instance = new MoneyFragment();
        return Instance;
    }

    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return super.onCreateView(inflater, container, savedInstanceState);

    }



}
