package psm.lab.w3_navigation_cyklzycia

import android.util.Log
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class DaneVM() : ViewModel() {
    private var _zmienna1 = MutableStateFlow<Int>(0)
    val zmienna1 = _zmienna1.asStateFlow()

    private var _zmienna2 = MutableStateFlow<Int>(0)
    val zmienna2 = _zmienna2.asStateFlow()




    fun startComputation(d:Int = 0) {
        GlobalScope.launch(Dispatchers.Default) {
            _zmienna1.value = d
            for ( i in 1..100) {
                _zmienna1.value = _zmienna1.value.inc()
                delay(1000L)
                Log.i("VM", "stan = ${_zmienna1.value}")
            }
        }
    }

    fun startThread(d:Int = 0) {
        object : Thread() {
            override fun run() {
                super.run()
                for (i in 1..100) {
                    _zmienna2.value = _zmienna2.value.inc()
                    Thread.sleep(1000L)
                    Log.i("VM", "stan = ${_zmienna2.value}")
                }
            }
        }.start()
    }
}