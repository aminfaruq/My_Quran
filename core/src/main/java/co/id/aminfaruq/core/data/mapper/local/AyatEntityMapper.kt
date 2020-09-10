package co.id.aminfaruq.core.data.mapper.local

import co.id.aminfaruq.core.data.mapper.BaseMapper
import co.id.aminfaruq.core.data.source.local.entity.AyatEntity
import co.id.aminfaruq.core.data.source.remote.response.AyatItem
import co.id.aminfaruq.core.domain.model.Ayat

class AyatEntityMapper : BaseMapper<AyatEntity , Ayat> {
    override fun mapToDomain(model: AyatEntity): Ayat {
        return Ayat(
            model.ar,
            model.id,
            model.nomor,
            model.tr
        )
    }

    override fun mapToModel(domain: Ayat): AyatEntity {
        return AyatEntity(
            domain.ar,
            domain.id,
            domain.nomor,
            domain.tr
        )
    }
}