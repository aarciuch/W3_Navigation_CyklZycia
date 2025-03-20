package psm.lab.w3_navigation_cyklzycia.pages

import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController

@Composable
fun Page2(navController : NavController, itemID : String? = "Ala") {
    Text ("Hello Page2 $itemID", modifier = Modifier.padding(top = 50.dp))
}