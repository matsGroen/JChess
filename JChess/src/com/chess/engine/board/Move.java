package com.chess.engine.board;

import com.chess.engine.pieces.Piece;

/***
 * @author mats-
 * @date 06.11.2020
 */
public abstract class Move {

    final Board board;
    final Piece movedPiece;
    final int destinationCoordinate;

    /***
     *
     * @param board on which Board
     * @param movedPiece which Piece
     * @param destinationCoordinate of the MovedPiece
     */
    private Move (final Board board, final Piece movedPiece, final int destinationCoordinate) {
        this.board = board;
        this.movedPiece = movedPiece;
        this.destinationCoordinate = destinationCoordinate;
    }

    public int getDestinationCoordinate() {
        return this.destinationCoordinate;
    }

    public abstract Board execute();

    public static final class StandardMove extends Move {

        public StandardMove(final Board board, final Piece movedPiece, final int destinationCoordinate) {
            super(board, movedPiece, destinationCoordinate);
        }

        //TODO here
        @Override
        public Board execute() {
            return null;
        }
    }

    public static final class AttackMove extends Move {

        final Piece attackedPiece;

        /***
         *
         * @param board *
         * @param movedPiece *
         * @param destinationCoordinate *
         * @param attackedPiece when the destinationCooridnate is Occupied
         */
        public AttackMove(final Board board, final Piece movedPiece, final int destinationCoordinate, final Piece attackedPiece) {
            super(board, movedPiece, destinationCoordinate);
            this.attackedPiece = attackedPiece;
        }

        //TODO here
        @Override
        public Board execute() {
            return null;
        }
    }
}
