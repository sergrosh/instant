package com.instant.api.model;

import org.apache.commons.lang.Validate;

import java.util.List;

/**
 * @author sroshchupkin
 */
public abstract class AbstractResponse<T extends ItemModel> {

    private long total;
    private List<T> data;


    public final List<T> getData() {
        return data;
    }
    public final long getTotal() {
        return total;
    }

    protected AbstractResponse(Builder builder) {


        if (total > 0) {
            Validate.notEmpty(builder.data);
        }

        Validate.isTrue(total > -1);

        data = builder.data;
        total = builder.total;
    }

    public abstract static class Builder<T extends ItemModel, R extends AbstractResponse<T>> {
        private long total;
        private List<T> data;

        public Builder<T, R> withData(List<T> data) {
            this.data = data;
            return this;
        }

        public Builder<T, R> withTotal(long total) {
            this.total = total;
            return this;
        }

        public abstract R build();
    }
}
