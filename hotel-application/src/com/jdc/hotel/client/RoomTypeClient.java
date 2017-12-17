package com.jdc.hotel.client;

import java.util.List;
import java.util.stream.Collectors;

import javax.ws.rs.core.GenericType;

import com.jdc.hotel.model.RoomType;

public class RoomTypeClient extends AbstractCacheClass<RoomType> {

	private static RoomTypeClient client;
	
	public static RoomTypeClient getClient() {
		
		if(null == client) {
			client = new RoomTypeClient();
		}
		return client;
	}

	@Override
	protected String getBaseUrl() {
		return String.format("%s%s", BASE_URL, "RoomType");
	}

	public List<RoomType> search(boolean selected) {
		return cache.stream().filter(a -> a.getSecurity().isDelFlag() != selected)
				.collect(Collectors.toList());
	}

	@Override
	protected GenericType<List<RoomType>> getListDataType() {
		return new GenericType<List<RoomType>>() {};
	}

}
