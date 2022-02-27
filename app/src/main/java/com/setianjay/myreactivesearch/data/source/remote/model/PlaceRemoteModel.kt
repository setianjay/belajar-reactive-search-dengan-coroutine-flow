package com.setianjay.myreactivesearch.data.source.remote.model

import com.google.gson.annotations.SerializedName

data class PlaceRemoteModel(
    @SerializedName("features") val places: List<PlacesItemRemoteModel>
)
