package eu.vinconafta.porovnajto.datas.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * Db entita ItemStorePrice ORM framerowku
 * @author Marek Štefanča
 */
@Entity(tableName = "ItemStorePrice")
data class ItemStorePrice(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val refToStore: Int,
    val refToPrice: Int,
    val refToItem: Int
) {

}