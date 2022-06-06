package com.iago.newsaccessibility.screens.home.commons

import androidx.compose.foundation.layout.size
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.clearAndSetSemantics
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.unit.dp
import com.iago.newsaccessibility.R

@Composable
fun Load() {

    val description = stringResource(R.string.desc_loading_news)

    CircularProgressIndicator(
        color = MaterialTheme.colors.onSurface,
        strokeWidth = 3.dp,
        modifier = Modifier
            .size(25.dp)
            .semantics(mergeDescendants = false) {
                contentDescription = description
            }
            .clearAndSetSemantics {}
    )
}