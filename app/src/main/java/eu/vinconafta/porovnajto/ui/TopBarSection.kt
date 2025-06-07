package eu.vinconafta.porovnajto.ui

import androidx.annotation.StringRes
import eu.vinconafta.porovnajto.R

/**
 * Tento vymenovací typ slúži na definovanie položiek v hlavnom menu aplikácie
 */
enum class TopBarSection(@StringRes val categoryName: Int) {
    CATEGORIES(categoryName = R.string.categories),
//    SETS(categoryName = R.string.sets),
    STORES(categoryName = R.string.stores),
    PRODUCTS(categoryName = R.string.products)
}