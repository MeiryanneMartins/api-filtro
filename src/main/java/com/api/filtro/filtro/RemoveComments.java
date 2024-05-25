package com.api.filtro.filtro;

import java.io.*;
import java.nio.file.*;

public class RemoveComments {

  public static void removerComentarios(String diretorio) throws IOException {
    Files.walk(Paths.get(diretorio))
        .filter(Files::isRegularFile)
        .filter(path -> path.toString().endsWith(".java"))
        .forEach(path -> {
          try {
            removerComentariosDeArquivo(path);
          } catch (IOException e) {
            e.printStackTrace();
          }
        });
  }

  public static void removerComentariosDeArquivo(Path path) throws IOException {
    BufferedReader reader = new BufferedReader(new FileReader(path.toFile()));
    StringBuilder builder = new StringBuilder();
    String line;
    while ((line = reader.readLine()) != null) {
      if (!line.trim().startsWith("//")) {
        builder.append(line).append(System.getProperty("line.separator"));
      }
    }
    reader.close();

    BufferedWriter writer = new BufferedWriter(new FileWriter(path.toFile()));
    writer.write(builder.toString());
    writer.close();
  }
}
