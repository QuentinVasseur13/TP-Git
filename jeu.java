package modele;

public class jeu {

    private int nbrAleatoire;
    private int nbEssai;
    private int valeurSaisie;

    public jeu() {
        nbEssai = 0;
//(int) (Math.random() * 20) donne un nombre compris entre 1 et 20
        nbrAleatoire = (int) Math.round(Math.random() * 20);
    }

    public void setNbEssai(int nbEssai) {
        this.nbEssai = nbEssai;
    }

    public int getNbEssai() {
        return nbEssai;
    }

    public int getNbrAleatoire() {
        return nbrAleatoire;
    }
    
    public void setValeurSaisie(int valeurSaisie) {
        this.valeurSaisie = valeurSaisie;
    }

    public int getValeurSaisie() {
        return valeurSaisie;
    }
}
