package gdrive

import util.regexForAnyReceiptFiscalWord

class ResponseRegexSubstringUtil() {

    fun substringAfterAnyOfWordsFiscalReceiptOrReturnOrigin(string: String): String {
        val matchedResult: MatchResult? = regexForAnyReceiptFiscalWord.findAll(string).lastOrNull()
        if (matchedResult != null)
            return string.substring(matchedResult.range.last + 1, string.lastIndex).trim()
        return string.trim()
    }
}