package journals;

import java.util.List;

public class Paper {

    private String journalName;
    private String paperTitle;
    private List<String> authorNames;
    private Journal journal;

    public Paper(String journalName, String paperTitle, List<String> authorNames) {
        this.journalName = journalName;
        this.paperTitle = paperTitle;
        this.authorNames = authorNames;
    }

    public String getJournalName() {
        return journalName;
    }

    public Journal getJournal() {
        return journal;
    }

    public void setJournal(Journal journal) {
        this.journal = journal;
    }
}
