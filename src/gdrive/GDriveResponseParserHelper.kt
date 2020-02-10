package gdrive

import Operation
import util.tokenize
import java.util.*

@Suppress("FunctionName")
class GDriveResponseParserHelper {
    private val tokenToListService = TokenToListService()
    private var dateForReceipt: Date? = null
    private var numberOfItems = 0;
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

        tokenizeAndParseStringComplexAlgh(stringAfterFiscalReceiptWords, numberOfItems)
        return googleDriveResponseParsedOperationsHolder.listOfParsedOperationsFromOCRString
    }

    private fun tokenizeAndParseStringComplexAlgh(responseString: String, numberOfItemsFromUserInputOnReceipt: Int) {
        val tokenizedString: List<String> = responseString.tokenize()
        addOperationsToResult(tokenToListService.matchTokensTitlesWithTokensValuesToListUsingRegex1(tokenizedString,
            numberOfItemsFromUserInputOnReceipt))
    }

    private fun tokenizeAndParseString(responseString: String) {
        val tokenizedString: List<String> = responseString.tokenize()
        addOperationsToResult(
            tokenToListService.matchTokensTitlesWithTokensValuesToListUsingRegex2(
                tokenizedString
            )
        )
        addOperationsToResult(
            tokenToListService.matchTokensTitlesWithTokensValuesToListUsingRegex3(
                tokenizedString
            )
        )
        addOperationsToResult(
            tokenToListService.matchTokensTitlesWithTokensValuesToListUsingRegex4(
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
