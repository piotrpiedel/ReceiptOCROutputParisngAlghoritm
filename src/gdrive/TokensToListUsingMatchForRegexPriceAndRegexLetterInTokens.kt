package gdrive

import util.*

//TODO: do this like match price normally but check if after regex next token is letter;
class TokensToListUsingMatchForRegexPriceAndRegexLetterInTokens {
    fun matchTokensTitlesWithTokensValuesToListUsingRegex1(
        tokensFromString: List<String>,
        numberOfItems: Int
    ): List<String> =
        matchToStringListUsingRegexDelimiter(tokensFromString, numberOfItems)

//    fun matchTokensTitlesWithTokensValuesToListUsingRegex2(tokensFromString: List<String>): List<String> =
//        matchToStringListUsingRegexDelimiter(tokensFromString, regexOneToTenDigitsDotTwoDigits)
//
//    fun matchTokensTitlesWithTokensValuesToListUsingRegex3(tokensFromString: List<String>): List<String> =
//        matchToStringListUsingRegexDelimiter(tokensFromString, regexOneToTenDigitsDotOrCommaThreeDigits)

    private fun matchToStringListUsingRegexDelimiter(
        tokensList: List<String>,
        numberOfItems: Int
    ): List<String> {
        val filteredTokensList = filterSingleCharTokensWhereLetterAtoD(tokensList)
        val listOfMatchedTokens: MutableList<String> = mutableListOf()
        val temporaryList: MutableList<String> = mutableListOf()
        val countTokensMatchingRegexCriteria =
            filteredTokensList.filter { token ->
                token.contains(regexLetterXOneToTenDigitsDotOrCommaTwoDigits)||token.contains(
                    regexLetterStarOneToTenDigitsDotOrCommaTwoDigits)
            }.size
        println("List of matched tokens from receipt every receipt $countTokensMatchingRegexCriteria")

//        for (tokenIndex in filteredTokensList.indices) {
//            if (filteredTokensList[tokenIndex].contains(regexLetterXOneToTenDigitsCommaTwoDigits)
//                ||filteredTokensList[tokenIndex].contains(regexLetterXOneToTenDigitsDotTwoDigits)
//            ) {
//                if (tokenIndex + 1 < filteredTokensList.size&&filteredTokensList[tokenIndex + 1].contains(
//                        regexLettersFromAtoDIgnoreCase)
//                ) {
//                    if (temporaryList.isNotEmpty()) {
//                        listOfMatchedTokens.add(temporaryList.joinToString())
//                        val price1: String? =
//                            regexOneToTenDigitsCommaOrDotTwoDigits.find(filteredTokensList[tokenIndex])
//                                ?.value.toString()
//                        val price2: String? =
//                            regexOneToTenDigitsDotOrCommaThreeDigits.find(filteredTokensList[tokenIndex])
//                                ?.value.toString()
//                        if (price1!=null) {
//                            listOfMatchedTokens.add(price1)
//                        } else if (price2!=null) {
//                            listOfMatchedTokens.add(price2)
//                        } else {
//                            listOfMatchedTokens.add("0000000000")
//                        }
//
//                    }
//                    temporaryList.clear()
//                } else temporaryList.add(filteredTokensList[tokenIndex])
//            } else {
//                temporaryList.add(filteredTokensList[tokenIndex])
//            }
//        }
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