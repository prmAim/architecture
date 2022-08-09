package ru.geekbrains;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.nio.charset.StandardCharsets;

public class InOutStreamsImpl implements InOutStreams {
  private final BufferedReader input;
  private final PrintWriter output;

  public InOutStreamsImpl(Socket socket) throws IOException {
    this.input = new BufferedReader(new InputStreamReader(socket.getInputStream(), StandardCharsets.UTF_8));
    this.output = new PrintWriter(socket.getOutputStream());
  }

  @Override
  public boolean getStatus() throws IOException {
    return input.ready();
  }

  @Override
  public String inputStreamReadLine() throws IOException {
    return input.readLine();
  }

  @Override
  public void sendResponse(String httpMessage) throws IOException{
    output.print(httpMessage);
    output.flush();
  }

  public PrintWriter getOutput() throws IOException{
    return output;
  }
}
