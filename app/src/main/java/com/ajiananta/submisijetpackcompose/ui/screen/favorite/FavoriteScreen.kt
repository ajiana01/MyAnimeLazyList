package com.ajiananta.submisijetpackcompose.ui.screen.favorite

import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.ajiananta.submisijetpackcompose.R
import com.ajiananta.submisijetpackcompose.di.DataInjection
import com.ajiananta.submisijetpackcompose.ui.ViewModelFactory
import com.ajiananta.submisijetpackcompose.ui.common.UIState
import com.ajiananta.submisijetpackcompose.ui.component.AnimeFavorite
import com.ajiananta.submisijetpackcompose.ui.theme.SubmisiJetpackComposeTheme

@Composable
fun FavoriteScreen(
    viewModel: FavoriteViewModel = viewModel(
        factory = ViewModelFactory(
            DataInjection.provideRepository()
        )
    )
) {
    viewModel.uiState.collectAsState(initial = UIState.Loading).value.let { uiState: UIState<StateFav> ->
        when (uiState) {
            is UIState.Loading -> {
                viewModel.getFavorite()
            }
            is UIState.Success -> {
                FavoriteContent(
                    uiState.data,
                    onFavoriteClick = { id, unFav ->
                        viewModel.updateFavorite(id, unFav)
                        Log.d("FavoriteScreen", "id: $id")
                    }
                )
            }
            is UIState.Error -> {}
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FavoriteContent(
    state: StateFav,
    onFavoriteClick: (id: Long, unFav: Boolean) -> Unit,
    modifier: Modifier = Modifier
) {
    Column(modifier = modifier) {
        CenterAlignedTopAppBar(
            title = {
                Text(
                    text = stringResource(R.string.myfav),
                    modifier = Modifier
                        .padding(16.dp)
                        .fillMaxWidth(),
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold,
                    textAlign = TextAlign.Center
                )
            }
        )
        LazyColumn(
            contentPadding = PaddingValues(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp),
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 16.dp)
        ) {
            items(state.favorite, key = {it.anime.id}) {
                AnimeFavorite(
                    id = it.anime.id,
                    title = it.anime.title,
                    type = it.anime.type,
                    date = it.anime.date,
                    member = it.anime.member,
                    image = it.anime.image,
                    unFavoriteClick = onFavoriteClick
                )
                Log.d("FavoriteScreen", "key: ${it.anime.id}")
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun FavoriteScreenPreview() {
    SubmisiJetpackComposeTheme {
        FavoriteScreen()
    }
}