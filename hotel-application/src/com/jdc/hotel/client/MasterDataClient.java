package com.jdc.hotel.client;

import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import javax.ws.rs.core.GenericType;

import com.jdc.hotel.model.MasterData;
import com.jdc.hotel.model.MasterData.Table;

public class MasterDataClient extends AbstractCacheClass<MasterData> {
	
	private static MasterDataClient client;
	public static MasterDataClient getClient() {
		
		if(null == client) {
			client = new MasterDataClient();
		}
		return client;
	}

	MasterDataClient() {
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

	@Override
	protected String getBaseUrl() {
		return String.format("%s%s", BASE_URL, "MasterData");
	}

	@Override
	protected GenericType<List<MasterData>> getListDataType() {
		return new GenericType<List<MasterData>>() {};
	}
	
}
