package co.id.aminfaruq.core.domain.repository

import androidx.lifecycle.LiveData
import androidx.paging.PagedList
import co.id.aminfaruq.core.domain.model.Ayat
import co.id.aminfaruq.core.domain.model.Surat
import io.reactivex.Single

interface AyatRepository {

    fun getAyat(nomorSurat: String): Single<List<Ayat>>

    fun saveAyat(ayat: List<Ayat>)

    fun getAyatAsPage() : LiveData<PagedList<Ayat>>

    fun deleteAyat()

}