package kr.ac.jejunu.ux.tour;

import android.app.Activity;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

/**
 * Created by Osy on 2017-10-07.
 */

public class TendencyActivity extends Activity{
    private final int DRAG_SELECT = 1;
    private final int DRAG_DELETE = 2;
    private final int DRAG_RESET = 3;

    private float originX;
    private float originY;

    private TextView progressText;
    private TextView dragTarget;
    private ImageView dragDelete;
    private ImageView dragSelect;

    private ArrayList<String> tendencyList;
    private int listPosition = 0;
    private int listLast;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tendency);

        Button skipText = findViewById(R.id.skip_drag);
        skipText.setOnClickListener( clickListener);

        progressText = findViewById(R.id.progress_text);
        dragTarget = findViewById(R.id.drag_target);
        dragDelete = findViewById(R.id.drag_delete);
        dragSelect = findViewById(R.id.drag_select);

        setTendencyList();
        setProgressText(listPosition+1);

        dragTarget.setText(tendencyList.get(listPosition));
        dragTarget.setOnTouchListener( touchListener);
    }

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);

        originX = dragTarget.getX();
        originY = dragTarget.getY();
    }

    private boolean dragCheck( ImageView v , float x, float y){

        Log.e(this.toString(), "y: " + (v.getY()+ v.getHeight()) + " " + y);

        if ( v.getY()+ v.getHeight() > y && v.getY() < y){
            if ( v.getX()+ v.getWidth() > x && v.getX() < x) return true;
            else                                             return false;
        }
        else                                                 return false;
    }

    private void setTendencyList(){
        tendencyList = new ArrayList<>();

        tendencyList.add("힐링");
        tendencyList.add("레저");
        tendencyList.add("절경");
        tendencyList.add("역사");
        tendencyList.add("문화");
        tendencyList.add("맛집");
        tendencyList.add("쇼핑");
        tendencyList.add("숲");
        tendencyList.add("올레길");
        tendencyList.add("바다");
        tendencyList.add("등산");
        tendencyList.add("스킨스쿠버");
        tendencyList.add("해녀체험");
        tendencyList.add("말");
        tendencyList.add("카트");
        tendencyList.add("오름");
        tendencyList.add("축제");
        tendencyList.add("드라이브");

        listLast = tendencyList.size();
    }

    private void setProgressText( int listPosition){
        progressText.setText( listPosition + " / " + listLast);
    }

    private View.OnTouchListener touchListener = new View.OnTouchListener() {
        @RequiresApi(api = Build.VERSION_CODES.M)
        @Override
        public boolean onTouch(View v, MotionEvent event) {
            if (event.getAction() == MotionEvent.ACTION_DOWN) {
                Log.i("Tag1", "Action Down rX " + event.getRawX() + "," + event.getRawY());
            }
            else if (event.getAction() == MotionEvent.ACTION_MOVE) {
                float curXValue = event.getRawX();
                float curYValue = event.getRawY();

                v.setX(event.getRawX() - v.getWidth() / 2);
                v.setY(event.getRawY() - v.getHeight() / 2);

                if (dragCheck(dragDelete, curXValue, curYValue)) {
                    v.setBackgroundResource(R.drawable.drag_view_delete);
                    v.setTag(DRAG_DELETE);
                } else if (dragCheck(dragSelect, curXValue, curYValue)) {
                    v.setBackgroundResource(R.drawable.drag_view_select);
                    v.setTag(DRAG_SELECT);
                } else {
                    v.setBackgroundResource(R.drawable.drag_view_background);
                    v.setTag(DRAG_RESET);
                }

                //  Log.i("Tag2", "Action Down " + me.getRawX() + "," + me.getRawY());
            }
            else if (event.getAction() == MotionEvent.ACTION_UP) {
                if ((int) v.getTag() == DRAG_DELETE) {
                    listPosition++;

                    if (listPosition != listLast){
                        setProgressText( listPosition + 1);
                        ((TextView) v).setText( tendencyList.get(listPosition));
                        v.setBackgroundResource(R.drawable.drag_view_background);
                        v.setX(originX);
                        v.setY(originY);
                    }
                    else {
                        Toast.makeText(TendencyActivity.this, "성향설정이 완료되었습니다!", Toast.LENGTH_SHORT).show();
                        finish();
                    }

                }
                else if ((int) v.getTag() == DRAG_SELECT) {
                    listPosition++;

                    if (listPosition != listLast){
                        setProgressText( listPosition + 1);
                        ((TextView) v).setText( tendencyList.get(listPosition));
                        v.setBackgroundResource(R.drawable.drag_view_background);
                        v.setX(originX);
                        v.setY(originY);
                    }
                    else {
                        Toast.makeText(TendencyActivity.this, "성향설정이 완료되었습니다!", Toast.LENGTH_SHORT).show();
                        finish();
                    }
                }
                else {
                    v.setX(originX);
                    v.setY(originY);
                }
                Log.i("Tag1", "Action up rX " + event.getRawX() + "," + event.getRawY());
            }
            return true;
        }
    };
    private View.OnClickListener clickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            Toast.makeText(TendencyActivity.this, "설정탭을 통하여 성향을 설정해주세요!", Toast.LENGTH_SHORT).show();
            finish();
        }
    };
}
