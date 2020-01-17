package gdrive

import Operation
import resources.receipt1
import util.tokenize
import java.util.*


@Suppress("FunctionName")
class GoogleDriveResponseParserHelper() {

    private var responseOCRString: String = receipt1;
    private var dateForReceipt: Date? = null
    var dividedStringPublicForDebugging: List<String> = mutableListOf()
    private val googleDriveResponseParsedOperationsHolder = ParsedOperationsHolder()

    fun parseStringFromOcrToListOfOperations(): List<Operation> {
        dateForReceipt = ResponseDateParser(responseOCRString)
            .getDateFromStringOrReturnTodayDate()

        val substringAfterFiscalReceiptWords =
            ResponseRegexSubstringUtil()
                .substringAfterAnyOfWordsFiscalReceiptOrReturnOrigin(responseOCRString)
        parseTokenizedString(substringAfterFiscalReceiptWords)
        return googleDriveResponseParsedOperationsHolder.listOfParsedOperationsFromOCRString
    }

    private fun parseTokenizedString(responseString: String) {
        val responseRegexSplitter = ResponseRegexMatcher()
        val tokenizedString: List<String> = responseString.tokenize()
        addOperationsToResult(
            tokenizedString,
            responseRegexSplitter::matchTitlesWithValuesToListUsingRegexOneToTenDigitsComma
        )
        addOperationsToResult(
            tokenizedString,
            responseRegexSplitter::matchTitlesToListUsingRegexOneToTenDigitsDot
        )
        addOperationsToResult(
            tokenizedString,
            responseRegexSplitter::matchTitlesToListUsingRegexOneToTenDigitsDotOrCommaThreeDigits
        )
    }

    private fun addOperationsToResult(
        responseString: List<String>,
        stringSplitterWithRegexFunctionToTitleValueStrings: (responseString: List<String>) -> List<String>
    ) {
        googleDriveResponseParsedOperationsHolder.addResultToOperationList(
            convertStringPairsTitleValueAndDateToListOfOperation(
                stringSplitterWithRegexFunctionToTitleValueStrings.invoke(responseString)
            )
        )
    }

    private fun convertStringPairsTitleValueAndDateToListOfOperation(stringSplitterWithRegexFunctionToTitleValueStrings: List<String>) =
        PairOfStringsToOperationConverter().matchPairsWithTitleValueStringToListOfOperation(
            matchStringFromListToTitleValuePair(stringSplitterWithRegexFunctionToTitleValueStrings),
            dateForReceipt
        )

    private fun matchStringFromListToTitleValuePair(listOfString: List<String>) =
        PairUtil().matchStringFromListToTitleValuePair(listOfString)

}
