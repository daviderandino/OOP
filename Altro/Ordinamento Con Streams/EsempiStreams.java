
/*
subTopics è una lista di oggetti di tipo Topic, la voglio ritornare ordinata utilizzando uno stream
*/

public List<Topic> getSubTopics() {
		 return subTopics.stream()
	                .sorted(Comparator.comparing(Topic::getKeyword))
	                .collect(Collectors.toList());
	}
