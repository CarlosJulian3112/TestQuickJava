package com.example.testquickjava;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.example.testquickjava.API.ApiService;
import com.example.testquickjava.models.Post;
import com.example.testquickjava.models.PostRespuesta;
import com.example.testquickjava.models.User;
import com.example.testquickjava.models.UserRespuesta;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    private Retrofit retrofit;

    private TextView mJsonTxtView;


    private static final String TAG = "POST";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mJsonTxtView = findViewById(R.id.jsonText);

        retrofit = new Retrofit.Builder()
                .baseUrl("https://gorest.co.in/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        obtenerPosts();;
    }
    private void obtenerPosts(){
        final ApiService service = retrofit.create(ApiService.class);
        Call<PostRespuesta> postRespuestaCall = service.obtenerListaPost();

        postRespuestaCall.enqueue(new Callback<PostRespuesta>() {
            @Override
            public void onResponse(Call<PostRespuesta> call, Response<PostRespuesta> response) {
                if (response.isSuccessful()){

                    final PostRespuesta postRespuesta = response.body();
                    ArrayList<Post> listaPost = postRespuesta.getResult();

                    for(final Post post : listaPost){
                        Call<UserRespuesta> userRespuestaCall = service.obtenerUsuario(post.getUser_id());

                        userRespuestaCall.enqueue(new Callback<UserRespuesta>() {
                            @Override
                            public void onResponse(Call<UserRespuesta> call, Response<UserRespuesta> response) {
                                if (response.isSuccessful()){

                                    UserRespuesta userRespuesta = response.body();
                                    User user = userRespuesta.getResult();


                                    String content = "";
                                    content += "name: " +user.getFirst_name()+" "+user.getLast_name()+"\n";
                                    content += "email: " +user.getEmail()+"\n";
                                    content += "\n\n";

                                    mJsonTxtView.append(content);

                                    content = "";
                                    content += "id: " +post.getId()+"\n";
                                    content += "title: " +post.getTitle()+"\n";
                                    content += "body: " +post.getBody()+"\n";
                                    content += "\n\n\n\n\n\n";

                                    mJsonTxtView.append(content);




                                } else {

                                    mJsonTxtView.setText("Codigo: "+response.code());
                                }
                            }

                            @Override
                            public void onFailure(Call<UserRespuesta> call, Throwable t) {
                                mJsonTxtView.setText(t.getMessage());
                            }
                        });





                    }

                } else {

                    mJsonTxtView.setText("Codigo: "+response.code());
                }
            }

            @Override
            public void onFailure(Call<PostRespuesta> call, Throwable t) {
                mJsonTxtView.setText(t.getMessage());
            }
        });
    }

    private synchronized void obtenerUsuarios(String user_id){
        final ApiService service2 = retrofit.create(ApiService.class);

        notify();
    }

}