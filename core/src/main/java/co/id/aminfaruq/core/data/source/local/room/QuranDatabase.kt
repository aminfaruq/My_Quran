package co.id.aminfaruq.core.data.source.local.room

import androidx.room.Database
import androidx.room.RoomDatabase
import co.id.aminfaruq.core.data.source.local.entity.AyatEntity
import co.id.aminfaruq.core.data.source.local.entity.SurahEntity

@Database(
    entities = [
        SurahEntity::class,
        AyatEntity::class
    ],
    version = 2,
    exportSchema = false
)
abstract class QuranDatabase : RoomDatabase() {
    abstract fun quranDao(): QuranDao

}