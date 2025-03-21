package cards

sealed class Card(
    val rank: Int,
    val name: String
)
{
    open fun fight(card: Card): FightResult {
        if (card.rank < rank) {
            return FightResult.WIN
        } else if (card.rank > rank) {
            return FightResult.LOSE
        }
        return FightResult.EQUAL
    }
}