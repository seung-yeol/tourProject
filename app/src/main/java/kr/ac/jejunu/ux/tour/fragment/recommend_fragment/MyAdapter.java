package kr.ac.jejunu.ux.tour.fragment.recommend_fragment;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import kr.ac.jejunu.ux.tour.R;

/**
 * Created by Osy on 2017-10-04.
 */

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {
    private Provider provider;
    private Context context;

    public MyAdapter (Provider provider, Context context){
        this.provider = provider;
        this.context = context;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate( R.layout.recommend_recycler_view, parent, false);

        return new MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        final CourseVO courseVO = provider.getCourse( position);

        //이미지용량이 커서 OutOfMemoryError 를 피하기 위해 BitmapFactory 를 이용해 이미지 용량을 줄임.
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inSampleSize = 2;
        Bitmap bitmap = BitmapFactory.decodeResource( context.getResources(), courseVO.getCourseImgId() , options);

        holder.courseImg.setImageBitmap(bitmap);
        holder.title.setText( courseVO.getTitle());
        holder.subTitle.setText( courseVO.getSubTitle());
        holder.info1.setText( "정보");
        holder.info2.setText( courseVO.getInfo());
    }

    @Override
    public int getItemCount() {
        return provider.getCount();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {
        ImageView courseImg;
        TextView title;
        TextView subTitle;
        TextView info1;
        TextView info2;

        public MyViewHolder(View v) {
            super(v);

            courseImg = v.findViewById(R.id.course_img);
            title = v.findViewById(R.id.title);
            subTitle = v.findViewById(R.id.subtitle);
            info1 = v.findViewById(R.id.info);
            info2 = v.findViewById(R.id.info2);
        }
    }
}
