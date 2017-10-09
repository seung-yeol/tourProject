package kr.ac.jejunu.ux.tour.fragment.diary_fragment;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

import kr.ac.jejunu.ux.tour.R;
import kr.ac.jejunu.ux.tour.util.DBUtil;

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

        return v;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        ArrayList<ArrayList> allData;

        DBUtil dbUtil = DBUtil.getInstance( getActivity(), "TOUR", null, 1);
        allData = dbUtil.getAllData( DBUtil.TABLE_DIARY);

        DiaryAdapter adapter = new DiaryAdapter( getActivity() ,allData);

        RecyclerView recyclerView = view.findViewById(R.id.grid_recycler);
        recyclerView.setLayoutManager( new GridLayoutManager( getActivity(), 3 ));
        recyclerView.setAdapter( adapter);
    }

    class ImageClickLister implements View.OnClickListener{

        @Override
        public void onClick(View view) {
            //클릭하는 이미지버튼에 따라 뜨는 상품이 다르게.
        }
    }
}
