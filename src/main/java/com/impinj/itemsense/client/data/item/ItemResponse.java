package com.impinj.itemsense.client.data.item;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.ZonedDateTime;
/**
 * Created by jcombopi on 1/26/16.
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ItemResponse {

    @JsonProperty("items")
    private Item[] items;

    @JsonProperty("nextPageMarker")
    private String nextPageMarker;
    
    @JsonIgnore
    private ZonedDateTime time;
}

