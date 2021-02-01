package com.chess.engine.pieces;

import com.chess.engine.Alliance;
import com.chess.engine.board.Board;
import com.chess.engine.board.Move;

import java.util.Collection;
import java.util.Collections;
import java.util.List;

/***
 * @author mats-
 * @date 06.11.2020
 */
public abstract class Piece {

    protected final int piecePosition;
    protected final Alliance pieceAlliance;
    protected final boolean isFirstMove;
    protected final PieceType pieceType;

    /***
     *
     * @param piecePosition of this Piece
     * @param pieceAlliance of this Alliance
     * @param pieceType of this Piece
     */
    Piece(final int piecePosition, final Alliance pieceAlliance, final PieceType pieceType) {
        this.piecePosition = piecePosition;
        this.pieceAlliance = pieceAlliance;
        this.pieceType = pieceType;
        //TODO more work To Do here!
        this.isFirstMove = false;
    }

    /***
     *
     * Getter & is Methode
     */
    public int getPiecePosition(){
        return this.piecePosition;
    }

    public Alliance getPieceAlliance() {
        return this.pieceAlliance;
    }
    public PieceType getPieceType() {
        return this.pieceType;
    }

    public boolean isFirstMove(){
        return this.isFirstMove;
    }

    /***
     *
     * @param board of this Piece
     * @return a Collection of legalMoves for a specific Piece
     */
    public abstract Collection<Move> calculateLegalMoves(final Board board);

    /***
     * @implNote enum PieceType for the Piece
     */
    public enum PieceType {

        PAWN("P") {
            @Override
            public boolean isKing(){
                return false;
            }
        },
        KNIGHT("N") {
            @Override
            public boolean isKing(){
                return false;
            }
        },
        BISHOP("B") {
            @Override
            public boolean isKing(){
                return false;
            }
        },
        ROOK("R") {
            @Override
            public boolean isKing(){
                return false;
            }
        },
        QUEEN("Q") {
            @Override
            public boolean isKing(){
                return false;
            }
        },
        KING("K") {
            @Override
            public boolean isKing(){
                return true;
            }
        };

        private String pieceName;

        /***
         *
         * @param pieceName of this PieceType
         */
        PieceType(final String pieceName) {
            this.pieceName = pieceName;
        }

        /***$
         *
         * @return the pieceName of this PieceType
         */
        @Override
        public String toString() {
            return this.pieceName;
        }

        /***
         *
         * @return isKing for the PieceType King
         */
        public abstract boolean isKing();
    }

}
