import java.util.Scanner;

public class Program {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Digite o nome do Pokemon: ");
        String nomePokemon = scanner.nextLine();

        try {
            Pokemon pokemon = PokeAPI.consultar(nomePokemon);
            String nome = pokemon.getNome();
            int[] estatisticas = pokemon.getEstatisticas();
            String[] habilidades = pokemon.getHabilidades();

            System.out.println("Nome: " + nome);
            System.out.println("Velocidade: " + estatisticas[0]);
            System.out.println("Defesa especial: " + estatisticas[1]);
            System.out.println("Ataque especial: " + estatisticas[2]);
            System.out.println("Defesa: " + estatisticas[3]);
            System.out.println("Ataque: " + estatisticas[4]);
            System.out.println("HP: " + estatisticas[5]);

        } catch (Exception e) {
            System.out.println("Erro ao consultar o Pokemon. Verifique se o nome foi digitado corretamente.");
        }
    }
}
