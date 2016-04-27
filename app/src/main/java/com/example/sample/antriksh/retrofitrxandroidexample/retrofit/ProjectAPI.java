package com.example.sample.antriksh.retrofitrxandroidexample.retrofit;

import com.example.sample.antriksh.retrofitrxandroidexample.api.GeoNamesApi;

import retrofit2.http.GET;
import rx.Observable;

/**
 * Created by antrikshtiwari on 09/04/16.
 */
public interface ProjectAPI {

    @GET("/countryInfoJSON?lang=en&username=antriksh")
    Observable<GeoNamesApi> getAllCountriesGeoNames();

}
