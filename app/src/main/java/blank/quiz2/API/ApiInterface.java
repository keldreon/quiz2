package blank.quiz2.API;

import blank.quiz2.model.User;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface ApiInterface {
    //atau pake body
    @GET("cek_saldo")
    Call<ApiData<User>> cek_saldo(@Query("id") String id, @Query("passwd") String password);

    @POST("buat_account")
    Call<User> buat_account(@Body User user);

    @FormUrlEncoded
    @POST("transfer_coin")
    //"id", "passwd", "id_tujuan", "jumlah"
    Call<ApiData<String>> transfer_coin(@Field("id") String iD, @Field("passwd") String pass, @Field("id_tujuan") String tujuan, @Field("jumlah") String jml);
}
