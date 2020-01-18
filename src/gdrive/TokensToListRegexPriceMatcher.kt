package gdrive

import util.*

class TokensToListRegexPriceMatcher {

    fun matchTokensTitlesWithTokensValuesToListUsingRegex1(tokensFromString: List<String>): List<String> =
        matchToStringListUsingRegexDelimiter(tokensFromString, regexOneToTenDigitsCommaTwoDigits)

    fun matchTokensTitlesWithTokensValuesToListUsingRegex2(tokensFromString: List<String>): List<String> =
        matchToStringListUsingRegexDelimiter(tokensFromString, regexOneToTenDigitsDotTwoDigits)

    fun matchTokensTitlesWithTokensValuesToListUsingRegex3(tokensFromString: List<String>): List<String> =
        matchToStringListUsingRegexDelimiter(tokensFromString, regexOneToTenDigitsDotOrCommaThreeDigits)

    private fun matchToStringListUsingRegexDelimiter(listOfStringTokensFromResponse: List<String>, priceFormatRegex: Regex): List<String> {
        val filteredListRemovedLettersAD: List<String> = filterSingleCharTokensWhereLetterA_D(listOfStringTokensFromResponse)
        val listOfFinallySplitStrings: MutableList<String> = mutableListOf()
        val temporaryList: MutableList<String> = mutableListOf()
        for (tokenFromList in listOfStringTokensFromResponse) {
            if (tokenFromList.contains(priceFormatRegex)) {
                if (temporaryList.isNotEmpty()) {
                    listOfFinallySplitStrings.add(temporaryList.joinToString())
                    listOfFinallySplitStrings.add(priceFormatRegex.find(tokenFromList)?.value.toString())
                }
                temporaryList.clear()
            } else {
                temporaryList.add(tokenFromList)
            }
        }
        return listOfFinallySplitStrings
    }

    private fun filterSingleCharTokensWhereLetterA_D(listOfStringTokensFromResponse: List<String>): List<String> {
        return listOfStringTokensFromResponse
            .filter { token -> !token.matches(regexLettersFromAtoDIgnoreCase) }
            .filter { token -> !token.matches(regexSingleDigitZeroToNine) }
            .filter { token -> token.isNotBlank() }
//                .filter { token -> token.length > 1 }
    }

}