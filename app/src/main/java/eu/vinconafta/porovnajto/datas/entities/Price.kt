package eu.vinconafta.porovnajto.datas.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.sql.Date

/**
 * Db entita Price ORM framerowku
 * @author Marek Štefanča
 */
@Entity(tableName = "price")
data class Price(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
//    val startDate: Date,
//    val endDate: Date,
    val currencyId: Int,
    val price: Double

)