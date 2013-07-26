package edu.monash.pserwylo.bnelicitator.io

import edu.monash.pserwylo.bnelicitator.pogo.Question
import groovy.sql.GroovyResultSet
import groovy.sql.Sql

class QuestionLoader extends DataLoader {

	QuestionLoader(Sql sql) {
		super(sql)
	}

	@Override
	protected String getQuery() {
		"""
		SELECT
			parent.label as parent,
			child.label  as child
		FROM variable AS parent,
			variable as child
		WHERE parent.id != child.id
		"""
	}

	@Override
	protected create(GroovyResultSet data) {
		String parent = data[ 'parent' ]
		String child  = data[ 'child' ]
		new Question( Question.createId( parent, child ) )
	}
}
