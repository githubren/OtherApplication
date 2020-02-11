package com.example.computer_ren.otherapplication;

import android.app.Activity;
import android.content.ContentValues;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class ContentProviderActivitty extends AppCompatActivity implements View.OnClickListener {
    private Button insertBtn,deleteBtn,updateBtn,queryBtn;
    private TextView showTv;

    public static final String URI_STRING = "content://com.example.computer_ren.androidipc_demo.StudentProvider";
    public static final String AGE = "age";
    public static final String NAME = "name";
    public static final String SEX = "sex";
    public static final String METHOD = "contentProviderMethod";
    private Uri uri = Uri.parse(URI_STRING);


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_content_provider);

        initView();

    }

    private void initView() {
        insertBtn = findViewById(R.id.insert_btn);
        deleteBtn = findViewById(R.id.delete_btn);
        updateBtn = findViewById(R.id.update_btn);
        queryBtn = findViewById(R.id.query_btn);
        showTv = findViewById(R.id.show_tv);

        insertBtn.setOnClickListener(this);
        deleteBtn.setOnClickListener(this);
        updateBtn.setOnClickListener(this);
        queryBtn.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.insert_btn:
                ContentValues contentValues = new ContentValues();
                contentValues.put(NAME,"张三");
//                contentValues.put(AGE,24);
                contentValues.put(SEX,"男");
                getContentResolver().insert(uri,contentValues);
                break;
            case R.id.delete_btn:
                getContentResolver().delete(uri,"name = ?",new String[]{"张三"});
                break;
            case R.id.update_btn:
                ContentValues contentValues1= new ContentValues();
                contentValues1.put(NAME,"张三");
//                contentValues1.put(AGE,24);
                contentValues1.put(SEX,"女");
                getContentResolver().update(uri,contentValues1,"name = ?",new String[]{"张三"});
                break;
            case R.id.query_btn:
                Cursor cursor = getContentResolver().query(uri,new String[]{NAME,/*AGE,*/SEX},null,null,null);
                if (cursor != null) {
                    List<Student> students = new ArrayList<>();
                    while (cursor.moveToNext()){
                        Student student = new Student(cursor.getString(0),/*cursor.getInt(1),*/cursor.getString(1));
                        students.add(student);
                    }
                    showTv.setText(students.toString());
                }
                break;
        }
    }
}
