package edu.monash.pserwylo.bnelicitator.pogo

class Participant {

	private String id

	Participant( String id ) {
		this.id = id
	}

	String getId() { id }

	@Override
	boolean equals( Object object ) {
		object instanceof Participant && object?.id == id
	}

	@Override
	String toString() { "participant $id" }

}
