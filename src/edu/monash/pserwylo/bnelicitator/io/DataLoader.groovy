package edu.monash.pserwylo.bnelicitator.io

import groovy.sql.GroovyResultSet
import groovy.sql.Sql

abstract class DataLoader<T> {

	private Sql sql
	private List<T> data = []

	DataLoader( Sql sql ) {
		this.sql = sql
	}

	public List<T> getData() { data }
	protected Sql  getSql()  { sql  }

	protected abstract T create( GroovyResultSet values )
	protected abstract String getQuery()

	void load() {
		sql.eachRow( query.toString() ) { GroovyResultSet result ->
			data.add( create( result ) )
		}
	}

}
