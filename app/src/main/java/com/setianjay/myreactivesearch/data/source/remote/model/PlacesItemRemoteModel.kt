package com.setianjay.myreactivesearch.data.source.remote.model

import com.google.gson.annotations.SerializedName

data class PlacesItemRemoteModel(
    @SerializedName("place_name") val address: String
)
