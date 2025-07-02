package application;

import entities.Sales;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class App {
  public static void main(String[] args) {
    Scanner scanner = new Scanner(System.in);
    List<Sales> customerList = new ArrayList<>();

    String path = "\\PENDENTES";
    String invalidPath = "\\INVALIDADO";
    String validPath = "\\VALIDADO";

    System.out.println("┇ Digite apenas o nome do arquivo .csv que deseja ser lido. Ex: compras");
    String fileName = scanner.nextLine() + ".csv";
    String filePath = path + "\\" + fileName;

    try (BufferedReader bufferedReader = new BufferedReader(new FileReader(filePath))) {
      String line = bufferedReader.readLine();
      String[] data = line.split(";");

      if (data.length != 4 || data[0].isEmpty()) {
        bufferedReader.close();
        System.out.println("┇ Não foi possível ler o arquivo ❌.");
        File failed = new File(invalidPath);
        Path sourcePath = Paths.get(filePath);
        Path targetPath = Paths.get(String.valueOf(failed), fileName);
        Files.move(sourcePath, targetPath, StandardCopyOption.REPLACE_EXISTING);
        System.out.println("┇ Arquivo movido para a pasta INVALIDADO 📁.");
        return;
      }

      while (line != null) {
        String[] dataFile = line.split(";");
        line = bufferedReader.readLine();

        try {
          Integer saleNumber = Integer.valueOf(dataFile[0]);
          String clientName = dataFile[1];
          SimpleDateFormat formatDate = new SimpleDateFormat("dd/MM/yyyy");
          Date dateOfSale = formatDate.parse(dataFile[2]);
          Double saleValue = Double.valueOf(dataFile[3].replace(",", "."));

          Sales sales = new Sales(saleNumber, clientName, dateOfSale, saleValue);
          customerList.add(sales);
          line = bufferedReader.readLine();
        } catch (NumberFormatException e) {
          System.out.println("┇ Erro na leitura do campo SaleNumber: " + e.getMessage() + " ❌.");
          Path sourcePath = Path.of(filePath);
          Path targetPath = Path.of(invalidPath, fileName);
          Files.move(sourcePath, targetPath, StandardCopyOption.REPLACE_EXISTING);
          System.out.println("┇ Arquivo movido para a pasta INVALIDO 📁.");
          return;
        } catch (ParseException e) {
          System.out.println("┇ Erro na leitura do campo Date: " + e.getMessage() + " ❌.");
          Path sourcePath = Path.of(filePath);
          Path targetPath = Path.of(invalidPath, fileName);
          Files.move(sourcePath, targetPath, StandardCopyOption.REPLACE_EXISTING);
          System.out.println("┇ Arquivo movido para a pasta INVALIDO 📁.");
          return;
        }
      }
      for (Sales salesData : customerList) {
        System.out.println(salesData);
      }
      bufferedReader.close();
      File success = new File(validPath);
      Path sourcePath = Paths.get(filePath);
      Path targetPath = Paths.get(String.valueOf(success), fileName);
      try {
        Files.move(sourcePath, targetPath, StandardCopyOption.REPLACE_EXISTING);
        System.out.println("┇ Arquivo movido com sucesso ✅.");
      } catch (IOException e) {
        System.out.println("┇ Erro ao mover o arquivo: " + e.getMessage() + " ❌.");
      }
    } catch (IOException e) {
      System.out.println("┇ Erro na leitura do arquivo: " + e.getMessage() + " ❌.");
    } finally {
      scanner.close();
    }
  }
}