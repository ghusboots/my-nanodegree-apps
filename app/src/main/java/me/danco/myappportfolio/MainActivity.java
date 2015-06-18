package me.danco.myappportfolio;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;


public class MainActivity extends AppCompatActivity {
    private void launchApp(String packageName) {
        Intent launchIntent = getPackageManager().getLaunchIntentForPackage(packageName);
        if (launchIntent == null) {
            Log.e(this.getPackageName(), packageName);
            Toast toast = Toast.makeText(getApplicationContext(), getString(R.string.launchapp_toast), Toast.LENGTH_SHORT);
            toast.show();
            return;
        }

        launchIntent.addCategory(Intent.CATEGORY_LAUNCHER);
        startActivity(launchIntent);
    }

    private View.OnClickListener buttonClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Button b = (Button) v;
            String buttonText = b.getText().toString();

            if (buttonText.equals(getString(R.string.button_streamer))) {
                launchApp(getString(R.string.package_streamer));
            } else {
                Toast toast = Toast.makeText(getApplicationContext(), String.format(getString(R.string.toast_format), b.getText()), Toast.LENGTH_SHORT);
                toast.show();
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ViewGroup appList = (ViewGroup) findViewById(R.id.apps);
        for (int i = 0; i < appList.getChildCount(); i++) {
            Button b = (Button) appList.getChildAt(i);
            b.setOnClickListener(this.buttonClickListener);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
