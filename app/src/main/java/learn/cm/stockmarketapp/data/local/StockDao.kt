package learn.cm.stockmarketapp.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface StockDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCompanyListings(
        companyListEntity: List<CompanyLisitingEntity>
    )

    @Query("DELETE FROM companylisitingentity")
    suspend fun clearCompanyListing()

    @Query("""SELECT * FROM companylisitingentity WHERE LOWER(name) LIKE '%' || LOWER(:query) || '%' OR 
        UPPER(:query) == symbol
    """)
    suspend fun searchCompanyListing(query: String): List<CompanyLisitingEntity>
}