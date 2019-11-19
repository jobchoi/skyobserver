package com.example.skyobserver.member;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.media.ExifInterface;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.FileProvider;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.resource.bitmap.CenterCrop;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.bumptech.glide.request.RequestOptions;
import com.bumptech.glide.signature.ObjectKey;
import com.example.skyobserver.Common;
import com.example.skyobserver.R;

import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Mypage extends AppCompatActivity {

    private static final int REQUEST_IMAGE_CAPTURE = 672;
    private String imageFilePath = null;
    private Uri photoUri;
    ProductWriteAsync pwa;
    File photoFile;
    URL url;
    File imgFile;
    private static final int REQUEST_CODE = 0;
    private final int REQUEST_CODE_PERMISSIONS = 1001;

    EditText pwd;
    TextView email;
    EditText nickName;
    ImageView imageView;

    Button xbtn;

    String sendUrl = Common.SERVER_URL + "/mypage.ob";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mypage);

        pwd = findViewById(R.id.pwd_my);
        email = findViewById(R.id.mypage_email);
        nickName = findViewById(R.id.mypage_nickName);
        xbtn = findViewById(R.id.mypage_xbtn);

        if (ActivityCompat.checkSelfPermission(this,
                Manifest.permission.READ_EXTERNAL_STORAGE) !=
                PackageManager.PERMISSION_GRANTED
                && ActivityCompat.checkSelfPermission(this,
                Manifest.permission.CAMERA) !=
                PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.READ_EXTERNAL_STORAGE,
                            Manifest.permission.CAMERA},
                    REQUEST_CODE_PERMISSIONS);
            return;
        }

        Button canclebtn = findViewById(R.id.myp_buttonCancel);
        canclebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), "등록 취소", Toast.LENGTH_LONG).show();
                finish();
            }
        });

        restoreState();

        xbtn = findViewById(R.id.mypage_xbtn);
        xbtn.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (imageFilePath != null) {
                    imageFilePath.isEmpty();
                    imageView.setImageResource(android.R.drawable.picture_frame);

                    xbtn.setVisibility(View.GONE);
                }
                return true;
            }
        });
    }

    private class ProductWriteAsync extends AsyncTask<String, String, String> {

        @Override
        protected String doInBackground(String... URI) {

            if (imageFilePath != null) {
                String imgPath = imageFilePath.toString();
                imgFile = new File(imgPath);
            }

            // multipart/form-data 타입으로 전송시 각각의 변수마다 들어가게 되는 문자열(twoHyphens, boundary) 이게 없으면 전송 안됨.
            String twoHyphens = "--";
            String boundary = "*****";
            DataOutputStream dos = null;
            String lindEnd = "\r\n"; // 개행문자
            byte[] buffer;
            int maxBufferSize = 10 * 1024 * 1024, ret = 0; // 버퍼 사이즈

            String http_result_ok = "fail";

            try {
                FileInputStream fis = null;
                if (imageFilePath != null) {
                    fis = new FileInputStream(imgFile);
                }

                url = new URL(sendUrl);
                HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                conn.setUseCaches(false); // 캐쉬 사용 안함.
                conn.setDoOutput(true);

                // POST 방식 설정(파일은 반드시 POST방식으로 전송해야 함)
                conn.setRequestMethod("POST");

                // post 방식으로 넘길 자료 정보 설정(header)
                conn.setRequestProperty("Connection", "Keep-Alive");
                conn.setRequestProperty("Content-Type", "multipart/form-data;boundary=" + boundary);

                // 파일업로드 이므로 다양한 파일 포맷을 지원하기 위해 DataOutputStream 객체 생성
                dos = new DataOutputStream(conn.getOutputStream());

                // 저장할 내용
                dos.writeBytes(twoHyphens + boundary + lindEnd); // header역할
                dos.writeBytes("Content-Disposition: form-data; name=\"nickname\"\r\n\r\n" + URLEncoder.encode(nickName.getText().toString(), "UTF-8") + lindEnd);
                dos.writeBytes(twoHyphens + boundary + lindEnd); // header역할
                dos.writeBytes("Content-Disposition: form-data; name=\"email\"\r\n\r\n" + URLEncoder.encode(email.getText().toString(), "UTF-8") + lindEnd);
                dos.writeBytes(twoHyphens + boundary + lindEnd); // header역할
                dos.writeBytes("Content-Disposition: form-data; name=\"pwd\"\r\n\r\n" + URLEncoder.encode(pwd.getText().toString(), "UTF-8") + lindEnd);
                dos.writeBytes(twoHyphens + boundary + lindEnd); // header역할
                dos.writeBytes("Content-Disposition: form-data; name=\"id1\"\r\n\r\n" + URLEncoder.encode("1", "UTF-8") + lindEnd);

                if (imageFilePath != null) {
                    dos.writeBytes(twoHyphens + boundary + lindEnd); // header역할
                    dos.writeBytes("Content-Disposition: form-data; name=\"imageView\";filename=\"" + imageFilePath.toString().trim() + "\"" + lindEnd);
                }
                dos.writeBytes(lindEnd);

                if (imageFilePath != null) {
                    buffer = new byte[maxBufferSize];
                    int length = -1;
                    while ((length = fis.read(buffer)) != -1) {
                        dos.write(buffer, 0, length);
                    }
                    // 여기서 전송 끝 -> 컨트롤러로 감
                    dos.writeBytes(lindEnd);
                }
                dos.writeBytes(twoHyphens + boundary + twoHyphens + lindEnd);

                Log.d("결과_닉네임 : ", nickName.getText().toString());
                Log.d("결과_email : ", email.getText().toString());
                Log.d("결과_pwd : ", pwd.getText().toString());
                Log.d("결과_filename",imageFilePath.toString());

                if (conn.getResponseCode() == HttpURLConnection.HTTP_OK) {
                    http_result_ok = "200";
                    String filename;

                    Log.i("서버통신", "성공");

                    try (InputStream in = conn.getInputStream(); ByteArrayOutputStream getServerRespon = new ByteArrayOutputStream()) {
                        byte[] buf = new byte[1024 * 8];
                        int len = 0;
                        while ((len = in.read(buf)) != -1) {
                            getServerRespon.write(buf, 0, len);
                        }
                        System.out.println("return to Server" + new String(getServerRespon.toByteArray(), "UTF-8"));

                        filename = getServerRespon.toByteArray().toString();

                        SharedPreferences userPref = getSharedPreferences("userPref", Activity.MODE_PRIVATE);
                        SharedPreferences.Editor editor = userPref.edit();
                        editor.putString("filename",filename);
                    }

                } else {
                    Log.i("conn.getResponseCode()", "" + conn.getResponseCode());
                    Log.i("서버통신", "실패");
                }

                fis.close();
                dos.flush();
                dos.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
            return http_result_ok;
        }

        //수정 (2019.10.16)
        @Override
        protected void onPostExecute(String http_result_ok) {
            Log.d("result_ok====> ", http_result_ok);
            if (http_result_ok.equals("200")) {
                Toast.makeText(Mypage.this, "등록 성공", Toast.LENGTH_SHORT).show();
                finish();
            } else {
                Toast.makeText(Mypage.this, "등록 실패", Toast.LENGTH_SHORT).show();
            }
        }
    }

    /*
     *  이미지 파일을 생성 : 이미지가 저장된 파일을 만드는게 아니라 이미지가 저장될 파일을 만드는 기능
     * */

    // 사진 찍고나서 작은 뷰에 담아놓는 메소드
    private Bitmap rotate(Bitmap bitmap, float degree) {
        Matrix matrix = new Matrix();
        matrix.postRotate(degree);
        return Bitmap.createBitmap(bitmap, 0, 0, bitmap.getWidth(), bitmap.getHeight(), matrix, true);
    }

    // 앨범에서 사진 가지고 올때 그 사진의 실제 경로를 가지고 오는 메소드
    private String getRealPathFromURI(Uri contentURI) {
        String result;
        Cursor cursor = getContentResolver().query(contentURI, null, null, null, null);
        if (cursor == null) {
            result = url.getPath();
        } else {
            cursor.moveToFirst();
            int idx = cursor.getColumnIndex(MediaStore.Images.ImageColumns.DATA);
            result = cursor.getString(idx);
            cursor.close();
        }
        return result;
    }

    // 사진 찍었을때 폰의 방향대로 사진방향을 돌려주는 메소드
    private int exifOrientationToDegrees(int exifOrientation) {
        if (exifOrientation == ExifInterface.ORIENTATION_ROTATE_90) {
            return 90;
        } else if (exifOrientation == ExifInterface.ORIENTATION_ROTATE_180) {
            return 180;
        } else if (exifOrientation == ExifInterface.ORIENTATION_ROTATE_270) {
            return 270;
        }
        return 0;
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK) {
            Bitmap bitmap = BitmapFactory.decodeFile(imageFilePath);
            ExifInterface exif = null;

            try {
                exif = new ExifInterface(imageFilePath);
            } catch (IOException e) {
                e.printStackTrace();
            }

            int exifOrientation;
            int exifDegree;

            if (exif != null) {
                exifOrientation = exif.getAttributeInt(ExifInterface.TAG_ORIENTATION, ExifInterface.ORIENTATION_NORMAL);
                exifDegree = exifOrientationToDegrees(exifOrientation);
            } else {
                exifDegree = 0;
            }

            ((ImageView) findViewById(R.id.imageView)).setImageBitmap(rotate(bitmap, exifDegree));
            xbtn = findViewById(R.id.mypage_xbtn);
            xbtn.setVisibility(View.VISIBLE);
        }

        if (requestCode == REQUEST_CODE) {
            if (resultCode == RESULT_OK) {
                try {
                    InputStream in = getContentResolver().openInputStream(data.getData());

                    Bitmap img = BitmapFactory.decodeStream(in);
                    in.close();

                    ((ImageView) findViewById(R.id.imageView)).setImageBitmap(img);
                    xbtn = findViewById(R.id.xbtn);
                    xbtn.setVisibility(View.VISIBLE);
                    imageFilePath = getRealPathFromURI(data.getData());

                } catch (Exception e) {

                }
            } else if (resultCode == RESULT_CANCELED) {
                Toast.makeText(Mypage.this, "사진 선택 취소", Toast.LENGTH_SHORT).show();
            }
        }
    }

    protected void restoreState() {
        SharedPreferences userPref = getSharedPreferences("userPref", Activity.MODE_PRIVATE);

        Log.d("mypage","restoreState : "+ userPref.getAll().values().toString());
        if (userPref.getAll().values().toString().length() > 2) {
            // 필요한 형식을 가져오가너 getAll로 모든 값을 사용.

            Log.d("mypage","restore : "+ userPref.getAll().values().toString());
            String filename = userPref.getString("filename","");

            email.setText(userPref.getString("email", ""));
            pwd.setText(userPref.getString("pwd", ""));
            nickName.setText(userPref.getString("name", ""));


            this.imageView=findViewById(R.id.imageView);

            //Glide.with(this).load(filename)).into(imageView);
            Glide.with(this).load(filename).into(this.imageView);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        switch (requestCode) {
            case REQUEST_CODE_PERMISSIONS:
                if (ActivityCompat.checkSelfPermission(this,
                        Manifest.permission.CAMERA)
                        != PackageManager.PERMISSION_GRANTED
                        && ActivityCompat.checkSelfPermission(this,
                        Manifest.permission.READ_EXTERNAL_STORAGE)
                        != PackageManager.PERMISSION_GRANTED) {
                    Toast.makeText(this, "권한 체크 거부 됨.", Toast.LENGTH_SHORT).show();
                }
                return;
        }
    }

    public void onClick(View v) {
        if (v.getId() == R.id.mypage_camerabtn) {
            takePhoto("camera");
        } else if (v.getId() == R.id.gallerybtn) {
            takePhoto("gallery");
        } else if (v.getId() == R.id.updateMypagebutton) {
            pwa = new ProductWriteAsync();
            pwa.execute();
        }
    }


    private void takePhoto(String photo) {
        if (photo.equals("camera")) {
            Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
            if (takePictureIntent.resolveActivity(getPackageManager()) != null) {
                photoFile = null;
                try {
                    photoFile = createImageFile();
                } catch (IOException ex) {
                    // Error occurred while creating the File
                }

                // photoUri는 createImageFile() 만들어진 File 객체에서 취득
                if (photoFile != null) {
                    // FileProvider는 AndroidManifest에서 추가했던 <Provider>요소를 이용해 uri를 불러오는 역할
                    photoUri = FileProvider.getUriForFile(Mypage.this, getPackageName(), photoFile);
                    takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT, photoUri);
                    startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE);
                }
            }
        } else if (photo.equals("gallery")) {
            Intent intent = new Intent();
            intent.setType("image/*");
            intent.setAction(Intent.ACTION_PICK);
            startActivityForResult(intent, REQUEST_CODE);

            Toast.makeText(getApplicationContext(), "사진 파일 선택", Toast.LENGTH_SHORT).show();
        }

    }

    /*
     *  이미지 파일을 생성 : 이미지가 저장된 파일을 만드는게 아니라 이미지가 저장될 파일을 만드는 기능
     * */
    private File createImageFile() throws IOException {
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        String imageFileName = "TEST_" + timeStamp + "_";
        File storageDir = getExternalFilesDir(Environment.DIRECTORY_PICTURES);
        File image = File.createTempFile(
                imageFileName,      /* prefix */
                ".jpg",         /* suffix */
                storageDir          /* directory */
        );
        imageFilePath = image.getAbsolutePath();
        Log.i("imageFilePath", "==========" + imageFilePath);
        return image;
    }
}

