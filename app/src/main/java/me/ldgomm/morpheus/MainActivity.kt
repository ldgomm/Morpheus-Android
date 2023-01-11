package me.ldgomm.morpheus

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import dagger.hilt.android.AndroidEntryPoint
import me.ldgomm.morpheus.app.main.view.MainView
import me.ldgomm.morpheus.app.util.theme.MorpheusTheme

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MorpheusTheme {
                MainView()
            }
        }
    }
}
