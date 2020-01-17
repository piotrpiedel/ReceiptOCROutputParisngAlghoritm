package gdrive


import util.getAnyDateIfStringContainsDate
import util.stringAnyFormatToDefaultDateFormat
import java.util.*

class ResponseDateParser() {

    fun getDateFromStringOrReturnTodayDate(stringToGetDateFrom: String): Date =
        stringToGetDateFrom.getAnyDateIfStringContainsDate()
            ?.stringAnyFormatToDefaultDateFormat() ?: Date()

}