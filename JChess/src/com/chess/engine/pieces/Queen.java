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
public class Queen extends Piece {

    // Moves for a Queen
    private final static int[] CANDIDATE_MOVE_VECTOR_COORDINATES = {-9, -8, -7, -1, 1, 7, 8,  9};

    /***
     *
     * @param piecePosition of this Queen
     * @param pieceAlliance of this Queen
     */
    public Queen(int piecePosition, Alliance pieceAlliance) {
        super(piecePosition, pieceAlliance, PieceType.QUEEN);
    }

    /***
     *
     * @param board of this Piece
     * @return Collection of legalMoves for a Queen
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

    /**
     *
     * @return String of the PieceType Queen
     */
    @Override
    public String toString() {
        return PieceType.QUEEN.toString();
    }

    /***
     *
     * @param currenPosition of the Queen
     * @param candidateOffset of the Destination, but it isn't legal
     * @return the Exclucsions of a Queen, if she stands somewhere on the First Column
     */
    private static boolean isFirstColumnExclusion(final int currenPosition, final int candidateOffset) {
        return BoardConfigs.FIRST_COLUMN[currenPosition] && (candidateOffset == -1 || candidateOffset == -9 || candidateOffset == 7);
    }

    /***
     *
     * @param currenPosition of this Queen
     * @param candidateOffset of the Destination, but it isn't legal
     * @return the Exclusions of a Queen, if she stands somewhere on the eighth Column
     */
    private static boolean isEightColumnExclusion(final int currenPosition, final int candidateOffset) {
        return BoardConfigs.EIGHTH_COLUMN[currenPosition] && (candidateOffset == -7 || candidateOffset == 1 || candidateOffset == 9);
    }
}
