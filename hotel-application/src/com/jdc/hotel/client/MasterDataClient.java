package com.jdc.hotel.client;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.MediaType;

import com.jdc.hotel.model.MasterData;
import com.jdc.hotel.model.MasterData.Table;

public class MasterDataClient {
	
	private static MasterDataClient client;
	
	private List<MasterData> cache;
	
	public static MasterDataClient getClient() {
		
		if(null == client) {
			client = new MasterDataClient();
		}
		return client;
	}
	
	public List<MasterData> getAll() {
		return new ArrayList<>(cache);
	}
	
	public void create(MasterData master) {
		try {
			
			Client client = ClientBuilder.newClient();
			// read entity from response
			client.target("http://localhost:8080/smart-hotel-svc/api/MasterData")
				.request()
				.post(Entity.entity(master, MediaType.APPLICATION_JSON));
			
			client.close();

			init();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void update(MasterData master) {
		try {
			
			Client client = ClientBuilder.newClient();
			// read entity from response
			client.target("http://localhost:8080/smart-hotel-svc/api/MasterData")
				.request()
				.put(Entity.entity(master, MediaType.APPLICATION_JSON));
			
			client.close();

			init();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	

	private MasterDataClient() {
	}
	
	public void init() {
		try {
			
			Client client = ClientBuilder.newClient();
			
			// read entity from response
			cache = client.target("http://localhost:8080/smart-hotel-svc/api/MasterData")
					.request(MediaType.APPLICATION_JSON)
					.get()
					.readEntity(new GenericType<List<MasterData>>() {});
			
			client.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	

	public List<MasterData> search(Table value, boolean valid) {
		Predicate<MasterData> pred = a -> true;
		
		if(null != value) {
			pred = pred.and(a -> a.getTblName().equals(value.toString()));
		}
		
		if(valid) {
			pred = pred.and(a -> !a.getSecurity().isDelFlag());
		}
		
		return cache.stream().filter(pred).collect(Collectors.toList());
	}
	
}
