
package controleur;

import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import modele.jeu;

@WebServlet("/control")
public class control extends HttpServlet {

    private static final long serialVersionUID = 1L;

    public control() {
        super();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //Activation ou r􀳦cup􀳦ration de la session
        HttpSession session = request.getSession();
        String renvoi = "/jouer_mvc.jsp";
        jeu Jeu_En_Cours;
        try {
// on regarde s'il existe d􀳦j􀳦 un Jeu en cours
            Jeu_En_Cours = (jeu) session.getAttribute("LeJeu");
            if (Jeu_En_Cours == null) {
// on vient de la page acceuil donc pas de jeu en cours
// on cree un nouveau jeu et on envoie vers la saisie
                Jeu_En_Cours = new jeu();
                session.setAttribute("LeJeu", Jeu_En_Cours);
                session.setAttribute("msg", "");
            } else {
                int nb = Integer.parseInt(request.getParameter("nb"));
                if (nb < 1 || nb > 20) {
                    session.setAttribute("msg", "Veuillez saisir un nb entre 1 et 20");
                } else {
                    Jeu_En_Cours.setNbEssai(Jeu_En_Cours.getNbEssai() + 1);
                    session.setAttribute("LeJeu", Jeu_En_Cours);
                    if (Jeu_En_Cours.getNbrAleatoire() == nb) // nombre trouv􀳦
                    {
                        session.setAttribute("msg", "Bravo, Trouv􀳦");
                        renvoi = "/fin.jsp";
                    } else // nombre non trouv􀳦
                    { // on regarde s'il reste encore des essais
                        if (Jeu_En_Cours.getNbEssai() >= 3) {
                            session.setAttribute("msg", "Desol􀳦, perdu, il fallait trouver " + Jeu_En_Cours.getNbrAleatoire());
                            renvoi = "/fin.jsp";
                        } else // on cree le msg d'indication - trop grand ou petit
                        {
                            if (nb > Jeu_En_Cours.getNbrAleatoire()) {
                                session.setAttribute("msg", nb + " est trop grand !");
                            } else {
                                session.setAttribute("msg", nb + " est trop petit !");
                            }
                        }
                    }
                }
            }
        } catch (Exception e) {
            session.setAttribute("msg", "Veuillez saisir un nb ...");
        }
        request.getRequestDispatcher(renvoi).forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
}
