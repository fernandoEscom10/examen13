package com.e.examen_candidato.Collection.DetalleLugar;

import com.e.webapirequest.webApi.dto.RestaurantDTO;

public interface DetalleView {

    void RestaurantSuccess(RestaurantDTO data);

    void onShowLoader();

    void onHideLoader();

    void mostrarMensaje(String mensaje);
}
