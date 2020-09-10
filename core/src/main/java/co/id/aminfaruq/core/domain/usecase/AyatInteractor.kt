package co.id.aminfaruq.core.domain.usecase

import androidx.lifecycle.LiveData
import androidx.paging.PagedList
import co.id.aminfaruq.core.domain.model.Ayat
import co.id.aminfaruq.core.domain.repository.AyatRepository

class AyatInteractor(private val ayatRepository: AyatRepository) : AyatUseCase {
    override fun getAyat(nomorSurat: String) = ayatRepository.getAyat(nomorSurat)

    override fun saveAyat(ayat: List<Ayat>) = ayatRepository.saveAyat(ayat)

    override fun getAyatAsPage(): LiveData<PagedList<Ayat>> = ayatRepository.getAyatAsPage()

    override fun deleteAyat() = ayatRepository.deleteAyat()
}