package com.ajiananta.submisijetpackcompose.ui.screen.home

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.ajiananta.submisijetpackcompose.di.DataInjection
import com.ajiananta.submisijetpackcompose.model.Favorite
import com.ajiananta.submisijetpackcompose.ui.ViewModelFactory
import com.ajiananta.submisijetpackcompose.ui.common.UIState
import com.ajiananta.submisijetpackcompose.ui.component.AnimeItem
import com.ajiananta.submisijetpackcompose.ui.component.Search
import com.ajiananta.submisijetpackcompose.ui.theme.SubmisiJetpackComposeTheme

@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    viewModel: HomeViewModel = viewModel(
        factory = ViewModelFactory(
            DataInjection.provideRepository()
        )
    ),
    navigateToDetail: (Long) -> Unit,
) {
    val query = viewModel.query.value
    val uiState = viewModel.uiState.collectAsState(initial = UIState.Loading).value

    Column (modifier = modifier.fillMaxWidth()){
        Search(
            query = query,
            onQueryChange = viewModel::searchAnime,
            modifier = Modifier
                .fillMaxWidth()
        )
        when (uiState) {
            is UIState.Loading -> {
                viewModel.getAllFavorites()
            }
            is UIState.Success -> {
                HomeContent(
                    favorite = uiState.data,
                    navigateToDetail = navigateToDetail,
                )
            }
            is UIState.Error -> {

            }
        }
    }
}

@Composable
fun HomeContent(
    favorite: List<Favorite>,
    modifier: Modifier = Modifier,
    navigateToDetail: (Long) -> Unit,
) {
    LazyColumn(verticalArrangement = Arrangement.spacedBy(16.dp),
        modifier = modifier
        .padding(start = 16.dp, end = 16.dp, bottom = 16.dp)){
        items(favorite) {
            AnimeItem(
                image = it.anime.image,
                title = it.anime.title,
                type = it.anime.type,
                date = it.anime.date,
                member = it.anime.member,
                modifier = Modifier
                    .clickable { navigateToDetail(it.anime.id) }
                    .fillMaxWidth()
            )
        }
    }
}


@Preview(showBackground = true)
@Composable
fun HomeScreenPreview() {
    SubmisiJetpackComposeTheme {
        HomeScreen(
            navigateToDetail = {}
        )
    }
}