package eu.vinconafta.porovnajto.datas.entities

import android.content.Context
import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import eu.vinconafta.porovnajto.R
import kotlinx.parcelize.Parcelize


@Entity(tableName = "items")
data class Item(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val name: String,
    val producer: String,
    val refToStoreItem: Int,
    val icon: String,
    val refToPrice: Int,
    val refToCategory: Int

){

    fun getResId(context: Context): Int {
        val iconResId = context.resources.getIdentifier(this.icon, "drawable", context.packageName)

        return if (iconResId != 0) iconResId else R.drawable.nic
    }
}





