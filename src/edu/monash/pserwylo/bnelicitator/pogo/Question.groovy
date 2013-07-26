package edu.monash.pserwylo.bnelicitator.pogo

class Question {

	private String id

	Question( String id ) {
		this.id = id
	}

	String getId() { id }

	static String createId( String parent, String child ) {
		"$parent -> $child"
	}

	@Override
	boolean equals( Object object ) {
		object instanceof Question && object?.id == id
	}

	@Override
	String toString() { "question $id" }

}
