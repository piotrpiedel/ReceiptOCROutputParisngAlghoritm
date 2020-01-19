package gdrive

import Operation
import util.tokenize
import java.util.*

@Suppress("FunctionName")
class GDriveResponseParserHelper {

    //    private var responseOCRString: String = receipt1
    private var dateForReceipt: Date? = null
    private var numberOfItems = 0;
    var dividedStringPublicForDebugging: List<String> = mutableListOf()
    private val googleDriveResponseParsedOperationsHolder = ParsedOperationsHolder()

    fun parseStringFromOcrToListOfOperations(
        responseOCRString: String,
        numberOfItemsFromUserInputOnReceipt: Int?
    ): List<Operation> {
        if (numberOfItemsFromUserInputOnReceipt!=null) {
            numberOfItems = numberOfItemsFromUserInputOnReceipt;
        }

        val stringWithReplacedNewLinesChars = responseOCRString.replace("\n", "").replace("\r", "")

        dateForReceipt = ResponseDateParser()
            .getDateFromStringOrReturnTodayDate(stringWithReplacedNewLinesChars)

        val stringAfterFiscalReceiptWords = ResponseRegexSubstringUtil()
            .substringAfterAnyOfWordsFiscalReceiptOrReturnOrigin(stringWithReplacedNewLinesChars)

//        tokenizeAndParseString(stringAfterFiscalReceiptWords)
//        splitStringToTokensWithRegexPattern(stringAfterFiscalReceiptWords)
        tokenizeAndParseStringComplexAlgh(stringAfterFiscalReceiptWords, numberOfItems)
        return googleDriveResponseParsedOperationsHolder.listOfParsedOperationsFromOCRString
    }

    private fun tokenizeAndParseStringComplexAlgh(responseString: String, numberOfItemsFromUserInputOnReceipt: Int) {
        val responseRegexSplitter = TokensToListUsingMatchForRegexPriceAndRegexLetterInTokens()
        val tokenizedString: List<String> = responseString.tokenize()
        addOperationsToResult(responseRegexSplitter.matchTokensTitlesWithTokensValuesToListUsingRegex1(tokenizedString,
            numberOfItemsFromUserInputOnReceipt))
//        addOperationsToResult(
//            responseRegexSplitter.matchTokensTitlesWithTokensValuesToListUsingRegex2(
//                tokenizedString
//            )
//        )
//        addOperationsToResult(
//            responseRegexSplitter.matchTokensTitlesWithTokensValuesToListUsingRegex3(
//                tokenizedString
//            )
//        )
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
