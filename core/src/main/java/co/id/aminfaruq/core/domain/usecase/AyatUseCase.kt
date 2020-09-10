package co.id.aminfaruq.core.domain.usecase

import androidx.lifecycle.LiveData
import androidx.paging.PagedList
import co.id.aminfaruq.core.domain.model.Ayat
import io.reactivex.Single

interface AyatUseCase {

    fun getAyat(nomorSurat: String): Single<List<Ayat>>

    fun saveAyat(ayat: List<Ayat>)

    fun getAyatAsPage() : LiveData<PagedList<Ayat>>

    fun deleteAyat()

}