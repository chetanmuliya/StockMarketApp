package learn.cm.stockmarketapp.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import learn.cm.stockmarketapp.data.csv.CompanyListingParser
import learn.cm.stockmarketapp.data.csv.CsvParser
import learn.cm.stockmarketapp.data.csv.IntradayInfoParser
import learn.cm.stockmarketapp.data.repository.StockRepositoryImpl
import learn.cm.stockmarketapp.domain.model.CompanyInfo
import learn.cm.stockmarketapp.domain.model.CompanyListings
import learn.cm.stockmarketapp.domain.model.IntradayInfo
import learn.cm.stockmarketapp.domain.repository.StockRepository
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    @Singleton
    abstract fun bindCompanyListingParser(
        companyListingParser: CompanyListingParser
    ): CsvParser<CompanyListings>

    @Binds
    @Singleton
    abstract fun bindIntraDayInfoParser(
        intradayInfoParser: IntradayInfoParser
    ): CsvParser<IntradayInfo>

    @Binds
    @Singleton
    abstract fun bindStockRepository(
        stockRepositoryImpl: StockRepositoryImpl
    ): StockRepository
}