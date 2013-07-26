package edu.monash.pserwylo.bnelicitator.analysis

import edu.monash.pserwylo.bnelicitator.analysis.summaries.ParticipantSummary
import edu.monash.pserwylo.bnelicitator.pogo.Answer
import edu.monash.pserwylo.bnelicitator.pogo.Participant
import edu.monash.pserwylo.bnelicitator.pogo.Question

class ParticipantAnalyser extends Analyser {

	private List<ParticipantSummary> summaries = []

	ParticipantAnalyser(List<Participant> participants, List<Question> questions, List<Answer> answers) {
		super(participants, questions, answers)
	}

	List<ParticipantSummary> getSummaries() { summaries }

	@Override
	void analyse() {
		participants.eachWithIndex { it, i ->
			print "."
			analyseParticipant( it )
		}
		println ""
	}

	private void analyseParticipant( Participant participant ) {
		ParticipantSummary summary = new ParticipantSummary(
			participant,
			answers.findAll { answer -> answer.participant == participant }
		).calc( answers )
		summaries.add( summary )
	}
}
