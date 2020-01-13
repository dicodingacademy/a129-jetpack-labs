package com.dicoding.picodiploma.mynoteapps.helper

import androidx.sqlite.db.SimpleSQLiteQuery

object SortUtils {
    const val NEWEST = "Newest"
    const val OLDEST = "Oldest"

    fun getSortedQuery(filter: String): SimpleSQLiteQuery {
        val simpleQuery = StringBuilder().append("SELECT * FROM note ")
        if (filter == NEWEST) {
            simpleQuery.append("ORDER BY id DESC")
        } else if (filter == OLDEST) {
            simpleQuery.append("ORDER BY id ASC")
        }
        return SimpleSQLiteQuery(simpleQuery.toString())
    }
}