package co.id.aminfaruq.core.domain.usecase

import androidx.lifecycle.LiveData
import androidx.paging.PagedList
import co.id.aminfaruq.core.domain.model.Surat
import io.reactivex.Single

interface SuratUseCase {

    fun getSurat() : Single<List<Surat>>

    fun saveSurah(surat: List<Surat>)

    fun getSuratAsPage() : LiveData<PagedList<Surat>>

    fun deleteSurat()

}