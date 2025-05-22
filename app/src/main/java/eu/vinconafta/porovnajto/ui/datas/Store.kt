package eu.vinconafta.porovnajto.ui.datas

import android.content.Context


data class StoreItem(
                     val storeName: String,
                     val iconName: String) {
    fun getResId(context: Context): Int {
        return context.resources.getIdentifier(iconName, "drawable", context.packageName)
    }
}