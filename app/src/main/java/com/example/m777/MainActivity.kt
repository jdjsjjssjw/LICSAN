package com.example.m777

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.tooling.preview.Preview
import com.example.m777.R

class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            var darkTheme by remember { mutableStateOf(false) }

            val lightColors = lightColorScheme(
                primary = Color(0xFF4CAF50),
                secondary = Color(0xFF8BC34A),
                background = Color(0xFFF3F3F3)
            )
            val darkColors = darkColorScheme(
                primary = Color(0xFF81C784),
                secondary = Color(0xFFA5D6A7),
                background = Color(0xFF121212)
            )

            MaterialTheme(
                colorScheme = if (darkTheme) darkColors else lightColors
            ) {
                Scaffold(
                    topBar = {
                        TopAppBar(
                            title = { Text("Мемы") },
                            actions = {
                                Switch(
                                    checked = darkTheme,
                                    onCheckedChange = { darkTheme = it }
                                )
                            }
                        )
                    }
                ) { padding ->
                    SuperheroesList(Modifier.padding(padding))
                }
            }
        }
    }
}

@Composable
fun SuperheroesList(modifier: Modifier = Modifier) {

    val heroes = listOf(
        Hero("лысый кот", "Наш герой", R.drawable.hero1),
        Hero("who is he?", "who is he?", R.drawable.hero2),
        Hero("комп", "Mощный комп", R.drawable.hero3),
        Hero("тмс шлб", "Мини Томас Шэлби", R.drawable.hero4),
        Hero("Гений", "самый умный человек во вселенной", R.drawable.hero5),
        Hero("странный кот", "кот был сдела с чата гпт", R.drawable.hero6)
    )

    Column(modifier = modifier.padding(8.dp)) {
        heroes.forEach {
            HeroCard(it)
            Spacer(Modifier.height(12.dp))
        }
    }
}

data class Hero(
    val name: String,
    val description: String,
    val image: Int
)

@Composable
fun HeroCard(hero: Hero) {
    Card(
        shape = RoundedCornerShape(12.dp),
        modifier = Modifier.fillMaxWidth()
    ) {
        Row(Modifier.padding(12.dp)) {

            Image(
                painter = painterResource(id = hero.image),
                contentDescription = hero.name,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .size(72.dp)
                    .padding(end = 12.dp)
            )

            Column {
                Text(text = hero.name, style = MaterialTheme.typography.titleMedium)
                Text(text = hero.description, style = MaterialTheme.typography.bodyMedium)
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewApp() {
    SuperheroesList()
}