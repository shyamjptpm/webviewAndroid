package toobler.paris;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.util.Log;

/**
 * Created by toobler on 30/6/15.
 */
public class Utility {
    public static boolean isConnectingToInternet(Context context){
        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }
    public static void showNetowrkAlert(final Context context){
        try {
            AlertDialog.Builder alertDialog = new AlertDialog.Builder(context);

            // Setting Dialog Title
            alertDialog.setTitle("No Internet");

            // Setting Dialog Message
            alertDialog.setMessage("Please Enable Mobile data or Wifi");


            // Setting Positive "Yes" Button
            alertDialog.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int which) {

                    // Write your code here to invoke YES event
                    context.startActivity(new Intent(android.provider.Settings.ACTION_SETTINGS));
                }
            });

            // Setting Negative "NO" Button
            alertDialog.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int which) {
                    // Write your code here to invoke NO event

                }
            });

            // Showing Alert Message
            alertDialog.show();
        }catch (Exception e){
            Log.e("IN utility.java", e.toString());
        }
    }
}
