package com.example.listaslazy

import android.net.TetheringManager
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.listaslazy.components.GameCard
import com.example.listaslazy.components.StudioCard
import com.example.listaslazy.model.Game
import com.example.listaslazy.repository.getAllGames
import com.example.listaslazy.repository.getGamesByStudio
import com.example.listaslazy.ui.theme.ListasLazyTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ListasLazyTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                GamesScreen(modifier = Modifier.padding(innerPadding))
                }
            }
        }
    }
}


@Composable
fun GamesScreen(modifier: Modifier = Modifier) {
    Column(
        modifier = modifier.padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {


        var studio by remember { mutableStateOf("") }
        var gamesListByStudio by remember {
            mutableStateOf(getGamesByStudio(""))
        }


        Text(
            text = "Meus jogos favoritos",
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold
        )

        OutlinedTextField(
            value = studio,
            onValueChange = {
                     studio = it
            },

            modifier = Modifier.fillMaxWidth(),
            label = {Text("Nome do estudio: ")},
            singleLine = true,
            trailingIcon = {
                IconButton(
                    onClick = {

                        gamesListByStudio = getGamesByStudio(studio)

                    }

                ) {
                    Icon(
                        imageVector = Icons.Default.Search,
                        contentDescription = "Imagem de uma lupa"
                    )
                } // Fecha IconButton


            } // Fecha o trailingIcon

        ) // Fecha outLinedTextField

        // Faz um linha que pode ser movida, assim ela vai renderizando aos poucos os cards
        LazyRow() {
            items(gamesListByStudio){
                StudioCard(it)

            }// Fecha Items
        } // Fecha o Lazy Row


        // Faz uma coluna que pode ser movida, assim ela vai renderizando aos poucos os cards
        LazyColumn() {
            items(gamesListByStudio){
                GameCard(it)

            }// Fecha Items
        } // Fecha LazyColumn


    } // Fecha a Coluna principal





}
