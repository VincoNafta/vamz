package eu.vinconafta.porovnajto.datas.entities

import android.content.Context
import androidx.room.Entity
import androidx.room.PrimaryKey
import eu.vinconafta.porovnajto.R


/**
 * Db entita Item ORM framerowku
 * @author Marek Štefanča
 */
@Entity(tableName = "items")
data class Item(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val name: String,
    val producer: String,
    val icon: String,
    val refToCategory: Int

){

    fun getResId(context: Context): Int {
        val iconResId = context.resources.getIdentifier(this.icon, "drawable", context.packageName)

        return if (iconResId != 0) iconResId else R.drawable.nic
    }
}





