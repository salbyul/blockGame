package com.game;

import com.game.block.BlockShape;
import com.game.block.BlockShapeEnum;
import com.game.board.Board;
import com.game.board.OccupationCalculator;
import com.game.view.View;
import com.game.view.translator.InputTranslator;

import java.io.IOException;
import java.util.List;

public class Application {

    public static void main(String[] args) {
        Board board = new Board(new boolean[5][5]);
        List<BlockShape> blockShapeList = List.of(BlockShapeEnum.values());

        Game game = new Game(
                blockShapeList,
                board,
                new OccupationCalculator(),
                new View(System.in, System.out, new InputTranslator(board, blockShapeList)),
                new RandomGeneratorImpl()
        );
        try {
            game.start();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
