import gdrive.GDriveResponseParserHelper

fun main() {

    println("\nRECEIPT1:")
    GDriveResponseParserHelper()
        .parseStringFromOcrToListOfOperations(resources.receipt1, 4).forEach {
            println(it)
        }

    println("\nRECEIPT2:")
    GDriveResponseParserHelper()
        .parseStringFromOcrToListOfOperations(resources.receipt2, 4).forEach {
            println(it)
        }

    println("\nRECEIPT3:")
    GDriveResponseParserHelper()
        .parseStringFromOcrToListOfOperations(resources.receipt3, 2).forEach {
            println(it)
        }

    println("\nRECEIPT4:")
    GDriveResponseParserHelper()
        .parseStringFromOcrToListOfOperations(resources.receipt4, 2).forEach {
            println(it)
        }

    println("\nRECEIPT5:")
    GDriveResponseParserHelper()
        .parseStringFromOcrToListOfOperations(resources.receipt5, 5).forEach {
            println(it)
        }

    println("\nRECEIPT6:")
    GDriveResponseParserHelper()
        .parseStringFromOcrToListOfOperations(resources.receipt6, 8).forEach {
            println(it)
        }

    println("\nRECEIPT7:")
    GDriveResponseParserHelper()
        .parseStringFromOcrToListOfOperations(resources.receipt7, 8).forEach {
            println(it)
        }

    println("\nRECEIPT13:")
    GDriveResponseParserHelper()
        .parseStringFromOcrToListOfOperations(resources.receipt13, 3).forEach {
            println(it)
        }

    println("\nRECEIPT14:")
    GDriveResponseParserHelper()
        .parseStringFromOcrToListOfOperations(resources.receipt14, 11).forEach {
            println(it)
        }

}