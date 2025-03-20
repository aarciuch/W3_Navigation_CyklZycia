package psm.lab.w3_navigation_cyklzycia.pages


import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.pointer.motionEventSpy
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import psm.lab.w3_navigation_cyklzycia.Dane

@Composable
fun Page1(navController : NavController, dane: Dane) {
    var liczbaPage1 by rememberSaveable { mutableStateOf(10) }
    Column (Modifier
                .padding(start = 24.dp)
                .padding(top = 24.dp)
                .padding(end = 24.dp)
                .fillMaxWidth())
    {
        Text("Hello Page1 liczba = ${liczbaPage1} dane = ${dane.dataInt}", modifier = Modifier.padding(top = 20.dp))
        Button(modifier = Modifier
                    .padding(all=8.dp)
                    .fillMaxWidth(),
            onClick = {navController.navigate(route = "${Pages.Page2.name}/${++dane.dataInt}")
        })
        {
            Icon(Icons.Filled.Favorite, contentDescription = "Page2")
        }
        Button(modifier = Modifier
            .padding(all=8.dp)
            .fillMaxWidth(),
            onClick = { ++liczbaPage1 })
        {
            Icon(Icons.Filled.Add, contentDescription = "ADD")
        }
    }
}