package learn.cm.stockmarketapp.data.csv

import com.opencsv.CSVReader
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import learn.cm.stockmarketapp.domain.model.CompanyListings
import java.io.InputStream
import java.io.InputStreamReader
import javax.inject.Inject

class CompanyListingParser @Inject constructor(): CsvParser<CompanyListings> {

    override suspend fun parse(stream: InputStream): List<CompanyListings> {
        val csvReader = CSVReader(InputStreamReader(stream))
        return withContext(Dispatchers.IO){
            csvReader
                .readAll()
                .drop(1)
                .mapNotNull { line ->
                    val symbol = line.getOrNull(0)
                    val name = line.getOrNull(1)
                    val exchange = line.getOrNull(2)
                    CompanyListings(
                        name = name ?: return@mapNotNull null,
                        symbol = symbol ?: return@mapNotNull null,
                        exchange = exchange ?: return@mapNotNull null
                    )
                }
                .also {
                    csvReader.close()
                }
        }
    }

}