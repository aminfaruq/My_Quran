package co.id.aminfaruq.core.ui

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import co.id.aminfaruq.core.R
import co.id.aminfaruq.core.domain.model.Ayat
import kotlinx.android.synthetic.main.item_ayat.view.*

class AyatAdapter(private val context: Context?) :
    PagedListAdapter<Ayat, AyatAdapter.ViewHolder>(DIFF_CALLBACK) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AyatAdapter.ViewHolder {

        return ViewHolder(
            LayoutInflater.from(context).inflate(R.layout.item_ayat, parent, false)
        )
    }

    override fun onBindViewHolder(holder: AyatAdapter.ViewHolder, position: Int) {
        val ayat = getItem(position)

        if (ayat != null) {
            holder.nomor.text = ayat.nomor
            holder.arabic.text = ayat.ar
            holder.terjemahan.text = ayat.id
        }
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val nomor = itemView.tvNomorAyat
        val arabic = itemView.tvArabic
        val terjemahan = itemView.tvTerjemahan
    }

    companion object {

        private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<Ayat>() {
            override fun areItemsTheSame(oldItem: Ayat, newItem: Ayat): Boolean {
                return oldItem.nomor == newItem.nomor
            }

            @SuppressLint("DiffUtilEquals")
            override fun areContentsTheSame(oldItem: Ayat, newItem: Ayat): Boolean {
                return oldItem == newItem
            }
        }
    }
}