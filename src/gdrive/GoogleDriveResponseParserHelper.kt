package gdrive

import Operation
import java.util.*


@Suppress("FunctionName")
class GoogleDriveResponseParserHelper() {

    private var responseOCRedStringFromGDrive: String? = null
    private var dateOnReceipt: Date? = null
    var dividedStringPublicForDebugging: List<String> = mutableListOf()
    private val googleDriveResponseParsedOperationsHolder = ParsedOperationsHolder()

    fun parseStringFromOcrToListOfOperations(): List<Operation> {
        dateOnReceipt = ResponseDateParser(responseOCRedStringFromGDrive!!)
                .getDateFromStringOrReturnTodayDate()

        parseStringFromOcrToListOfOperations(ResponseRegexSubstringer(responseOCRedStringFromGDrive!!).substringAfterWordsFiscalReceiptOrReturnWholeString())

        return googleDriveResponseParsedOperationsHolder.listOfParsedOperationsFromOCRString
    }

    private fun parseStringFromOcrToListOfOperations(responseString: String) {
        val responseRegexSplitter = ResponseRegexSplitter()
        addOperationsToResult(responseString, responseRegexSplitter::tokenizeAndSplitStringToListUsingRegexOneToTenDigitsComma)
        addOperationsToResult(responseString, responseRegexSplitter::tokenizeAndSplitStringToListUsingRegexOneToTenDigitsDot)
        addOperationsToResult(responseString, responseRegexSplitter::tokenizeAndSplitStringToListUsingRegexOneToTenDigitsDotOrCommaThreeDigits)
    }

    private fun addOperationsToResult(responseString: String, stringSplitterWithRegexFunctionToTitleValueStrings: (responseString: String) -> List<String>) {
        googleDriveResponseParsedOperationsHolder.addResultToOperationList(convertStringPairsTitleValueAndDateToListOfOperation(stringSplitterWithRegexFunctionToTitleValueStrings.invoke(responseString)))
    }

    private fun convertStringPairsTitleValueAndDateToListOfOperation(stringSplitterWithRegexFunctionToTitleValueStrings: List<String>) =
            PairOfStringsToOperationConverter().matchPairsWithTitleValueStringToListOfOperation(matchStringFromListToTitleValuePair(stringSplitterWithRegexFunctionToTitleValueStrings), dateOnReceipt)

    private fun matchStringFromListToTitleValuePair(listOfString: List<String>) = ResponsePairsMatcher().matchStringFromListToTitleValuePair(listOfString)

}
