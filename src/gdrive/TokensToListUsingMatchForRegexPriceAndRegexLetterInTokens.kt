package gdrive

import util.*

//TODO: do this like match price normally but check if after regex next token is letter;
class TokensToListUsingMatchForRegexPriceAndRegexLetterInTokens {
    fun matchTokensTitlesWithTokensValuesToListUsingRegex1(tokensFromString: List<String>,
                                                           numberOfItems: Int): List<String> =
        matchToStringListUsingRegexDelimiter(tokensFromString, numberOfItems)

    private fun matchToStringListUsingRegexDelimiter(
        tokensList: List<String>,
        numberOfItems: Int
    ): List<String> {
        var filteredTokensList = filterSingleCharTokensWhereLetterAtoD(tokensList)
        val listOfMatchedTokens: MutableList<String> = mutableListOf()
        val temporaryList: MutableList<String> = mutableListOf()
        val countTokensMatchingRegexCriteria =
            filteredTokensList.filter { token ->
                token.contains(regexLetterXOneToTenDigitsDotOrCommaTwoDigits)||token.contains(
                    regexLetterStarOrPlusOneToTenDigitsDotOrCommaTwoDigits)
            }.size
        if (countTokensMatchingRegexCriteria==numberOfItems) {
            filteredTokensList =
                filteredTokensList.filter { !it.matches(regexOneToTenDigitsDotOrCommaTwoDigitsAndLetterA_D) }
                    .filter { !it.matches(regexOneToTenDigitsDotOrCommaThreeDigits) }
                    .filter { !it.matches(regexOneToTenDigitsCommaOrDotTwoDigits) }
                    .toMutableList()
        }

        println("List of matched tokens from receipt every receipt $countTokensMatchingRegexCriteria")

        filteredTokensList.forEach { print("$it ") }

        for (tokenIndex in filteredTokensList.indices) {
            if (filteredTokensList[tokenIndex].contains(regexLetterXOneToTenDigitsDotOrCommaTwoDigits)
                ||filteredTokensList[tokenIndex].contains(regexLetterStarOrPlusOneToTenDigitsDotOrCommaTwoDigits)
            ) {
                if (temporaryList.isNotEmpty()) {
                    listOfMatchedTokens.add(temporaryList.joinToString())
                    val valuePrice: String? =
                        regexOneToTenDigitsCommaOrDotTwoDigits.find(filteredTokensList[tokenIndex])?.value
                    if (!valuePrice.isNullOrEmpty()){
                        listOfMatchedTokens.add(valuePrice)
                    }
                }
                temporaryList.clear()
            } else {
                temporaryList.add(filteredTokensList[tokenIndex])
            }
        }
        println("TokensToListUsingMatchForRegexPriceAndRegexLetterInTokens -> matchToStringListUsingRegexDelimiter-> listOfMatchedTokens $listOfMatchedTokens ")
        return listOfMatchedTokens
    }

    private fun filterSingleCharTokensWhereLetterAtoD(listOfStringTokensFromResponse: List<String>): List<String> {
        return listOfStringTokensFromResponse
            .filter { token -> !token.matches(regexLettersFromAtoDIgnoreCase) }
            .filter { token -> !token.matches(regexSingleDigitZeroToNine) }
            .filter { token -> token.isNotBlank() }
//                .filter { token -> token.length > 1 }
    }
}