package toobler.paris;

import android.app.Activity;
import android.app.ProgressDialog;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;


public class Home extends Activity {
    WebView parisweb;
    String url = "http://parisdeboutique.in";
    boolean finished;
    ProgressDialog progressDialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        finished =false;
        setContentView(R.layout.activity_home);
        parisweb = (WebView) findViewById(R.id.parisweb);
        parisweb.setWebViewClient(new MyBrowser());
        parisweb.getSettings().setLoadsImagesAutomatically(true);
        parisweb.getSettings().setJavaScriptEnabled(true);
        parisweb.setScrollBarStyle(View.SCROLLBARS_INSIDE_OVERLAY);
        loadURL();
    }
    public void loadURL(){
        if(Utility.isConnectingToInternet(getApplicationContext()))
            parisweb.loadUrl(url);
        else
            Utility.showNetowrkAlert(Home.this);
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_home, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_refresh) {
            loadURL();
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onResume() {
        if(!finished)
            loadURL();
        super.onResume();
    }

    private class MyBrowser extends WebViewClient {
        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            view.loadUrl(url);
            return true;
        }
        public void onLoadResource (WebView view, String url) {
            if (progressDialog == null) {
                // in standard case YourActivity.this
                progressDialog = new ProgressDialog(Home.this);
                progressDialog.setMessage("Loading...");
                progressDialog.show();
            }
        }
        @Override
        public void onPageFinished(WebView view, String url) {
            super.onPageFinished(view, url);
            finished = true;
            if(progressDialog!=null)
                progressDialog.cancel();
        }
    }
}
