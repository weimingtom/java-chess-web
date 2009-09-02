package com.brasee.games.lobby;

import java.awt.image.BufferedImage;
import java.io.OutputStream;
import java.util.Map;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.View;

import com.brasee.chess.Game;

public class GamePreviewView implements View {

	private Game game;
	private GamePreviewImageGenerator imageGenerator;

	public GamePreviewView(Game game, GamePreviewImageGenerator imageGenerator) {
		this.game = game;
		this.imageGenerator = imageGenerator;
	}

	@Override
	public String getContentType() {
		return "image/png";
	}

	@Override
	@SuppressWarnings("unchecked")
	public void render(Map model, HttpServletRequest request, HttpServletResponse response) throws Exception {
		//Expire response
		response.setHeader("Cache-Control", "no-cache");
		response.setDateHeader("Expires", 0);
		response.setHeader("Pragma", "no-cache");
		response.setDateHeader("Max-Age", 0);

		BufferedImage previewImage = imageGenerator.createPngPreviewImage(game);
		OutputStream responseStream = response.getOutputStream();
		ImageIO.write(previewImage, "PNG", responseStream);
	}

}
