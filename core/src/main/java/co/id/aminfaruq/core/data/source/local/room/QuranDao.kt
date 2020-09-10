package co.id.aminfaruq.core.data.source.local.room

import androidx.paging.DataSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import co.id.aminfaruq.core.data.source.local.entity.AyatEntity
import co.id.aminfaruq.core.data.source.local.entity.SurahEntity
import co.id.aminfaruq.core.domain.model.Ayat
import co.id.aminfaruq.core.domain.model.Surat

@Dao
interface QuranDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertSurah(meal: List<SurahEntity>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAyat(meal: List<AyatEntity>)

    @Query("SELECT * FROM surat")
    fun getSurahPagination(): DataSource.Factory<Int, Surat>

    @Query("SELECT * FROM ayat")
    fun getAyatPagination(): DataSource.Factory<Int, Ayat>

    @Query("DELETE FROM ayat")
    fun nukeAyatTable()
}