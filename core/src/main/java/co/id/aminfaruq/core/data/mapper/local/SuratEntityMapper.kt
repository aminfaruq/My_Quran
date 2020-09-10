package co.id.aminfaruq.core.data.mapper.local

import co.id.aminfaruq.core.data.mapper.BaseMapper
import co.id.aminfaruq.core.data.source.local.entity.SurahEntity
import co.id.aminfaruq.core.domain.model.Surat

class SuratEntityMapper : BaseMapper<SurahEntity , Surat> {
    override fun mapToDomain(model: SurahEntity): Surat {
        return Surat(
            model.arti,
            model.asma,
            model.audio,
            model.ayat,
            model.keterangan,
            model.nama,
            model.nomor,
            model.rukuk,
            model.type,
            model.urut
        )
    }

    override fun mapToModel(domain: Surat): SurahEntity {
        return SurahEntity(
            domain.arti,
            domain.asma,
            domain.audio,
            domain.ayat,
            domain.keterangan,
            domain.nama,
            domain.nomor,
            domain.rukuk,
            domain.type,
            domain.urut
        )
    }
}