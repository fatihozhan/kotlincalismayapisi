package com.example.calismayapisi

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.calismayapisi.ui.theme.CalismaYapisiTheme
import com.google.gson.Gson

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            CalismaYapisiTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    SayfaGecisleri()
                }
            }
        }
    }
}

@Composable
fun SayfaGecisleri() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = "anasayfa") {
        composable("anasayfa") {
            Anasayfa(navController = navController)
        }
        composable(
            "sayfaA/{nesne}", arguments = listOf(
                navArgument("nesne") { type = NavType.StringType },
            )
        ) {
            val json = it.arguments?.getString("nesne")
            val nesne = Gson().fromJson(json, Kisiler::class.java)
            SayfaA(navController = navController, nesne)
        }
        composable("sayfaB") {
            SayfaB()
        }
    }
}

@Composable
fun Anasayfa(navController: NavController) {
    val sayac = remember { mutableStateOf(0) }
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.SpaceEvenly,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Text(text = "Anasayfa", fontSize = 50.sp)
        Button(onClick = {
            val kisi = Kisiler("Fatih", 25, 180f, true)
            val kisiJson = Gson().toJson(kisi)
            navController.navigate("sayfaA/$kisiJson")
        }) {
            Text(text = "Sayfa A'ya Git")
        }
        Text(text = "Sayac ${sayac.value}")
        Button(onClick = { sayac.value = sayac.value + 1 }) {
            Text(text = "Tıkla")

        }

    }

    LaunchedEffect(key1 = true) {
        Log.e("Anasayfa", "LaunchedEffect çalıştı")
    }
    SideEffect {
        Log.e("Anasayfa", "SideEffect çalıştı")

    }
    DisposableEffect(Unit) {
        onDispose {
            Log.e("Anasayfa", "DisposableEffect çalıştı")

        }
    }
}


@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    CalismaYapisiTheme {

    }
}