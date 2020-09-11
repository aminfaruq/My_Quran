package co.id.aminfaruq.myquran.ui.surat

import androidx.lifecycle.MutableLiveData
import co.id.aminfaruq.core.domain.usecase.SuratUseCase
import co.id.aminfaruq.core.ui.BaseViewModel
import co.id.aminfaruq.core.utils.RxUtils

class SuratVM(private val suratUseCase: SuratUseCase) : BaseViewModel() {

    val showProgressbar = MutableLiveData<Boolean>()
    val messageData = MutableLiveData<String>()

    fun getSurat() {
        showProgressbar.value = true
        compositeDisposable.add(
            suratUseCase.getSurat()
                .compose(RxUtils.applySingleAsync())
                .subscribe({ result ->
                    showProgressbar.value = false
                    suratUseCase.saveSurah(result)
                }, this::onError)
        )
    }

    val suratByLocal = suratUseCase.getSuratAsPage()

    fun deleteSuratAfterDestroy() = suratUseCase.deleteSurat()


    override fun onError(error: Throwable) {
        messageData.value = error.message
    }
}