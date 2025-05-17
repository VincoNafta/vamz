package eu.vinconafta.porovnajto.ui.Rooms

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import eu.vinconafta.porovnajto.ui.daos.CategoryDao
import eu.vinconafta.porovnajto.ui.daos.ItemDao
import eu.vinconafta.porovnajto.ui.datas.Category
import eu.vinconafta.porovnajto.ui.datas.Item

@Database(entities = [Item::class, Category::class], version = 2, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {

    abstract fun itemDao(): ItemDao


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
