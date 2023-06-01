package journals;

import java.util.*;

public class Journals {

    //R1
    /**
     * inserts a new journal with name and impact factor.
     *
     * @param name	name of the journal
     * @param impactFactor relative impact factor
     * @return  the number of characters of the name
     * @throws JException if the journal (represented by its name) already exists
     */

    TreeMap<String,Journal> journals = new TreeMap<>();

    public int addJournal (String name, double impactFactor) throws JException {
        if(journals.containsKey(name)) throw new JException("Journal already exists");
        Journal journal = new Journal(name,impactFactor);
        journals.put(name,journal);
        return name.length();
    }

    /**
     * retrieves the impact factor of the journal indicated by the name
     *
     * @param name the journal name
     * @return the journal IF
     * @throws JException if the journal does not exist
     */
    public double getImpactFactor (String name) throws JException {
        if(!journals.containsKey(name)) throw new JException("Invalid journal name");
        return journals.get(name).getImpactFactor();
    }

    /**
     * groups journal names by increasing impact factors.
     * Journal names are listed in alphabetical order
     *
     * @return the map of IF to journal
     */
    public SortedMap<Double, List<String>> groupJournalsByImpactFactor () {

       // TreeMap mantiene l'ordine naturale per le chiavi :D
       TreeMap<Double,List<String>>  groupedJournals = new TreeMap<>();

       for(Journal j:journals.values()){
           Double d = j.getImpactFactor();
           if(groupedJournals.containsKey(d)){
               List<String> list = groupedJournals.get(d);
               list.add(j.getName());
           }
           else{
               groupedJournals.put(d, new ArrayList<>());
               List<String> list = groupedJournals.get(d);
               list.add(j.getName());
           }
       }
       return groupedJournals;
    }

    //R2
    /**
     * adds authors.
     * Duplicated authors are ignored.
     *
     * @param authorNames names of authors to be added
     * @return the number of authors entered so far
     */

    TreeMap<String,Author> authors = new TreeMap<>();

    public int registerAuthors (String... authorNames) {

        for(String a:authorNames){
            if(!authors.containsKey(a)){
                Author author = new Author(a);
                authors.put(a,author);
            }
        }
        return authors.size();

    }

    /**
     * adds a paper to a journal.
     * The journal is indicated by its name;
     * the paper has a title that must be unique in the specified journal,
     * the paper can have one or more authors.
     *
     * @param journalName
     * @param paperTitle
     * @param authorNames
     * @return the journal name followed by ":" and the paper title.
     * @throws JException if the journal does not exist or the title is not unique within the journal or not all authors have been registered
     */


    TreeMap<String,Paper> papers = new TreeMap<>();

    public String addPaper (String journalName, String paperTitle, String... authorNames) throws JException {

        if(!journals.containsKey(journalName)) throw new JException("Invalid journal");
        if(papers.containsKey(paperTitle)) throw new JException("Paper already exists");

        for(String a:authorNames){
            if(!authors.containsKey(a)) throw new JException("Invalid author");
        }

        TreeSet<String> authorsNonDuplicated = new TreeSet<>();

        authorsNonDuplicated.addAll(Arrays.asList(authorNames));


        Paper paper = new Paper(journalName,paperTitle, authorsNonDuplicated.stream().toList());
        papers.put(paperTitle,paper);

        Journal journal = journals.get(journalName);
        journal.addPaper(paper);
        paper.setJournal(journal);

        for(String a:authorsNonDuplicated){
            Author author = authors.get(a);
            author.addPaper(paper);
        }

        return journalName + ":" + paperTitle;

    }

    /**
     * gives the number of papers for each journal.
     * Journals are sorted alphabetically.
     * Journals without papers are ignored.
     *
     * @return the map journal to count of papers
     */
    public SortedMap<String, Integer> giveNumberOfPapersByJournal () { //serve toMap
        TreeMap<String,Integer> groupedJournals = new TreeMap<>();

        for(Journal j:journals.values()){
            if(j.getNumPapers()!=0)
                groupedJournals.put(j.getName(),j.getNumPapers());
        }

        return groupedJournals;
    }

    //R3
    /**
     * gives the impact factor for the author indicated.
     * The impact factor of an author is obtained by adding
     * the impact factors of his/her papers.
     * The impact factor of a paper is equal to that of the
     * journal containing the paper.
     * If the author has no papers the result is 0.0.
     *
     * @param authorName
     * @return author impact factor
     * @throws JException if the author has not been registered
     */
    public double getAuthorImpactFactor (String authorName) throws JException {

        if(!authors.containsKey(authorName)) throw new JException();
        Author author = authors.get(authorName);
        return author.getImpactFactor();

    }

    /**
     * groups authors (in alphabetical order) by increasing impact factors.
     * Authors without papers are ignored.
     *
     * @return the map IF to author list
     */
    public SortedMap<Double, List<String>> getImpactFactorsByAuthors () {

        TreeMap<Double,List<String>> groupedAuthors = new TreeMap<>();

        for(Author author:authors.values()){

            if(author.numPapers()> 0){
                Double d = author.getImpactFactor();

                if(groupedAuthors.containsKey(d)){
                    List<String> list = groupedAuthors.get(d);
                    list.add(author.getName());

                }
                else{
                    List<String> list = new LinkedList<>();
                    list.add(author.getName());
                    groupedAuthors.put(d,list);
                }
            }

        }

        return groupedAuthors;

    }


    //R4
    /**
     * gives the number of papers by author;
     * authors are sorted alphabetically.
     * Authors without papers are ignored.
     *
     * @return
     */
    public SortedMap<String, Integer> getNumberOfPapersByAuthor() {
        TreeMap<String,Integer> map = new TreeMap<>();

        for(Author author:authors.values()){
            if(author.numPapers() > 0){
                map.put(author.getName(),author.numPapers());
            }
        }
        return map;

    }

    /**
     * gives the name of the journal having the largest number of papers.
     * If the largest number of papers is common to two or more journals
     * the result is the name of the journal which is the first in
     * alphabetical order.
     *
     * @return journal with more papers
     */
    public String getJournalWithTheLargestNumberOfPapers() {

        int max = -1;
        Journal bestJournal = null;

        for(Journal journal:journals.values()){
            if(journal.getNumPapers() > max || journal.getNumPapers() == max && bestJournal != null && journal.getName().compareTo(bestJournal.getName()) < 0){
                max = journal.getNumPapers();
                bestJournal = journal;
            }
        }

        return bestJournal!=null ? bestJournal.getName() + ":" + max : "";

    }

}

