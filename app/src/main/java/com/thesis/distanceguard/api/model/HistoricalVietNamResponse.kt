package com.thesis.distanceguard.api.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class HistoricalVietNamResponse(
    @SerializedName("country")
    val country: String,
    @SerializedName("province")
    val province: ArrayList<String>,
    @SerializedName("timeline")
    val timeline: HistoricalAllResponse
) : Parcelable