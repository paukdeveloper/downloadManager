package com.datsenko.yevhenii.newdownloadmanager.activity;

import android.app.DownloadManager;
import android.content.IntentFilter;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import com.datsenko.yevhenii.newdownloadmanager.R;
import com.datsenko.yevhenii.newdownloadmanager.adapter.RecyclerAdapter;
import com.datsenko.yevhenii.newdownloadmanager.db.DatabaseManager;
import com.datsenko.yevhenii.newdownloadmanager.db.MySQLiteOpenHelper;
import com.datsenko.yevhenii.newdownloadmanager.model.Show;
import com.datsenko.yevhenii.newdownloadmanager.receiver.DownloadedReceiver;
import com.datsenko.yevhenii.newdownloadmanager.receiver.NotificationClickedReceiver;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private RecyclerView mRecyclerView;
    private RecyclerView.LayoutManager mLayoutManager;
    private RecyclerAdapter mRecyclerAdapter;
    private ArrayList<Show> shows = new ArrayList<>();
    private DownloadedReceiver downloadReceiver;
    private NotificationClickedReceiver notificationClickedReceiver;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        DatabaseManager.initializeInstance(new MySQLiteOpenHelper(MainActivity.this));

        mRecyclerView = (RecyclerView) findViewById(R.id.main_recycler);
        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);

        shows.add(new Show(98, "http://ka4ka.ru/files.php?mod=dload&fid=6212892", "first video",
                "http://www.razumniki.ru/images/articles/obuchenie_detey/raskraska_cifra1.jpg", 0, 0, 0));
        shows.add(new Show(97, "http://ka4ka.ru/files.php?mod=dload&fid=6068846", "Second video",
                "http://online-azbuka.ru/image/ts2.jpg", 0, 0, 0));
        shows.add(new Show(96, "http://ka4ka.ru/files.php?mod=dload&fid=5944007", "Pomazanije video",
                "http://st0.vo.org.ua/images/0/31/226/v_b8bdc215d4.jpg", 0, 0, 0));
        shows.add(new Show(95, "http://youtubeinmp4.com/redirect.php?video=7lE6aMVrFoA&r=efnNYaXSyFjPhfATyjuoHzYprCIPX9Xden3JUbNiAAg%3D", "Pomazanije video",
                "http://st0.vo.org.ua/images/0/31/226/v_b8bdc215d4.jpg", 0, 0, 0));
        mRecyclerAdapter = new RecyclerAdapter(shows,this);
        mRecyclerView.setAdapter(mRecyclerAdapter);

        Toast.makeText(MainActivity.this, "sadas", Toast.LENGTH_SHORT).show();
        SQLiteDatabase database = DatabaseManager.getInstance().openDatabase();
        MySQLiteOpenHelper.readTableTABLE_DOWNLOADED_VIDEO(database);
        DatabaseManager.getInstance().closeDatabase();





    }

    @Override
    protected void onPause() {
        super.onPause();
        unregisterReceiver(downloadReceiver);
        unregisterReceiver(notificationClickedReceiver);
    }

    @Override
    protected void onResume() {
        super.onResume();

        downloadReceiver = new DownloadedReceiver(this);
        IntentFilter filter = new IntentFilter(DownloadManager.ACTION_DOWNLOAD_COMPLETE);
        registerReceiver(downloadReceiver, filter);

        notificationClickedReceiver = new NotificationClickedReceiver();
        IntentFilter filterClicked = new IntentFilter(DownloadManager.ACTION_NOTIFICATION_CLICKED);
        registerReceiver(notificationClickedReceiver, filterClicked);

    }
}
