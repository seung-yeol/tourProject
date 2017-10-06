package kr.ac.jejunu.ux.tour.fragment.recommend_fragment;

import java.util.ArrayList;

/**
 * Created by Osy on 2017-10-04.
 */

public class Provider {
    private int count;
    private ArrayList<CourseVO> data;

    public Provider(ArrayList<CourseVO> data){
        this.data = data;
        this.count = data.size();
    }

    public int getCount(){
        return count;
    }

    public CourseVO getCourse(int position){
        return data.get(position);
    }

}
