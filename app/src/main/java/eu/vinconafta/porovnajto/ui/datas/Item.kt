package eu.vinconafta.porovnajto.ui.datas

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import eu.vinconafta.porovnajto.ui.daos.PriceDao
import kotlinx.parcelize.Parcelize

@Parcelize
@Entity(tableName = "items")
data class Item(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val name: String,
    val producer: String,
    val refToStoreItem: Int,
    val icon: String,
    val refToPrice: Int,
    val refToCategory: Int

): Parcelable


