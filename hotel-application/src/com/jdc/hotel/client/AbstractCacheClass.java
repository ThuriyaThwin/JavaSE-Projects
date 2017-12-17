package com.jdc.hotel.client;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.MediaType;

public abstract class AbstractCacheClass<T> {

	protected List<T> cache;
	protected static final String BASE_URL = "http://localhost:8080/smart-hotel-svc/api/";

	protected abstract String getBaseUrl();
	
	protected abstract GenericType<List<T>> getListDataType();
	
	public AbstractCacheClass() {
		super();
	}

	public List<T> getAll() {
		return new ArrayList<>(cache);
	}

	public void create(T master) {
		try {
			
			Client client = ClientBuilder.newClient();
			// read entity from response
			client.target(getBaseUrl())
				.request()
				.post(Entity.entity(master, MediaType.APPLICATION_JSON));
			
			client.close();
	
			init();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void update(T master) {
		try {
			
			Client client = ClientBuilder.newClient();
			// read entity from response
			client.target(getBaseUrl())
				.request()
				.put(Entity.entity(master, MediaType.APPLICATION_JSON));
			
			client.close();
	
			init();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void init() {
		try {
			
			Client client = ClientBuilder.newClient();
			
			// read entity from response
			cache = client.target(getBaseUrl())
					.request(MediaType.APPLICATION_JSON)
					.get()
					.readEntity(getListDataType());
			
			client.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

}