package blank.quiz2.activity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import org.json.JSONException;
import org.json.JSONObject;

import java.nio.charset.StandardCharsets;
import java.util.HashMap;

import blank.quiz2.R;
import blank.quiz2.utils.prefUtils;
import cz.msebera.android.httpclient.Header;

public class register extends AppCompatActivity {
    private static final String TAG = register.class.getSimpleName();
    private EditText ad_id;
    private EditText ad_pass;
    private Button btn_daftar;
    private TextView tv;
    private prefUtils pref;
    private ProgressDialog pDialog;
    private Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        tv = findViewById(R.id.ref_2);
        ad_id = findViewById(R.id.ins_uname);
        ad_pass = findViewById(R.id.ins_pass);
        btn_daftar = findViewById(R.id.btn_daftar);

        //pref = new prefUtils(this);

        pDialog = new ProgressDialog(this);
        pDialog.setCancelable(false);

        btn_daftar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String uname = ad_id.getText().toString().trim();
                String pass = ad_pass.getText().toString().trim();
                refAkun(uname,pass);
            }
        });

        tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goLogin();
            }
        });

    }

    private void goLogin(){
        intent = new Intent(getApplicationContext(),login.class);
        finish();
        startActivity(intent);
    }

    private void refAkun(final String id, final String pass) {
        if(TextUtils.isEmpty(id)){
            Toast.makeText(getApplicationContext(), "Please enter email", Toast.LENGTH_LONG).show();
            return;
        }
        if(TextUtils.isEmpty(pass)){
            Toast.makeText(getApplicationContext(), "Please enter email", Toast.LENGTH_LONG).show();
            return;
        }
        //final Context c = this;
        pDialog.setMessage("Logging in ...");
        showDialog();
        /*String urlParams = "id="+id+"&passwd="+pass;
        String target ="http://mobprog.yuliadi.pro:5000/buat_account";*/
        kirim_data(id, pass);
        //Toast.makeText(getApplicationContext(), kirim_data(target, urlParams), Toast.LENGTH_LONG).show();
    }

    private void showDialog() {
        if (!pDialog.isShowing())
            pDialog.show();
    }

    private void hideDialog() {
        if (pDialog.isShowing())
            pDialog.dismiss();
    }

    protected void kirim_data(String v1, String v2){
        pDialog.setMessage("Logging in ...");
        showDialog();
        AsyncHttpClient client = new AsyncHttpClient();
        HashMap<String,String> paramMap = new HashMap<>();
        paramMap.put("id",v1);
        paramMap.put("passwd",v2);
        RequestParams params = new RequestParams(paramMap);
        /*params.put("id",v1);
        params.put("passwd",v2);*/
        client.post("http://mobprog.yuliadi.pro:5000/buat_account", params, new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                hideDialog();
                String response = new String(responseBody, StandardCharsets.UTF_8);
                errorReader(response);
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
                hideDialog();
                if (statusCode == 404) {
                    Toast.makeText(getApplicationContext(), "Requested resource not found", Toast.LENGTH_LONG).show();
                } else if (statusCode == 500) {
                    Toast.makeText(getApplicationContext(), "Something went wrong at server end", Toast.LENGTH_LONG).show();
                } else {
                    Toast.makeText(getApplicationContext(), "Unexpected Error occcured! [Most common Error: Device might not be connected to Internet]",
                            Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    private void errorReader(String respon){
        try{
            JSONObject arr = new JSONObject(respon);
            if(!respon.isEmpty()){
                String ss = arr.get("respon").toString();
                Toast.makeText(getApplicationContext(), ss, Toast.LENGTH_LONG).show();
            }else{
                Toast.makeText(getApplicationContext(), "??", Toast.LENGTH_LONG).show();
            }
        } catch (JSONException e) {
            e.printStackTrace();
            Toast.makeText(getApplicationContext(), "Json error: " + e.getMessage(), Toast.LENGTH_LONG).show();
        }
    }
}
