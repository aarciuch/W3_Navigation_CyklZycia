package psm.lab.w3_navigation_cyklzycia

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Build
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.AbsoluteAlignment
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import psm.lab.w3_navigation_cyklzycia.pages.Page1
import psm.lab.w3_navigation_cyklzycia.pages.Page2
import psm.lab.w3_navigation_cyklzycia.pages.Page3
import psm.lab.w3_navigation_cyklzycia.pages.Pages
import psm.lab.w3_navigation_cyklzycia.ui.theme.W3_Navigation_CyklZyciaTheme

class MainActivity : ComponentActivity() {
    val SP_TAG = "SharedPref"
    var d: Dane = Dane()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            W3_Navigation_CyklZyciaTheme {
                AppWithBottomBarNavigation(d)
                if (savedInstanceState != null) d.dataInt = savedInstanceState.getInt("dataState")
                else {
                    val persistantState =
                        applicationContext.getSharedPreferences(SP_TAG, Context.MODE_PRIVATE)
                    d.dataInt = persistantState.getInt("DANE", 0)
                }
                Log.i("STATE ACTIVITY 1", "onCreate")
            }
        }
    }

    override fun onStart() {
        super.onStart()
        Log.i("STATE ACTIVITY 1", "onStart")
    }

    override fun onStop() {
        super.onStop()
        Log.i("STATE ACTIVITY 1", "onStop")
        val persistantState =
            applicationContext.getSharedPreferences(SP_TAG, Context.MODE_PRIVATE) ?: return
        with(persistantState.edit()) {
            putInt("DANE", d.dataInt)
            apply()
        }
    }

    override fun onResume() {
        super.onResume()
        Log.i("STATE ACTIVITY 1", "onResume")
    }

    override fun onPause() {
        super.onPause()
        Log.i("STATE ACTIVITY 1", "onPause")
    }

    override fun onDestroy() {
        Log.i("STATE ACTIVITY 1", "onDestroy")
        super.onDestroy()
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)
        Log.i("STATE ACTIVITY 1", "onRestoreInstanceState")
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        Log.i("STATE ACTIVITY 1", "onSaveInstanceState")
        outState.putInt("dataState", d.dataInt)
    }
}

@Composable
fun AppWithBottomBarNavigation(dane: Dane) {
    val navController = rememberNavController()

    Scaffold(
        bottomBar = {
            BottomNavigationBar(navController, dane)
        }
    ) { innerPadding ->
        NavHost(
            navController = navController,
            startDestination = Pages.Page1.name,
            modifier = Modifier.padding(innerPadding)
        ) {
            composable(route = Pages.Page1.name) { Page1(navController, dane) }
            composable(
                route = "${Pages.Page2.name}/{itemId}",
                arguments = listOf(navArgument("itemId") { type = NavType.StringType })
            ) {
                Page2(navController, it.arguments?.getString("itemId") ?: "")
            }
            composable(route = Pages.Page3.name) { Page3(navController, dane) }
        }
    }
}

@Composable
fun BottomNavigationBar(navController: NavController, dane: Dane) {
    var context = LocalContext.current

    BottomAppBar() {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Absolute.SpaceEvenly
        ) {
            Column() {
                Text(
                    text = "Page1",
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.primary,
                )
                IconButton(onClick = { navController.navigate(route = Pages.Page1.name) })  {
                    Icon(Icons.Filled.Home, contentDescription = "Page1")
                }
            }
            Column () {
                Text(
                    text = "Page2",
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.primary,
                )
                IconButton(onClick = { navController.navigate(route = "${Pages.Page2.name}/${++dane.dataInt}") }) {
                    Icon(Icons.Filled.Favorite, contentDescription = "Page2")
                }
            }
            Column() {
                Text(
                    text = "Page3",
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.primary,
                )
                IconButton(onClick = { navController.navigate(route = Pages.Page3.name) })  {
                    Icon(Icons.Filled.Build, contentDescription = "Page3")
                }
            }
            Column () {
                Text(
                    text = "Activity2",
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.primary,
                )
                IconButton(onClick = {
                    context.startActivity(Intent(context, MainActivity2::class.java)) }) {
                    Icon(Icons.Filled.LocationOn, contentDescription = "Activity2")
                }
            }
        }
    }
}

