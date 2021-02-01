package com.chess.engine.board;

import com.chess.engine.pieces.Piece;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BoardTest {

    @Test
    void testToString() {
        Board board = Board.createStandardBoard();
        System.out.println(board);
        assertTrue(isBoardCreated(board));
    }

    @Test
    void getPieces() {
        Board board = Board.createStandardBoard();
        assertTrue(areThereEqualPieces(board));
    }

    @Test
    void getTile() {
        Board board = Board.createStandardBoard();
        assertTrue(howManyTiles(board));
    }

    private boolean isBoardCreated(Board board) {
        if (board != null) {
            return true;
        } else {
            return false;
        }
    }

    private boolean areThereEqualPieces(Board board) {
        if (board.getBlackPieces().size() != board.getWhitePieces().size()) {
            return false;
        } else {
            System.out.println("Black Pieces:");
            for (int i = 0; i < 16; i++) {
                if (i == 7) {
                    System.out.print(board.getTile(i) + "\n");
                    continue;
                }
                System.out.print(board.getTile(i));
            }
            System.out.println("\n\n\nWhite Pieces: ");
            for (int i = 63; i > 47; i--) {
                if (i == 56) {
                    System.out.print(board.getTile(i) + "\n");
                    continue;
                }
                System.out.print(board.getTile(i));
            }
            return true;
        }
    }

    private boolean howManyTiles(Board board) {
        for (int i = 0; i < 64; i++) {
            System.out.println(board.getTile(i).toString());
        }
        if (board.getWhitePieces().size() == board.getBlackPieces().size()) {
            return true;
        } else {
            return false;
        }
    }
}
