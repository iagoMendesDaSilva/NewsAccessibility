package com.iago.newsaccessibility.utils

import java.text.SimpleDateFormat

object Format {

    fun formatContent(content: String): String {
        return content.split("[+")[0]
    }

    fun formatDate(publishedAt: String): String {
        val parser = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'")
        val formatter = SimpleDateFormat("dd/MM/yyyy HH:mm a")
        return formatter.format(parser.parse(publishedAt))
    }

    fun ordinalOf(i: Int): String {
        val j = i % 10
        val k = i % 100;
        if (j == 1 && k != 11) {
            return "${i}st";
        }
        if (j == 2 && k != 12) {
            return "${i}nd";
        }
        if (j == 3 && k != 13) {
            return "${i}rd";
        }
        return "${i}th";
    }
}