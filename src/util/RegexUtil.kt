@file:Suppress("FunctionName")

package util
//README
// | -> or
// (?i) -> ignore case of words to detect

// (one letter [a-d][A-D])
val regexLettersFromAtoDIgnoreCase = Regex("(?i)[a-d]")

// (one letter [a-d][A-D])
val regexSingleDigitZeroToNine = Regex("[0-9]")

// (one letter [a-d][A-D])
val regexLetterOrDigit = Regex("[a-zA-Z0-9]")

val regexForAnyReceiptFiscalWord: Regex = Regex("""((?i)(fiskalny|paragon fiskalny|paragon))""")


// (1-10 digits)(,)(one or more spaces)(one letter A-D)
val regexOneToTenDigitsCommaTwoDigitsWhiteSpaceAndLetterA_D: Regex = Regex("""\b\d{1,10}[,]\d{2}\s*(?i)[A-D]{1}\b""")

// (1-10 digits)(,)(one or more spaces)(one letter A-D)
val regexOneToTenDigitsDotTwoDigitsWhiteSpaceAndLetterA_D: Regex = Regex("""\b\d{1,10}[.]\d{2}\s*(?i)[A-D]{1}\b""")

// (1-10 digits)(.,)(one or more spaces)(one letter A-D)
val regexOneToTenDigitsDotOrCommaTwoDigitsWhiteSpaceAndLetterA_D: Regex = Regex("""\b\d{1,10}[.,]\d{2}\s*(?i)[A-D]{1}\b""")

// (1-10 digits)(.,)(one or more spaces)(one letter A-D)
val regexOneToTenDigitsDotOrCommaTwoDigitsAndLetterA_D: Regex = Regex("""\b\d{1,10}[.,]\d{2}(?i)[A-D]{1}\b""")

//REGEX PRICES WITH FORMAT #,## V #.##, #,.##
// (1-10 digits)(,)(2 digits)
val regexOneToTenDigitsCommaTwoDigits: Regex = Regex("""\b\d{1,10}[,]\d{2}""")

// (1-10 digits)(.)(2 digits)
val regexOneToTenDigitsDotTwoDigits: Regex = Regex("""\b\d{1,10}[.]\d{2}""")

// (1-10 digits)(,)(2 digits)
val regexOneToTenDigitsCommaOrDotTwoDigits: Regex = Regex("""\b\d{1,10}[,.]\d{2}""")

// (1-10 digits)(,|.)(3 digits)
val regexOneToTenDigitsDotOrCommaThreeDigits: Regex = Regex("""\b\d{1,10}[.,]\d{3}\b""")


//REGEX PRICES WITH FORMAT X#,## V X#.##, X#,.##
// x(1-10 digits)(,)(2 digits)
val regexLetterXOneToTenDigitsCommaTwoDigits: Regex = Regex("""\b(?i)[x]\d{1,10}[,]\d{2}""")

// x(1-10 digits)(.)(2 digits)
val regexLetterXOneToTenDigitsDotTwoDigits: Regex = Regex("""\b(?i)[x]\d{1,10}[.]\d{2}""")

//X#,.## TokensToListUsingMatchForRegexPriceAndRegexLetterInTokens
val regexLetterXOneToTenDigitsDotOrCommaTwoDigits: Regex = Regex("""(?i)[x]\s?\d{1,10}[.,]\d{2}""")

// x(1-10 digits)(,|.)(3 digits)
val regexLetterXOneToTenDigitsDotOrCommaThreeDigits: Regex = Regex("""\b(?i)[x]\d{1,10}[.,]\d{3}\b""")

//REGEX PRICES WITH FORMAT, *#,.## ||  +#,.## TokensToListUsingMatchForRegexPriceAndRegexLetterInTokens
val regexLetterStarOneToTenDigitsDotOrCommaTwoDigits: Regex = Regex("""[*+]\s?\d{1,10}[.,]\d{2}""")

//DATE REGEX
// match (year{1 or 2}{and match 3 digits}) {- | / | \} (month{match 01-09 | 10-12}) {- | / | \} (day{01-09 | 1 or 2 and any digit(0-9)| 3 and (0 or 1)})
val regexDatePattern_YYYY_MM_DD: Regex = Regex("""([12]\d{3}([-\\\/])(0[1-9]|1[0-2])([-\\\/])(0[1-9]|[12]\d|3[01]))""")

// (day{01-09 | 1 or 2 and any digit(0-9)| 3 and (0 or 1)}) {- | / | \} (month{match 01-09 | 10-12}) {- | / | \} (year{1 or 2}{and match 3 digits})
val regexDatePattern_DD_MM_YYYY: Regex = Regex("""([0-2][0-9]|(3)[0-1])([-\\\/])(((0)[0-9])|((1)[0-2]))([-\\\/])\d{4}""")