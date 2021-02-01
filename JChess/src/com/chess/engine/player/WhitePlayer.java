package com.chess.engine.player;

import com.chess.engine.Alliance;
import com.chess.engine.board.Board;
import com.chess.engine.board.Move;
import com.chess.engine.pieces.Piece;

import java.util.Collection;

/***
 * @author mats-
 * @date 13.11.2020
 */
public class WhitePlayer extends Player {

    /***
     *
     * @param board of this WhitePlayer
     * @param whiteStandardLegalMoves his own Moves of this WhitePlayer
     * @param blackStandardLegalMoves the opponentsMove of this WhitePlayer
     */
    public WhitePlayer(Board board, Collection<Move> whiteStandardLegalMoves, Collection<Move> blackStandardLegalMoves) {
        super(board, whiteStandardLegalMoves, blackStandardLegalMoves);

    }

    /***
     *
     * @return Collection of the White ActivePieces
     */
    @Override
    public Collection<Piece> getActivePieces() {
        return this.board.getWhitePieces();
    }

    @Override
    public Alliance getAlliance() {
        return Alliance.WHITE;
    }

    @Override
    public Player getOpponent() {
        return this.board.blackPlayer();
    }
}
