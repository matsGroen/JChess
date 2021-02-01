package com.chess.engine.pieces;

import com.chess.engine.Alliance;
import com.chess.engine.board.Board;
import com.chess.engine.board.BoardConfigs;
import com.chess.engine.board.Move;
import com.chess.engine.board.Tile;
import com.google.common.collect.ImmutableList;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/***
 * @author mats-
 * @date 06.11.2020
 */
public class Rook extends Piece{

    //Moves for a Rook
    private final static int[] CANDIDATE_MOVE_VECTOR_COORDINATES = {-8, -1, 1, 8};

    /***
     *
     * @param piecePosition of this Rook
     * @param pieceAlliance of this Rook
     */
    public Rook(final int piecePosition, final Alliance pieceAlliance) {
        super(piecePosition, pieceAlliance, PieceType.ROOK);
    }

    /***
     *
     * @param board of this Piece
     * @return Collection of legalMoves for a Rook
     */
    @Override
    public Collection<Move> calculateLegalMoves(final Board board) {
        final List<Move> legalMoves = new ArrayList<>();
        for (final int candidateCoordinateOffset : CANDIDATE_MOVE_VECTOR_COORDINATES) {
            int candidateDestinationCoordinate = this.piecePosition;
            while (BoardConfigs.isValidTileCoordinate(candidateDestinationCoordinate)) {
                if (isFirstColumnExclusion(candidateDestinationCoordinate, candidateCoordinateOffset) ||
                        isEightColumnExclusion(candidateDestinationCoordinate, candidateCoordinateOffset)) {
                    break;
                }
                candidateDestinationCoordinate += candidateCoordinateOffset;
                if (BoardConfigs.isValidTileCoordinate(candidateDestinationCoordinate)) {
                    final Tile candidateDestinationTile = board.getTile(candidateDestinationCoordinate);
                    if (!candidateDestinationTile.isTileOccupied()) {
                        legalMoves.add(new Move.StandardMove(board, this, candidateDestinationCoordinate));
                    } else {
                        final Piece pieceAtDestination = candidateDestinationTile.getPiece();
                        final Alliance pieceAlliance = pieceAtDestination.getPieceAlliance();
                        // if Enemy
                        if (this.pieceAlliance != pieceAlliance) {
                            legalMoves.add(new Move.AttackMove(
                                    board, this, candidateDestinationCoordinate, pieceAtDestination));
                        }
                        break;
                    }
                }
            }
        }
        return ImmutableList.copyOf(legalMoves);
    }

    /***
     *
     * @return String of the PieceType ROOK
     */
    @Override
    public String toString() {
        return PieceType.ROOK.toString();
    }

    /***
     *
     * @param currenPosition of the Rook
     * @param candidateOffset of the Destination, but it isn't legal
     * @return Exclusions of the Rook, if he stands somewhere on the first column
     */
    private static boolean isFirstColumnExclusion(final int currenPosition, final int candidateOffset) {
        return BoardConfigs.FIRST_COLUMN[currenPosition] && (candidateOffset == -1);
    }
    private static boolean isEightColumnExclusion(final int currenPosition, final int candidateOffset) {
        return BoardConfigs.EIGHTH_COLUMN[currenPosition] && (candidateOffset == 1);
    }
}
