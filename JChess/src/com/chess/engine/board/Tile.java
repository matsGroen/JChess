package com.chess.engine.board;

import com.chess.engine.pieces.Piece;
import com.google.common.collect.ImmutableMap;

import java.util.HashMap;
import java.util.Map;

/***
 * @author mats-
 * @date 06.11.2020
 */
public abstract class Tile {

    protected final int tileCoordinate;

    /***
     *
     * @param tileCoordinate of this Tile
     */
    private Tile(int tileCoordinate) {
        this.tileCoordinate = tileCoordinate;
    }

    private static final Map<Integer, EmptyTile> EMPTY_TILES_CACHE = createAllPossibleEmptyTiles();

    /***
     *
     * @return a List of all EmptyTiles
     */
    private static Map<Integer, EmptyTile> createAllPossibleEmptyTiles() {
        final Map<Integer, EmptyTile> emptyTileMap = new HashMap<>();
        for (int i = 0; i < BoardConfigs.NUM_TILES; i++) {
            emptyTileMap.put(i, new EmptyTile(i));
        }
        return ImmutableMap.copyOf(emptyTileMap);
    }

    /***
     *
     * @param tileCoordinate of the Tile that should be created
     * @param piece when a Piece is on the Tile
     * @return an OccupiedTile in the case the Tile is occupied, when not it returns the EmptyTile
     */
    public static Tile createTile(final int tileCoordinate, final Piece piece) {
        // creation of a Tile
        return piece != null ? new OccupiedTile(tileCoordinate, piece) : EMPTY_TILES_CACHE.get(tileCoordinate);
    }

    public abstract boolean isTileOccupied();
    public abstract Piece getPiece();

    public static final class EmptyTile extends Tile{

        private EmptyTile(final int coordinate) {
            super(coordinate);
        }

        @Override
        public String toString () {
            return "-";
        }

        @Override
        public boolean isTileOccupied() {
            return false;
        }
        @Override
        public Piece getPiece() {
            return null;
        }
    }

    public static final class OccupiedTile extends Tile{

        private final Piece pieceOnTile;

        private OccupiedTile(int tileCoordinate, Piece pieceOnTile) {
            super(tileCoordinate);
            this.pieceOnTile = pieceOnTile;
        }

        @Override
        public String toString() {
            if (getPiece().getPieceAlliance().isWhite()) {
                return getPiece().toString();
            } else {
                return getPiece().toString().toLowerCase();
            }
        }

        @Override
        public boolean isTileOccupied() {
            return true;
        }
        @Override
        public Piece getPiece() {
            return this.pieceOnTile;
        }
    }
}
