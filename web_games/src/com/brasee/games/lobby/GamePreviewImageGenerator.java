package com.brasee.games.lobby;

import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;
import java.io.BufferedOutputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.imageio.ImageIO;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;
import org.springframework.core.io.Resource;

import com.brasee.chess.Board;
import com.brasee.chess.Game;
import com.brasee.chess.Square;
import com.brasee.chess.pieces.Knight;
import com.brasee.chess.pieces.Piece;
import com.brasee.chess.pieces.Piece.Color;

public class GamePreviewImageGenerator {

	public static final int BOARD_SIZE = 200;
	public static final int SQUARE_SIZE = 25;
	public static final String IMAGE_FORMAT = "PNG";
	
	public BufferedImage boardImage;
	private Map<Piece, BufferedImage> pieceImages;
	
	public GamePreviewImageGenerator(BufferedImage boardImage, Map<Piece, BufferedImage> pieceImages) {
		this.boardImage = boardImage;
		this.pieceImages = pieceImages;
	}
	
	/**
	 * Generate a PNG image representing the current state of board.
	 *  
	 * @param game
	 */
	public BufferedImage createPngPreviewImage(Game game) {
	    BufferedImage image = new BufferedImage(BOARD_SIZE, BOARD_SIZE, BufferedImage.TYPE_INT_ARGB); 
	
	    Graphics2D g2d = image.createGraphics();
	    AffineTransformOp transformOp = new AffineTransformOp(new AffineTransform(), AffineTransformOp.TYPE_BILINEAR);
	    g2d.drawImage(this.boardImage, transformOp, 0, 0);
	    Map<Square, Piece> pieceMap = game.pieces();
	    for (Square square : pieceMap.keySet()) {
	    	int xPos = SQUARE_SIZE * (square.file() - 'a');
	    	int yPos = SQUARE_SIZE * ('8' - square.rank());
	    	BufferedImage pieceImage = this.pieceImages.get(pieceMap.get(square));
	    	g2d.drawImage(pieceImage, transformOp, xPos, yPos);
	    }
   
	    return image;
	}
	
	public static void main(String[] args) {
		BufferedImage boardImage = null;
		Map<Piece, BufferedImage> pieceImages = new HashMap<Piece, BufferedImage>();
		
		try {
			//ApplicationContext context = new FileSystemXmlApplicationContext();
			//Resource boardImageResource = context.getResource("d:/Kaleb/java/chess/web_games/war/img/board_small.png");
			ApplicationContext context = new ClassPathXmlApplicationContext();
			Resource boardImageResource = context.getResource("classpath:board_small.png");
			boardImage = ImageIO.read(boardImageResource.getFile());
			//Resource knightBlackImageResource = context.getResource("d:/Kaleb/java/chess/web_games/war/img/knight_black_small.png");
			Resource knightBlackImageResource = context.getResource("classpath:knight_black_small.png");
			BufferedImage knightBlackImage = ImageIO.read(knightBlackImageResource.getFile());
			pieceImages = new HashMap<Piece, BufferedImage>();
			pieceImages.put(new Knight(Color.BLACK), knightBlackImage);
		}
		catch (Exception e) {
			e.printStackTrace();
		}

		GamePreviewImageGenerator generator = new GamePreviewImageGenerator(boardImage, pieceImages);
		Game game = new Game();
		game.initializeBoard();
		//BufferedImage image = generator.createPngPreviewImage(game);
	    //testFileOutput(image);
	}
	
}
