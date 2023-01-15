import java.util.*;

public class Bibliotheque {
    private TreeMap<Livre,Integer> liste=new TreeMap<>();
    private String fichier;
    public Bibliotheque(String fichier){
        this.fichier=fichier;
    }
    public Bibliotheque(){
        fichier="bibliothèque.bin.";

    }

    public TreeMap<Livre, Integer> getListe() {
        return liste;
    }

    public void setListe(TreeMap<Livre, Integer> liste) {
        this.liste = liste;
    }

    public String getFichier() {
        return fichier;
    }

    public void setFichier(String fichier) {
        this.fichier = fichier;
    }
    public int nombreDeLivres(){
        return liste.size();
    }
    public Livre existe(String ISBN){
        for (Livre l : liste.keySet()){
            if (Objects.equals(l.getIsbn(), ISBN))
                return l;
        }
        return null;
    }
    public void ajouterLivre(Livre l){
        if (l==null || liste.containsKey(l))
            throw new ArithmeticException("Erreur (livre)");
        else{
            if (liste.containsKey(l))
                liste.put(l, liste.get(l)+1);
            else
                liste.put(l,1);
        }
    }
    public void acheterLivre(String ISBN, int quantite){
        Livre l = Livre.db.get(ISBN);
        if (quantite<=0)
            throw new ArithmeticException("quantite negative ou nulle");
        if (l==null)
            throw new ArithmeticException("livre n'existe pas");
        if (liste.containsKey(l))
            liste.put(l, liste.get(l)+quantite);
        else
            liste.put(l,1);
    }
    public void vendreLivre(String ISBN, int quantite){
        Livre l = Livre.db.get(ISBN);
        if (quantite<=0)
            throw new ArithmeticException("quantite negative ou nulle");
        if (l==null)
            throw new ArithmeticException("livre n'existe pas");
        if (quantite>liste.get(Livre.db.get(ISBN)))
            throw new ArithmeticException("quantite insuffisante");
        else {
            if (quantite==liste.get(l))
                liste.remove(l);
            else
                liste.put(l,liste.get(l)-quantite);
        }
    }
    public void supprimer(String ISBN){
        Livre l = Livre.db.get(ISBN);
        if (l==null)
            throw new ArithmeticException("Le livre est null");
        if (!liste.containsKey(l))
            throw new ArithmeticException("Le livre n'existe pas");
        liste.remove(l);
    }
    public void supprimer(Livre l){
        if (l==null)
            throw new ArithmeticException("Le livre est null");
        if (!liste.containsKey(l))
            throw new ArithmeticException("Le livre n'existe pas");
        liste.remove(l);
    }
    public ArrayList<Livre> livres(){
        Dictionnaire d = new Dictionnaire("","",Langue.ARABE);
        Manuel m = new Manuel("","",NiveauScolaire.PRIMAIRE);
        ArrayList<Livre> livres = new ArrayList<>();
        for (Livre l : liste.keySet()){
            if (!(l.getClass()).equals(d.getClass()) && !(l.getClass().equals(m.getClass())))
                livres.add(l);
        }
        return livres;
    }
    public ArrayList<Manuel> manuels(){
        Dictionnaire d = new Dictionnaire("","",Langue.ARABE);
        Livre liv = new Livre("","");
        ArrayList<Manuel> manuels = new ArrayList<>();
        for (Livre l : liste.keySet()){
            if (!(l.getClass()).equals(d.getClass()) && !(l.getClass().equals(liv.getClass()))) {
                Manuel m = (Manuel) l;
                manuels.add(m);
            }
        }
        return manuels;
    }
    public ArrayList<Dictionnaire> dictionnaires(){
        Manuel m = new Manuel("","",NiveauScolaire.PRIMAIRE);
        Livre liv = new Livre("","");
        ArrayList<Dictionnaire> dictionnaires = new ArrayList<>();
        for (Livre l : liste.keySet()){
            if (!(l.getClass()).equals(m.getClass()) && !(l.getClass().equals(liv.getClass()))) {
                Dictionnaire d = (Dictionnaire) l;
                dictionnaires.add(d);
            }
        }
        return dictionnaires;
    }
    public HashSet<Auteur> auteurs(){
        HashSet<Auteur> auteurs = new HashSet<>();
        for (Livre l : liste.keySet()){
            auteurs.addAll(l.getAuteurs());
        }
        return auteurs;
    }

    @Override
    public String toString() {
        String r = "";
        for (Livre l : liste.keySet()){
            r+=l.getTitre()+" : "+liste.get(l)+" ";
        }
        return r;
    }
}
