package com.instant.api.model.venue;

import com.instant.api.model.AbstractResponse;

/**
 * @author sroshchupkin
 */
public final class VenueResponse extends AbstractResponse<Venue> {

    private VenueResponse(Builder builder) {
        super(builder);
    }

    public static class Builder extends AbstractResponse.Builder<Venue, VenueResponse> {

        @Override
        public VenueResponse build()
        {
            return new VenueResponse(this);
        }
    }
}

