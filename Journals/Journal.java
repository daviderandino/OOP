package journals;

import java.util.LinkedList;
import java.util.List;

public class Journal {

    private String name;
    private double impactFactor;
    private List<Paper> papers;

    public Journal(String name, double impactFactor) {
        this.name = name;
        this.impactFactor = impactFactor;
        papers = new LinkedList<>();
    }

    public String getName() {
        return name;
    }

    public double getImpactFactor() {
        return impactFactor;
    }

    public void addPaper(Paper paper) {
        papers.add(paper);
    }

    public int getNumPapers(){
        return papers.size();
    }

}
