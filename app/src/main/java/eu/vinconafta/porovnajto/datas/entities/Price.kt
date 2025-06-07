package eu.vinconafta.porovnajto.datas.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * Db entita Price ORM framerowku
 * @author Marek Štefanča
 */
@Entity(tableName = "price")
data class Price(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val currencyId: Int,
    val price: Double

)