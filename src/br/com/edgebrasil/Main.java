package br.com.edgebrasil;

import br.com.edgebrasil.br.com.edgebrasil.model.Webhooks;
import br.com.edgebrasil.br.com.edgebrasil.util.Converter;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Collectors;
/**
 * Created by DIEGO on 17/05/2017.
 * ProjectName TesteMoip.
 */
public class Main {

    public static void main(String[] args) {
        // Cria lista de objetos
        List<Webhooks> lista = new ArrayList<Webhooks>();
        // Cria variavel converter para inicializar o conversor criado para transforma a string em um objeto Webhook
        Converter converter = new Converter();

        try {
            /* Lê arquivo log.txt filtrando o que as linhas que iniciam com texto 'level' transforma a linha retornada
            * em um objeto Webhook
            */
            System.out.println("---------------------------------------");
            System.out.println("3 urls mais chamadas com a quantidade");
            System.out.println("---------------------------------------");

            Files.lines(Paths.get("log.txt"))
                    .filter(s -> s.startsWith("level"))
                    .forEach((string) -> {
                        lista.add(converter.getWebhooksFromString(string));
                    });

            Map<String, Long> chamadaMap = new LinkedHashMap<>();
            /* Java 8 resolvendo rsrs.
            * 1) Transforma a lista em uma interface do tipo stream
            * 2) Utilizei o collect para transformar o stream novamente em uma coleção para poder utilizar os metodos de uma collectio neste caso o groupby
            * 3) Utilizei o entrySet para transformar o resultado em mapa e conseguir interar sobre ele.
            * 4) Utilizei o sorted com a opção reversed para pegar de forma decrescente comparando por valor(comparingByValue)
            * 5) Utilizei o forEachOrdered para incluir os itens no map de forma ordenada.
            * */
            lista.stream()
                .collect(Collectors.groupingBy(p -> p.getRequestTo(),Collectors.counting()))
                .entrySet().stream()
                .sorted(Map.Entry.<String, Long>comparingByValue().reversed())
                .forEachOrdered(e -> chamadaMap.put(e.getKey(), e.getValue()));
            // Exibe na tela os 3 primeiros limit(3)
            chamadaMap.entrySet().stream().limit(3).forEach(a ->  System.out.println(a.getKey() + " - " + a.getValue()));

            //Muito parecido com a descrição acima basicamente alterando para utilizar o status code
            System.out.println("---------------------------------------");
            System.out.println("Quantidade de webhooks por status");
            System.out.println("---------------------------------------");
            Map<String, Long> statusMap = new LinkedHashMap<>();
            lista.stream()
                .collect(Collectors.groupingBy(p -> p.getCodeStatus()+"",Collectors.counting()))
                .entrySet().stream()
                .sorted(Map.Entry.<String, Long>comparingByValue().reversed())
                .forEachOrdered(e -> System.out.println(e.getKey() +" - "+ e.getValue()));

        /*Tratamento de erros de arquivo não encontrado
        * erro na leitura do arquivo
        * Demais erros.
        * */
        }catch (FileNotFoundException f){
            System.out.println("Arquivo log.txt não encotrado - " + f.toString());
        }catch (IOException i){
            System.out.println("Ocorreu um erro na leitura do arquivo log.ext." + i.toString());
        }catch (Exception e){
            System.out.println("Ocorreu um erro contate o adminsitrador do sistema." + e.toString());
        }
    }
}