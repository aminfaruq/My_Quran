package co.id.aminfaruq.core.domain.usecase

import co.id.aminfaruq.core.domain.model.Surat
import co.id.aminfaruq.core.domain.repository.SuratRepository

class SuratInteractor(private val suratRepository: SuratRepository) : SuratUseCase {
    override fun getSurat() = suratRepository.getSurat()
    override fun saveSurah(surat: List<Surat>) = suratRepository.saveSurah(surat)
    override fun getSuratAsPage() = suratRepository.getSuratAsPage()
}