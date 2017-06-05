package com.example.android.capstone;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URISyntaxException;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.ContentUris;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.provider.DocumentsContract;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.hardware.Camera;
import android.util.Log;
import android.hardware.Camera.CameraInfo;
import android.widget.ImageView;
import android.content.pm.PackageManager;
import android.widget.TextView;
import android.widget.Toast;

import com.amazonaws.mobileconnectors.s3.transferutility.TransferObserver;
import com.amazonaws.mobileconnectors.s3.transferutility.TransferUtility;

import org.w3c.dom.Text;

public class CameraActivity extends AppCompatActivity {
    private final static String DEBUG_TAG = "CameraActivity";
    private static final String TAG = "CameraActivity";
    private Camera camera;
    private int cameraId = 0;
    private TransferUtility transferUtility;
    Handler mHandler;
    ProgressDialog mProgressDialog;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_camera);
        transferUtility = Util.getTransferUtility(this);
    }

    public void uploadImg(View view){
        Intent intent = new Intent();
        if (Build.VERSION.SDK_INT >= 19) {
            // For Android versions of KitKat or later, we use a
            // different intent to ensure
            // we can get the file path from the returned intent URI
            intent.setAction(Intent.ACTION_OPEN_DOCUMENT);
            intent.addCategory(Intent.CATEGORY_OPENABLE);
            intent.putExtra(Intent.EXTRA_LOCAL_ONLY, true);
        } else {
            intent.setAction(Intent.ACTION_GET_CONTENT);
        }
        intent.setType("image/*");
        startActivityForResult(intent, 0);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == Activity.RESULT_OK) {
            GlobalVariable globalvariable = (GlobalVariable)getApplication();
            Uri uri = data.getData();
            try {
                String path = getPath(uri);
                beginUpload(path);
                globalvariable.setcameradone(true);
                showMessage();
                mHandler = new Handler();
                mHandler.postDelayed(new Runnable(){
                    @Override
                    public void run(){
                        beginDownload();
                    }
                },15000);
                mHandler.postDelayed(new Runnable(){
                    @Override
                    public void run(){
                        Intent intent = new Intent(CameraActivity.this,SettingActivity.class);
                        startActivity(intent);
                    }
                },18000);
            } catch (URISyntaxException e) {
                Toast.makeText(this,
                        "Unable to get the file from the given URI.  See error log for details",
                        Toast.LENGTH_LONG).show();
                Log.e(TAG, "Unable to upload file from the given uri", e);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private int findFrontFacingCamera() {
        int cameraId = -1;
        // Search for the front facing camera
        int numberOfCameras = Camera.getNumberOfCameras();
        for (int i = 0; i < numberOfCameras; i++) {
            CameraInfo info = new CameraInfo();
            Camera.getCameraInfo(i, info);
            if (info.facing == CameraInfo.CAMERA_FACING_FRONT) {
                cameraId = i;
                break;
            }
        }
        return cameraId;
    }

    @Override
    protected void onPause() {
        if (camera != null) {
            camera.release();
            camera = null;
        }
        super.onPause();
    }

    private void beginUpload(String filePath) throws IOException {
        if (filePath == null) {
            Toast.makeText(this, "Could not find the filepath of the selected file",
                    Toast.LENGTH_LONG).show();
            return;
        }
        //이 부분에서 파일 이름을 targetfile로 바꿔야함 (기존 사진의 이름을 rename)
        //밑의 4줄은 파일 형식을 알아오는 것. extension에 jpg가 들어가있음.
        File file = new File(filePath);
        String filename = file.getName();
        String filenameArray[] = filename.split("\\.");
        String extension = filenameArray[filenameArray.length-1];
        File file2 = new File(Environment.getExternalStorageDirectory().toString() +"/" + "targetfile.jpg");
        copy(file,file2);
        TransferObserver observer = transferUtility.upload(Constants.BUCKET_NAME, file2.getName(), file2);
    }

    private void beginDownload() {
        // Location to download files from S3 to. You can choose any accessible
        // file.
        File file = new File(Environment.getExternalStorageDirectory().toString() + "/" + "result.txt");
        TransferObserver observer = transferUtility.download(Constants.BUCKET_NAME, "result.txt", file);
        Toast.makeText(CameraActivity.this,"결과 다운로드",Toast.LENGTH_SHORT).show();
    }

    public void copy(File src, File dst) throws IOException {
        InputStream in = new FileInputStream(src);
        OutputStream out = new FileOutputStream(dst);

        // Transfer bytes from in to out
        byte[] buf = new byte[1024];
        int len;
        while ((len = in.read(buf)) > 0) {
            out.write(buf, 0, len);
        }
        in.close();
        out.close();
    }

    public void showMessage(){
        mHandler = new Handler();
        mProgressDialog = ProgressDialog.show(CameraActivity.this, "",
                "사진을 전송합니다.", true);
        mHandler.postDelayed(new Runnable() {
            @Override
            public void run() {
                try {
                    if (mProgressDialog != null && mProgressDialog.isShowing()) {
                        mProgressDialog.dismiss();
                        mProgressDialog = ProgressDialog.show(CameraActivity.this, "",
                                "얼굴을 인식합니다.", true);
                        mHandler.postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                try {
                                    if (mProgressDialog != null && mProgressDialog.isShowing()) {
                                        mProgressDialog.dismiss();
                                        mProgressDialog = ProgressDialog.show(CameraActivity.this, "",
                                                "프로그램을 통해 결과를 출력합니다.", true);
                                        mHandler.postDelayed(new Runnable() {
                                            @Override
                                            public void run() {
                                                try {
                                                    if (mProgressDialog != null && mProgressDialog.isShowing()) {
                                                        mProgressDialog.dismiss();
                                                    }
                                                } catch (Exception e) {
                                                    e.printStackTrace();
                                                }
                                            }
                                        }, 7000);
                                    }
                                } catch (Exception e) {
                                    e.printStackTrace();
                                }
                            }
                        }, 7000);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }, 5000);
    }

    @SuppressLint("NewApi")
    private String getPath(Uri uri) throws URISyntaxException {
        final boolean needToCheckUri = Build.VERSION.SDK_INT >= 19;
        String selection = null;
        String[] selectionArgs = null;
        // Uri is different in versions after KITKAT (Android 4.4), we need to
        // deal with different Uris.
        if (needToCheckUri && DocumentsContract.isDocumentUri(getApplicationContext(), uri)) {
            if (isExternalStorageDocument(uri)) {
                final String docId = DocumentsContract.getDocumentId(uri);
                final String[] split = docId.split(":");
                return Environment.getExternalStorageDirectory() + "/" + split[1];
            } else if (isDownloadsDocument(uri)) {
                final String id = DocumentsContract.getDocumentId(uri);
                uri = ContentUris.withAppendedId(
                        Uri.parse("content://downloads/public_downloads"), Long.valueOf(id));
            } else if (isMediaDocument(uri)) {
                final String docId = DocumentsContract.getDocumentId(uri);
                final String[] split = docId.split(":");
                final String type = split[0];
                if ("image".equals(type)) {
                    uri = MediaStore.Images.Media.EXTERNAL_CONTENT_URI;
                } else if ("video".equals(type)) {
                    uri = MediaStore.Video.Media.EXTERNAL_CONTENT_URI;
                } else if ("audio".equals(type)) {
                    uri = MediaStore.Audio.Media.EXTERNAL_CONTENT_URI;
                }
                selection = "_id=?";
                selectionArgs = new String[] {
                        split[1]
                };
            }
        }
        if ("content".equalsIgnoreCase(uri.getScheme())) {
            String[] projection = {
                    MediaStore.Images.Media.DATA
            };
            Cursor cursor = null;
            try {
                cursor = getContentResolver()
                        .query(uri, projection, selection, selectionArgs, null);
                int column_index = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
                if (cursor.moveToFirst()) {
                    return cursor.getString(column_index);
                }
            } catch (Exception e) {
            }
        } else if ("file".equalsIgnoreCase(uri.getScheme())) {
            return uri.getPath();
        }
        return null;
    }

    public static boolean isExternalStorageDocument(Uri uri) {
        return "com.android.externalstorage.documents".equals(uri.getAuthority());
    }
    public static boolean isDownloadsDocument(Uri uri) {
        return "com.android.providers.downloads.documents".equals(uri.getAuthority());
    }
    public static boolean isMediaDocument(Uri uri) {
        return "com.android.providers.media.documents".equals(uri.getAuthority());
    }

    public void captureImg(View view) {
        // do we have a camera?
        if (!getPackageManager()
                .hasSystemFeature(PackageManager.FEATURE_CAMERA)) {
            Toast.makeText(this, "No camera on this device", Toast.LENGTH_LONG)
                    .show();
        } else {
            cameraId = findFrontFacingCamera();
            if (cameraId < 0) {
                Toast.makeText(this, "No front facing camera found.",
                        Toast.LENGTH_LONG).show();

            } else {
                Intent intent = new Intent(CameraActivity.this,FrontCamera.class);
                startActivity(intent);
            }
        }
    }
}