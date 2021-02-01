package com.chess.engine.board;

/***
 * @author mats-
 * @date 13.11.2020
 */
public class BoardConfigs {

    //Column initialisation
    public static final boolean[] FIRST_COLUMN = initColumn(0);
    public static final boolean[] SECOND_COLUMN = initColumn(1);
    public static final boolean[] SEVENTH_COLUMN = initColumn(6);
    public static final boolean[] EIGHTH_COLUMN = initColumn(7);

    //Row initialisation
    public static final boolean[] SECOND_ROW = initRow(8);
    public static final boolean[] SEVENTH_ROW = initRow(48);

    //Tile initialisation
    public static final int NUM_TILES = 64;
    public static final int NUM_TILES_PER_ROW = 8;

    /***
     * cannot instantiate a BoardConfig
     */
    private BoardConfigs() {
        throw new RuntimeException("You cannot instantiate me!");
    }

    /***
     *
     * @param columnNumber
     * @return an Array of Booleans for a Column
     */
    private static boolean[] initColumn(int columnNumber) {
        final boolean[] column = new boolean[64];
        do {
            column[columnNumber] = true;
            columnNumber += NUM_TILES_PER_ROW;
        } while (columnNumber < NUM_TILES);
        return column;
    }

    /***
     *
     * @param rowNumber
     * @return an Array of booleans of a Row
     */
    private static boolean[] initRow (int rowNumber) {
        final boolean[] row = new boolean[NUM_TILES];
        do {
            row[rowNumber] = true;
            rowNumber++;
        } while (rowNumber % NUM_TILES_PER_ROW != 0);
        return row;
    }

    /***
     *
     * @param coordinate
     * @return a boolean if the Destination is valid
     */
    public static boolean isValidTileCoordinate(final int coordinate) {
        return coordinate >= 0 && coordinate < NUM_TILES;
    }
}
