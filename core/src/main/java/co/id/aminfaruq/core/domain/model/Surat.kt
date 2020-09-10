package co.id.aminfaruq.core.domain.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Surat(
    val arti: String,
    val asma: String,
    val audio: String,
    val ayat: Int,
    val keterangan: String,
    val nama: String,
    val nomor: String,
    val rukuk: String,
    val type: String,
    val urut: String
) : Parcelable