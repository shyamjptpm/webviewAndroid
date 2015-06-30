package toobler.paris;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.widget.Toast;

/**
 * Created by toobler on 30/6/15.
 */
public class ConnectionReciever extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activinfo = connectivityManager.getActiveNetworkInfo();
        NetworkInfo mobinfo = connectivityManager.getNetworkInfo(connectivityManager.TYPE_MOBILE);
        if(activinfo==null)
            Toast.makeText(context,"Please Check Internet Connectivity",Toast.LENGTH_LONG).show();

    }
}
