package kr.ac.jejunu.ux.tour;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.view.ContextThemeWrapper;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.util.ArrayList;

import kr.ac.jejunu.ux.tour.util.DBUtil;

/**
 * Created by Osy on 2017-10-09.
 */

public class DiaryAdderActivity extends Activity {
    private final static int PICK_FROM_ALBUM = 0;
    private final static int PICK_FROM_CAMERA = 1;
    private final static int CROP_FROM_IMAGE = 2;

    private final static int REQUEST_EXTERNAL_STORAGE = 3;
    private final static int REQUEST_CAMERA = 4;

    private String absolutePath;
    private Uri imageCaptureUri;
    private ImageView diaryPhoto;
    private EditText diaryTitle;
    private EditText diaryText;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate( savedInstanceState);
        setContentView( R.layout.activity_diary_adder);

        final Context context = this;

        diaryPhoto = findViewById( R.id.diary_photo);
        diaryTitle = findViewById( R.id.diary_title);
        diaryText = findViewById( R.id.diary_text);

        Button setPhotoBtn = findViewById( R.id.select_picture );
        Button diaryConfirm = findViewById( R.id.diary_confirm );
        diaryConfirm.setOnClickListener( clickListener );

        setPhotoBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //테마충돌 때문에 랩퍼클래스를 이용하여 context 확보
                new AlertDialog.Builder( new ContextThemeWrapper( context , R.style.MyDialog))
                        .setTitle( "사진 선택!")
                        .setNegativeButton( "앨범 선택", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                getFromAlbum();
                            }
                        })
                        .setNeutralButton( "사진 촬영", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                getFromCamera();
                            }
                        })
                        .setPositiveButton( "취소", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                dialogInterface.dismiss();
                            }
                        })
                        .show();
            }
        });

        if (Build.VERSION.SDK_INT >= 23){
            //퍼미션을 얻기위해
            if ( ContextCompat.checkSelfPermission( this,
                    Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_DENIED){
                 ActivityCompat.requestPermissions( this,
                        new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, REQUEST_EXTERNAL_STORAGE);
            }
            if ( ContextCompat.checkSelfPermission( this,
                    Manifest.permission.CAMERA) == PackageManager.PERMISSION_DENIED){
                 ActivityCompat.requestPermissions( this, new String[]{Manifest.permission.CAMERA}, REQUEST_CAMERA);
            }
        }
    }

    @Override
    public void onRequestPermissionsResult( int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult( requestCode, permissions, grantResults);

        switch ( requestCode){
            case REQUEST_CAMERA:
                for( int i = 0; i < permissions.length; i++){
                    String permission = permissions[i];
                    int grantResult = grantResults[i];
                    if( permission.equals( Manifest.permission.CAMERA)){
                        if ( grantResult != PackageManager.PERMISSION_GRANTED){
                            finish();
                        }
                    }
                }
                break;

            case REQUEST_EXTERNAL_STORAGE:
                for( int i = 0; i < permissions.length; i++){
                    String permission = permissions[i];
                    int grantResult = grantResults[i];
                    if( permission.equals( Manifest.permission.WRITE_EXTERNAL_STORAGE)){
                        if ( grantResult != PackageManager.PERMISSION_GRANTED){
                            finish();
                        }
                    }
                }
                break;

        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        Handler handler = new Handler();
        handler.postDelayed( new Runnable() {
            @Override
            public void run() {

            }
        }, 100);
    }

    @Override
    protected void onActivityResult( int requestCode, int resultCode, Intent data) {
        super.onActivityResult( requestCode, resultCode, data);

        if ( resultCode != RESULT_OK){
            return;
        }

        switch (requestCode){
            case PICK_FROM_ALBUM:
                imageCaptureUri = data.getData();
                startActivityForResult( cropping(), CROP_FROM_IMAGE);   //CROP_FROM_CAMERA case문으로 이동
                break;

            case PICK_FROM_CAMERA:
                startActivityForResult( cropping(), CROP_FROM_IMAGE);
                break;

            case CROP_FROM_IMAGE:
                //크롭이 된 이후의 이미지를 넘겨 받음.
                //이미지뷰에 이미지를 보여주는 등의 부가작업후 임시파일 삭제함.
                if (resultCode != RESULT_OK) return;

                final Bundle extras = data.getExtras();

                //crop된 이미지를 저장하기 위한 file경로
                String filePath = Environment.getExternalStorageDirectory().getAbsolutePath()+
                        "/diaryPhoto/"+ System.currentTimeMillis()+ ".jpg";

                if ( extras != null){
                    //crop된 bitmap
                    Bitmap photo = extras.getParcelable("data");
                    diaryPhoto.setImageBitmap(photo);
                    storeCropImage( photo, filePath);
                    absolutePath = filePath;
                    break;
                }
                //임시 파일 삭제
                File f = new File( imageCaptureUri.getPath());
                if ( f.exists()){
                    f.delete();
                }

        }
    }


    //앨범에서 이미지 가져오기
    public void getFromAlbum(){
        Intent intent = new Intent( Intent.ACTION_PICK);
        intent.setType( MediaStore.Images.Media.CONTENT_TYPE);
        startActivityForResult( intent, PICK_FROM_ALBUM);
    }
    //촬영해서 이미지 가져오기
    public void getFromCamera(){
        Intent intent = new Intent( MediaStore.ACTION_IMAGE_CAPTURE);

        //임시로 사용할 파일의 경로를 생성
        String url = "tmp_"+ String.valueOf( System.currentTimeMillis())+".jpg";
        imageCaptureUri = Uri.fromFile( new File( Environment.getExternalStorageDirectory(), url));

        intent.putExtra( MediaStore.EXTRA_OUTPUT, imageCaptureUri);
        startActivityForResult( intent, PICK_FROM_CAMERA);
    }

    private Intent cropping(){
        //이미지를 가져온 이후의 리사이즈할 이미지 크기를 결정함.
        //이후에 이미지 크롭 어플리케이션을 호출함.
        Intent intent = new Intent( "com.android.camera.action.CROP");
        intent.setDataAndType( imageCaptureUri, "image/*");
        intent.putExtra( "outputX", 200);    // 크롭한 이미지 x축 크기
        intent.putExtra( "outputY", 200);    // 크롭한 이미지 y축 크기
        intent.putExtra( "aspectX", 1);      // 크롭박스의 x축 비율
        intent.putExtra( "aspectY", 1);      // 크롭박스의 y축 비율
        intent.putExtra( "scale", true);
        intent.putExtra( "return-data", true);

        return intent;
    }

    private void storeCropImage( Bitmap bitmap, String filePath){
        String dirPath = Environment.getExternalStorageDirectory().getAbsolutePath()+ "/diaryPhoto";
        File dirPhoto = new File( dirPath);

        //디렉터리에 폴더가 없으면 생성
        if (!dirPhoto.exists()){
            dirPhoto.mkdir();
        }

        File copyFile = new File( filePath);
        BufferedOutputStream out = null;

        try {
            copyFile.createNewFile();
            out = new BufferedOutputStream( new FileOutputStream(copyFile));
            bitmap.compress( Bitmap.CompressFormat.JPEG, 100, out);

            sendBroadcast( new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE, Uri.fromFile( copyFile)));
            out.flush();
            out.close();
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }

    private View.OnClickListener clickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            if ( diaryTitle.getText().toString().length() == 0){
                Toast.makeText(DiaryAdderActivity.this, "제목을 적어주세요!", Toast.LENGTH_SHORT).show();
            }
            else if ( diaryText.getText().toString().length() == 0){
                Toast.makeText(DiaryAdderActivity.this, "내용을 적어주세요!", Toast.LENGTH_SHORT).show();
            }
            else if ( absolutePath.length() == 0){
                Toast.makeText(DiaryAdderActivity.this, "사진을 선택해주세요!", Toast.LENGTH_SHORT).show();
            }
            else {
                String title = diaryTitle.getText().toString();
                String sentence = diaryText.getText().toString();
                String url = absolutePath;

                ArrayList<String> arrayList = new ArrayList<>(3);
                arrayList.add(title);
                arrayList.add(sentence);
                arrayList.add(url);

                DBUtil db = DBUtil.getInstance();
                db.insertData( DBUtil.TABLE_DIARY, arrayList);

                finish();
            }
        }
    };
}
