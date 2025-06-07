package eu.vinconafta.porovnajto.datas.daos

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import eu.vinconafta.porovnajto.datas.entities.Category
import kotlinx.coroutines.flow.Flow

/**
 * Rozhranie popisujuce akcie nad DB pre ORM mapovanie s entitou Category
 *  @author Marek Štefanča
 */
@Dao
interface CategoryDao {

    /**
     * funkcia vráti zoznam všetkých kategorii v DB
     * @return zoznam kategorii
     */
    @Query("SELECT * FROM category")
    fun getAll(): Flow<List<Category>>

    /**
     * funkcia slúžiaca na uloženie danej katgorie
     * @param category referencia na danu kategoriu
     */
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCategory(category: Category)

    /**
     * Funkcia slúžiaca na zmazanie existujucej kategorie
     * @param category referencia na kategoriu
     */
    @Delete
    suspend fun deleteCategory(category: Category)

}