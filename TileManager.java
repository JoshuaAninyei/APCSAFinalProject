package com.FirstJavaProject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import javax.imageio.ImageIO;
import java.awt.*;

public class TileManager {

	GamePanel gp;
	Tile[] tile;
	public int mapTileNum[][];

	public TileManager(GamePanel gp) {
		this.gp = gp;
		tile = new Tile[10];
		mapTileNum = new int[gp.maxWorldCol][gp.maxWorldRow];

		getTileImage();
		loadMap("GameMap.txt");
	}

	public void getTileImage() {
		try {
			tile[0] = new Tile();
			tile[0].image = ImageIO.read(getClass().getResourceAsStream("grass.png"));

			tile[1] = new Tile();
			tile[1].image = ImageIO.read(getClass().getResourceAsStream("wall.png"));
			tile[1].collision = true;
			
			tile[2] = new Tile();
			tile[2].image = ImageIO.read(getClass().getResourceAsStream("water.png"));
			tile[2].collision = true;
			
			tile[3] = new Tile();
			tile[3].image = ImageIO.read(getClass().getResourceAsStream("earth.png"));

			tile[4] = new Tile();
			tile[4].image = ImageIO.read(getClass().getResourceAsStream("tree.png"));
			tile[4].collision = true;
			
			tile[5] = new Tile();
			tile[5].image = ImageIO.read(getClass().getResourceAsStream("sand.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void loadMap(String filePath) {
		try {
			InputStream is = getClass().getResourceAsStream(filePath);
			BufferedReader br = new BufferedReader(new InputStreamReader(is));
/*
			int col = 0;
			int row = 0;

			while (col < gp.maxWorldCol && row < gp.maxWorldRow) {
				String line = br.readLine();

				/while (col < gp.maxWorldCol) {
					String numbers[] = line.split(" ");

					int num = Integer.parseInt(numbers[col]);

					mapTileNum[col][row] = num;
					col++;
				}
				if (col == gp.maxWorldCol) {
					col = 0;
					row++;
				}
			}
			for(int arr[] : mapTileNum) {
				for(int val : arr) {
					System.out.print(val + " ");
				}
				System.out.println();
			}
		
			for(int row = 0; row <= gp.maxWorldRow; row++) {
				for(int col = 0; col <= gp.maxWorldCol , col++ ) {
					int num = Integer.parseInt(number[row]);
					mapTileNm[row][col];
				}
			}
*/				
				
				
			for(int row = 0; row < gp.maxWorldRow; row++) {
				String line = br.readLine();
//System.out.print(line);
String numbers[] = line.split(" ");
				for(int col = 0; col < gp.maxWorldCol ;col++ ) {
					int num = Integer.parseInt(numbers[col]);
				mapTileNum[row][col] = num;
					System.out.print(numbers[col]);
					
					
				}
				System.out.println();
			}
			/*
			for(int arr[] : mapTileNum) {
				for(int val : arr) {
					System.out.print(val + " ");
				}
				System.out.println();
			}*/
			br.close();
		} catch (Exception e) {

		}
	}

	public void draw(Graphics2D g2) {

		
		int y = 0;
		
		for(int row = 0; row < 50; row++) {
			
			for(int col = 0; col < 50; col++) {
				int worldCol = col;
				int worldRow = row;

				int worldX = worldCol * gp.tileSize;
				int worldY = worldRow * gp.tileSize;
				double screenX = worldX - gp.player.worldX + gp.player.screenX;
				double  screenY = worldY - gp.player.worldY + gp.player.screenY;
				int tileNum = mapTileNum[row][col];
				int x = 0;
				
				if (worldX + gp.tileSize > gp.player.worldX - gp.player.screenX && worldX - gp.tileSize < gp.player.worldX + gp.player.screenX
						&& worldY + gp.tileSize > gp.player.worldY - gp.player.screenY && worldY - gp.tileSize < gp.player.worldY + gp.player.screenY) {
					g2.drawImage(tile[tileNum].image,(int) screenX,(int) screenY, gp.tileSize, gp.tileSize, null);
				}
				
				
			

			
			}
			
			y-=45;
		}
/*
		while (worldCol < gp.maxScreenCol && worldRow < gp.maxScreenRow) {

			int tileNum = mapTileNum[worldCol][worldRow];

			int worldX = worldCol * gp.tileSize;
			int worldY = worldRow * gp.tileSize;
			int screenX = worldX - gp.player.worldX + gp.player.screenX;
			int screenY = worldY - gp.player.worldY + gp.player.screenY;
			if (worldX + gp.tileSize > gp.player.worldX - gp.player.screenX && worldX - gp.tileSize < gp.player.worldX + gp.player.screenX
					&& worldY + gp.tileSize > gp.player.worldY - gp.player.screenY && worldY - gp.tileSize < gp.player.worldY + gp.player.screenY) {
				g2.drawImage(tile[tileNum].image, screenX, screenY, gp.tileSize, gp.tileSize, null);
			}

			worldCol++;

			if (worldCol == gp.maxWorldCol) {
				worldCol = 0;
				worldRow++;

			}
		}*/
		
		
		 

	} 
}
