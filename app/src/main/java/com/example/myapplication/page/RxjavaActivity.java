package com.example.myapplication.page;

import androidx.appcompat.app.AppCompatActivity;

import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.myapplication.R;
import com.example.myapplication.network.IpData;
import com.example.myapplication.network.IpModel;
import com.example.myapplication.network.IpServiceForPost;
import com.example.myapplication.utils.Constants;
import com.facebook.drawee.view.SimpleDraweeView;

import java.io.IOException;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.ObservableEmitter;
import io.reactivex.rxjava3.core.ObservableOnSubscribe;
import io.reactivex.rxjava3.core.Observer;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.schedulers.Schedulers;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class RxjavaActivity extends AppCompatActivity {

    private static final String TAG = RxjavaActivity.class.getSimpleName();

    private Button button1;
    private Button button2;
    private SimpleDraweeView simpleDraweeView;
    private OkHttpClient mOkHttpClient;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rxjava);
        button1 = findViewById(R.id.button1);
        button2 = findViewById(R.id.button2);
        simpleDraweeView = findViewById(R.id.image1);
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                OKHttp("59.108.54.37");
            }
        });
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                retrofit("59.108.54.37");
            }
        });
        simpleDraweeView.setImageURI(Uri.parse("http://www.baidu.com/img/bdlogo.png"), null);

    }

    private void OKHttp(String ip) {
        postAsyncHttp(ip);
    }

    private void retrofit(String ip) {
        Retrofit retrofit = new Retrofit.Builder().baseUrl(Constants.BASE_IP_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
                .build();
        IpServiceForPost ipServiceForPost = retrofit.create(IpServiceForPost.class);
        ipServiceForPost.getIpMsg(ip, Constants.APP_ID, Constants.SECRET_KEY)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<IpModel<IpData>>() {
            @Override
            public void onSubscribe(@NonNull Disposable d) {

            }

            @Override
            public void onNext(@NonNull IpModel<IpData> ipModel) {
                String city = ipModel.getData().getCity();
                Toast.makeText(RxjavaActivity.this, city, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onError(@NonNull Throwable e) {
                Log.e(TAG, e.getMessage());
            }

            @Override
            public void onComplete() {
                Log.d(TAG, "onComplete");
            }
        });
    }

    private Observable<String> getObservable(String ip) {
        return Observable.create(new ObservableOnSubscribe<String>() {

            @Override
            public void subscribe(@NonNull ObservableEmitter<String> emitter) throws Throwable {
                if (mOkHttpClient == null) {
                    mOkHttpClient = new OkHttpClient();
                }
                HttpUrl.Builder builder = HttpUrl.parse(Constants.BASE_IP_URL).newBuilder();
                builder.addQueryParameter("app_id", Constants.APP_ID);
                builder.addQueryParameter("app_secret", Constants.SECRET_KEY);
                builder.addQueryParameter("ip", ip);
                Request request = new Request.Builder()
                        .url(builder.build())
                        .build();
                Call call = mOkHttpClient.newCall(request);
                call.enqueue(new Callback() {
                    @Override
                    public void onFailure(@androidx.annotation.NonNull Call call, IOException e) {
                        emitter.onError(new Exception("error"));
                    }

                    @Override
                    public void onResponse(Call call, Response response) throws IOException {
                        String str = response.body().string();
                        emitter.onNext(str);
                        emitter.onComplete();
                    }
                });
            }
        });
    }

    private void postAsyncHttp(String size) {
        getObservable(size).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<String>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {

                    }

                    @Override
                    public void onNext(@NonNull String s) {
                        Log.d(TAG, "onNext: " + s);
                        Toast.makeText(RxjavaActivity.this, s, Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        Log.e(TAG, "onError", e);
                    }

                    @Override
                    public void onComplete() {
                        Log.d(TAG, "onComplete");
                    }
                });
    }



}