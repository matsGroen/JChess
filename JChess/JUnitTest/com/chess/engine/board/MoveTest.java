package com.chess.engine.board;

import com.chess.engine.Alliance;
import com.chess.engine.board.Move.StandardMove;
import com.chess.engine.pieces.Knight;
import com.chess.engine.pieces.Pawn;
import com.chess.engine.pieces.Piece;
import com.chess.engine.pieces.Queen;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class MoveTest {

    @Test
    void areQueensOnPlaces() {
        Board board = Board.createStandardBoard();
        assertTrue(isBlackQueen(board) && isWhiteQueen(board));
    }

    @Test
    void isPawnRowReady() {
        Board board = Board.createStandardBoard();
        assertTrue(areWhitePawnsInPosition(board) && areBlackPawnsInPosition(board));
    }

    @Test
    void areRowNumberDefaultEmpty() {
        Board board = Board.createStandardBoard();
    }
/*
    @Test
    void doesTheKnightJump() {
        Board board = Board.createStandardBoard();

        assertTrue(knightJumps(board));
    } */

    /*    private boolean knightJumps(Board board) {
        // Knight knight = new Knight(1,Alliance.BLACK);
        StandardMove standardMove = new StandardMove(board, board.getTile(1).getPiece(), 6);
        System.out.println(standardMove.destinationCoordinate + "\n");
        board.getTile(6).getPiece().getPieceType();

        System.out.println(board + "\n" + board.getTile(6).getPiece().getPiecePosition() + board.getTile(6).getPiece().getPieceType());
        System.out.println("\n" + board);
        return true;
    } */

    private boolean areBlackPawnsInPosition(Board board) {
        ArrayList<Piece> blackPawns = new ArrayList<>();

        for (int i = 0; i < 64; i++) {
            if (board.getTile(i).getPiece().getPieceAlliance().isBlack() &&
                board.getTile(i).getPiece().getPieceType() == Piece.PieceType.PAWN){
                Piece piece = new Pawn(i, Alliance.BLACK);
                blackPawns.add(piece);
                System.out.println("On the second Row are standig the: " + board.getTile(i).toString());
                for (int p = i; p > 7 && p < 16; p++) {
                    System.out.print(board.getTile(p).getPiece().getPieceAlliance().toString());
                }
                return true;
            }
        }
        throw new RuntimeException("they should be on there Position...");
    }

    private boolean areWhitePawnsInPosition(Board board) {
        ArrayList<Piece> whitePawns = new ArrayList<>();

        for (int i = 0; i < 64; i++) {
            if (board.getTile(i).isTileOccupied()) {
                if (board.getTile(i).getPiece().getPieceAlliance().isWhite() &&
                        board.getTile(i).getPiece().getPieceType() == Piece.PieceType.PAWN) {
                    Piece piece = new Pawn(i, Alliance.WHITE);
                        whitePawns.add(piece);
                        System.out.println("On the seventh Row are standig the: " + board.getTile(i).toString());
                        for (int p = i; p > 46 && p < 56; p++) {
                            System.out.print(board.getTile(p).getPiece().getPieceAlliance().toString());
                        }
                    System.out.println("\n");
                        return true;
                }
            }
        }
        throw new RuntimeException("they should be on there Position...");
    }


    private boolean isWhiteQueen(Board board) {

        for (int i = 0; i < 64; i++) {
            Piece piece = new Queen(59, Alliance.WHITE);
            if (board.getTile(i).isTileOccupied()) {
                if (board.getTile(i).getPiece().getPieceAlliance() == piece.getPieceAlliance() &&
                        board.getTile(i).getPiece().getPieceType() == Piece.PieceType.QUEEN) {
                    System.out.println("The Tile: " + (board.getTile(i).tileCoordinate + 1) +" is Occupied, \n"
                           + "the Piece is on it is a:  " + board.getTile(i).getPiece().getPieceType().toString());
                    return true;
                }
            }
        }
        throw new RuntimeException("it must be a Queen...");
    }

    private boolean isBlackQueen(Board board) {

        for (int i = 0; i < 64; i++) {
            Piece piece = new Queen(3, Alliance.BLACK);
            if (board.getTile(i).isTileOccupied()) {
                if (board.getTile(i).getPiece().getPieceAlliance() == piece.getPieceAlliance() &&
                        board.getTile(i).getPiece().getPieceType() == Piece.PieceType.QUEEN) {
                    System.out.println("The Tile: " + (board.getTile(i).tileCoordinate + 1) +" is Occupied, \n"
                            + "the Piece is on it is a:  " + board.getTile(i).getPiece().getPieceType().toString());
                    return true;
                }
            }
        }
        throw new RuntimeException("it must be a Queen...");
    }
}

