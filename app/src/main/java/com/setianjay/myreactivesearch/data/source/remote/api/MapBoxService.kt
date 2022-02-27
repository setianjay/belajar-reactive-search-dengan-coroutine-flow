package com.setianjay.myreactivesearch.data.source.remote.api

import com.setianjay.myreactivesearch.data.source.remote.model.PlaceRemoteModel
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface MapBoxService {
    @GET("mapbox.places/{query}.json")
    suspend fun getLocation(
        @Path("query") query: String,
        @Query("access_token") accessToken: String,
        @Query("autocomplete") autoComplete: Boolean = true
    ): PlaceRemoteModel
}