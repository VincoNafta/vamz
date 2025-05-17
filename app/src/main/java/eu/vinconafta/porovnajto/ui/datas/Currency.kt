package eu.vinconafta.porovnajto.ui.datas

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "currency")
data class Currency(
    @PrimaryKey(
        autoGenerate = true) val id: Int = 0,
        val name: String,
        val symbol: String,
        val short_name: String)