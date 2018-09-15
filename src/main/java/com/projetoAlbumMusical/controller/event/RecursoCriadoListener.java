package com.projetoAlbumMusical.controller.event;

import java.net.URI;

import javax.servlet.http.HttpServletResponse;

import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@Component
public class RecursoCriadoListener implements ApplicationListener<RecursoCriadoEvent> {


    @Override
    public void onApplicationEvent(RecursoCriadoEvent eventoRecursoCriado) {
       this.adcionaHeaderLocation( eventoRecursoCriado.getResponse(), eventoRecursoCriado.getId() );
    }

    private void adcionaHeaderLocation(HttpServletResponse response, Integer id) {

        URI uri = ServletUriComponentsBuilder.fromCurrentRequestUri()
                .path("/{id}")
                .buildAndExpand(id)
                .toUri();

        response.setHeader("Location", uri.toString() );
    }
}
