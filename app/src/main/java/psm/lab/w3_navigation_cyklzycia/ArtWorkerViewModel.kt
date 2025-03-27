package psm.lab.w3_navigation_cyklzycia

import androidx.lifecycle.ViewModel
import androidx.work.OneTimeWorkRequestBuilder
import androidx.work.WorkManager
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import java.util.UUID

class ArtWorkerViewModel(private val workManager : WorkManager) : ViewModel() {
    private var workerId: UUID? = null
    private val _progressState = MutableStateFlow(0)
    val progressState: StateFlow<Int> = _progressState.asStateFlow()



    fun startWorker() {
        val request = OneTimeWorkRequestBuilder<ArtWorker>().build()
        workerId = request.id
        workManager.enqueue(request)
        observeWorkerProgress()
    }

    private fun observeWorkerProgress() {
        workerId?.let { id ->
            workManager.getWorkInfoByIdLiveData(id).observeForever { workInfo ->
                workInfo?.progress?.getInt("progress", 0)?.let {
                    _progressState.value = it
                }
            }
        }
    }

}