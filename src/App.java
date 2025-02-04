import DataAcces.DAO.JugadorDAO;
import DataAcces.DTO.JugadorDTO;
public class App {
    public static void main(String[] args) throws Exception {


        /*StarShipGame StarShipGame = new StarShipGame();
        frame.add(StarShipGame);
        frame.pack();
        StarShipGame.requestFocus();
        frame.setVisible(true); */

        JugadorDAO j = new JugadorDAO();
        System.out.println(j.readBy(1).toString());
        for (JugadorDTO lj : j.readAll()){
            System.out.println(lj.toString());
        }

                // MainForm a = new MainForm();

    }
}