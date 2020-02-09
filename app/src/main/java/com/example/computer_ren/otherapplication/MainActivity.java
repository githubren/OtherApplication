package com.example.computer_ren.otherapplication;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.os.RemoteException;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.computer_ren.androidipc_demo.Book;
import com.example.computer_ren.androidipc_demo.BookManager;

import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private Button bindServiceBtn;
    private Button unbindServiceBtn;
    private Button addBookBtn;
    private Button getBooksBtn;
    private TextView showBooksTv;

    private BookManager mBookManager = null;
    private boolean isBind = false;//绑定服务的标志符

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();
    }

    private void initView() {
        bindServiceBtn = findViewById(R.id.btn_bind);
        unbindServiceBtn = findViewById(R.id.btn_unbind);
        addBookBtn = findViewById(R.id.btn_add_book);
        getBooksBtn = findViewById(R.id.btn_get_book);
        showBooksTv = findViewById(R.id.tv_show_books);

        bindServiceBtn.setOnClickListener(this);
        unbindServiceBtn.setOnClickListener(this);
        addBookBtn.setOnClickListener(this);
        getBooksBtn.setOnClickListener(this);
    }

    ServiceConnection mServiceConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            mBookManager = BookManager.Stub.asInterface(service);
            Toast.makeText(MainActivity.this, "绑定服务成功", Toast.LENGTH_SHORT).show();
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            Log.e("TAG", "onServiceDisconnected");
        }
    };

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_bind://绑定服务
                bindService();
                break;
            case R.id.btn_unbind://解绑服务
                unbindService();
                break;
            case R.id.btn_add_book://增加书
                addBook();
                break;
            case R.id.btn_get_book://获取书列表
                getBooks();
                break;
        }
    }

    /**
     * 解绑服务
     */
    private void unbindService() {
        if (isBind) {
            unbindService(mServiceConnection);
            mBookManager = null;
            Toast.makeText(this, "成功解绑服务", Toast.LENGTH_SHORT).show();
            isBind = false;
        }
    }

    /**
     * 获取书列表
     */
    private void getBooks() {
        if (!isBind) {
            Toast.makeText(this, "请先绑定服务", Toast.LENGTH_SHORT).show();
            return;
        }
        try {
            List<Book> books = mBookManager.getBooks();
            showBooksTv.setText(books.toString());
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    /**
     * 增加书
     */
    private void addBook() {
        if (!isBind) {
            Toast.makeText(this, "请先绑定服务", Toast.LENGTH_SHORT).show();
            return;
        }
        try {
            mBookManager.addBook(new Book("西游记"+System.currentTimeMillis(),69));
            Toast.makeText(this, "添加成功", Toast.LENGTH_SHORT).show();
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    /**
     * 绑定服务
     */
    private void bindService() {
        if (!isBind) {
            Intent intent = new Intent();
            intent.setAction("com.example.computer_ren.androidipc_demo.AIDLService");
            intent.setPackage("com.example.computer_ren.androidipc_demo");
            bindService(intent,mServiceConnection, Context.BIND_AUTO_CREATE);
            isBind = true;
        }
    }
}
