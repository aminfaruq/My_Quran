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
import co.id.aminfaruq.core.data.source.local.entity.SurahEntity
import co.id.aminfaruq.core.domain.model.Surat
import kotlinx.android.synthetic.main.item_surah.view.*

class SuratAdapter(private val context: Context? , private val onItemClick: OnItemClick) : PagedListAdapter<Surat, SuratAdapter.ViewHolder>(DIFF_CALLBACK) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SuratAdapter.ViewHolder {
        return ViewHolder(
            LayoutInflater.from(context).inflate(R.layout.item_surah, parent, false)
        )

    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: SuratAdapter.ViewHolder, position: Int) {
        val surat = getItem(position)

        if (surat != null){
            holder.nomor.text = surat.nomor
            holder.nama.text = surat.asma
            holder.info.text = surat.type + " - " + surat.ayat + " Ayat "
            holder.ayat.text = surat.nama

            holder.itemView.setOnClickListener {
                onItemClick.onClick(surat)
            }
        }

    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val nomor = itemView.txtNumber
        val nama = itemView.txtName
        val info = itemView.txtInfo
        val ayat = itemView.txtAyat
    }

    companion object {

        private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<Surat>() {
            override fun areItemsTheSame(oldItem: Surat, newItem: Surat): Boolean {
                return oldItem.nomor == newItem.nomor
            }

            @SuppressLint("DiffUtilEquals")
            override fun areContentsTheSame(oldItem: Surat, newItem: Surat): Boolean {
                return oldItem == newItem
            }
        }
    }

    interface OnItemClick{
        fun onClick(item : Surat)
    }

}