package eu.vinconafta.porovnajto.datas

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.sql.Date
@Entity(tableName = "price")
data class Price(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
//    val startDate: Date,
//    val endDate: Date,
    val currencyId: Int,
    val price: Double

)