package learn.cm.stockmarketapp.domain.repository

import kotlinx.coroutines.flow.Flow
import learn.cm.stockmarketapp.domain.model.CompanyInfo
import learn.cm.stockmarketapp.domain.model.CompanyListings
import learn.cm.stockmarketapp.domain.model.IntradayInfo
import learn.cm.stockmarketapp.util.Resource

interface StockRepository {

    suspend fun getCompanyListings(
        fetchFromRemote: Boolean,
        query: String
    ): Flow<Resource<List<CompanyListings>>>

    suspend fun getIntradayInfo(
         symbol: String
    ): Resource<List<IntradayInfo>>

    suspend fun getCompanyInfo(
        symbol: String
    ): Resource<CompanyInfo>
}