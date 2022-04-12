package learn.cm.stockmarketapp.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class CompanyLisitingEntity (
    val name: String,
    val exchange: String,
    val symbol: String,
    @PrimaryKey val id: Int? = null
)