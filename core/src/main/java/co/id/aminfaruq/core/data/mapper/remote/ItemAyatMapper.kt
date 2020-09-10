package co.id.aminfaruq.core.data.mapper.remote

import co.id.aminfaruq.core.data.mapper.BaseMapper
import co.id.aminfaruq.core.data.source.remote.response.AyatItem
import co.id.aminfaruq.core.domain.model.Ayat

class ItemAyatMapper :
    BaseMapper<AyatItem, Ayat> {
    override fun mapToDomain(model: AyatItem): Ayat {
        return Ayat(
            model.ar,
            model.id,
            model.nomor,
            model.tr
        )
    }

    override fun mapToModel(domain: Ayat): AyatItem {
        return AyatItem(
            domain.ar,
            domain.id,
            domain.nomor,
            domain.tr
        )
    }
}