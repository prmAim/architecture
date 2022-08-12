package ru.geekbrains;

import ru.geekbrains.domain.HttpResponce;

public class ResponceSerializer {

    /**
     * Объединение в ответ
     */
    public String serialaze(HttpResponce httpResponce){
        return httpResponce.getStatusHttp() + httpResponce.getAllHeaders() + "\n" + httpResponce.getBody();
    }
}
