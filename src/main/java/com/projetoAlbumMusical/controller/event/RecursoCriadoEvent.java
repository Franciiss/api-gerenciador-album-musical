package com.projetoAlbumMusical.controller.event;

import javax.servlet.http.HttpServletResponse;

import org.springframework.context.ApplicationEvent;

public class RecursoCriadoEvent extends ApplicationEvent {


    private final HttpServletResponse response;
    private final Integer id;

    public RecursoCriadoEvent(Object source, HttpServletResponse response, Integer id) {
        super(source);

        this.response = response;
        this.id = id;
    }

    public HttpServletResponse getResponse() {
        return response;
    }

    public Integer getId() {
        return id;
    }
}
