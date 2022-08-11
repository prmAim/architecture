package ru.geekbrains;

import java.io.*;
import java.net.Socket;
import java.nio.charset.StandardCharsets;
import java.util.Deque;
import java.util.LinkedList;

public class SocketService implements Closeable {

    private final Socket socket;

    public SocketService(Socket socket) {
        this.socket = socket;
    }

    /**
     * Чтение данных запроса от клиента
     */
    public Deque<String> readRequest() {
        try {
            // открываем поток
            BufferedReader input = new BufferedReader(new InputStreamReader(socket.getInputStream(), StandardCharsets.UTF_8));

            // Ожидаем пока не придут данные из сокета
            while (!input.ready());

            Deque<String> responce = new LinkedList<>();

            // Ожидаем пока не придут данные из сокета
            while(input.ready()){
                String line = input.readLine();
                System.out.println("LOG: " + line);
                responce.add(line);
            }
            return responce;
        } catch (IOException ex) {
            throw new IllegalStateException(ex);
        }
    }

    /**
     * Отправка ответа клиенту
     */
    public void writeResponse(String rawResponce) {
        try {
            PrintWriter output = new PrintWriter(socket.getOutputStream());
            output.print(rawResponce);
            output.flush();
        } catch (IOException ex) {
            throw new IllegalStateException(ex);
        }
    }

    @Override
    public void close() throws IOException {
        if (!socket.isClosed() || socket != null){
            socket.close();
            System.out.println("LOG: Socket close!");
        }
    }
}
