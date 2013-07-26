package edu.monash.pserwylo.bnelicitator.io

import edu.monash.pserwylo.bnelicitator.pogo.Participant
import groovy.sql.GroovyResultSet
import groovy.sql.Sql

class ParticipantLoader extends DataLoader<Participant> {

	ParticipantLoader(Sql sql) {
		super(sql)
	}

	@Override
	protected String getQuery() {
		"SELECT id FROM shiro_user"
	}

	@Override
	protected Participant create(GroovyResultSet values) {
		new Participant( values[ 'id' ] as String )
	}
}
