package com.iloadti.testegithub.extension

import com.iloadti.testegithub.utils.PATTERN_DATE
import com.iloadti.testegithub.utils.PATTERN_DEFAULT
import java.math.BigDecimal
import java.text.DecimalFormat
import java.text.SimpleDateFormat
import java.util.*

fun BigDecimal.formatForBrazilianCurrency(): String {
    val brazilianFormat = DecimalFormat
        .getCurrencyInstance(Locale("pt", "br"))
    return brazilianFormat.format(this)
}

fun BigDecimal.formatForEUACurrency(): String {
    val euaFormat = DecimalFormat("'$'0.00")
    return euaFormat.format(this)
}

internal fun String.formatToDateAndHour(pattern: String = PATTERN_DEFAULT) = try {
    val locale = Locale("pt", "BR")
    val date = SimpleDateFormat(pattern, locale).parse(this)
    SimpleDateFormat(PATTERN_DATE, locale).format(date)
} catch (e: Exception) {
    this
}