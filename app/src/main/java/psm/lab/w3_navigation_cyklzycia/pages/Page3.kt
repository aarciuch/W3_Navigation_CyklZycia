package psm.lab.w3_navigation_cyklzycia.pages

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.work.WorkManager
import psm.lab.w3_navigation_cyklzycia.ArtWorkerViewModel
import psm.lab.w3_navigation_cyklzycia.Dane
import psm.lab.w3_navigation_cyklzycia.DaneVM

@Composable
fun Page3(navController: NavController, dane: Dane) {
    val context1 = LocalContext.current
    val vM: DaneVM = viewModel()
    val artWorker = remember{ WorkManager.getInstance(context1) }
    val artWorkeVM = remember { ArtWorkerViewModel(artWorker) }
    val progress by artWorkeVM.progressState.collectAsState()



    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(all = 8.dp)
            .background(Color.Yellow),
        horizontalAlignment = Alignment.CenterHorizontally,

        )
    {
        //var stan = vM.zmienna1.collectAsState().value
        var stan =  vM.zmienna1.collectAsState().value
        val stan2 by remember { vM.zmienna2 }
        HorizontalDivider(thickness = 4.dp, color = Color.Red)
        Text(
            text = "Coroutine: dane = ${dane.dataInt}, ${stan}",
            fontStyle = FontStyle.Italic,
            fontWeight = FontWeight.Bold,
            fontFamily = FontFamily.Serif,
            fontSize = 30.sp,
            modifier = Modifier
                .fillMaxWidth()
                .background(Color.Yellow),
            textAlign = TextAlign.Center
        )
        Button(onClick = { vM.startComputation() }, modifier = Modifier.fillMaxWidth()) {
            Text("Start", fontSize = 20.sp)
        }
        HorizontalDivider(thickness = 4.dp, color = Color.Red)
        Text(
            "Thread: dane = ${dane.dataInt}, ${stan2}",
            modifier = Modifier
                .fillMaxWidth(),
                //.background(color = Color(0xF0FFFF00)),
            fontWeight = FontWeight.Bold,
            fontSize = 20.sp,
            textAlign = TextAlign.Center
        )
        Button(onClick = { vM.startThread() }, modifier = Modifier.fillMaxWidth(), contentPadding = PaddingValues(24.dp)) {
            Text("Start", fontWeight = FontWeight.Bold, fontSize = 20.sp)
        }
        HorizontalDivider(thickness = 4.dp, color = Color.Red, modifier = Modifier.padding(4.dp))
        Column(
            modifier = Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Text("Postęp: $progress%")
            LinearProgressIndicator(
                progress = { progress / 100f },
            )
            Spacer(Modifier.height(4.dp))
            Button(onClick = { artWorkeVM.startWorker() }) {
                Text("Start WorkManager")
            }
        }
        HorizontalDivider(thickness = 4.dp, color = Color.Red, modifier = Modifier.padding(4.dp))
    }
}
