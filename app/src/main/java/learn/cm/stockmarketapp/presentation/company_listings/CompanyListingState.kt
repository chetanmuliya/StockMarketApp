package learn.cm.stockmarketapp.presentation.company_listings

import learn.cm.stockmarketapp.domain.model.CompanyListings

data class CompanyListingState(
    val companies: List<CompanyListings> = emptyList(),
    val isLoading: Boolean = false,
    val isRefreshing: Boolean = false,
    val searchQuery: String = ""
)
