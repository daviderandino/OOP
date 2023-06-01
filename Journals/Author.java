package journals;

import java.util.LinkedList;
import java.util.List;

public class Author {

    private String name;
    private List<Paper> papers;

    public Author(String name) {
        this.name = name;
        papers = new LinkedList<>();
    }

    public String getName() {
        return name;
    }

    public void addPaper(Paper paper){
        papers.add(paper);
    }

    public int numPapers(){
        return papers.size();
    }

    public List<Paper> getPapers() {
        return papers;
    }

    public double getImpactFactor(){
        double k = 0.0;

        for(Paper paper:this.getPapers()){
            Journal journal = paper.getJournal();
            k += journal.getImpactFactor();
        }
        return k;
    }
}
