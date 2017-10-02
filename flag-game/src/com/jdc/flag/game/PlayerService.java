package com.jdc.flag.game;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class PlayerService {

	private static PlayerService service;
	private static final String FILE = "player.obj";
	
	public static PlayerService getService() {
		if(null == service) {
			service = new PlayerService();
		}
		return service;
	}
	
	private List<Player> players;
	
	@SuppressWarnings("unchecked")
	private PlayerService() {
		try(ObjectInputStream in = new ObjectInputStream(new FileInputStream(FILE))) {
			players = (List<Player>) in.readObject();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		
		if(null == players) {
			players = new ArrayList<>();
		}
	}
	
	public Player login(String player, String pass) {
		
		Player old = find(player);
		
		if(null == old) {
			throw new GameException("Player check player name!");
		}
		
		if(!old.getPassword().equals(pass)) {
			throw new GameException("Player check password!");
		}
		
		return old;
	}
	
	public void signUp(Player player) {
		if(null != find(player.getPlayer())) {
			throw new GameException("Player Name has already been used!");
		}
		
		if(null == player.getPlayer() || player.getPlayer().isEmpty()) {
			throw new GameException("Please enter Player Name!");
		}
		
		if(null == player.getPassword() || player.getPassword().isEmpty()) {
			throw new GameException("Please enter Password!");
		}

		players.add(player);
		save();
	}
	
	public void save() {
		try(ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(FILE))) {
			out.writeObject(players);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public List<Player> getTopPlayers() {
		return players.stream().sorted().limit(5).collect(Collectors.toList());
	}

	private Player find(String player) {
		return players.stream().filter(a -> a.getPlayer().equals(player))
				.findAny().orElse(null);
	}
	
}
