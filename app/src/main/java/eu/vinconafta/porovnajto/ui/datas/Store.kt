package eu.vinconafta.porovnajto.ui.datas

import android.content.Context
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "store")
data class StoreItem(
    @PrimaryKey(
        autoGenerate = true) val id: Int = 0,
                     val storeName: String,
                     val iconName: String) {
    fun getResId(context: Context): Int {
        return context.resources.getIdentifier(iconName, "drawable", context.packageName)
    }
}