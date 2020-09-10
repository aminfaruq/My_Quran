package co.id.aminfaruq.core.data.mapper.remote

import co.id.aminfaruq.core.data.mapper.BaseMapper
import co.id.aminfaruq.core.data.source.remote.response.SuratItem
import co.id.aminfaruq.core.domain.model.Surat

class ItemSuratMapper :
    BaseMapper<SuratItem, Surat> {
    override fun mapToDomain(model: SuratItem): Surat {
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

    override fun mapToModel(domain: Surat): SuratItem {
        return SuratItem(
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