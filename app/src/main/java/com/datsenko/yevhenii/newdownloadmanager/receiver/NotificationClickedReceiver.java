package com.datsenko.yevhenii.newdownloadmanager.receiver;

import android.app.DownloadManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

/**
 * Created by Yevhenii on 6/16/2016.
 */
public class NotificationClickedReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        String extraId = DownloadManager.EXTRA_NOTIFICATION_CLICK_DOWNLOAD_IDS;
        long[] references = intent.getLongArrayExtra(extraId);
        for (long reference : references) {
            Log.d("receiver", "" + reference);

        }
    }
}
