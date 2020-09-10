package co.id.aminfaruq.core.ui

import co.id.aminfaruq.core.R
import co.id.aminfaruq.core.domain.model.Ayat
import com.xwray.groupie.kotlinandroidextensions.Item
import com.xwray.groupie.kotlinandroidextensions.ViewHolder
import kotlinx.android.synthetic.main.item_ayat.view.*

class AyatItemView(val ayat: Ayat) : Item() {
    override fun bind(viewHolder: ViewHolder, position: Int) {

        val nomor = viewHolder.itemView.tvNomorAyat
        val arabic = viewHolder.itemView.tvArabic
        val terjemahan = viewHolder.itemView.tvTerjemahan

        nomor.text = ayat.nomor
        arabic.text = ayat.ar
        terjemahan.text = ayat.id
    }

    override fun getLayout(): Int = R.layout.item_ayat
}