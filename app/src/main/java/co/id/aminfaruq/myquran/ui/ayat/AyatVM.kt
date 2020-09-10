package co.id.aminfaruq.myquran.ui.ayat

import androidx.lifecycle.MutableLiveData
import co.id.aminfaruq.core.domain.usecase.AyatUseCase
import co.id.aminfaruq.core.ui.BaseViewModel
import co.id.aminfaruq.core.utils.RxUtils

class AyatVM(private val ayatUseCase: AyatUseCase) : BaseViewModel() {

    val showProgressbar = MutableLiveData<Boolean>()
    val messageData = MutableLiveData<String>()

    fun getAyatBySurat(nomor: String) {
        showProgressbar.value = true
        compositeDisposable.add(
            ayatUseCase.getAyat(nomor)
                .compose(RxUtils.applySingleAsync())
                .subscribe({ result ->
                    ayatUseCase.saveAyat(result)
                    showProgressbar.value = false
                }, this::onError)
        )
    }

    fun deleteAyatAfterDestroy() = ayatUseCase.deleteAyat()

    val ayatByLocal = ayatUseCase.getAyatAsPage()

    override fun onError(error: Throwable) {
        messageData.value = error.message
    }
}