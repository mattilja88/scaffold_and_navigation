package com.example.scaffold

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.scaffold.ui.theme.ScaffoldTheme
import androidx.compose.material3.*
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.graphics.Color
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ScaffoldTheme {
                ScaffoldApp()
            }
        }
    }
}

@Composable
fun ScaffoldApp() {
    val navController = rememberNavController()
    NavHost(
        navController = navController,
        startDestination = "Home"
    ) {
        composable(route = "Home"){
            MainScreen(navController)
        }
        composable(route = "Info"){
            InfoScreen(navController)
        }
        composable(route = "Settings"){
            SettingsScreen(navController)
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainTopBar(title: String, navController: NavController) {
    var expanded by remember { mutableStateOf(false) }

    TopAppBar(
        title = { Text(title, color = Color.White) }, // White text on the top bar
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = Color(0xFF6200EE) // Purple background for the main screen's top bar
        ),
        actions = {
            IconButton(
                onClick = {
                expanded = !expanded
                }
            ) {
                Icon(Icons.Filled.MoreVert, tint = Color.White, contentDescription = null)
            }
            DropdownMenu(
                expanded = expanded,
                onDismissRequest = { expanded = false },
            ) {
                DropdownMenuItem(
                    text = { Text("Info") },
                    onClick = {
                        navController.navigate("info")
                    }
                )
                DropdownMenuItem(
                    text = { Text("Settings") },
                    onClick = {
                        navController.navigate("settings")
                    }
                )
            }
        }
    )
}
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ScreenTopBar(title: String, navController: NavController) {
    TopAppBar(title = { Text(title, color = Color.White) }, // White text for screen titles
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = Color(0xFF03DAC5) // Teal background for secondary screens
        ),
        navigationIcon = {
            IconButton(onClick = {navController.navigateUp()}) {
                Icon(Icons.Filled.ArrowBack, contentDescription = null)
            }
        }
    )
}

@Composable
fun MainScreen(navController: NavController) {
    Scaffold(
        topBar = {
            MainTopBar("My App", navController)
        },
        containerColor = Color(0xFFF0F0F0)
    ) { paddingValues ->
        Text(
            text = "Content for Home Screen",
            modifier = Modifier.padding(paddingValues),
        )
    }
}

@Composable
fun InfoScreen(navController: NavController) {
    Scaffold(
        topBar = {
            ScreenTopBar("Info", navController)
        },
        containerColor = Color(0xFFE0F7FA)
    ) { paddingValues ->
        Text(
            text = "Content for Info screen",
            modifier = Modifier.padding(paddingValues)
        )
    }
}

@Composable
fun SettingsScreen(navController: NavController) {
    Scaffold(
        topBar = {
            ScreenTopBar("Settings", navController)
        },
        containerColor = Color(0xFFFCE4EC)
    ) { paddingValues ->
        Text(
            text = "Content for Settings Screen",
            modifier = Modifier.padding(paddingValues)
        )
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    ScaffoldTheme {
        ScaffoldApp()
    }
}