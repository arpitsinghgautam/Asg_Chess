package com.chess.engine.board;

import com.chess.engine.pieces.Piece;
import com.google.common.collect.ImmutableMap;

import java.util.HashMap;
import java.util.Map;

public abstract class Tile
{
    protected final int tileCoordinate;

    private static final Map<Integer, EmptyTile> EMPTY_TILES_CACHE = createAllPossibleEmptyTiles();
    private static Map<Integer, EmptyTile> createAllPossibleEmptyTiles()
    {

        final Map<Integer, EmptyTile> emptyTileMap = new HashMap<>();
        for(int i=0; i<64; i++)
        {
            emptyTileMap.put(i, new EmptyTile(i));
        }
        return ImmutableMap.copyOf(emptyTileMap);
    }
    public static Tile createTile(final int tileCoordinate, final Piece piece)
    {
        return piece !=null ? new OccupiedTile(tileCoordinate, piece) : EMPTY_TILES_CACHE.get(tileCoordinate);
    }

    Tile(int tileCoordinate)
    {
        this.tileCoordinate = tileCoordinate;
    }
    public abstract boolean isTileOccupied();
    public abstract Piece getPiece();

    public static final class EmptyTile extends Tile
    {
        private EmptyTile(final int coordinate)
        {
            super(coordinate);
        }

        @Override
        public String toString()
        {
            return "-";
        }
        @Override
        public boolean isTileOccupied()
        {
            return false;
        }
        @Override
        public Piece getPiece()
        {
            return null;
        }
    }
    public static final class OccupiedTile extends  Tile
    {
        private final Piece pieceOnTile;
        private OccupiedTile(int tileCoordinate, final Piece pieceOnTile)
        {
            super(tileCoordinate);
            this.pieceOnTile = pieceOnTile;

        }

        @Override
        public String toString()
        {
            return getPiece().getPieceAlliance().isBlack() ? getPiece().toString().toLowerCase() :
                    getPiece().toString();
        }

        @Override
        public boolean isTileOccupied()
        {
            return true;
        }
        @Override
        public Piece getPiece()
        {
            return this.pieceOnTile;
        }
    }
}

/*
we have defined a class which represent a chess tile
it take tileCoordinate as a parameter
key methods are whether a tile is occupied or not (isTileOccupied)
and
to retrive a piece on a tile
we have sub classes of empty tile and occuiped tile which define those behavious
*/
