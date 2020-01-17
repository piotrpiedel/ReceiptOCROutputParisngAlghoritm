package gdrive


import util.getAnyDateIfStringContainsDate
import util.stringAnyFormatToDefaultDateFormat
import java.util.*

class ResponseDateParser(private val stringToGetDateFrom: String) {

    fun getDateFromStringOrReturnTodayDate(): Date =
            getAnyDateIfStringContainsDate(stringToGetDateFrom)
                    ?.stringAnyFormatToDefaultDateFormat() ?: Date()
}