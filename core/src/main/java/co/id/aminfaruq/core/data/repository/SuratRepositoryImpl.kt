package co.id.aminfaruq.core.data.repository

import androidx.lifecycle.LiveData
import androidx.paging.DataSource
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import co.id.aminfaruq.core.data.mapper.local.SuratEntityMapper
import co.id.aminfaruq.core.data.mapper.remote.ItemSuratMapper
import co.id.aminfaruq.core.data.source.local.room.QuranDao
import co.id.aminfaruq.core.data.source.remote.network.ApiService
import co.id.aminfaruq.core.domain.model.Surat
import co.id.aminfaruq.core.domain.repository.SuratRepository
import io.reactivex.Single
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import java.util.concurrent.Executor

class SuratRepositoryImpl(
    private val apiService: ApiService,
    private val itemSuratMapper: ItemSuratMapper,
    private val suratEntityMapper: SuratEntityMapper,
    private val quranDao: QuranDao,
    private val executor: Executor
) : SuratRepository {
    override fun getSurat(): Single<List<Surat>> {
        return apiService.getAllSurat().map {
            itemSuratMapper.mapToListDomain(it)
        }
    }

    override fun saveSurah(surat: List<Surat>) {
        runBlocking {
            val suratEntity = suratEntityMapper.mapToListModel(surat)
            val job = GlobalScope.launch {
                executor.execute { quranDao.insertSurah(suratEntity) }
            }
            job.join()
        }
    }

    private fun getSuratAsPaged(): DataSource.Factory<Int, Surat> {
        return quranDao.getSurahPagination()
    }

    override fun getSuratAsPage(): LiveData<PagedList<Surat>> {
        return LivePagedListBuilder(getSuratAsPaged(), 50).build()
    }

    override fun deleteSurat() {
        runBlocking {
            val job = GlobalScope.launch {
                quranDao.nukeSuratTable()
            }
            job.join()
        }
    }

}