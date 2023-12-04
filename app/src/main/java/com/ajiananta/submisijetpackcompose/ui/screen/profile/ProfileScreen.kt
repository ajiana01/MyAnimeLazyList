package com.ajiananta.submisijetpackcompose.ui.screen.profile

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.ajiananta.submisijetpackcompose.R
import com.ajiananta.submisijetpackcompose.ui.theme.SubmisiJetpackComposeTheme

@Composable
fun ProfileScreen(
    modifier: Modifier = Modifier
) {
    Box(modifier = modifier) {
        ProfileScreenContent(
            modifier = Modifier
                .padding(top = 160.dp)
                .fillMaxWidth()
                .fillMaxHeight()
        )
    }
}

@Composable
fun ProfileScreenContent(
    modifier: Modifier = Modifier,
) {
    Column (
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier
    ) {
        Image(
            painter = painterResource(R.drawable.profil),
            contentDescription = stringResource(R.string.profile_photo),
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .size(256.dp)
                .clip(CircleShape)
            )
        Spacer(modifier = Modifier.padding(16.dp))
        Text(
            text = stringResource(R.string.ajiananta),
            style = MaterialTheme.typography.titleLarge.copy(
                fontWeight = FontWeight.ExtraBold
            ),
        )
        Text(
            text = stringResource(R.string.email),
            style = MaterialTheme.typography.titleMedium,
        )
    }
}

@Preview(showBackground = true)
@Composable
fun ProfileScreenPreview() {
    SubmisiJetpackComposeTheme {
        ProfileScreen()
    }
}