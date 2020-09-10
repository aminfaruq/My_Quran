package co.id.aminfaruq.core.data.source.local.entity

import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "ayat")
data class AyatEntity(
    @ColumnInfo(name = "ar")
    val ar: String,

    @ColumnInfo(name = "id")
    val id: String,

    @PrimaryKey
    @NonNull
    @ColumnInfo(name = "nomor")
    val nomor: String,

    @ColumnInfo(name = "tr")
    val tr: String
)