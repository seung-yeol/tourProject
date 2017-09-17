package kr.ac.jejunu.ux.tour.fragment;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import kr.ac.jejunu.ux.tour.R;

/**
 * Created by Osy on 2017-09-16.
 */

public class DiaryFragment extends Fragment {
    private View v;
    public static DiaryFragment newInstance(){
        DiaryFragment Instance = new DiaryFragment();
        return Instance;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        v = inflater.inflate( R.layout.fragment_diary, container, false );
        super.onCreateView(inflater, container, savedInstanceState);

        Button btn1 = (Button) v.findViewById(R.id.diary_btn1);
        Button btn2 = (Button) v.findViewById(R.id.diary_btn2);
        Button btn3 = (Button) v.findViewById(R.id.diary_btn3);
        Button btn4 = (Button) v.findViewById(R.id.diary_btn4);
        Button btn5 = (Button) v.findViewById(R.id.diary_btn5);
        Button btn6 = (Button) v.findViewById(R.id.diary_btn6);

        return v;
    }

@Override
public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        }

class ImageClickLister implements View.OnClickListener{

    @Override
    public void onClick(View view) {
        //클릭하는 이미지버튼에 따라 뜨는 상품이 다르게.
    }
}
}
