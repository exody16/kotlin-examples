package map

import cards.CardsMap
import com.soywiz.klock.TimeSpan
import com.soywiz.korev.Key
import com.soywiz.korge.Korge
import com.soywiz.korge.view.*
import com.soywiz.korim.color.Colors
import com.soywiz.korim.color.RGBA
import com.soywiz.korim.font.BitmapFont
import com.soywiz.korim.font.readBitmapFont
import com.soywiz.korio.file.std.resourcesVfs
import com.soywiz.korma.geom.vector.*
import kotlin.properties.Delegates

var cellSize: Double = 0.0
var fieldSize: Double = 0.0
var leftIndent: Double = 0.0
var topIndent: Double = 0.0
var font: BitmapFont by Delegates.notNull()


suspend fun main() = Korge(width = 800, height = 800, title = "2048", bgcolor = RGBA(253, 247, 240)) {

    cellSize = views.virtualWidth / 6.0
    fieldSize = 50 + 4 * cellSize
    leftIndent = (views.virtualWidth - fieldSize) / 2
    topIndent = 150.0

    val map = CardsMap()

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
            com.soywiz.korio.async.runBlockingNoJs { resourcesVfs["clear_sans.fnt"].readBitmapFont() }) {
//            text(map.getCardName(Player.TWO, i), cellSize * 0.5, Colors.WHITE, getFont()) {
            centerOn(blocks[i])
        }
    }
//    val top =
//    List(5) {it}
//        .map {index ->
//        graphics {
//            fill(Colors["#cec0b2"]) {
//                roundRect(10 + (10 + cellSize) * index, 10 + (10 + cellSize), cellSize, cellSize, 5.0)
//            }
//        }
//    }
//
//    for (i in 0..4) {
//        text(map.getCardName(Player.TWO, i), cellSize * 0.5, Colors.WHITE, font) {
//            centerOn(top[i])
//        }
//    }


// TODO extract this to a class to be able to remove later
//    val bottom =
//        List(5) {it}
//            .map {index ->
//                graphics {
//                    fill(Colors["#cec0b2"]) {
//                        roundRect(10 + (10 + cellSize) * index, 450 + (10 + cellSize), cellSize, cellSize, 5.0)
//                    }
//                }
//            }
//
//    for (i in 0..4) {
//        text(map.getCardName(Player.ONE, i), cellSize * 0.5, Colors.WHITE, font) {
//            centerOn(bottom[i])
//        }
//    }

    // TODO handle all buttons
    addUpdater { timespan: TimeSpan ->
        if (input.keys.justPressed(Key.N1)) {
            graphics {
                fill(Colors["#cec0b2"]) {
                    roundRect(10 + (10 + cellSize), 250 + (10 + cellSize), cellSize, cellSize, 5.0)
                }
            }
        }
    }
}

private suspend fun doIt(cellSize: Double) : Block {
    return Block(cellSize)
}


