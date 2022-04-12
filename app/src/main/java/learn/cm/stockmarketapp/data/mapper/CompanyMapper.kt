package learn.cm.stockmarketapp.data.mapper

import learn.cm.stockmarketapp.data.local.CompanyLisitingEntity
import learn.cm.stockmarketapp.domain.model.CompanyListings

fun CompanyLisitingEntity.toCompanyListing(): CompanyListings{
    return CompanyListings(
        symbol = symbol,
        name = name,
        exchange = exchange
    )
}

fun CompanyListings.toCompanyListing(): CompanyLisitingEntity{
    return CompanyLisitingEntity(
        symbol = symbol,
        name = name,
        exchange = exchange
    )
}