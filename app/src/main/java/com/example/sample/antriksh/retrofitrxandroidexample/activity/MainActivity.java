package com.example.sample.antriksh.retrofitrxandroidexample.activity;

import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;

import com.example.sample.antriksh.retrofitrxandroidexample.R;
import com.example.sample.antriksh.retrofitrxandroidexample.adapter.GeoNamesAdapter;
import com.example.sample.antriksh.retrofitrxandroidexample.api.GeoNamesApi;
import com.example.sample.antriksh.retrofitrxandroidexample.retrofit.ProjectAPI;
import com.example.sample.antriksh.retrofitrxandroidexample.retrofit.RetrofitService;

import rx.Observer;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class MainActivity extends AppCompatActivity {

    private final String TAG = MainActivity.class.getName();

    private Subscription subscription;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        FloatingActionButton floatingAction = (FloatingActionButton)
                findViewById(R.id.floatingAction);

        floatingAction.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                callServerFetchGeoNamesList();
            }
        });

        final Snackbar snackbar = Snackbar
                .make(findViewById(R.id.coordinatorLayout),
                        getString(R.string.server_call), Snackbar.LENGTH_INDEFINITE)
                .setAction("OK", new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        //Do nothing, just dismiss snackbar
                    }
                });

        snackbar.show();
    }

    private void callServerFetchGeoNamesList() {

        final ProjectAPI service = RetrofitService.createRetrofitClient();

        subscription = service.getAllCountriesGeoNames()
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(myObserver);
    }

    Observer<GeoNamesApi> myObserver = new Observer<GeoNamesApi>() {

        @Override
        public void onCompleted() {
            subscription.unsubscribe();
        }

        @Override
        public void onError(Throwable e) {
            // Called when the observable encounters an error
            Log.d(TAG, ">>>> onError gets called : " + e.getMessage());
        }

        @Override
        public void onNext(GeoNamesApi geoNamesApi) {
            findViewAndSetAdapter(geoNamesApi);
        }
    };

    private void findViewAndSetAdapter(GeoNamesApi geoNamesApi) {

        RecyclerView recList = (RecyclerView) findViewById(R.id.cardList);
        recList.setHasFixedSize(true);

        LinearLayoutManager llm = new LinearLayoutManager(this);

        llm.setOrientation(LinearLayoutManager.VERTICAL);
        recList.setLayoutManager(llm);

        GeoNamesAdapter ca = new GeoNamesAdapter(geoNamesApi.getGeonames());
        recList.setAdapter(ca);
        ca.notifyDataSetChanged();
    }
}
