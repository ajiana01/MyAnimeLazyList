package com.ajiananta.submisijetpackcompose.ui.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.ajiananta.submisijetpackcompose.ui.theme.SubmisiJetpackComposeTheme

@Composable
fun AnimeItem(
    image: Int,
    title: String,
    type: Int,
    date: Int,
    member: Int,
    modifier: Modifier = Modifier,
) {
    Card (
        shape = RoundedCornerShape(8.dp),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.background,
        ),
        modifier = modifier
            .fillMaxWidth()
    ) {
        Row (horizontalArrangement = Arrangement.Start){
            Image(
                painter = painterResource(image),
                contentDescription = "Thumbnail Anime",
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

    }
}


@Composable
@Preview(showBackground = true)
fun AnimeItemPreview() {
    SubmisiJetpackComposeTheme {
        /*AnimeItem(anime = Anime(
                id = 1,
                image = R.drawable.sousou_no_frieren,
                title = "Sousou no Frieren",
                type = R.string.type_eps_sousou,
                date = R.string.date_sousou,
                member = R.string.members_sousou,
            synopsis = 123,
            ),)*/
    }
}