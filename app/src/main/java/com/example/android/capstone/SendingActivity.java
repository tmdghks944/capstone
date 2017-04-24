package com.example.android.capstone;

import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import android.widget.SimpleAdapter;
import android.widget.SimpleAdapter.ViewBinder;

import android.content.Intent;
import android.widget.TextView;
import android.widget.Toast;

import com.amazonaws.mobileconnectors.s3.transferutility.TransferListener;
import com.amazonaws.mobileconnectors.s3.transferutility.TransferObserver;
import com.amazonaws.mobileconnectors.s3.transferutility.TransferState;
import com.amazonaws.mobileconnectors.s3.transferutility.TransferType;
import com.amazonaws.mobileconnectors.s3.transferutility.TransferUtility;

public class SendingActivity extends AppCompatActivity {

    String image_path;
    private TransferUtility transferUtility;
    private ArrayList<HashMap<String, Object>> transferRecordMaps;
    private List<TransferObserver> observers;
    private int checkedIndex;
    private SimpleAdapter simpleAdapter;
    private static final String TAG = "SendingActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        transferUtility = Util.getTransferUtility(this);
        setContentView(R.layout.activity_sending);
        image_path = getIntent().getStringExtra("imagePath");
        Bitmap myBitmap = BitmapFactory.decodeFile(image_path);
        ImageView uploadimage = (ImageView)findViewById(R.id.targetImg);
        uploadimage.setImageBitmap(myBitmap);

    }
    public void trasferImg(View view){
        beginUpload(image_path);
    }

    private void beginUpload(String filePath) {
        if (filePath == null) {
            Toast.makeText(this, "Could not find the filepath of the selected file",
                    Toast.LENGTH_LONG).show();
            return;
        }
        File file = new File(filePath);
        TransferObserver observer = transferUtility.upload(Constants.BUCKET_NAME, file.getName(), file);
        Toast.makeText(SendingActivity.this,"전송 되었습니다.",Toast.LENGTH_SHORT).show();
        //올린 이미지 보여주기 조금있다가 얼굴인식된 사진으로 보여줘야함.
      //Bitmap myBitmap = BitmapFactory.decodeFile(file.getAbsolutePath());
      //ImageView uploadimage = (ImageView)findViewById(R.id.uploadImage);
      //uploadimage.setImageBitmap(myBitmap);
    }
}
