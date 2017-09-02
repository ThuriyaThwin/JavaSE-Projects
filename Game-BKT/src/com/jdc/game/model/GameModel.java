package com.jdc.game.model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import com.jdc.game.model.GameItem.Item;

public class GameModel {

	private static GameModel MODEL;
	
	private List<GameItem> items;
	
	private GameModel() {
		items = Arrays.stream(Item.values())
				.map(GameItem::new)
				.collect(Collectors.toList());
	}
	
	public List<GameItem> getItems() {
		return items;
	}
	
	public GameItem getItemRandom() {
		List<GameItem> list = new ArrayList<>(items);
		Collections.shuffle(list);
		return list.get(0);
	}
	
	public static GameModel getModel() {
		if(null == MODEL) {
			MODEL = new GameModel();
		}
		
		return MODEL;
	}
}
