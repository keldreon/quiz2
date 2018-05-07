package blank.quiz2.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import blank.quiz2.API.ApiInterface;
import blank.quiz2.R;
import blank.quiz2.utils.prefUtils;

/*
* 1505735
* Galih Abdul Muhyi
*
* */
public class MainActivity extends AppCompatActivity {
    private ApiInterface apiInterface;
    private prefUtils session;
    private Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu1, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.cekSaldo:
                return true;
            case R.id.transfer:
                return true;
            case R.id.off:
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void logoutUser() {
        session.setLogin(false);
        session.logoutUser();
        // Launching the login activity
        intent = new Intent(getApplicationContext(), login.class);
        startActivity(intent);
        finish();
    }
}
