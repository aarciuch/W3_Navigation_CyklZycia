package psm.lab.w3_navigation_cyklzycia

import android.app.Service
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.IBinder
import android.os.Looper
import android.os.Message
import android.os.Messenger
import android.util.Log
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class ArtService : Service() {

    private lateinit var messenger: Messenger


    override fun onCreate() {
        super.onCreate()
        messenger = Messenger(IncommighHandler())
    }

    private inner class IncommighHandler : Handler(Looper.getMainLooper()) {
        override fun handleMessage(msg: Message) {
            when(msg.what) {
                RESPONSE_REQUEST-> {
                    val replyMessenger = msg.replyTo
                    val replyMassage = Message.obtain(null, RESPONSE_DONE)
                    val payload = Bundle().apply {
                        putString("response", "ArtService response!!!")
                    }
                    replyMassage.data = payload
                    replyMessenger.send(replyMassage)
                }
                DO_TASK -> {
                    startTaskInBackground(msgReplyTo = msg.replyTo)
                }
                else -> super.handleMessage(msg)
            }
        }

    }

    private fun startTaskInBackground(a : Int = 0, msgReplyTo : Messenger) {
        var _a = a
        GlobalScope.launch {
            for (i in 0..99) {
                delay(100)
                sendProgressUpdate(++_a, msgReplyTo)
            }
        }
    }

    private fun sendProgressUpdate(i: Int, msgReplyTo: Messenger) {
          val message = Message.obtain().apply {
            what = TASK_PROGRESS
            arg1 = i
        }
        msgReplyTo.send(message)
    }

    override fun onBind(intent: Intent): IBinder {
        return messenger.binder
    }

    companion object {
        const val RESPONSE_REQUEST = 1
        const val RESPONSE_DONE = 2
        const val DO_TASK = 3
        const val TASK_PROGRESS = 4
    }
}