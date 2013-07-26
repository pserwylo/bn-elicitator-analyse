package edu.monash.pserwylo.bnelicitator.analysis

import edu.monash.pserwylo.bnelicitator.pogo.Question
import edu.monash.pserwylo.bnelicitator.pogo.Participant
import edu.monash.pserwylo.bnelicitator.pogo.Answer

abstract class Analyser {

	private List<Participant> participants
	private List<Question>    questions
	private List<Answer>      answers

	Analyser( List<Participant> participants, List<Question> questions, List<Answer> answers ) {
		this.participants = participants
		this.questions    = questions
		this.answers      = answers
	}

	protected List<Participant> getParticipants() { participants }
	protected List<Question>    getQuestions()    { questions    }
	protected List<Answer>      getAnswers()      { answers      }

	public abstract void analyse()

}
