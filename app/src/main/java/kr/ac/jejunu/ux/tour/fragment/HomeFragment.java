package kr.ac.jejunu.ux.tour.fragment;

import android.app.Fragment;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import kr.ac.jejunu.ux.tour.R;
import kr.ac.jejunu.ux.tour.fragment.recommend_fragment.RecommendFragment;

/**
 * Created by Osy on 2017-09-16.
 */

public class HomeFragment extends Fragment {
    private View v;
    private FragmentTransaction fragmentTransaction;

    public static HomeFragment newInstance(FragmentTransaction fragmentTransaction){
        HomeFragment INSTANCE = new HomeFragment();
        INSTANCE.fragmentTransaction = fragmentTransaction;

        return INSTANCE;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_home, container, false);

        return v;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        Button allList = view.findViewById(R.id.all_list);
        Button myList = view.findViewById(R.id.my_list);

        allList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Fragment fragment = RecommendFragment.newInstance();

                fragmentTransaction = getActivity().getFragmentManager().beginTransaction();

                fragmentTransaction.replace( R.id.fragment_container, fragment);
                fragmentTransaction.commit();
            }
        });
    }
}

