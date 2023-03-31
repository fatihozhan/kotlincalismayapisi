package com.example.calismayapisi

import android.app.Activity
import android.util.Log
import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.sp


@Composable
fun SayfaB() {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.SpaceEvenly,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Text(text = "Sayfa B", fontSize = 50.sp)

    }


    //Üzerinde çalışılan contexti bir değişkene atıp geri tuşuna bastığımızda bitmesini sağlıyoruz.
    val activity = (LocalContext as Activity)
    BackHandler(onBack = {
        Log.e("SayfaB", "Geri Tuşuna Basıldı")
        activity.finish()
    })
}