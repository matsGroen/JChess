package com.chess.engine.pieces;

import com.chess.engine.Alliance;
import com.chess.engine.board.Board;
import com.chess.engine.board.BoardConfigs;
import com.chess.engine.board.Move;
import com.chess.engine.board.Move.AttackMove;
import com.chess.engine.board.Move.StandardMove;
import com.chess.engine.board.Tile;
import com.google.common.collect.ImmutableList;

import java.util.ArrayList;
import java.util.List;

/***
 * @author mats-
 * @date 06.11.2020
 */
public class Knight extends Piece {

    //List of Moves for a Knight
    private final static int[] CANDIDATE_MOVE_COORIDNATES = {-17, -15, -10, -6, 6, 10, 15, 17};

    /***
     *
     * @param piecePosition of this Knight
     * @param pieceAlliance of this Knight
     */
    public Knight(final int piecePosition, final Alliance pieceAlliance) {
        super(piecePosition, pieceAlliance, PieceType.KNIGHT);
    }

    /***
     *
     * @param board on which this Knight stands
     * @return a List of all legal Moves for this Knight
     */
    @Override
    public List<Move> calculateLegalMoves(final Board board) {
        final List<Move> legalMoves = new ArrayList<>();
        for (final int currentCandidateOffset : CANDIDATE_MOVE_COORIDNATES) {
            final int candidateDestinationCoordinate = this.piecePosition + currentCandidateOffset;
            if (BoardConfigs.isValidTileCoordinate(candidateDestinationCoordinate)) {
                if (isFirstColumnExclusion(this.piecePosition, currentCandidateOffset) ||
                    isSecondColumnExclusion(this.piecePosition, currentCandidateOffset) ||
                    isSeventhColumnExclusion(this.piecePosition, currentCandidateOffset) ||
                    isEighthColumnExclusion(this.piecePosition, currentCandidateOffset)) {
                    continue;
                }
                final Tile candidateDestinationTile = board.getTile(candidateDestinationCoordinate);
                if (!candidateDestinationTile.isTileOccupied()) {
                    legalMoves.add(new StandardMove(board, this, candidateDestinationCoordinate));
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
     * @return the String of this Knight
     */
    @Override
    public String toString() {
        return PieceType.KNIGHT.toString();
    }

    /***
     *
     * @param currentPosition of this Knight
     * @param candidateOffset of the Knights Destination, but it isn't legal
     * @return the Exclusions for a Knight's Move Vector, if he stands somewhere one the first column
     */
    private static boolean isFirstColumnExclusion(final int currentPosition, final int candidateOffset) {
        return BoardConfigs.FIRST_COLUMN[currentPosition] &&
                (candidateOffset == -17 || candidateOffset == -10 ||
                        candidateOffset == 6 || candidateOffset == 15);
    }

    /***
     *
     * @param currentPosition of this Knight
     * @param candidateOffset of the Knights Destination, but it isn't legal
     * @return the Exclusions for a Knight's Move Vector, if he stands somewhere one the second column
     */
    private static boolean isSecondColumnExclusion(final int currentPosition, final int candidateOffset) {
        return BoardConfigs.SECOND_COLUMN[currentPosition] &&
                (candidateOffset == -10 || candidateOffset == 6);
    }

    /***
     *
     * @param currentPosition of this Knight
     * @param candidateOffset of the Knights Destination, but it isn't legal
     * @return the Exclusions for a Knight's Move Vector, if he stands somewhere one the seventh column
     */
    private static boolean isSeventhColumnExclusion(final int currentPosition, final int candidateOffset) {
        return BoardConfigs.SEVENTH_COLUMN[currentPosition] &&
                (candidateOffset == -6 || candidateOffset == 10);
    }

    /***
     *
     * @param currentPosition of this Knight
     * @param candidateOffset of the Knights Destination, but it isn't legal
     * @return the Exclusions for a Knight's Move Vector, if he stands somewhere one the eighth column
     */
    private static boolean isEighthColumnExclusion(final int currentPosition, final int candidateOffset) {
        return BoardConfigs.EIGHTH_COLUMN[currentPosition] &&
                (candidateOffset == -15 || candidateOffset == -6 ||
                        candidateOffset == 10 || candidateOffset == 17);
    }
}
