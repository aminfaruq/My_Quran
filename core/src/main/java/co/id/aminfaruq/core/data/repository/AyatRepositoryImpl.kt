package co.id.aminfaruq.core.data.repository

import androidx.lifecycle.LiveData
import androidx.paging.DataSource
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import co.id.aminfaruq.core.data.mapper.local.AyatEntityMapper
import co.id.aminfaruq.core.data.mapper.remote.ItemAyatMapper
import co.id.aminfaruq.core.data.source.local.room.QuranDao
import co.id.aminfaruq.core.data.source.remote.network.ApiService
import co.id.aminfaruq.core.domain.model.Ayat
import co.id.aminfaruq.core.domain.model.Surat
import co.id.aminfaruq.core.domain.repository.AyatRepository
import io.reactivex.Single
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import java.util.concurrent.Executor

class AyatRepositoryImpl(
    private val apiService: ApiService,
    private val itemAyatMapper: ItemAyatMapper,
    private val ayatEntityMapper: AyatEntityMapper,
    private val quranDao: QuranDao,
    private val executor: Executor
) : AyatRepository {
    override fun getAyat(nomorSurat: String): Single<List<Ayat>> {
        return apiService.getAyat(nomorSurat).map {
            itemAyatMapper.mapToListDomain(it)
        }
    }

    override fun saveAyat(ayat: List<Ayat>) {
        runBlocking {
            val ayatEntity = ayatEntityMapper.mapToListModel(ayat)
            val job = GlobalScope.launch {
                executor.execute { quranDao.insertAyat(ayatEntity) }
            }
            job.join()
        }
    }

    private fun getAyatAsPaged(): DataSource.Factory<Int, Ayat> {
        return quranDao.getAyatPagination()
    }

    override fun getAyatAsPage(): LiveData<PagedList<Ayat>> {
        return LivePagedListBuilder(getAyatAsPaged(), 20).build()
    }

    override fun deleteAyat() {
        quranDao.nukeAyatTable()
    }
}