package edu.monash.pserwylo.bnelicitator.io

import edu.monash.pserwylo.bnelicitator.pogo.Answer
import edu.monash.pserwylo.bnelicitator.pogo.Participant
import edu.monash.pserwylo.bnelicitator.pogo.Question
import groovy.sql.GroovyResultSet
import groovy.sql.Sql

class AnswerLoader extends DataLoader<Answer> {

	private List<Question>    questions
	private List<Participant> participants

	AnswerLoader( Sql sql, List<Question> questions, List<Participant> participants ) {
		super( sql )
		this.questions    = questions
		this.participants = participants
	}

	@Override
	protected String getQuery() {
		"""
		SELECT
			parent.label as parent,
			child.label  as child,
			rel.doesRelationshipExist as answer,
			rel.created_by_id as participantId
		FROM
			relationship as rel
			JOIN variable as parent ON (parent.id = rel.parent_id)
			JOIN variable as child ON (child.id = rel.child_id)
		WHERE
			rel.delphi_phase = 1
		"""
	}

	@Override
	protected Answer create(GroovyResultSet values) {
		String parent        = values[ 'parent' ]
		String child         = values[ 'child' ]
		int    answer        = values[ 'answer' ] ? Answer.EXISTS : Answer.DOESNT_EXIST
		String participantId = values[ 'participantId' ]

		String      questionId  = Question.createId( parent, child )
		Question    question    = questions.find    { q -> q.id == questionId    }
		Participant participant = participants.find { p -> p.id == participantId }

		new Answer( question, participant, answer )
	}
}
