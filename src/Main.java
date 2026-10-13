import dao.GanhoDAO;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import model.Ganho;
public class Main {
    public static void main(String[] args) {
Ganho ganho = new Ganho(1, 100.0, "Ifood", LocalDate.now());
        GanhoDAO ganhoDAO = new GanhoDAO();
        try {
            ganhoDAO.salvar(ganho);
            System.out.println("Ganho salvo com sucesso!");
        } catch (SQLException e) {
            System.err.println("Erro ao salvar o ganho: " + e.getMessage());
        }

    Ganho ganho2 = new Ganho(2, 200.0, "99", LocalDate.now());
        GanhoDAO ganhoDAO2 = new GanhoDAO();
        try {
            ganhoDAO2.salvar(ganho2);
            System.out.println("Ganho salvo com sucesso!" );
            ArrayList<Ganho> ganhos = ganhoDAO2.listGanhos();
            for (Ganho g : ganhos) {
                System.out.println("ID: " + g.getId() + ", Valor: " + g.getValor() + ", Data: " + g.getCadastrado_Em() + ", Plataforma: " + g.getPlataforma());
            }
        } catch (SQLException e) {
            System.err.println("Erro ao salvar o ganho: " + e.getMessage());
        }
        }
}
