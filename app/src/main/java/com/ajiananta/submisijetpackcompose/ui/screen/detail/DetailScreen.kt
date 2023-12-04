package com.ajiananta.submisijetpackcompose.ui.screen.detail

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.ajiananta.submisijetpackcompose.R
import com.ajiananta.submisijetpackcompose.di.DataInjection
import com.ajiananta.submisijetpackcompose.ui.ViewModelFactory
import com.ajiananta.submisijetpackcompose.ui.common.UIState
import com.ajiananta.submisijetpackcompose.ui.component.FavoriteButton
import com.ajiananta.submisijetpackcompose.ui.theme.SubmisiJetpackComposeTheme

@Composable
fun DetailScreen(
    id: Long,
    viewModel: DetailAnimeViewModel = viewModel(
        factory = ViewModelFactory(DataInjection.provideRepository())
    ),
    navigateBack: () -> Unit,
    navigateToFavorite: () -> Unit
) {
    viewModel.uiState.collectAsState(initial = UIState.Loading).value.let { uiState ->
        when (uiState) {
            is UIState.Loading -> {
                viewModel.getFavoriteById(id)
            }
            is UIState.Success -> {
                val data = uiState.data
                DetailAnimeContent(
                    image = data.anime.image,
                    title = data.anime.title,
                    type = data.anime.type,
                    date = data.anime.date,
                    member = data.anime.member,
                    synopsis = data.anime.synopsis,
                    onBackClick = navigateBack,
                    onAddToFavorite = {
                        viewModel.addToFavorite(data.anime, true)
                        navigateToFavorite()
                    },
                )
            }
            is UIState.Error -> {}
        }
    }
}

@Composable
fun DetailAnimeContent(
    image: Int,
    title: String,
    type: Int,
    date: Int,
    member: Int,
    synopsis: Int,
    onBackClick: () -> Unit,
    onAddToFavorite: (fav: Boolean) -> Unit,
    modifier: Modifier = Modifier
    ) {
    Column(modifier = Modifier
        .verticalScroll(rememberScrollState())
        .padding(16.dp)) {
        Row (verticalAlignment = Alignment.CenterVertically) {
            Icon(
                imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                contentDescription = stringResource(R.string.back),
                modifier = Modifier
                    .clickable { onBackClick() }
            )
            Spacer(modifier = Modifier.width(8.dp))
            Text(text = stringResource(R.string.detail_anime), style = MaterialTheme.typography.titleLarge)
        }
        Spacer(modifier = Modifier.height(16.dp))
        Text(text = title, style = MaterialTheme.typography.titleLarge.copy(
            fontWeight = FontWeight.Bold))
        Spacer(modifier = Modifier.height(8.dp))
        Row(modifier = modifier) {
            Image(
                painter = painterResource(image),
                contentDescription = "Thumbnail Anime",
                contentScale = ContentScale.Fit,
                modifier = Modifier
                    .width(120.dp)
                    .clip(RoundedCornerShape(8.dp))
            )
            Column (horizontalAlignment = Alignment.Start,
                modifier = Modifier.padding(16.dp)) {
                Text(
                    text = stringResource(type),
                    maxLines =1,
                    style = MaterialTheme.typography.bodyMedium,
                )
                Text(
                    text = stringResource(date),
                    maxLines =1,
                    style = MaterialTheme.typography.bodyMedium,
                )
                Text(
                    text = stringResource(member),
                    maxLines =1,
                    style = MaterialTheme.typography.bodyMedium,
                )
            }
        }
        Spacer(modifier = Modifier.height(8.dp))
        Text(text = stringResource(R.string.synopsis), style = MaterialTheme.typography.titleMedium.copy(
            fontWeight = FontWeight.Bold))
        Spacer(modifier = Modifier.height(8.dp))
        Text(text = stringResource(synopsis), style = MaterialTheme.typography.bodyMedium)
        Spacer(modifier = Modifier.height(64.dp))
        FavoriteButton(text = stringResource(R.string.favorite), onClick = { onAddToFavorite(true) })
    }
}

@Preview(showBackground = true)
@Composable
fun DetailScreenPreview() {
    SubmisiJetpackComposeTheme {
        DetailScreen(2, navigateBack = {}, navigateToFavorite = {})
    }
}