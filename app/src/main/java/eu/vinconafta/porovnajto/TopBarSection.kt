package eu.vinconafta.porovnajto

import androidx.annotation.StringRes

enum class TopBarSection(@StringRes val category_name: Int) {
    Kategorie(category_name = R.string.categories),
    Zoznamy(category_name = R.string.sets),
    Obchody(category_name = R.string.stores),
    Produkty(category_name = R.string.products)
}