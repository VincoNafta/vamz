package eu.vinconafta.porovnajto.ui.states

data class InsertItemFormUiState (val storeName:String = "",
                                  val ean:Long = 0,
                                  val itemName: String = "",
                                  val producer:String = "",
                                  val price:Double = 0.0) {
}