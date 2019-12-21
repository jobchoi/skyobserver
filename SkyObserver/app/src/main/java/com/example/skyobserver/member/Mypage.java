package com.example.skyobserver.member;

import android.Manifest;
import android.app.Activity;
import android.app.ProgressDialog;
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
import com.example.skyobserver.MainActivity;
import com.example.skyobserver.R;
import com.example.skyobserver.msmap.RegiongpsDTO;

import org.json.JSONObject;

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
import java.util.ArrayList;
import java.util.Date;

public class Mypage extends AppCompatActivity {


    private String imageFilePath = null;
    private Uri photoUri;
    userUpdateWriteAsync pwa;
    File photoFile;
    URL url;
    File imgFile;
    private static final int GALLERY_CODE = 4444;
    private static final int CROP_GALLERY_CODE = 4447;
    private static final int CAMERA_CODE = 672;
    private static final int CROP_CAMERA_CODE = 6738;
    private static final int PERMISSIONS_CODE_REQUEST = 1001;
    TextView rc;
    EditText pwd;
    TextView email;
    EditText nickName;
    ImageView imageView;
    EditText sr ;
    SharedPreferences userPref;
    SharedPreferences.Editor editor;
    Button xbtn;

    //String sendUrl = Common.SERVER_URL + "/mypage.hanul";
    String sendUrl = Common.SERVER_URL + "/mypageupdate";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mypage);
        rc = findViewById(R.id.regioncode);
        sr = findViewById(R.id.selectRegion);
        pwd = findViewById(R.id.pwd_my);
        email = findViewById(R.id.mypage_email);
        nickName = findViewById(R.id.mypage_nickName);
        xbtn = findViewById(R.id.mypage_xbtn);
        imageView = findViewById(R.id.mypageimg);
        if (ActivityCompat.checkSelfPermission(this,
                Manifest.permission.READ_EXTERNAL_STORAGE) !=
                PackageManager.PERMISSION_GRANTED
                && ActivityCompat.checkSelfPermission(this,
                Manifest.permission.CAMERA) !=
                PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.READ_EXTERNAL_STORAGE,
                            Manifest.permission.CAMERA},
                    PERMISSIONS_CODE_REQUEST);
            return;
        }

        Button canclebtn = findViewById(R.id.myp_buttonCancel);
        canclebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               // Toast.makeText(getApplicationContext(), "등록 취소", Toast.LENGTH_LONG).show();
                finish();
            }
        });

        restoreState();

        Button mypageregion = findViewById(R.id.mypageregion);

        mypageregion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                RegionSelDialog bDialog = new RegionSelDialog(Mypage.this, Mypage.this);

                bDialog.popBoard();

            }
        });


        xbtn = findViewById(R.id.mypage_xbtn);
        xbtn.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (imageFilePath != null) {
                    imageFilePath.isEmpty();
                    userPref = getSharedPreferences("userPref", Activity.MODE_PRIVATE);
                    if (!userPref.getString("filename", "").equals("")) {
                        String filename = userPref.getString("filepath", "");
                        Glide.with(Mypage.this).load(Common.SERVER_URL + filename).into(imageView);
                    } else {
                        imageView.setImageResource(R.drawable.logo);
                    }

                    xbtn.setVisibility(View.GONE);
                }
                return true;
            }
        });
    }

    private class userUpdateWriteAsync extends AsyncTask<String, String, String> {

        ProgressDialog progressDialog;
        @Override
        protected void onPreExecute() {
            super.onPreExecute();

            progressDialog = ProgressDialog.show(Mypage.this, "MY Info", "데이터 저장중");
        }

        @Override
        protected String doInBackground(String... URI) {

            if (imageFilePath != null) {
                String imgPath = imageFilePath.toString();
                imgFile = new File(imgPath);
            }
            Log.d("마이어싱크", "imageFilePath : " + imageFilePath);

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
                dos.writeBytes("Content-Disposition: form-data; name=\"email\"\r\n\r\n" + email.getText().toString() + lindEnd);
                dos.writeBytes(twoHyphens + boundary + lindEnd); // header역할
                dos.writeBytes("Content-Disposition: form-data; name=\"pwd\"\r\n\r\n" + pwd.getText().toString() + lindEnd);
                dos.writeBytes(twoHyphens + boundary + lindEnd); // header역할
                dos.writeBytes("Content-Disposition: form-data; name=\"regioncode\"\r\n\r\n" + rc.getText().toString() + lindEnd);
                dos.writeBytes(twoHyphens + boundary + lindEnd); // header역할
                dos.writeBytes("Content-Disposition: form-data; name=\"address\"\r\n\r\n" + URLEncoder.encode("지역", "UTF-8") + lindEnd);

                userPref = getSharedPreferences("userPref", Activity.MODE_PRIVATE);
                String filename = userPref.getString("filename", "");
                //String filenameChk = filename.substring(filename.lastIndexOf("/")+1);

                if (imageFilePath != null) {
                    dos.writeBytes(twoHyphens + boundary + lindEnd); // header역할
                    dos.writeBytes("Content-Disposition: form-data; name=\"imageView\";filename=\"" + imageFilePath.toString().trim() + "\"" + lindEnd);
                }
                if (imageFilePath == null && !filename.equals("null")) {
                    Log.d("333333333", userPref.getString("filename", ""));

                    dos.writeBytes(twoHyphens + boundary + lindEnd); // header역할
                    dos.writeBytes("Content-Disposition: form-data; name=\"filename\"\r\n\r\n" + filename + lindEnd);
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
//                Log.d("결과_filename",imageFilePath.toString());

                if (conn.getResponseCode() == HttpURLConnection.HTTP_OK) {
                    http_result_ok = "200";

                    Log.i("서버통신", "성공");

                    InputStream in = conn.getInputStream();
                    ByteArrayOutputStream getServerRespon = new ByteArrayOutputStream();
                    byte[] buf = new byte[1024 * 8];
                    int len = 0;
                    while ((len = in.read(buf)) != -1) {
                        getServerRespon.write(buf, 0, len);
                    }


                    String returnServer = new String(getServerRespon.toByteArray(), "UTF-8");

                    JSONObject json2 = new JSONObject(returnServer);
                    JSONObject json = new JSONObject(json2.getString("login_info"));
                    // JSONArray jArr = json2.getJSONArray("login_info");
                    // JSONObject json = jArr.getJSONObject(0);

                    MemberDTO sDto = new MemberDTO();
//                        sDto.setEmail(json.getString("email"));
                    sDto.setPwd(json.getString("pwd"));
                    sDto.setName(json.getString("nickname"));
                    sDto.setFilename(json.getString("filename"));
                    sDto.setFilepath(json.getString("filepath"));
                    sDto.setRegioncode(json.getString("regioncode"));

                    editor = userPref.edit();
                    editor.putString("filename", sDto.getFilename());
                    editor.putString("filepath", sDto.getFilepath());
                    editor.putString("nickname", sDto.getName());
                    editor.putString("pwd", sDto.getPwd());
                    editor.putString("regioncode", sDto.getRegioncode());
                    editor.commit();
                    Thread.sleep(1000);
                    Log.i("서버응답", "결과 : " + new String(getServerRespon.toByteArray(), "UTF-8"));


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
            progressDialog.dismiss();
            Log.d("result_ok====> ", http_result_ok);
            if (http_result_ok.equals("200")) {
//                Toast.makeText(com.example.skyobserver.member.Mypage.this, "등록 성공", Toast.LENGTH_SHORT).show();
//                SharedPreferences userPref = getSharedPreferences("userPref", Activity.MODE_PRIVATE);
//                SharedPreferences.Editor editor = userPref.edit();
//                editor.putString("filename", imageFilePath.toString());
//                Log.d("Mypage", "profileUpdate ==> " + imageFilePath.toString());

                Intent intent = new Intent();
                setResult(RESULT_OK, intent);

                userPref = getSharedPreferences("userPref", Activity.MODE_PRIVATE);
                Log.i("쉐어드", userPref.getAll().toString());

                finish();
            } else {
                Toast.makeText(com.example.skyobserver.member.Mypage.this, "등록 실패", Toast.LENGTH_SHORT).show();
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
        if (resultCode != RESULT_OK) return;
        if (requestCode == CAMERA_CODE) {
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


            Uri uri = photoUri;
            File file = new File(String.valueOf(photoFile));
            Uri out = Uri.fromFile(file);
            Intent intent = getCropIntent(photoUri, out);
            startActivityForResult(intent, CROP_CAMERA_CODE);

           /* ((ImageView) findViewById(R.id.mypageimg)).setImageBitmap(rotate(bitmap, exifDegree));
            xbtn = findViewById(R.id.mypage_xbtn);
            xbtn.setVisibility(View.VISIBLE);*/

        } else if (requestCode == CROP_CAMERA_CODE) {
          /*  Bitmap bitmap = BitmapFactory.decodeFile(imageFilePath);
             ((ImageView) findViewById(R.id.mypageimg)).setImageBitmap(bitmap);
            xbtn = findViewById(R.id.mypage_xbtn);
            xbtn.setVisibility(View.VISIBLE);*/
            try {
                InputStream in = null;
                in = getContentResolver().openInputStream(data.getData());

                Bitmap img = BitmapFactory.decodeStream(in);
                in.close();

                ((ImageView) findViewById(R.id.mypageimg)).setImageBitmap(img);

                xbtn = findViewById(R.id.mypage_xbtn);
                xbtn.setVisibility(View.VISIBLE);
                imageFilePath = getRealPathFromURI(data.getData());
            } catch (Exception e) {
                e.printStackTrace();
            }

        }

        if (requestCode == GALLERY_CODE) {


            try {
                photoFile = createImageFile();
            } catch (IOException ex) {

            }
            Uri uri = data.getData();
            File file = new File(String.valueOf(photoFile));
            Uri out = Uri.fromFile(file);
            Intent intent = getCropIntent(uri, out);
            startActivityForResult(intent, CROP_GALLERY_CODE);

        } else if (requestCode == CROP_GALLERY_CODE) {


            try {
                InputStream in = getContentResolver().openInputStream(data.getData());

                Bitmap img = BitmapFactory.decodeStream(in);
                in.close();

                ((ImageView) findViewById(R.id.mypageimg)).setImageBitmap(img);

                xbtn = findViewById(R.id.mypage_xbtn);
                xbtn.setVisibility(View.VISIBLE);
                imageFilePath = getRealPathFromURI(data.getData());

            } catch (Exception e) {

            }
        }
    }

    protected void restoreState() {
        SharedPreferences userPref = getSharedPreferences("userPref", Activity.MODE_PRIVATE);

        //Log.d("mypage", "restoreState : " + userPref.getAll().values().toString());
        if (userPref.getAll().values().toString().length() > 2) {
            // 필요한 형식을 가져오가너 getAll로 모든 값을 사용.

       //     Log.d("mypage", "restore : " + userPref.getAll().values().toString());

            email.setText(userPref.getString("email", ""));
            pwd.setText(userPref.getString("pwd", ""));
            nickName.setText(userPref.getString("nickname", ""));
            if (!userPref.getString("regioncode", "null").equals("null")) {

                ArrayList<RegiongpsDTO> dto=MainActivity.regiongpsDTO;
               for (int i = 0; i < dto.size(); i++) {
                    if (userPref.getString("regioncode", "").equals(dto.get(i).getCode())) {


                            sr.setText(dto.get(i).getSido());
                        if (!dto.get(i).getGugun().equals("null")) {
                            sr.append(" " + dto.get(i).getGugun());
                        } else {
                            sr.append("");
                        }

                        if (!dto.get(i).getDong().equals("null")) {
                            sr.append(" " + dto.get(i).getDong());
                        } else {
                            sr.append("");
                        }


                        if (!dto.get(i).getRi().equals("null")) {
                            sr.append(" " + dto.get(i).getRi());
                        } else {
                            sr.append("");
                        }
                        rc.setText(dto.get(i).getCode());
                        break;



                    }
                }
            }
            if (!userPref.getString("filename","null").equals("null")) {
                this.imageView = findViewById(R.id.mypageimg);

                String filename = userPref.getString("filepath", "");


                RequestOptions requestOptions = new RequestOptions();
                requestOptions.diskCacheStrategy(DiskCacheStrategy.NONE);
                requestOptions.skipMemoryCache(true);
                requestOptions.signature(new ObjectKey(System.currentTimeMillis()));
                requestOptions.transform(new RoundedCorners(20));


                Glide.with(this)
                        .load(Common.SERVER_URL+filename)
                        .apply(requestOptions)
                        .into(imageView);



            }
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        switch (requestCode) {
            case PERMISSIONS_CODE_REQUEST:
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

            pwa = new userUpdateWriteAsync();
            pwa.execute();
        }
    }


    private void takePhoto(String photo) {
        if (photo.equals("camera")) {
            Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
            if (takePictureIntent.resolveActivity(getPackageManager()) != null) {
                photoFile = null;
                try {
                    this.photoFile = createImageFile();
                } catch (IOException ex) {
                    // Error occurred while creating the File
                }

                // photoUri는 createImageFile() 만들어진 File 객체에서 취득
                if (photoFile != null) {
                    // FileProvider는 AndroidManifest에서 추가했던 <Provider>요소를 이용해 uri를 불러오는 역할
                    this.photoUri = FileProvider.getUriForFile(Mypage.this, getPackageName(), photoFile);
                    // this.photoUri = Uri.fromFile(photoFile);
                    takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT, photoUri);
                    startActivityForResult(takePictureIntent, CAMERA_CODE);
                }
            }
        } else if (photo.equals("gallery")) {
            Intent intent = new Intent();
            intent.setType("image/*");
            intent.setAction(Intent.ACTION_PICK);
            startActivityForResult(intent, GALLERY_CODE);
//            mua = new MemberUpdateAsync();
//            mua.execute(imageFilePath.toString());
          //  Toast.makeText(getApplicationContext(), "사진 파일 선택", Toast.LENGTH_SHORT).show();
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

    private Intent getCropIntent(Uri inputUri, Uri outPutUri) {
        Intent intent = new Intent("com.android.camera.action.CROP");
        intent.setDataAndType(inputUri, "image/*");
        intent.putExtra("aspectX", 1);
        intent.putExtra("aspectY", 1);
        intent.putExtra("outputX", true);
       intent.putExtra("outputY", true);
        intent.putExtra("scale", true);
        intent.putExtra(MediaStore.EXTRA_OUTPUT, outPutUri);
        intent.putExtra("outputFormat", Bitmap.CompressFormat.PNG.toString());
        intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
        intent.addFlags(Intent.FLAG_GRANT_WRITE_URI_PERMISSION);

        return intent;
    }

    public void setRegion(ArrayList<RegiongpsDTO> searchDTO, int pos) {







        if (!searchDTO.get(pos).getSido().equals("null")) {
            sr.setText(searchDTO.get(pos).getSido());
        } else {
            sr.setText("");
        }


        if (!searchDTO.get(pos).getGugun().equals("null")) {
            sr.append(" " + searchDTO.get(pos).getGugun());
        } else {
            sr.append("");
        }


        if (!searchDTO.get(pos).getDong().equals("null")) {
            sr.append(" " + searchDTO.get(pos).getDong());
        } else {
            sr.append("");
        }


        if (!searchDTO.get(pos).getRi().equals("null")) {
            sr.append(" " + searchDTO.get(pos).getRi());
        } else {
            sr.append("");
        }

        rc.setText(searchDTO.get(pos).getCode());

    }
}