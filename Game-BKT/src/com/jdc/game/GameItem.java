package com.jdc.game;

import java.io.File;

public class GameItem implements Comparable<GameItem> {
	
	private Item item;
	
	public GameItem(Item item) {
		super();
		this.item = item;
	}
	
	public String getName() {
		return item.toString();
	}
	
	public String getImage() {
		return item.getImage();
	}

	@Override
	public int compareTo(GameItem o) {
		
		if(item == o.item) {
			return 0;
		} else if ((item == Item.Captain && o.item == Item.Gun) || 
				(item == Item.Gun && o.item == Item.Tiger) ||
				(item == Item.Tiger && o.item == Item.Captain)) {
			return 1;
		} 
		return -1;
	}
	
	public enum Item {
		Captain("captain.png", "Captain.wav"), 
		Tiger("tiger.jpg", "Tiger.mp3"), 
		Gun("gun.png", "Gun.mp3");
		
		private String image;
		private String voice;

		private Item(String image, String voice) {
			this.image = image;
			this.voice = new File(voice).toURI().toString();
		}
		
		public String getVoice() {
			return voice;
		}
		
		public String getImage() {
			return image;
		}
	}
	
	public String getVoice() {
		return item.getVoice();
	}
}
