package gdrive

import Operation
import util.tokenize
import java.util.*

@Suppress("FunctionName")
class GDriveResponseParserHelper {

    //    private var responseOCRString: String = receipt1
    private var dateForReceipt: Date? = null
    var dividedStringPublicForDebugging: List<String> = mutableListOf()
    private val googleDriveResponseParsedOperationsHolder = ParsedOperationsHolder()

    fun parseStringFromOcrToListOfOperations(responseOCRString: String): List<Operation> {
        val stringWithReplacedNewLinesChars = responseOCRString.replace("\n", "").replace("\r", "")

        dateForReceipt = ResponseDateParser()
            .getDateFromStringOrReturnTodayDate(stringWithReplacedNewLinesChars)

        val stringAfterFiscalReceiptWords = ResponseRegexSubstringUtil()
            .substringAfterAnyOfWordsFiscalReceiptOrReturnOrigin(stringWithReplacedNewLinesChars)

        tokenizeAndParseString(stringAfterFiscalReceiptWords)
        splitStringToTokensWithRegexPattern(stringAfterFiscalReceiptWords)
        return googleDriveResponseParsedOperationsHolder.listOfParsedOperationsFromOCRString
    }

    private fun splitStringToTokensWithRegexPattern(responseString: String) {
        val tokensToListRegexPriceLetterMatcher = TokensToListRegexPriceLetterMatcher()
        addOperationsToResult(
            tokensToListRegexPriceLetterMatcher.matchTitlesWithValuesToListUsingValueAndLetterRegex1(responseString)
        )
        addOperationsToResult(
            tokensToListRegexPriceLetterMatcher.matchTitlesWithValuesToListUsingValueAndLetterRegex2(responseString)
        )
        addOperationsToResult(
            tokensToListRegexPriceLetterMatcher.matchTitlesWithValuesToListUsingValueAndLetterRegex3(responseString)
        )
    }

    private fun tokenizeAndParseString(responseString: String) {
        val responseRegexSplitter = TokensToListRegexPriceMatcher()
        val tokenizedString: List<String> = responseString.tokenize()
        addOperationsToResult(
            responseRegexSplitter.matchTokensTitlesWithTokensValuesToListUsingRegex1(
                tokenizedString
            )
        )
        addOperationsToResult(
            responseRegexSplitter.matchTokensTitlesWithTokensValuesToListUsingRegex2(
                tokenizedString
            )
        )
        addOperationsToResult(
            responseRegexSplitter.matchTokensTitlesWithTokensValuesToListUsingRegex3(
                tokenizedString
            )
        )
    }

    private fun addOperationsToResult(
        listOfMatchedPairsTitleToValue: List<String>
    ) {
        googleDriveResponseParsedOperationsHolder.addResultToOperationList(
            convertTitleToValuePairsAndDateToOperationsList(listOfMatchedPairsTitleToValue)
        )
    }

    private fun convertTitleToValuePairsAndDateToOperationsList(stringSplitterWithRegexFunctionToTitleValueStrings: List<String>) =
        OperationsBuilder().buildOperationsUsingTitleToValuePairsListToDate(
            matchTokensListToTitleValuePairList(stringSplitterWithRegexFunctionToTitleValueStrings),
            dateForReceipt
        )

    private fun matchTokensListToTitleValuePairList(listOfTokens: List<String>) =
        PairUtil().getListOfTitleToValuePairs(listOfTokens)
}
