/**
 * Datova trieda popisujúca dané položky vo formulári
 * @param Marek Štefanča
 */
data class InsertItemFormUiState(
    val itemName: String = "",
    val producer: String = "",
    val price: Double = 0.0,
    val storeName:String = "",
    val storeId: Int = -1
)
