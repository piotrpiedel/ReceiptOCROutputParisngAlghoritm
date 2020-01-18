package gdrive

import util.*

//TODO: do this like match price normally but check if after regex next token is letter;
class TokensToListUsingMatchForRegexPriceAndRegexLetterInTokens {
    fun matchTokensTitlesWithTokensValuesToListUsingRegex1(tokensFromString: List<String>): List<String> =
        matchToStringListUsingRegexDelimiter(tokensFromString, regexOneToTenDigitsCommaTwoDigits)

//    fun matchTokensTitlesWithTokensValuesToListUsingRegex2(tokensFromString: List<String>): List<String> =
//        matchToStringListUsingRegexDelimiter(tokensFromString, regexOneToTenDigitsDotTwoDigits)
//
//    fun matchTokensTitlesWithTokensValuesToListUsingRegex3(tokensFromString: List<String>): List<String> =
//        matchToStringListUsingRegexDelimiter(tokensFromString, regexOneToTenDigitsDotOrCommaThreeDigits)

    private fun matchToStringListUsingRegexDelimiter(
        tokensList: List<String>,
        priceFormatRegex: Regex
    ): List<String> {
        val listOfMatchedTokens: MutableList<String> = mutableListOf()
        val temporaryList: MutableList<String> = mutableListOf()
        for (tokenIndex in tokensList.indices) {
            if (tokensList[tokenIndex].contains(regexOneToTenDigitsCommaOrDotTwoDigits)
                || tokensList[tokenIndex].contains(regexOneToTenDigitsDotOrCommaThreeDigits)
            ) {
                if (tokenIndex + 1 < tokensList.size && tokensList[tokenIndex + 1].contains(
                        regexLettersFromAtoDIgnoreCase
                    )
                ) {
                    if (temporaryList.isNotEmpty()) {
                        listOfMatchedTokens.add(temporaryList.joinToString())
                        val price1: String? =
                            regexOneToTenDigitsCommaOrDotTwoDigits.find(tokensList[tokenIndex])?.value.toString()
                        val price2: String? =
                            regexOneToTenDigitsDotOrCommaThreeDigits.find(tokensList[tokenIndex])?.value.toString()
                        if (price1 != null) {
                            listOfMatchedTokens.add(price1)
                        } else if (price2 != null) {
                            listOfMatchedTokens.add(price2)
                        } else {
                            listOfMatchedTokens.add("0000000000")
                        }

                    }
                    temporaryList.clear()
                } else temporaryList.add(tokensList[tokenIndex])
            } else {
                temporaryList.add(tokensList[tokenIndex])
            }
        }
        return listOfMatchedTokens
    }

    private fun filterSingleCharTokensWhereLetterA_D(listOfStringTokensFromResponse: List<String>): List<String> {
        return listOfStringTokensFromResponse
            .filter { token -> !token.matches(regexLettersFromAtoDIgnoreCase) }
            .filter { token -> !token.matches(regexSingleDigitZeroToNine) }
            .filter { token -> token.isNotBlank() }
//                .filter { token -> token.length > 1 }
    }
}