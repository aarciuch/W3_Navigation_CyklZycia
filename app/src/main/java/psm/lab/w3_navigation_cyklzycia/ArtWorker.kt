package psm.lab.w3_navigation_cyklzycia

import android.content.Context
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import androidx.work.workDataOf
import kotlinx.coroutines.delay

class ArtWorker(context: Context, params : WorkerParameters
) :  CoroutineWorker(context, params){
    override suspend fun doWork(): Result {
       var a = 0
       for (i in 1..100) {
           a = i
           val progress = (workDataOf("progress" to a))
           setProgress(progress)
           delay(200)
       }
       return Result.success()
    }

}