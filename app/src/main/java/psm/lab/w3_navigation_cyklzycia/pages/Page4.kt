package psm.lab.w3_navigation_cyklzycia.pages

import android.inputmethodservice.Keyboard
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material3.Button
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import org.koin.androidx.compose.koinViewModel
import psm.lab.w3_navigation_cyklzycia.ArtServiceVM

@Composable
fun Page4(navController: NavController, artServiceVM: ArtServiceVM = koinViewModel()) {
    val response by artServiceVM.response.collectAsState()
    var reposneClicked by remember { mutableStateOf(false) }
    val progress by artServiceVM.progress.collectAsState()

    LaunchedEffect(Unit) {
        artServiceVM.bindService()
        artServiceVM.requestData()
    }

    DisposableEffect(Unit) {
        onDispose {
            artServiceVM.unbindService()
        }
    }

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center
    ) {
        Text(text = "ArtService", modifier = Modifier.padding(top = 8.dp, bottom = 8.dp))
        HorizontalDivider(thickness = 2.dp)
        if (reposneClicked) {
            Text(text = if (reposneClicked) "Odpowiedź: $response" else "")
        }
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 2.dp, bottom = 2.dp),
            horizontalArrangement = Arrangement.Absolute.SpaceEvenly,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Box(Modifier
                .clickable
                {
                    artServiceVM.requestData()
                    reposneClicked = true
                }
                .background(color = Color.LightGray))
            {
                Column(verticalArrangement = Arrangement.Center) {
                    Icon(Icons.Default.PlayArrow, contentDescription = "clear")
                    Text("Get Data")
                }
            }
            Button(onClick = { reposneClicked = false }) {
                Column(verticalArrangement = Arrangement.Center) {
                    Icon(Icons.Default.PlayArrow, contentDescription = "clear")
                    Text("Clear")
                }
            }
        }
        HorizontalDivider(thickness = 2.dp)
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalArrangement = Arrangement.Center
        ) {
            Text("Task progress: $progress%", style = MaterialTheme.typography.headlineSmall)

            Spacer(modifier = Modifier.height(16.dp))

            LinearProgressIndicator(
                progress = { progress / 100f },
                modifier = Modifier.fillMaxWidth(),
            )

            Spacer(modifier = Modifier.height(16.dp))

            Button(onClick = { artServiceVM.startTask() }) {
                Text("Start task")
            }
        }
        HorizontalDivider(thickness = 2.dp)
    }
}