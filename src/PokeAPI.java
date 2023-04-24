import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PokeAPI {
    public static Pokemon consultar(String nome) throws IOException {
    	String nomeMinusculo = nome.toLowerCase();
        URL url = new URL("https://pokeapi.co/api/v2/pokemon/" + nomeMinusculo);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("GET");

        int codigoResposta = conn.getResponseCode();
        if (codigoResposta != 200) {
            throw new RuntimeException("Falha ao conectar com a PokeAPI: código de erro HTTP " + codigoResposta);
        }

        BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
        String linha;
        StringBuilder resposta = new StringBuilder();
        while ((linha = reader.readLine()) != null) {
            resposta.append(linha);
        }
        reader.close();

        String nomePokemon = extrairNomePokemon(resposta.toString());
        int idPokemon = extrairIdPokemon(resposta.toString());
        String[] tiposPokemon = extrairTiposPokemon(resposta.toString());
        String[] habilidadesPokemon = extrairHabilidadesPokemon(resposta.toString());
        int[] estatisticasPokemon = extrairEstatisticasPokemon(resposta.toString());

        return new Pokemon(nomePokemon, idPokemon, tiposPokemon, habilidadesPokemon, estatisticasPokemon);
    }

    private static String extrairNomePokemon(String resposta) {
        int inicio = resposta.indexOf("\"name\":\"") + "\"name\":\"".length();
        int fim = resposta.indexOf("\",", inicio);
        return resposta.substring(inicio, fim);
    }

    private static int extrairIdPokemon(String resposta) {
        int inicio = resposta.indexOf("\"id\":") + "\"id\":".length();
        int fim = resposta.indexOf(",", inicio);
        return Integer.parseInt(resposta.substring(inicio, fim));
    }

    private static String[] extrairTiposPokemon(String resposta) {
        try {
            Pattern pattern = Pattern.compile("\"name\":\"(\\w+)\"");
            Matcher matcher = pattern.matcher(resposta);
            List<String> tiposPokemon = new ArrayList<>();

            while (matcher.find()) {
                tiposPokemon.add(matcher.group(1));
            }

            return tiposPokemon.toArray(new String[tiposPokemon.size()]);
        } catch (Exception e) {
            System.out.println("Ocorreu um erro ao extrair os tipos de Pokémon: " + e.getMessage());
            return null;
        }
    }

    private static String[] extrairHabilidadesPokemon(String resposta) {
        try {
            Pattern pattern = Pattern.compile("\"name\":\"(\\w+)\"");
            Matcher matcher = pattern.matcher(resposta);
            List<String> habilidadesPokemon = new ArrayList<>();

            while (matcher.find()) {
                habilidadesPokemon.add(matcher.group(1));
            }

            return habilidadesPokemon.toArray(new String[habilidadesPokemon.size()]);
        } catch (Exception e) {
            System.out.println("Ocorreu um erro ao extrair as habilidades de Pokémon: " + e.getMessage());
            return null;
        }
    }

    private static int[] extrairEstatisticasPokemon(String resposta) {
        try {
            Pattern pattern = Pattern.compile("\"base_stat\":(\\d+)");
            Matcher matcher = pattern.matcher(resposta);
            List<Integer> estatisticasPokemon = new ArrayList<>();

            while (matcher.find()) {
                estatisticasPokemon.add(Integer.parseInt(matcher.group(1)));
            }

            return estatisticasPokemon.stream().mapToInt(i -> i).toArray();
        } catch (Exception e) {
            System.out.println("Ocorreu um erro ao extrair as estatísticas de Pokémon: " + e.getMessage());
            return null;
        }
    }
}
    
