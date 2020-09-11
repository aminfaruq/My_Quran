package co.id.aminfaruq.myquran.ui.ayat

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import co.id.aminfaruq.core.domain.model.Surat
import co.id.aminfaruq.core.ui.AyatAdapter
import co.id.aminfaruq.myquran.R
import kotlinx.android.synthetic.main.activity_detail_ayat.*
import org.koin.android.ext.android.inject

class DetailAyatActivity : AppCompatActivity() {

    companion object {
        const val EXTRA_SURAT = "surat"
    }

    private val viewModel: AyatVM by inject()

    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_ayat)

        val surat = intent.getParcelableExtra<Surat>(EXTRA_SURAT)

        tv_header_asma.text = surat?.nama
        tv_detail_nama.text = surat?.nama
        tv_arti_surat.text = surat?.arti
        tv_info_surat.text = "${surat?.type} ${surat?.ayat} ayat"

        surat?.nomor?.let { viewModel.getAyatBySurat(it) }
        img_back.setOnClickListener {
            onBackPressed()
        }

        val ayatAdapter = AyatAdapter(this)

        with(viewModel) {
            showProgressbar.observe(this@DetailAyatActivity, Observer { isVisible ->
                pb_ayat.visibility = if (isVisible) View.VISIBLE else View.GONE
            })

            ayatByLocal.observe(this@DetailAyatActivity, Observer { response ->
                ayatAdapter.submitList(response)
                ayatAdapter.notifyDataSetChanged()
            })

            messageData.observe(this@DetailAyatActivity, Observer {
                Toast.makeText(
                    this@DetailAyatActivity,
                    "Data gagal ditampilkan",
                    Toast.LENGTH_SHORT
                )
                    .show()
            })

        }

        val linearLayout = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        rv_ayat.apply {
            layoutManager = linearLayout
            adapter = ayatAdapter
        }

    }

    override fun onDestroy() {
        super.onDestroy()
        viewModel.deleteAyatAfterDestroy()
    }


}