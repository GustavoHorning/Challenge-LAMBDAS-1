package desafio3.application;

import desafio3.entities.Sale;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

public class Program {
    public static void main(String[] args) {
        Locale.setDefault(Locale.US);
        Scanner sc = new Scanner(System.in);

        System.out.print("Entre o caminho do arquivo:");
        String path = sc.nextLine();

        try (BufferedReader br = new BufferedReader(new FileReader(path))) {
            List<Sale> list = new ArrayList<>();
            String line = br.readLine();
            while (line != null) {
                String[] fields = line.split(",");
                list.add(new Sale(Integer.parseInt(fields[0]),Integer.parseInt(fields[1]),fields[2]
                        ,Integer.parseInt(fields[3]),Double.parseDouble(fields[4])));
                line = br.readLine();
            }
            System.out.println();
            System.out.println("Cinco primeiras vendas de 2016 de maior preço médio: ");
            list.stream()
                    .filter(s -> s.getYear() == 2016)
                    .sorted(Comparator.comparing(Sale::averagePrice).reversed())
                    .limit(5)
                    .forEach(System.out::println);

            System.out.println();
            double sum = list.stream()
                    .filter(s -> s.getSeller().equalsIgnoreCase("Logan")
                            && (s.getMonth() == 1 || s.getMonth() == 7))
                    .mapToDouble(Sale::getTotal).sum();
            System.out.printf("Valor total vendido pelo vendedor Logan nos meses 1 e 7 = %.2f\n",sum);
        }
        catch (IOException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}
