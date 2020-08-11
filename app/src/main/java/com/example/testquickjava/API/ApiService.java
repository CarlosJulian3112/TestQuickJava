package com.example.testquickjava.API;

import com.example.testquickjava.models.PostRespuesta;
import com.example.testquickjava.models.User;
import com.example.testquickjava.models.UserRespuesta;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface ApiService {

    @GET("public-api/posts?_format=json&access-token=TpTAE5A9uoNAe14GihziDbLwZ0OvWhc18J6g")
    Call<PostRespuesta> obtenerListaPost();
    @GET("public-api/users/{user_id}?_format=json&access-token=TpTAE5A9uoNAe14GihziDbLwZ0OvWhc18J6g")
    Call<UserRespuesta> obtenerUsuario(@Path("user_id") String user_id);
}
