package com.jdc.hotel.resources;

import java.util.List;
import java.util.Optional;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.jdc.hotel.model.AbstractModel;

@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public abstract class AbstractResource<T> {

	protected abstract AbstractModel<T> getModel();

	protected abstract String getAllQuery();

	@GET
	public List<T> getAll() {
		return getModel().findByQuery(getAllQuery(), Optional.empty());
	}

	@GET
	@Path("{id}")
	public T findById(@PathParam("id") String id) {
		Object idObj = null;
		try {
			idObj = Long.parseLong(id);
		} catch (Exception e) {
			idObj = id;
		}

		return getModel().findById(idObj);
	}

	@POST
	public Response create(T t) {
		getModel().save(t);
		return Response.ok().build();
	}

	@PUT
	public Response update(T t) {
		getModel().save(t);
		return Response.ok().build();
	}
}
