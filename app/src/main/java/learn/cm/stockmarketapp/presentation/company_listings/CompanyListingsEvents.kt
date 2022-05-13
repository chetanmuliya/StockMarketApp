package learn.cm.stockmarketapp.presentation.company_listings

sealed class CompanyListingsEvents{
    object Refresh: CompanyListingsEvents()
    data class onSearchQueryChange(val query: String): CompanyListingsEvents()
}
