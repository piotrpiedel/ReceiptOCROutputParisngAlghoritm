import gdrive.GDriveResponseParserHelper

fun main() {

    println("receipt1")
    GDriveResponseParserHelper()
        .parseStringFromOcrToListOfOperations(resources.receipt1, 4).forEach {
            println(it)
        }


    println("receipt2")
    GDriveResponseParserHelper()
        .parseStringFromOcrToListOfOperations(resources.receipt2, 4).forEach {
            println(it)
        }


    println("receipt3")
    GDriveResponseParserHelper()
        .parseStringFromOcrToListOfOperations(resources.receipt3, 2).forEach {
            println(it)
        }

    println("receipt4")
    GDriveResponseParserHelper()
        .parseStringFromOcrToListOfOperations(resources.receipt4, 2).forEach {
            println(it)
        }

    println("receipt5")
    GDriveResponseParserHelper()
        .parseStringFromOcrToListOfOperations(resources.receipt5, 5).forEach {
            println(it)
        }

    println("receipt6")
    GDriveResponseParserHelper()
        .parseStringFromOcrToListOfOperations(resources.receipt6, 8).forEach {
            println(it)
        }

    println("receipt7")
    GDriveResponseParserHelper()
        .parseStringFromOcrToListOfOperations(resources.receipt7, 8).forEach {
            println(it)
        }
}