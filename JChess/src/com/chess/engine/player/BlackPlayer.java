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
public class BlackPlayer extends Player {

    /***
     *
     * @param board of this Player
     * @param whiteStandardLegalMoves the opponents Move for this BlackPlayer
     * @param blackStandardLegalMoves his own Moves for this BlackPlayer
     */
    public BlackPlayer(Board board, Collection<Move> whiteStandardLegalMoves, Collection<Move> blackStandardLegalMoves) {
        super(board, blackStandardLegalMoves, whiteStandardLegalMoves);
    }

    /***
     *
     * @return Collection of Black ActivePieces
     */
    @Override
    public Collection<Piece> getActivePieces() {
        return this.board.getBlackPieces();
    }

    @Override
    public Alliance getAlliance() {
        return Alliance.BLACK;
    }

    @Override
    public Player getOpponent() {
        return this.board.whitePlayer();
    }


}
