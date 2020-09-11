package co.id.aminfaruq.myquran.ui.surat

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import co.id.aminfaruq.core.domain.model.Surat
import co.id.aminfaruq.core.ui.SuratAdapter
import co.id.aminfaruq.myquran.R
import co.id.aminfaruq.myquran.ui.ayat.DetailAyatActivity
import kotlinx.android.synthetic.main.activity_surat.*
import org.koin.android.ext.android.inject

class SuratActivity : AppCompatActivity() {

    private val viewModel: SuratVM by inject()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_surat)

        val suratAdapter = SuratAdapter(this, object : SuratAdapter.OnItemClick {
            override fun onClick(item: Surat) {
                val intent = Intent(this@SuratActivity, DetailAyatActivity::class.java)
                intent.putExtra(DetailAyatActivity.EXTRA_SURAT, item)
                startActivity(intent)
            }
        })

        viewModel.getSurat()

        with(viewModel) {
            showProgressbar.observe(this@SuratActivity, Observer { isVisible ->
                pb_surat.visibility = if (isVisible) View.VISIBLE else View.GONE

            })
            suratByLocal.observe(this@SuratActivity, Observer { response ->
                suratAdapter.submitList(response)
                suratAdapter.notifyDataSetChanged()
            })

            messageData.observe(this@SuratActivity, Observer {
                Toast.makeText(
                    this@SuratActivity,
                    "Data gagal ditampilkan",
                    Toast.LENGTH_SHORT
                )
                    .show()
            })
        }


        val linearLayout = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        rv_surat.apply {
            layoutManager = linearLayout
            adapter = suratAdapter
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        viewModel.deleteSuratAfterDestroy()
    }


}