package map

import cards.CardsMap
import cards.Player
import com.soywiz.korge.view.*
import com.soywiz.korim.color.Colors
import com.soywiz.korim.font.BitmapFont
import com.soywiz.korim.font.readBitmapFont
import com.soywiz.korio.async.runBlockingNoJs
import com.soywiz.korio.file.std.resourcesVfs
import com.soywiz.korma.geom.vector.roundRect

fun Container.block(number: Double) = Block(number).addTo(this)

class Block(
    private val cellSize: Double,
//    private val map: CardsMap
) : Container() {


    fun allIn() {

        val blocks: List<Graphics> = List(5) { it }
            .map { index ->
                graphics {
                    fill(Colors["#cec0b2"]) {
                        roundRect(10 + (10 + cellSize) * index, 10 + (10 + cellSize), cellSize, cellSize, 5.0)
                    }
                }
            }

            for (i in 0..4) {
                text(
                    "Text",
                    cellSize * 0.5,
                    Colors.WHITE,
                    runBlockingNoJs { resourcesVfs["clear_sans.fnt"].readBitmapFont() }) {
//            text(map.getCardName(Player.TWO, i), cellSize * 0.5, Colors.WHITE, getFont()) {
                    centerOn(blocks[i])
                }
            }

        }


}