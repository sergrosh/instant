package com.instant.controller;

/**
 * @author sroshchupkin
 */
public interface Mappings {
    String HOME = "/home";

    //MVC
    String ERROR_PATH = "/error";
    String VENUE_SAVE = "/saveVenue";
    String UPLOAD_VENUE_IMAGE = "/upload/venue/image";
    String DELETE_VENUE_IMAGE = "/delete/venue/image";
    String CLIENTS = "/clients";
    String VENUE_SUGGESTIONS = "/suggestions/venue";
    String CITY_SUGGESTIONS = "/suggestions/city";
    String ITEM = "/item";
    String ITEM_NEW = "/new_item";
    String ITEM_FAVOURITE = ITEM + "/{id}/favourite";

    String ACCOUNT = "/account";
    String USER = "/user";
    String USER_ADD = "/user/add";

    //REST
    String REST="/rest";
    String SEARCH="/search";
    String VENUE = "/venue";


    String FB = "/facebook";
    String FB_FEED = FB + "/feed";
    String FB_FRIENDS = FB + "/friends";
    String FB_ALBUMS = FB + "/albums";
    String FB_ALBUM = FB + "/album/{albumId}";
    String FB_EXPIRED = FB + "/expired";

}
