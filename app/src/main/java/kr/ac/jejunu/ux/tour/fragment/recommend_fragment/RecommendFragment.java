package kr.ac.jejunu.ux.tour.fragment.recommend_fragment;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

import kr.ac.jejunu.ux.tour.R;

/**
 * Created by Osy on 2017-10-04.
 */

public class RecommendFragment extends Fragment {
    private Provider provider;

    public static RecommendFragment newInstance(){
        RecommendFragment INSTANCE = new RecommendFragment();

        return INSTANCE;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view  = inflater.inflate(R.layout.fragment_recommend, container, false);
        providerSample();

        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        RecyclerView recyclerView = view.findViewById(R.id.recycler_view);

        MyAdapter myAdapter = new MyAdapter(provider, this.getActivity());

        recyclerView.setAdapter( myAdapter );
        recyclerView.setLayoutManager( new LinearLayoutManager( getActivity()) );

        super.onViewCreated(view, savedInstanceState);
    }

    //샘플데이터생성(실제로는 task를 이용하여 서버로부터 데이터 얻어와야함)
    public void providerSample(){
        ArrayList<CourseVO> arrayList = new ArrayList<>();

        arrayList.add( sampleCreate( R.drawable.bong, "성산일출봉", "성산에 있는 봉", "3000원"));
        arrayList.add( sampleCreate( R.drawable.reed, "갈대", "갈대가 많아", "5000원"));
        arrayList.add( sampleCreate( R.drawable.school, "학교", "알록달록해", "7000원"));
        arrayList.add( sampleCreate( R.drawable.stone_wall, "돌담길", "돌담이있어", "9000원"));
        arrayList.add( sampleCreate( R.drawable.beach, "해변", "바닷가", "10000원"));
        arrayList.add( sampleCreate( R.drawable.power_generator, "풍력발전기", "바람이 분다", "6000원"));
        arrayList.add( sampleCreate( R.drawable.windmil, "해바라기풍차", "한국인건가", "5000원"));
        arrayList.add( sampleCreate( R.drawable.ranch, "목장", "아마도 한라산", "5000원"));
        arrayList.add( sampleCreate( R.drawable.light_house, "등대", "등대", "5000원"));
        arrayList.add( sampleCreate( R.drawable.palm_beach, "야자수해변", "야자수해변", "5000원"));
        arrayList.add( sampleCreate( R.drawable.black_sand_beach, "검은모래해변", "검은모래해변", "7000원"));
        arrayList.add( sampleCreate( R.drawable.rape_blossom_beach, "유채꽃해변", "유채꽃해변", "8000원"));
        arrayList.add( sampleCreate( R.drawable.way, "길", "길", "9000원"));
        arrayList.add( sampleCreate( R.drawable.bong2, "성산일출봉인가", "성산일출봉인가", "5000원"));
        arrayList.add( sampleCreate( R.drawable.bluff, "절벽길", "절벽길", "4000원"));
        arrayList.add( sampleCreate( R.drawable.rape_blossom_field, "유채꽃밭", "유채꽃밭", "2000원"));

        provider = new Provider(arrayList);
    }

    public CourseVO sampleCreate(int imageId, String title, String subTitle, String info){
        CourseVO vo = new CourseVO.SampleBuilder()
                .setCourseImgId(imageId)
                .setTitle(title)
                .setSubTitle(subTitle)
                .setInfo(info)
                .build();

        return vo;
    }
}
