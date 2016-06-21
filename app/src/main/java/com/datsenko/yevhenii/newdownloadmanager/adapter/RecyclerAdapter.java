package com.datsenko.yevhenii.newdownloadmanager.adapter;

import android.app.Activity;
import android.app.DownloadManager;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.datsenko.yevhenii.newdownloadmanager.R;
import com.datsenko.yevhenii.newdownloadmanager.model.Show;
import com.datsenko.yevhenii.newdownloadmanager.db.DatabaseManager;
import com.datsenko.yevhenii.newdownloadmanager.db.MySQLiteOpenHelper;
import com.squareup.picasso.Picasso;

import java.io.File;
import java.util.ArrayList;

/**
 * Created by Yevhenii on 6/16/2016.
 */
public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.MyViewHolder> {
    private ArrayList<Show> showArray;
    private Context context;
    private ArrayList<Long> downloadReferences = new ArrayList<>();

    public RecyclerAdapter(ArrayList<Show> showArray, Context context) {
        this.showArray = showArray;
        this.context = context;
    }

    @Override
    public RecyclerAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.item_show,parent,false);
        final MyViewHolder vh = new MyViewHolder(v);
        vh.download.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int position = vh.getAdapterPosition();
                Show show = showArray.get(position);
                DownloadManager downloadManager = (DownloadManager)context.getSystemService(Activity.DOWNLOAD_SERVICE);
                Uri Download_Uri = Uri.parse(show.getUrl());
                DownloadManager.Request request = new DownloadManager.Request(Download_Uri);
                request.setAllowedNetworkTypes(DownloadManager.Request.NETWORK_WIFI | DownloadManager.Request.NETWORK_MOBILE);
                request.setAllowedOverRoaming(false);
                request.setTitle("Download " + show.getTitle());
                //Set a description of this download, to be displayed in notifications (if enabled)
                request.setDescription(String.valueOf(show.getId()));
                File directory = context.getExternalFilesDir(null);
                File hiddenDir = new File(directory,".cache");
                if(!hiddenDir.exists()) {
                    hiddenDir.mkdir();
                }
                File newFile = new File(hiddenDir,String.valueOf(showArray.get(position).getId()));
                request.setDestinationUri(Uri.fromFile(newFile));

                SQLiteDatabase database = DatabaseManager.getInstance().openDatabase();
                Log.d("DataBaseDownloaded", "insert");
                database.insert("downloaded_video", "null", MySQLiteOpenHelper.getContentValueFromShow(show));
                DatabaseManager.getInstance().closeDatabase(); // correct way

                //Enqueue a new download and same the referenceId
                downloadReferences.add(downloadManager.enqueue(request));
            }
        });
        return vh;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        holder.title.setText(showArray.get(position).getTitle());
        Picasso.with(context).load(showArray.get(position).getImageUrlSmallPreview()).into(holder.image);
    }

    @Override
    public int getItemCount() {
        return showArray.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {
        private TextView title;
        private ImageView image;
        private ImageView download;

        public MyViewHolder(View itemView) {
            super(itemView);
            title = (TextView) itemView.findViewById(R.id.item_title);
            image = (ImageView) itemView.findViewById(R.id.item_image_view);
            download = (ImageView) itemView.findViewById(R.id.item_download);
        }
    }
}
