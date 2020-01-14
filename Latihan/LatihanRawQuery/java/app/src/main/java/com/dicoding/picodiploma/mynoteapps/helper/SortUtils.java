package com.dicoding.picodiploma.mynoteapps.helper;

import androidx.sqlite.db.SimpleSQLiteQuery;

public class SortUtils {
    public static final String NEWEST = "Newest";
    public static final String OLDEST = "Oldest";
    public static final String RANDOM = "Random";

    public static SimpleSQLiteQuery getSortedQuery(String filter) {
        StringBuilder simpleQuery = new StringBuilder().append("SELECT * FROM note ");
        if (filter.equals(NEWEST)) {
            simpleQuery.append("ORDER BY id DESC");
        }
        else if (filter.equals(OLDEST)) {
            simpleQuery.append("ORDER BY id ASC");
        } else if (filter.equals(RANDOM)) {
            simpleQuery.append("ORDER BY RANDOM()");
        }
        return new SimpleSQLiteQuery(simpleQuery.toString());
    }
}
