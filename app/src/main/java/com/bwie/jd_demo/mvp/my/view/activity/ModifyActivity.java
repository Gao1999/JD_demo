package com.bwie.jd_demo.mvp.my.view.activity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bwie.jd_demo.R;
import com.bwie.jd_demo.base.BaseActivity;
import com.bwie.jd_demo.mvp.home.model.bean.AdBean;
import com.bwie.jd_demo.mvp.my.model.bean.UploadBean;
import com.bwie.jd_demo.mvp.my.model.bean.UserInfoBean;
import com.bwie.jd_demo.mvp.my.presenter.MyPresenter;
import com.bwie.jd_demo.mvp.my.view.iview.MyView;
import com.facebook.drawee.view.SimpleDraweeView;

import java.io.File;
import java.io.FileOutputStream;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;

public class ModifyActivity extends BaseActivity<MyPresenter> implements MyView {

    private LinearLayout mLlUserIconSetting;
    private TextView mTvUserNameSetting;
    private LinearLayout mLlUserNameSetting;
    private TextView mTvNickNameSetting;
    private LinearLayout mLlNickNameSetting;
    private TextView mTvGenderSetting;
    private LinearLayout mLlGenderSetting;
    private LinearLayout mLlUserBirthdaySetting;
    private Button button;
    private SharedPreferences.Editor editor;

    private String[] item = {"相机", "相册", "取消"};
    private String path = Environment.getExternalStorageDirectory() + "/JdPic.png";
    private int uid;
    private SimpleDraweeView simpleDraweeView;

    @Override
    protected void initData() {
        presenter.UserInfoData(uid);
    }

    @Override
    protected void initListener() {
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String pic = "";
                Intent intent = getIntent();
                intent.putExtra("pic", pic);
                intent.putExtra("mobile", "请登录");
                setResult(2, intent);
                finish();

                editor.clear();
                editor.commit();
                finish();
            }
        });
        mLlUserIconSetting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog dialog = new AlertDialog.Builder(ModifyActivity.this)
                        .setTitle("相机相册")
                        .setIcon(R.mipmap.ic_launcher)
                        .setItems(item, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                switch (which) {
                                    case 0:
                                        //打开相机
                                        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                                        //将图片放到SD card
                                        intent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(new File(path)));
                                        startActivityForResult(intent, 100);
                                        break;
                                    case 1:
                                        //打开相册
                                        Intent intent1 = new Intent(Intent.ACTION_PICK);
                                        //设置图片格式
                                        intent1.setType("image/*");
                                        startActivityForResult(intent1, 200);
                                        break;
                                }
                            }
                        })

                        .create();
                dialog.show();
            }
        });
    }

    @Override
    protected void initView() {

        mLlUserIconSetting = (LinearLayout) findViewById(R.id.ll_userIcon_setting);
        mTvUserNameSetting = (TextView) findViewById(R.id.tv_userName_setting);
        mLlUserNameSetting = (LinearLayout) findViewById(R.id.ll_userName_setting);
        mTvNickNameSetting = (TextView) findViewById(R.id.tv_nickName_setting);
        mLlNickNameSetting = (LinearLayout) findViewById(R.id.ll_nickName_setting);
        mTvGenderSetting = (TextView) findViewById(R.id.tv_gender_setting);
        mLlGenderSetting = (LinearLayout) findViewById(R.id.ll_gender_setting);
        mLlUserBirthdaySetting = (LinearLayout) findViewById(R.id.ll_userBirthday_setting);
        button = findViewById(R.id.btn_cancel_setting);
        simpleDraweeView = findViewById(R.id.sdv_userIcon);

        SharedPreferences sharedPreferences = getSharedPreferences("jd", MODE_PRIVATE);
        uid = sharedPreferences.getInt("uid", 0);
        editor = sharedPreferences.edit();


    }

    @Override
    protected MyPresenter providerPresenter() {
        return presenter = new MyPresenter(this);
    }

    @Override
    protected int providerLayout() {
        return R.layout.activity_modify;
    }

    @Override
    public void Success(AdBean adBean) {

    }

    @Override
    public void Error(String error) {
        Toast.makeText(this, "" + error, Toast.LENGTH_SHORT).show();

    }

    @Override
    public void UpLoadSuccess(UploadBean uploadBean) {
        Toast.makeText(this, "" + uploadBean.getMsg(), Toast.LENGTH_SHORT).show();
        presenter.UserInfoData(uid);
    }

    @Override
    public void UserInfoSuccess(UserInfoBean userInfoBean) {
        Log.e("tag", "UserInfoSuccess: " + userInfoBean.getMsg());
        String icon = userInfoBean.getData().getIcon();
        simpleDraweeView.setImageURI(icon);

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        // 相机调用裁剪功能
        if (requestCode == 100 && resultCode == RESULT_OK) {
            Intent it = new Intent("com.android.camera.action.CROP");
            //得到拍完的照片进行裁剪
            it.setDataAndType(Uri.fromFile(new File(path)), "image/*");
            //设置是否支持裁剪
            it.putExtra("crop", true);
            //设置框的宽高比
            it.putExtra("aspactX", 1);
            it.putExtra("aspactY", 1);
            //设置输出的照片
            it.putExtra("outputX", 250);
            it.putExtra("outputY", 250);
            //将图片返回
            it.putExtra("return-data", true);

            startActivityForResult(it, 300);

        }

        //设置裁剪
        if (requestCode == 200 && resultCode == RESULT_OK) {
            //得到图片路径
            Uri uri = data.getData();
            //调用系统裁剪功能
            Intent it = new Intent("com.android.camera.action.CROP");
            //得到照片进行裁剪
            it.setDataAndType(uri, "image/*");
            //设置是否支持裁剪
            it.putExtra("crop", true);
            //设置框的宽高比
            it.putExtra("aspactX", 1);
            it.putExtra("aspactY", 1);
            //设置输出图片大小
            it.putExtra("outputX", 250);
            it.putExtra("outputY", 250);
            //将图片返回
            it.putExtra("return-data", true);

            startActivityForResult(it, 300);
        }
        //裁剪完后回到设置图片
        if (requestCode == 300 && resultCode == RESULT_OK) {

            Bitmap bitmap = data.getParcelableExtra("data");
            //获取文件路径
            File file = new File(getFilesDir().getAbsolutePath());
            if (!file.exists()) {
                //如果路径不存在就创建
                file.mkdirs();
            }
            //创建文件
            File file1 = new File(file, "photo.png");
            FileOutputStream fileOutputStream;
            try {
                //文件输出流
                fileOutputStream = new FileOutputStream(file1);
                //将bitmap写入文件流
                bitmap.compress(Bitmap.CompressFormat.PNG, 100, fileOutputStream);
                //刷新此输出流并强制将所有缓冲的输出字节被写出
                fileOutputStream.flush();
                fileOutputStream.close();
            } catch (Exception e) {
                e.printStackTrace();
            }

            //RequestBody封装了文件和文件的类型
            RequestBody requestBody = RequestBody.create(MediaType.parse("image/*"), file1);
            // MultipartBody.Part封装了接受的key和文件名字和RequestBody
            MultipartBody.Part part = MultipartBody.Part.createFormData("file", file1.getName(), requestBody);

            presenter.UpLoadDataNet(uid, part);
        }
    }
}
