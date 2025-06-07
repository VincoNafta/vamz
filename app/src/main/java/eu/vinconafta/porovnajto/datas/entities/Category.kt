package eu.vinconafta.porovnajto.datas.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * Db entita Category ORM framerowku
 * @author Marek Štefanča
 */
@Entity(tableName = "category")
data class Category(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val name: String)


