import gdrive.GDriveResponseParserHelper

fun main() {
    val gDriveResponseParserHelper = GDriveResponseParserHelper()
    gDriveResponseParserHelper
        .parseStringFromOcrToListOfOperations(resources.receipt1, 3).forEach {
            println(it)
        }

    println("receipt1")
    gDriveResponseParserHelper
        .parseStringFromOcrToListOfOperations(resources.receipt2, 3).forEach {
            println(it)
        }

    println("receipt2")
    gDriveResponseParserHelper
        .parseStringFromOcrToListOfOperations(resources.receipt3, 3).forEach {
            println(it)
        }

    println("receipt5")
    gDriveResponseParserHelper
        .parseStringFromOcrToListOfOperations(resources.receipt5, 3).forEach {
            println(it)
        }

    println("receipt6")
    gDriveResponseParserHelper
        .parseStringFromOcrToListOfOperations(resources.receipt6, 3).forEach {
            println(it)
        }
}