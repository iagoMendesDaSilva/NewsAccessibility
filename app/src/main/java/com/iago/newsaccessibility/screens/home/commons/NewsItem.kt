package com.iago.newsaccessibility.screens.home.commons

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.iago.newsaccessibility.R
import com.iago.newsaccessibility.models.Article
import com.iago.newsaccessibility.utils.Format.formatDate
import com.iago.newsaccessibility.utils.Format.ordinalOf

@Composable
fun NewsItem(article: Article, index: Int, callback: (url: Int) -> Unit) {

    val descCardOrder = stringResource(R.string.desc_ordinal_list, ordinalOf(index + 1))
    val descPublishedAt = stringResource(R.string.desc_publishedAt, formatDate(article.publishedAt))

    Row(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colors.surface)
            .semantics(mergeDescendants = true) {
                contentDescription = descCardOrder
            }
            .clickable(
                onClickLabel = stringResource(R.string.desc_action_open_news),
                onClick = { callback(index) })
    ) {
        AsyncImage(
            modifier = Modifier
                .width(150.dp)
                .height(100.dp),
            model = ImageRequest.Builder(LocalContext.current)
                .data(
                    if (article.urlToImage.isNullOrEmpty())
                        R.drawable.no_image
                    else
                        article.urlToImage
                )
                .crossfade(true)
                .build(),
            placeholder = painterResource(R.drawable.no_image),
            contentDescription = null,
            contentScale = ContentScale.Crop,
        )
        Column(
            modifier = Modifier
                .height(100.dp)
                .padding(5.dp),
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            Text(text = article.title, maxLines = 2, style = TextStyle(fontSize = 13.sp), color=MaterialTheme.colors.onSurface)
            Text(
                modifier = Modifier.semantics { contentDescription = descPublishedAt },
                text = formatDate(article.publishedAt),
                style = TextStyle(fontSize = 12.sp),
                    color = MaterialTheme.colors.secondary
            )
        }

    }
}
