package com.iago.newsaccessibility.screens.home.commons

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.OpenInBrowser
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalUriHandler
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.clearAndSetSemantics
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.heading
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.iago.newsaccessibility.R
import com.iago.newsaccessibility.models.Article
import com.iago.newsaccessibility.ui.theme.Primary
import com.iago.newsaccessibility.utils.Format.formatContent
import com.iago.newsaccessibility.utils.Format.formatDate

@Composable
fun NewsPage(article: Article) {

    val uriHandler = LocalUriHandler.current

    val descPublishedAt = stringResource(R.string.desc_publishedAt, formatDate(article.publishedAt))
    val content = if (article.content.isNullOrEmpty()) stringResource(R.string.no_content)
    else formatContent(article.content)

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .background(color = MaterialTheme.colors.background),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        item {
            AsyncImage(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(250.dp),
                model = ImageRequest.Builder(LocalContext.current)
                    .data(
                        if (article.urlToImage.isNullOrEmpty()) R.drawable.no_image
                        else article.urlToImage
                    )
                    .crossfade(true)
                    .build(),
                placeholder = painterResource(R.drawable.no_image),
                contentDescription = null,
                contentScale = ContentScale.Crop,
            )
            Column(modifier = Modifier.padding(8.dp)) {
                Text(
                    text = article.title,
                    color = MaterialTheme.colors.onSurface,
                    modifier = Modifier.semantics { heading() },
                    style = TextStyle(fontSize = 23.sp, fontWeight = FontWeight.Bold),
                )
                Spacer(modifier = Modifier.height(10.dp))
                Text(
                    color = MaterialTheme.colors.onSurface,
                    modifier = Modifier.semantics { heading() },
                    text = article.description ?: stringResource(R.string.no_description),
                    style = TextStyle(fontSize = 18.sp, fontWeight = FontWeight.Bold),
                )
                Spacer(modifier = Modifier.height(10.dp))
                Text(
                    text = content,
                    Modifier.semantics { heading() },
                    color = MaterialTheme.colors.onSurface,
                    style = TextStyle(fontSize = 18.sp),
                )
                Spacer(modifier = Modifier.height(15.dp))
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.Bottom,
                    horizontalArrangement = Arrangement.SpaceBetween,
                ) {
                    Text(
                        color = MaterialTheme.colors.secondary,
                        style = TextStyle(fontSize = 16.sp),
                        text = formatDate(article.publishedAt),
                        modifier = Modifier
                            .semantics(mergeDescendants = false) {
                                contentDescription = descPublishedAt
                            }
                            .clearAndSetSemantics {}
                    )
                    Icon(
                        tint = Primary,
                        modifier = Modifier
                            .size(35.dp)
                            .clickable(
                                onClickLabel = stringResource(R.string.desc_action_open_on_web),
                                onClick = { uriHandler.openUri(article.url) }),
                        imageVector = Icons.Default.OpenInBrowser,
                        contentDescription = stringResource(R.string.desc_icon_open_on_web)
                    )
                }
            }
        }
    }
}