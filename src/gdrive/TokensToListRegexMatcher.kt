package gdrive

import util.*

class TokensToListRegexMatcher {

    fun matchTitlesWithValuesToListUsingRegexOneToTenDigitsComma(tokensFromString: List<String>): List<String> =
        splitToStringListUsingRegexDelimiter(tokensFromString, regexOneToTenDigitsCommaTwoDigits)

    fun matchTitlesToListUsingRegexOneToTenDigitsDot(tokensFromString: List<String>): List<String> =
        splitToStringListUsingRegexDelimiter(tokensFromString, regexOneToTenDigitsDotTwoDigits)

    fun matchTitlesToListUsingRegexOneToTenDigitsDotOrCommaThreeDigits(tokensFromString: List<String>): List<String> =
        splitToStringListUsingRegexDelimiter(tokensFromString, regexOneToTenDigitsDotOrCommaThreeDigits)

    private fun splitToStringListUsingRegexDelimiter(listOfStringTokensFromResponse: List<String>, priceFormatRegex: Regex): List<String> {
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