package edu.monash.pserwylo.bnelicitator.analysis.summaries

import edu.monash.pserwylo.bnelicitator.pogo.Answer
import edu.monash.pserwylo.bnelicitator.pogo.Participant

class ParticipantSummary {

	private Map<Answer, Float> agreementValues = [:]
	private Participant participant
	private List<Answer> answers

	ParticipantSummary( Participant participant, List<Answer> answers ) {
		this.participant = participant
		this.answers     = answers
	}

	Participant getParticipant() { participant }

	ParticipantSummary calc( List<Answer> allAnswers ) {
		answers.each { answer ->
			List<Answer> othersAnswers = allAnswers.findAll { otherAnswer ->
				otherAnswer.question == answer.question && otherAnswer.participant != participant
			}

			if ( othersAnswers.size() == 0 ) {
				println "Ignoring $answer, because nobody else answered this question."
			} else {
				int agreeWith    = othersAnswers.count { it.answer == answer.answer }
				int disagreeWith = othersAnswers.size() - agreeWith
				float agreement  = agreeWith / ( othersAnswers.size() )
				agreementValues.put( answer, agreement )
			}
		}
		return this
	}

	boolean hasAgreementValues() {
		agreementValues.size() > 0
	}

	float getAverageAgreement() {
		agreementValues.values().sum() / agreementValues.size()
	}

	@Override
	String toString() {
		StringBuilder sb = new StringBuilder( "[Summary for $participant]\n" );
		int numAnswers = answers.size()
		if ( numAnswers == 0 ) {
			sb.append( "\tNo answers given" )
		} else {
			sb.append( "\tAverage agreement level: $averageAgreement\n" )
			sb.append( "\tAgreement levels for $numAnswers answers: (${agreementValues.values().join(', ')})" )
		}
		sb.toString()
	}

}
