package kr.ac.jejunu.ux.tour.fragment.diary_fragment;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

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
            holder.imageView.setBackgroundResource(R.drawable.ic_diary_add_backgroud);
            holder.imageView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent( context, DiaryAdderActivity.class);
                    context.startActivity(intent);
                }
            });
        }
        else {
            //holder.imageView.setBackground();
        }

    }

    //마지막 추가하는 칸.
    @Override
    public int getItemCount() {
        return data.size() + 1;
    }

    class GridViewHolder extends RecyclerView.ViewHolder {
        ImageView imageView;

        public GridViewHolder(View itemView) {
            super(itemView);

            imageView = itemView.findViewById(R.id.diary_img);
        }
    }
}
