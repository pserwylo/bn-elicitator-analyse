package edu.monash.pserwylo.bnelicitator.analysis

import edu.monash.pserwylo.bnelicitator.analysis.summaries.ParticipantSummary
import edu.monash.pserwylo.bnelicitator.analysis.summaries.WeightedVoteSummary
import edu.monash.pserwylo.bnelicitator.pogo.Answer
import edu.monash.pserwylo.bnelicitator.pogo.Participant
import edu.monash.pserwylo.bnelicitator.pogo.Question

class WeightedVoteAnalyser extends Analyser {

	private final List<ParticipantSummary> participantSummaries

	WeightedVoteAnalyser(List<ParticipantSummary> participantSummaries, List<Participant> participants, List<Question> questions, List<Answer> answers) {
		super(participants, questions, answers)
		this.participantSummaries = participantSummaries
	}

	List<WeightedVoteSummary> getSummaries() { summaries }

	@Override
	void analyse() {
		questions.eachWithIndex { it, i ->
			print "."
			analyseQuestion( it )
		}
		println ""
	}

	private void analyseQuestion( Question question ) {
		WeightedVoteSummary summary = new WeightedVoteSummary(
			question,
			answers.findAll { answer -> answer.question == question },
			participantSummaries
		).calc( answers )
		summaries.add( summary )
	}
}
