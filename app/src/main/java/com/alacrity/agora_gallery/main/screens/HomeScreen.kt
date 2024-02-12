package com.alacrity.agora_gallery.main.screens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.alacrity.agora_gallery.entity.Image
import com.skydoves.landscapist.glide.GlideImage

@Composable
fun HomeScreen(images: List<Image>, onImageClick: (Image) -> Unit) {
    LazyVerticalGrid(
        modifier = Modifier.fillMaxSize(),
        columns = GridCells.Fixed(3)
    ) {
        items(images) { photo ->
            PhotoItem(photo) { onImageClick(photo) }
        }
    }
}

@Composable
fun PhotoItem(image: Image, onClick: () -> Unit) {
    GlideImage(
        modifier = Modifier
            .fillMaxWidth()
            .aspectRatio(1f)
            .padding(4.dp)
            .clickable { onClick() },
        imageModel = { image.urls.regular },
        loading = { LoadingView() }
    )
}
