package edu.monash.pserwylo.bnelicitator.pogo

class Answer {

	static final EXISTS = 1;
	static final DOESNT_EXIST = 0;

	private Question    question
	private Participant participant
	private int         answer

	Answer( Question question, Participant participant, int answer ) {
		this.question    = question
		this.participant = participant
		this.answer      = answer
	}

	Question getQuestion()       { question    }
	Participant getParticipant() { participant }
	int getAnswer()              { answer      }

	@Override
	String toString() { "Answer ($participant, $question, response $answer)" }

}
