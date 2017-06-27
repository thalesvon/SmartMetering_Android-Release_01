package com.iot.unb.model.service;

import com.android.volley.VolleyError;

/**
 * Created by aclopesjr on 5/9/17.
 */

public class Raise {

    /** Callback interface for delivering parsed responses. */
    public interface Listener<T> {
        /** Called when a response is received. */
        public void onSucces(T response);
    }

    /** Callback interface for delivering error responses. */
    public interface ErrorListener {
        /**
         * Callback method that an error has been occurred with the
         * provided error code and optional user-readable message.
         */
        public void onError(VolleyError error);
    }
}
