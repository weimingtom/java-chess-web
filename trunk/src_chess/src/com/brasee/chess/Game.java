package com.brasee.chess;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.brasee.chess.moves.CaptureMove;
import com.brasee.chess.moves.CastlingMove;
import com.brasee.chess.moves.EnPassantMove;
import com.brasee.chess.moves.InvalidMove;
import com.brasee.chess.moves.Move;
import com.brasee.chess.moves.NormalMove;
import com.brasee.chess.moves.Move.MoveType;
import com.brasee.chess.pieces.Bishop;
import com.brasee.chess.pieces.King;
import com.brasee.chess.pieces.Knight;
import com.brasee.chess.pieces.Pawn;
import com.brasee.chess.pieces.Piece;
import com.brasee.chess.pieces.Queen;
import com.brasee.chess.pieces.Rook;
import com.brasee.chess.pieces.Piece.Color;

public class Game {

	private Board board;
	private List<Move> moves; 
	private Color playersTurn;
	private Map<Color, Set<Piece>> capturedPieces;
	
	public Game() {
		board = new Board();
		moves = new ArrayList<Move>();
		playersTurn = Color.WHITE;
		clearCapturedPieces();
	}

	public void initializeBoard() {
		board = new Board();
		moves = new ArrayList<Move>();
		playersTurn = Color.WHITE;
		placeWhitePieces();
		placeBlackPieces();
		clearCapturedPieces();
	}
	
	public Move move(Square startSquare, Square endSquare) {
		Move move = InvalidMove.execute();	
		if (validPieceOnStartSquare(startSquare)) {
			move = executeMove(startSquare, endSquare);
			if (!move.moveType().equals(MoveType.INVALID)) {
				moves.add(move);
				changePlayersTurn();
			}
		}
		return move;
	}
	
	public Set<Piece> capturedPieces(Color color) {
		return capturedPieces.get(color);
	}
	
	protected Board board() {
		return board;
	}
	
	protected List<Move> moves() {
		return moves;
	}
	
	protected Move lastMove() {
		return (moves.size() > 0 ? moves.get(moves.size() - 1) : null);
	}
	
	private Move executeMove(Square startSquare, Square endSquare) {
		Move move = InvalidMove.execute();
		
		if (EnPassantMove.canBeExecuted(board, startSquare, endSquare, lastMove())) {
			move = EnPassantMove.execute(board, startSquare, endSquare);
			EnPassantMove enPassantMove = (EnPassantMove) move;
			capturedPieces.get(enPassantMove.opposingPiece().color()).add(enPassantMove.opposingPiece());
		}
		else if (CastlingMove.canBeExecuted(board, startSquare, endSquare)) {
			move = CastlingMove.execute(board, startSquare, endSquare);
		}
		else if (NormalMove.canBeExecuted(board, startSquare, endSquare)) {
			move = NormalMove.execute(board, startSquare, endSquare);
		}
		else if (CaptureMove.canBeExecuted(board, startSquare, endSquare)) {
			move = CaptureMove.execute(board, startSquare, endSquare);
			CaptureMove captureMove = (CaptureMove) move;
			capturedPieces.get(captureMove.opposingPiece().color()).add(captureMove.opposingPiece());
		}
		
		if (board.inCheck(playersTurn)) {
			move.undo(board);
			removeCapturedPiece(move);
			move = InvalidMove.execute();
		}

		return move;
	}

	private void removeCapturedPiece(Move move) {
		Piece capturedPiece = null;
		if (MoveType.CAPTURE.equals(move.moveType())) {
			capturedPiece = ((CaptureMove)move).opposingPiece();
		}
		else if (MoveType.EN_PASSANT.equals(move.moveType())) {
			capturedPiece = ((EnPassantMove)move).opposingPiece();
		}
		
		if (capturedPiece != null) {
			capturedPieces.get(capturedPiece.color()).remove(capturedPiece);
		}
	}

	private void changePlayersTurn() {
		if (playersTurn.equals(Color.WHITE)) {
			playersTurn = Color.BLACK;
		}
		else {
			playersTurn = Color.WHITE;
		}
	}
	
	private boolean validPieceOnStartSquare(Square startSquare) {
		boolean validPieceOnStartSquare = true;
		
		if (board().pieceOn(startSquare) == null) {
			validPieceOnStartSquare = false;
		}
		else if (!board().pieceOn(startSquare).color().equals(playersTurn)) {
			validPieceOnStartSquare = false;
		}
		
		return validPieceOnStartSquare;
	}

	private void placeWhitePieces() {
		board.placePiece(new Square("a1"), new Rook(Color.WHITE));
		board.placePiece(new Square("b1"), new Knight(Color.WHITE));
		board.placePiece(new Square("c1"), new Bishop(Color.WHITE));
		board.placePiece(new Square("d1"), new Queen(Color.WHITE));
		board.placePiece(new Square("e1"), new King(Color.WHITE));
		board.placePiece(new Square("f1"), new Bishop(Color.WHITE));
		board.placePiece(new Square("g1"), new Knight(Color.WHITE));
		board.placePiece(new Square("h1"), new Rook(Color.WHITE));
		for (char row : new char[] {'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h'}) {
			board.placePiece(new Square(row, '2'), new Pawn(Color.WHITE));
		}		
	}
	
	private void placeBlackPieces() {
		board.placePiece(new Square("a8"), new Rook(Color.BLACK));
		board.placePiece(new Square("b8"), new Knight(Color.BLACK));
		board.placePiece(new Square("c8"), new Bishop(Color.BLACK));
		board.placePiece(new Square("d8"), new King(Color.BLACK));
		board.placePiece(new Square("e8"), new Queen(Color.BLACK));
		board.placePiece(new Square("f8"), new Bishop(Color.BLACK));
		board.placePiece(new Square("g8"), new Knight(Color.BLACK));
		board.placePiece(new Square("h8"), new Rook(Color.BLACK));
		for (char row : new char[] {'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h'}) {
			board.placePiece(new Square(row, '7'), new Pawn(Color.BLACK));
		}
	}
	
	private void clearCapturedPieces() {
		capturedPieces = new HashMap<Color, Set<Piece>>();
		capturedPieces.put(Color.WHITE, new HashSet<Piece>());
		capturedPieces.put(Color.BLACK, new HashSet<Piece>());
	}
}
