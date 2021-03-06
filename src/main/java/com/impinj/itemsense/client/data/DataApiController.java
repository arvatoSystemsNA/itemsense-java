package com.impinj.itemsense.client.data;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.impinj.itemsense.client.data.item.ItemController;
import com.impinj.itemsense.client.data.itemhistory.ItemHistoryController;

import com.impinj.itemsense.client.helpers.ZonedDateTimeSerialization;
import lombok.Data;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.WebTarget;
import java.net.URI;
import java.time.ZonedDateTime;

/**
 * Created by jcombopi on 1/26/16.
 */

@Data
public class DataApiController {

    private ItemController itemController;
    private ItemHistoryController itemHistoryController;
    private WebTarget target;
    private Gson gson;

    public DataApiController(final Client client, final URI uri){
        this(new GsonBuilder().registerTypeAdapter(ZonedDateTime.class, new ZonedDateTimeSerialization()).create(), client, uri);
    }

    public DataApiController(final Gson gson, final Client client, final URI uri) {
        this.gson = gson;
        this.target = client.target(uri);
        this.itemController = new ItemController(gson, target);
        this.itemHistoryController = new ItemHistoryController(gson, target);
    }
}
