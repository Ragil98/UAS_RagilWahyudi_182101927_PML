package com.ragil.userscurdproject;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.ragil.userscurdproject.model.PostPutDelUser;
import com.ragil.userscurdproject.rest.ApiClient;
import com.ragil.userscurdproject.rest.ApiInterface;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class InsertActivity extends AppCompatActivity {
    EditText editName,editEmail;
    Button btnInsert,btnBack;
    ApiInterface mApiInterface;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_insert);
        editName = (EditText) findViewById(R.id.editNama);
        editEmail = (EditText) findViewById(R.id.editEmail);
        mApiInterface = ApiClient.getClient().create(ApiInterface.class);
        btnInsert = (Button) findViewById(R.id.btnInsert);

        btnInsert.setOnClickListener(new View.OnClickListener() {

             @Override
            public void onClick(View view) {
                 Call<PostPutDelUser> postuserCall = mApiInterface.postUser(editName.getText().toString(),editEmail.getText().toString());
                 postuserCall.enqueue(new Callback<PostPutDelUser>() {
                     @Override
                     public void onResponse(Call<PostPutDelUser> call, Response<PostPutDelUser> response) {
                         MainActivity.ma.refresh();
                         finish();
                     }

                     @Override
                     public void onFailure(Call<PostPutDelUser> call, Throwable t) {
                         Toast.makeText(getApplicationContext(),"error", Toast.LENGTH_LONG).show();
                     }
                 });
                btnBack = (Button)findViewById(R.id.btnBack);
                btnBack.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        MainActivity.ma.refresh();
                        finish();
                    }
                });
             }
        });
    }
}