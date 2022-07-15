package com.programmer.a10119246_latihanapi;
//NIM : 10119246
//NAMA : FERDI BAYU FEBRYAN
//KELAS : IF6
//TANGGAL PENGERJAAN : 15-07-2022
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class MainActivity extends AppCompatActivity {

    EditText editText, editText2;
    Button button;
    ImageView imageView;
    TextView desc, titl;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        editText = findViewById(R.id.Link);
        button = findViewById(R.id.button);
        imageView = findViewById(R.id.image);
        desc = findViewById(R.id.desc);
        titl = findViewById(R.id.titl);

        editText.addTextChangedListener(textWatcher);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                searchWiki();
                showw();
            }
        });

    }

    private TextWatcher textWatcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

        }

        @Override
        public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            String Linkk = editText.getText().toString().trim();
            button.setEnabled(!Linkk.isEmpty());

        }

        @Override
        public void afterTextChanged(Editable editable) {

        }
    };

    public void showw(){
        imageView.setVisibility(View.VISIBLE);
        desc.setVisibility(View.VISIBLE);
        titl.setVisibility(View.VISIBLE);
    }

    public void searchWiki()
    {

        final String yuerel = editText.getText().toString();
        String url = "https://api.akuari.my.id/search/wiki?query="+yuerel;
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                //calling API

                try {
                    JSONObject jsonObject = new JSONObject(response);
                    JSONArray jsonArray = jsonObject.getJSONArray("result");
                    JSONObject object = jsonArray.getJSONObject(0);
                    //Finding thumb
                    String thumb = object.getString("thumb");
                    Picasso.get().load(thumb).into(imageView);

                    //Finding Title
                    String tit = object.getString("judul");
                    titl.setText(tit);

                    //Finding Desc
                    String des = object.getString("wiki");
                    desc.setText(des);

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(MainActivity.this,error.getLocalizedMessage(),Toast.LENGTH_SHORT).show();

            }

        });

        RequestQueue requestQueue = Volley.newRequestQueue(MainActivity.this);
        requestQueue.add(stringRequest);
    }

}