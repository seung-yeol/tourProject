package kr.ac.jejunu.ux.tour.fragment.diary_fragment;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.File;
import java.util.ArrayList;

import kr.ac.jejunu.ux.tour.DiaryAdderActivity;
import kr.ac.jejunu.ux.tour.R;

/**
 * Created by Osy on 2017-10-09.
 */

public class DiaryAdapter extends RecyclerView.Adapter<DiaryAdapter.GridViewHolder> {
    private ArrayList<ArrayList> data;
    private Context context;

    public DiaryAdapter( Context context, ArrayList data){
        this.context = context;
        this.data = data;
    }

    @Override
    public GridViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate( R.layout.diary_recycler_view, parent, false);

        return new GridViewHolder(v);
    }

    @Override
    public void onBindViewHolder(GridViewHolder holder, int position) {


        //마지막은 추가하는 탭으로다가.
        if ( position == data.size() ){
            holder.diaryImg.setBackgroundResource(R.drawable.ic_diary_add_backgroud);
            holder.diaryImg.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent( context, DiaryAdderActivity.class);
                    context.startActivity(intent);
                }
            });
        }
        else {
            String title = (String)data.get(position).get(0);
            String story = (String)data.get(position).get(1);
            String url = (String)data.get(position).get(2);

            Log.e(this.toString(), "position: " + position );
            Log.e(this.toString(), "url: " + url );

            File file = new File(url);
            Bitmap bitmap = BitmapFactory.decodeFile( file.getAbsolutePath());

            holder.diaryImg.setImageBitmap( bitmap);
            holder.diaryTitle.setText( title);
        }
    }

    //마지막 추가하는 칸.
    @Override
    public int getItemCount() {
        return data.size() + 1;
    }

    public void changeData(ArrayList<ArrayList> data){
        this.data = data;
    }

    class GridViewHolder extends RecyclerView.ViewHolder {
        ImageView diaryImg;
        TextView diaryTitle;

        public GridViewHolder(View itemView) {
            super(itemView);

            diaryImg = itemView.findViewById(R.id.diary_img);
            diaryTitle = itemView.findViewById(R.id.diary_title);
        }
    }
}
