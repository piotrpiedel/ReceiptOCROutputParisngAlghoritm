package gdrive

class TokenToListService {

    fun matchTokensTitlesWithTokensValuesToListUsingRegex1(tokensFromString: List<String>,
                                                           numberOfItems: Int): List<String> =
        MatchTokensForRegexPriceAndRegexLetter().matchTokensTitlesWithTokensValuesToListUsingRegex1(tokensFromString,
            numberOfItems)

    fun matchTokensTitlesWithTokensValuesToListUsingRegex2(tokensFromString: List<String>): List<String> =
        MatchWithRegexPriceMatcher().matchTokensTitlesWithTokensValuesToListUsingRegex1(tokensFromString)

    fun matchTokensTitlesWithTokensValuesToListUsingRegex3(tokensFromString: List<String>): List<String> =
        MatchWithRegexPriceMatcher().matchTokensTitlesWithTokensValuesToListUsingRegex2(tokensFromString)

    fun matchTokensTitlesWithTokensValuesToListUsingRegex4(tokensFromString: List<String>): List<String> =
        MatchWithRegexPriceMatcher().matchTokensTitlesWithTokensValuesToListUsingRegex3(tokensFromString)


}