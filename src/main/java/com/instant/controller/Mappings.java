package com.instant.controller;

/**
 * @author sroshchupkin
 */
public interface Mappings {
    String HOME = "/home";
    String ERROR_PATH = "/error";
    String VENUE_SAVE = "/saveVenue";
    String UPLOAD_VENUE_IMAGE = "/upload/venue/image";
    String DELETE_VENUE_IMAGE = "/delete/venue/image";
    String CLIENTS = "/clients";
    String REST_CLIENTS = "/rest/clients";
    String VENUE_SUGGESTIONS = "/suggestions/venue";
    String CITY_SUGGESTIONS = "/suggestions/city";
    String REST_SUGGESTIONS = "/rest/suggestions";

    String ITEM = "/item";
    String ITEM_NEW = "/new_item";
    String ITEM_FAVOURITE = ITEM + "/{id}/favourite";

    String REST_VENUE = "/rest/venue";
    String REST_IMAGE = "/rest/image";
    String ACCOUNT = "/account";
    String USER = "/user";
    String USER_ADD = "/user/add";

    String FB = "/facebook";
    String FB_FEED = FB + "/feed";
    String FB_FRIENDS = FB + "/friends";
    String FB_ALBUMS = FB + "/albums";
    String FB_ALBUM = FB + "/album/{albumId}";
    String FB_EXPIRED = FB + "/expired";

}
