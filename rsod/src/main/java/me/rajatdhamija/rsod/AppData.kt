package me.rajatdhamija.rsod

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
internal data class AppData(
    val name: String,
    val versionName: String,
    val versionCode: String,
) : Parcelable
