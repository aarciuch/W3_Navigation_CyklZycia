package psm.lab.w3_navigation_cyklzycia

import android.app.Application
import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.content.ServiceConnection
import android.os.Handler
import android.os.IBinder
import android.os.Looper
import android.os.Message
import android.os.Messenger
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class ArtServiceVM(application: Application) : AndroidViewModel(application) {

    private var serviceMessenger4ArtService : Messenger? = null
    private val _response = MutableStateFlow("Brak danych")
    val response: StateFlow<String> = _response.asStateFlow()

    private val _progress = MutableStateFlow(0)
    val progress : StateFlow<Int> = _progress

    private val incomingMessenger = Messenger(IncomingHandler())

    private inner class IncomingHandler : Handler(Looper.getMainLooper()) {
        override fun handleMessage(msg: Message) {
            when(msg.what) {
                ArtService.RESPONSE_DONE -> {
                    val responseData = msg.data.getString("response") ?: "No response"
                    viewModelScope.launch {
                        _response.emit(responseData)
                    }
                }
                ArtService.TASK_PROGRESS -> {
                    updateProgress(msg.arg1)
                }
                else -> super.handleMessage(msg)
            }

        }
    }

    private val artServiceConnection = object : ServiceConnection {
        override fun onServiceConnected(p0: ComponentName?, p1: IBinder?) {
           serviceMessenger4ArtService = Messenger(p1)
        }

        override fun onServiceDisconnected(p0: ComponentName?) {
            serviceMessenger4ArtService = null
        }

    }

    fun bindService() {
        val intent = Intent(getApplication(), ArtService::class.java)
        getApplication<Application>().bindService(
            intent,
            artServiceConnection,
            Context.BIND_AUTO_CREATE)
    }

    fun unbindService() {
        getApplication<Application>().unbindService(artServiceConnection)
    }

    fun requestData() {
        serviceMessenger4ArtService?.let {
            val message = Message.obtain(null,ArtService.RESPONSE_REQUEST)
            message.replyTo = incomingMessenger
            it.send(message)
        }
    }

    fun startTask(param : Int = 0) {
        serviceMessenger4ArtService?.let {
            val message = Message.obtain(null,ArtService.DO_TASK)
            message.replyTo = incomingMessenger
            message.arg1 = param
            it.send(message)
        }
    }

    fun updateProgress(value: Int) {
        viewModelScope.launch {
            _progress.emit(value)
        }
    }

}