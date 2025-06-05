package eu.vinconafta.porovnajto.ui.Rooms

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import eu.vinconafta.porovnajto.datas.daos.CategoryDao
import eu.vinconafta.porovnajto.datas.daos.CurrencyDao
import eu.vinconafta.porovnajto.datas.daos.ItemDao
import eu.vinconafta.porovnajto.datas.daos.PriceDao
import eu.vinconafta.porovnajto.datas.daos.StoreDao
import eu.vinconafta.porovnajto.datas.entities.Category
import eu.vinconafta.porovnajto.datas.entities.Currency
import eu.vinconafta.porovnajto.datas.entities.Item
import eu.vinconafta.porovnajto.datas.entities.ItemStorePrice
import eu.vinconafta.porovnajto.datas.entities.Price
import eu.vinconafta.porovnajto.datas.entities.StoreItem

@Database(entities =
[StoreItem::class, Item::class, Category::class, Currency::class,ItemStorePrice::class, Price::class],
    version = 6, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {

    abstract fun storeDao(): StoreDao

    abstract fun itemDao(): ItemDao
    abstract fun currencyDao(): CurrencyDao
    abstract fun priceDao(): PriceDao

    abstract fun categoryDao(): CategoryDao


    companion object {
        @Volatile
        private var INSTANCE: AppDatabase? = null

        fun getDatabase(context: Context): AppDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "app_database"
                )
                    .fallbackToDestructiveMigration()
                    .build()
                INSTANCE = instance
                instance
            }
        }
    }
}
