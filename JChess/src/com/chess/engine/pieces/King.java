package com.chess.engine.pieces;

import com.chess.engine.Alliance;
import com.chess.engine.board.Board;
import com.chess.engine.board.BoardConfigs;
import com.chess.engine.board.Move;
import com.chess.engine.board.Move.AttackMove;
import com.chess.engine.board.Tile;
import com.google.common.collect.ImmutableList;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/***
 * @author mats-
 * @date 06.11.2020
 */
public class King extends Piece{

    //Moves for a King
    private final static int[] CANDIDATE_MOVE_COORDINATE = {-9, -8, -7, -1, 1, 7, 8, 9};

    /***
     *
     * @param piecePosition of this King
     * @param pieceAlliance of this King
     */
    public King(final int piecePosition, final Alliance pieceAlliance) {
        super(piecePosition, pieceAlliance, PieceType.KING);
    }

    /***
     *
     * @param board of this Piece
     * @return Collection of legalMoves for this King
     */
    @Override
    public Collection<Move> calculateLegalMoves(final Board board) {
        final List<Move> legalMoves = new ArrayList<>();
        for (final int currentCandidateOffset : CANDIDATE_MOVE_COORDINATE) {
            final int candidateDestinationCoordinate = this.piecePosition + currentCandidateOffset;
            if (isFirstColumnExclusion(this.piecePosition, currentCandidateOffset) || isEighthColumnExclusion(this.piecePosition, currentCandidateOffset)) {
                continue;
            }
            if (BoardConfigs.isValidTileCoordinate(candidateDestinationCoordinate)) {
                final Tile candidateDestinationTile = board.getTile(candidateDestinationCoordinate);
                if (!candidateDestinationTile.isTileOccupied()) {
                    legalMoves.add(new Move.StandardMove(board, this, candidateDestinationCoordinate));
                } else {
                    final Piece pieceAtDestination = candidateDestinationTile.getPiece();
                    final Alliance pieceAlliance = pieceAtDestination.getPieceAlliance();

                    // if Enemy
                    if (this.pieceAlliance != pieceAlliance) {
                        legalMoves.add(new AttackMove(board, this, candidateDestinationCoordinate, pieceAtDestination));
                    }
                }
            }
        }
        return ImmutableList.copyOf(legalMoves);
    }

    /***
     *
     * @return String of the PieceType King
     */
    @Override
    public String toString() {
        return PieceType.KING.toString();
    }

    /***
     *
     * @param currentPosition of this King
     * @param candidateOffset of the Destination, but it isn't legal
     * @return Exclusions for a King, if he stands somewhere on the first column
     */
    private static boolean isFirstColumnExclusion(final int currentPosition, final int candidateOffset) {
        return BoardConfigs.FIRST_COLUMN[currentPosition] &&
                (candidateOffset == -9 || candidateOffset == -1 ||
                        candidateOffset == 7);
    }

    /***
     *
     * @param currenPosition of this King
     * @param candidateOffSet of this King
     * @return Exclusion of this King, if he stands somewhere on the eighth column
     */
    private static boolean isEighthColumnExclusion(final int currenPosition, final int candidateOffSet) {
        return BoardConfigs.EIGHTH_COLUMN[currenPosition] &&
                (candidateOffSet == -7 || candidateOffSet == 1 ||
                        candidateOffSet == 9);
    }
}
