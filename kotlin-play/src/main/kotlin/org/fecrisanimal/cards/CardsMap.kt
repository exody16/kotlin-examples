package cards

class CardsMap(
    private val deck: Deck = Deck()
) {
    private val map:  MutableMap<Player, MutableList<Card>> = mutableMapOf()

    init {
        map[Player.ONE] = MutableList(5) {deck.drawCard()}
        map[Player.TWO] = MutableList(5) {deck.drawCard()}
    }

    fun getCardName(player: Player, index: Int): String {
        return map[player]?.get(index)?.name ?: "CARD_NOT_FOUND"
    }


}