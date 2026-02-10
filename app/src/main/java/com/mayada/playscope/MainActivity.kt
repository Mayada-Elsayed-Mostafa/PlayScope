package com.mayada.playscope

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.mayada.playscope.ui.navigation.GameHubNavGraph
import com.mayada.playscope.ui.theme.PlayScopeTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            PlayScopeTheme {
                GameHubNavGraph()
            }
        }
    }
}
