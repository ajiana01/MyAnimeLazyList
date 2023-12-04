package com.ajiananta.submisijetpackcompose.ui.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconToggleButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.ajiananta.submisijetpackcompose.R

@Composable
fun AnimeFavorite(
    id: Long,
    title: String,
    type: Int,
    date: Int,
    member: Int,
    image: Int,
    modifier: Modifier = Modifier,
    unFavoriteClick: (animeId: Long, unfav: Boolean) -> Unit
) {
    Card (
        shape = RoundedCornerShape(8.dp),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.background,
        ),
        modifier = modifier
            .fillMaxWidth()
    ) {
        Row (
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ){
            Row (horizontalArrangement = Arrangement.Start,
                verticalAlignment = Alignment.CenterVertically){
                Image(
                    painter = painterResource(image),
                    contentDescription = "Thumbnail Anime $id",
                    contentScale = ContentScale.Fit,
                    modifier = Modifier
                        .width(80.dp)
                        .clip(RoundedCornerShape(8.dp))
                )
                Column (modifier = Modifier.padding(8.dp)) {
                    Text(
                        text = title,
                        maxLines =1,
                        style = MaterialTheme.typography.titleMedium.copy(
                            fontWeight = FontWeight.ExtraBold
                        ),
                    )
                    Spacer(modifier = Modifier
                        .height(16.dp))
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
            IconToggleButton(
                checked = true,
                onCheckedChange = { unFavoriteClick(id,false) }
            ) {
                Icon(
                    imageVector = Icons.Filled.Favorite,
                    contentDescription = "Favorite",
                    tint = Color.Red,
                    modifier = Modifier.size(48.dp)
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun AnimeFavoritePreview() {
    AnimeFavorite(
        id = 1,
        title = "Naruto",
        type = 1,
        date = 1,
        member = 1,
        image = R.drawable.fullmetal_alchemist_brotherhood,
        unFavoriteClick = { _, _ ->}
    )
}