package jetbrains.kotlin.course.alias.card

import jetbrains.kotlin.course.alias.util.IdentifierFactory

class CardService {
    private val identifierFactory = IdentifierFactory()

    companion object {
        const val WORDS_IN_CARD = 4
        val words = listOf(
            "apple", "banana", "cherry", "date", "elderberry", "fig", "grape", "honeydew",
            "kiwi", "lemon", "mango", "nectarine", "orange", "papaya", "quince", "raspberry",
            "strawberry", "tangerine", "orange", "vanilla", "watermelon", "xylophone", "yam", "zucchini"
        )
        val cardsAmount = words.size / WORDS_IN_CARD
    }

    val cards: List<Card> = generateCards()

    @Suppress("UNUSED")
    private fun List<String>.toWords(): List<Word> = this.map { Word(it) }

    private fun generateCards(): List<Card> {
        val shuffledWords = words.shuffled()
        return shuffledWords.chunked(WORDS_IN_CARD)
            .take(cardsAmount)
            .map { chunk ->
                Card(identifierFactory.uniqueIdentifier(), chunk.toWords())
            }
    }

    fun getCardByIndex(index: Int): Card {
        require(index in cards.indices) { "Invalid card index" }
        return cards[index]
    }
}