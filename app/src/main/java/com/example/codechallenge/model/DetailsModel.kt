package com.example.codechallenge.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize
import kotlinx.android.parcel.RawValue

@Parcelize
data class DetailsModel(
    val params: List<Params>
) : Parcelable

@Parcelize
data class Params(
    val name: String,
    val value: String
): Parcelable