package learn.cm.stockmarketapp.data.repository

import com.opencsv.CSVParser
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import learn.cm.stockmarketapp.data.csv.CsvParser
import learn.cm.stockmarketapp.data.local.StockDatabase
import learn.cm.stockmarketapp.data.mapper.toCompanyListing
import learn.cm.stockmarketapp.data.remote.StockApi
import learn.cm.stockmarketapp.domain.model.CompanyListings
import learn.cm.stockmarketapp.domain.repository.StockRepository
import learn.cm.stockmarketapp.util.Resource
import retrofit2.HttpException
import java.io.IOException
import java.io.InputStreamReader
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class StockRepositoryImpl @Inject constructor(
    val api: StockApi,
    val db: StockDatabase,
    val companyListingsParser: CsvParser<CompanyListings>
): StockRepository{

    val dao = db.dao

    override suspend fun getCompanyListings(
        fetchFromRemote: Boolean,
        query: String
    ): Flow<Resource<List<CompanyListings>>> {
        return flow {
            emit(Resource.Loading(true))
            val localListing = dao.searchCompanyListing(query)
            emit(Resource.Success(
                data = localListing.map { it.toCompanyListing() }
            ))

            val isDbEmpty = localListing.isEmpty() && query.isBlank()
            val shouldJustLoadFromCache = !isDbEmpty && !fetchFromRemote

            if (shouldJustLoadFromCache){
                emit(Resource.Loading(false))
                return@flow
            }

            val remoteListing = try{
                val response = api.getListings()
                companyListingsParser.parse(response.byteStream())
            } catch(e: IOException){
                e.printStackTrace()
                emit(Resource.Error("Couldn't load data"))
                null
            } catch (e: HttpException){
                e.printStackTrace()
                emit(Resource.Error("Couldn't load data"))
                null
            }

            remoteListing?.let { listings ->
                dao.clearCompanyListing()
                dao.insertCompanyListings(
                    listings.map { it.toCompanyListing() }
                )
                emit(Resource.Success(
                    data = dao.searchCompanyListing("")
                        .map { it.toCompanyListing() }
                ))
                emit(Resource.Loading(false))
            }
        }
    }
}