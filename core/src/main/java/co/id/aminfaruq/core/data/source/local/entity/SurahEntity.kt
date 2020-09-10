package co.id.aminfaruq.core.data.source.local.entity

import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "surat")
data class SurahEntity(
    @ColumnInfo(name = "arti")
    val arti: String,

    @ColumnInfo(name = "asma")
    val asma: String,

    @ColumnInfo(name = "audio")
    val audio: String,

    @ColumnInfo(name = "ayat")
    val ayat: Int,

    @ColumnInfo(name = "keterangan")
    val keterangan: String,

    @ColumnInfo(name = "nama")
    val nama: String,

    @PrimaryKey
    @NonNull
    @ColumnInfo(name = "nomor")
    val nomor: String,

    @ColumnInfo(name = "rukuk")
    val rukuk: String,

    @ColumnInfo(name = "type")
    val type: String,

    @ColumnInfo(name = "urut")
    val urut: String
)