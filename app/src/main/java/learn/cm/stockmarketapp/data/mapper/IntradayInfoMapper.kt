package learn.cm.stockmarketapp.data.mapper

import android.os.Build
import androidx.annotation.RequiresApi
import learn.cm.stockmarketapp.data.remote.dto.IntradayInfoDto
import learn.cm.stockmarketapp.domain.model.IntradayInfo
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.util.*

fun IntradayInfoDto.toIntradayInfo(): IntradayInfo{
    val pattern = "yyyy-MM-dd HH:mm:ss"
    val formatter = DateTimeFormatter.ofPattern(pattern, Locale.getDefault())
    val localDateTime = LocalDateTime.parse(timestamp,formatter)
    return IntradayInfo(
        date = localDateTime,
        close = close
    )
}