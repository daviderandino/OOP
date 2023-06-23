
/*
subTopics è una lista di oggetti di tipo Topic, la voglio ritornare ordinata utilizzando uno stream
*/

public List<Topic> getSubTopics() {
		 return subTopics.stream()
	                .sorted(Comparator.comparing(Topic::getKeyword))
	                .collect(Collectors.toList());
	}

/*
Filtraggio per parametri
*/

public Collection<String> getStudents(double inf, double sup){
    	return students.values().stream()
                .filter(student -> student.getGradeAverage() >= inf && student.getGradeAverage() <= sup)
                .map(Student::getName)
                .collect(Collectors.toList());
    }
