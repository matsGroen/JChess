package com.chess.engine.pieces;

import com.chess.engine.Alliance;
import com.chess.engine.board.Board;
import com.chess.engine.board.BoardConfigs;
import com.chess.engine.board.Move;
import com.chess.engine.board.Move.StandardMove;
import com.google.common.collect.ImmutableList;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/***
 * @author mats-
 * @date 06.11.2020
 */
public class Pawn extends Piece {

    //Moves for a Pawn
    private final static int[] CANDIDATE_MOVE_COORDINATE = {7, 8, 9, 16};

    /***
     *
     * @param piecePosition of this Pawn
     * @param pieceAlliance of this Pawn
     */
    public Pawn(final int piecePosition, final Alliance pieceAlliance) {
        super(piecePosition, pieceAlliance, PieceType.PAWN);
    }

    /***
     *
     * @param board of this Piece
     * @return Collection of legalMoves for this Pawn
     */
    @Override
    public Collection<Move> calculateLegalMoves(final Board board) {
        final List<Move> legalMoves = new ArrayList<>();
        for (final int currentCandidateOffset : CANDIDATE_MOVE_COORDINATE) {
            final int candidateDestinationCoordinate = this.piecePosition + (this.pieceAlliance.getDirection() * currentCandidateOffset);
            if (!BoardConfigs.isValidTileCoordinate(candidateDestinationCoordinate)) {
                continue;
            }
            if (currentCandidateOffset == 8 && board.getTile(candidateDestinationCoordinate).isTileOccupied()) {
                //TODO more work to do here(promotion)!
                legalMoves.add(new StandardMove(board, this, candidateDestinationCoordinate));
            }
            //Pawn Jumb
            else if (currentCandidateOffset == 16 && this.isFirstMove() &&
                    (BoardConfigs.SECOND_ROW[this.piecePosition] && this.getPieceAlliance().isBlack()) ||
                    (BoardConfigs.SEVENTH_ROW[this.piecePosition] && this.getPieceAlliance().isWhite())) {
                final int behindCandidateDestinationCoordinate = this.piecePosition + (this.pieceAlliance.getDirection() * 8);
                if (!board.getTile(behindCandidateDestinationCoordinate).isTileOccupied() &&
                        !board.getTile(candidateDestinationCoordinate).isTileOccupied()) {
                    legalMoves.add(new StandardMove(board, this, candidateDestinationCoordinate));
                }
            }
            //Pawn Attack
            else if (currentCandidateOffset == 7 &&
                    !((BoardConfigs.EIGHTH_COLUMN[this.piecePosition]) && this.pieceAlliance.isWhite() ||
                        (BoardConfigs.FIRST_COLUMN[this.piecePosition] && this.pieceAlliance.isBlack()))) {
                if (board.getTile(candidateDestinationCoordinate).isTileOccupied()) {
                    final Piece pieceOnCandidate = board.getTile(candidateDestinationCoordinate).getPiece();
                    if (this.pieceAlliance != pieceOnCandidate.getPieceAlliance()) {
                        //TODO more to do here!
                        legalMoves.add(new StandardMove(board, this, candidateDestinationCoordinate));
                    }
                }
            } else if (currentCandidateOffset == 9 &&
                    !((BoardConfigs.FIRST_COLUMN[this.piecePosition]) && this.pieceAlliance.isWhite() ||
                            (BoardConfigs.EIGHTH_COLUMN[this.piecePosition] && this.pieceAlliance.isBlack())) ) {
                if (board.getTile(candidateDestinationCoordinate).isTileOccupied()) {
                    final Piece pieceOnCandidate = board.getTile(candidateDestinationCoordinate).getPiece();
                    if (this.pieceAlliance != pieceOnCandidate.getPieceAlliance()) {
                        //TODO more to do here!
                        legalMoves.add(new StandardMove(board, this, candidateDestinationCoordinate));
                    }
                }
            }
        }
        return ImmutableList.copyOf(legalMoves);
    }

    /***
     *
     * @return String of PieceType Pawn
     */
    @Override
    public String toString() {
        return PieceType.PAWN.toString();
    }
}
