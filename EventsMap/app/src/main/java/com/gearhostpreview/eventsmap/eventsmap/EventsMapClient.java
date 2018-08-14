package com.gearhostpreview.eventsmap.eventsmap;

import com.loopj.android.http.*;

public class EventsMapClient {

    private static final String BASE_URL_SEARCH = "https://www.eventbriteapi.com/v3/events/search/?";

    private static AsyncHttpClient client = new AsyncHttpClient();

    public static void get(String url, RequestParams params, AsyncHttpResponseHandler responseHandler) {
        client.get(getAbsoluteUrl(url), params, responseHandler);
    }

    public static void post(String url, RequestParams params, AsyncHttpResponseHandler responseHandler) {
        client.post(getAbsoluteUrl(url), params, responseHandler);
    }

    private static String getAbsoluteUrl(String relativeUrl) {
        return BASE_URL_SEARCH + relativeUrl;
    }
}
