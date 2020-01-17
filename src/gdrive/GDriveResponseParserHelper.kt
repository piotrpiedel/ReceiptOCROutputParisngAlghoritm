package gdrive

import Operation
import resources.receipt1
import util.tokenize
import java.util.*

@Suppress("FunctionName")
class GDriveResponseParserHelper {

    private var responseOCRString: String = receipt1
    private var dateForReceipt: Date? = null
    var dividedStringPublicForDebugging: List<String> = mutableListOf()
    private val googleDriveResponseParsedOperationsHolder = ParsedOperationsHolder()

    fun parseStringFromOcrToListOfOperations(): List<Operation> {
        dateForReceipt = ResponseDateParser()
            .getDateFromStringOrReturnTodayDate(responseOCRString)

        val stringAfterFiscalReceiptWords = ResponseRegexSubstringUtil()
            .substringAfterAnyOfWordsFiscalReceiptOrReturnOrigin(responseOCRString)

        tokenizeAndParseString(stringAfterFiscalReceiptWords)
        return googleDriveResponseParsedOperationsHolder.listOfParsedOperationsFromOCRString
    }

    private fun tokenizeAndParseString(responseString: String) {
        val responseRegexSplitter = TokensToListRegexMatcher()
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
        val listOf
        googleDriveResponseParsedOperationsHolder.addResultToOperationList(
            convertStringPairsTitleValueAndDateToListOfOperation(
                stringSplitterWithRegexFunctionToTitleValueStrings.invoke(responseString)
            )
        )
    }

    private fun convertStringPairsTitleValueAndDateToListOfOperation(stringSplitterWithRegexFunctionToTitleValueStrings: List<String>) =
        OperationsBuilder().buildOperationsFromTitleToValuePairToDate(
            matchTokensListToTitleValuePairList(stringSplitterWithRegexFunctionToTitleValueStrings),
            dateForReceipt
        )

    private fun matchTokensListToTitleValuePairList(listOfTokens: List<String>) =
        PairUtil().getListOfTitleToValuePairs(listOfTokens)
}
