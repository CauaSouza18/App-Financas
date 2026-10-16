
import dao.GanhoDAO;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;
import model.Ganho;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        GanhoDAO ganhoDAO = new GanhoDAO();
        int opcao;
        do {
            System.out.println("Escolha uma opção:");
            System.out.println("1. Listar ganhos");
            System.out.println("2. Adicionar ganho");
            System.out.println("3. Sair");
            System.err.println("4. Deletar ganho");
            System.out.println("5. Atualizar ganho");
            System.out.println("6. Filtrar por data");
            System.out.println("7. Total por plataforma");
            opcao = scanner.nextInt();
            scanner.nextLine(); // Limpar o buffer

            switch (opcao) {

                case 1:
                    try {
                        ArrayList<Ganho> ganhos = ganhoDAO.listGanhos();
                        for (Ganho ganho : ganhos) {
                            System.out.println("ID: " + ganho.getId() + ", Valor: " + ganho.getValor() + ", Plataforma: " + ganho.getPlataforma() + ", Cadastrado Em: " + ganho.getData());
                        }
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                    break;
                case 2:
                    System.out.print("Digite o valor do ganho: ");
                    double valor = scanner.nextDouble();
                    scanner.nextLine(); // Limpar o buffer
                    System.out.print("Digite a plataforma do ganho: ");
                    String plataforma = scanner.nextLine();
                    Ganho novoGanho = new Ganho(0, valor, plataforma, LocalDate.now());
                    try {
                        ganhoDAO.salvar(novoGanho);
                        System.out.println("Novo ganho salvo com sucesso!");
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                    break;
                case 3:
                    System.out.println("saindo");

                    break;
                case 4:
                    try {
                        System.out.println("Informe o ID que deseja deletar:");
                        int id = scanner.nextInt();
                        scanner.nextLine();
                        boolean apagou = ganhoDAO.deletar(id);
                        if (apagou) {
                            System.out.println("Ganho apagado");
                        } else {
                            System.out.println("ID não encontrado");
                        }

                    } catch (SQLException e) {
                        e.printStackTrace();
                    }

                    break;
                case 5:
                    System.out.println("Qual o ID que deseja atualizar?");
                    int id = scanner.nextInt();
                    scanner.nextLine();
                    System.out.println("Qual o valor novo");
                    double novoValor = scanner.nextDouble();
                    scanner.nextLine();
                    System.out.println("Qual a plataforma?");
                    String novaPlataforma = scanner.nextLine();
                    Ganho novGanho = new Ganho(id, novoValor, novaPlataforma, null);

                    try {
                        boolean atualizou = ganhoDAO.atualizar(novGanho);
                        if (atualizou) {
                            System.out.println("Ganho atualizado");
                        } else {
                            System.out.println("ID não encontrado");
                        }

                    } catch (SQLException e) {
                        e.printStackTrace();
                    }

                    break;
                case 6:
                    System.out.println("Qual a data de inicio");
                    LocalDate inicio = LocalDate.parse(scanner.nextLine());
                    System.out.println("Qual a data do fim?");
                    LocalDate fim = LocalDate.parse(scanner.nextLine());
                    try {
                        ArrayList<Ganho> ganhos
                                = ganhoDAO.listarPorPeriodo(inicio, fim);
                        for (Ganho ganho : ganhos) {
                            System.out.println("ID: " + ganho.getId()
                                    + ", Valor: " + ganho.getValor()
                                    + ", Plataforma: " + ganho.getPlataforma()
                                    + ", Data: " + ganho.getData());
                        }
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }

                    break;
                    case 7 :
                        System.out.println("Digite a plataforma");
                        String plataformaBusca = scanner.nextLine();
                        try {
                            double totalPlataforma = ganhoDAO.totalPorPlataforma(plataformaBusca);
                            System.out.println("Total da " + plataformaBusca + ":" + totalPlataforma);

                        }
                        catch(SQLException e) {
                            e.printStackTrace();
                            
                        }
                     break; 
                default:
                    System.out.println("Opção inválida.");
            }
        } while (opcao != 3);

    }
}
