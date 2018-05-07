package blank.quiz2.activity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import blank.quiz2.API.ApiClient;
import blank.quiz2.API.ApiData;
import blank.quiz2.API.ApiInterface;
import blank.quiz2.R;
import blank.quiz2.model.User;
import blank.quiz2.utils.prefUtils;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class login extends AppCompatActivity {
    private static final String TAG = login.class.getSimpleName();
    private EditText ed_id;
    private EditText ed_pass;
    private TextView reg;
    private Intent intent;
    private Button btn_login;
    private ProgressDialog pDialog;
    private prefUtils pref;
    private ApiData<User> account;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        pref = new prefUtils(this);
        ed_id = findViewById(R.id.id_uname);
        ed_pass = findViewById(R.id.pass);
        btn_login = findViewById(R.id.btn_masuk);
        // Progress dialog
        pDialog = new ProgressDialog(this);
        pDialog.setCancelable(false);

        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String uname = ed_id.getText().toString().trim();
                String pass = ed_pass.getText().toString().trim();
                cekAkun(uname,pass);
            }
        });

        reg = findViewById(R.id.ref);
        reg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goRegister();
            }
        });
    }

    private void goRegister(){
        intent = new Intent(getApplicationContext(),register.class);
        finish();
        startActivity(intent);
    }

    private void cekAkun(final String id, final String pass){
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
        ApiInterface apiService = ApiClient.getClient(this).create(ApiInterface.class);

        Call<ApiData<User>> call = apiService.cek_saldo(id,pass);

        call.enqueue(new Callback<ApiData<User>>() {
            @Override
            public void onResponse(@NonNull Call<ApiData<User>> call, @NonNull Response<ApiData<User>> response) {
                account = response.body();
                hideDialog();

                if(account!=null){
                    if(account.getRespon().equals("OK")){
                        intent = new Intent(getApplicationContext(),MainActivity.class);
                        pref.createLoginSession(id, pass, account.getData().getSaldo());
                        finish();
                        startActivity(intent);
                        //Toast.makeText(getApplicationContext(), account.getRespon(), Toast.LENGTH_LONG).show();
                    }
                    else{
                        Toast.makeText(getApplicationContext(), account.getRespon(), Toast.LENGTH_LONG).show();
                        return;
                    }
                }else{
                    Toast.makeText(getBaseContext(), response.code(), Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<ApiData<User>> call, Throwable t) {
                hideDialog();
                Log.e(TAG, "onFailure: ", t);
                Toast.makeText(getApplicationContext(), "connection error", Toast.LENGTH_LONG).show();
            }
        });
    }

    private void showDialog() {
        if (!pDialog.isShowing())
            pDialog.show();
    }

    private void hideDialog() {
        if (pDialog.isShowing())
            pDialog.dismiss();
    }
}
