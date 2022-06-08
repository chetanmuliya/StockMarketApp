package learn.cm.stockmarketapp.presentation.company_info

import learn.cm.stockmarketapp.domain.model.CompanyInfo
import learn.cm.stockmarketapp.domain.model.IntradayInfo

data class CompanyInfoState(
    val stockInfos: List<IntradayInfo> = emptyList(),
    val company: CompanyInfo? = null,
    val isLoading: Boolean = false,
    val error: String? = null
)
