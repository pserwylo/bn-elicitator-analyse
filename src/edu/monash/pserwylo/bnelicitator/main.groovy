package edu.monash.pserwylo.bnelicitator

import edu.monash.pserwylo.bnelicitator.analysis.ParticipantAnalyser
import edu.monash.pserwylo.bnelicitator.analysis.summaries.ParticipantSummary
import edu.monash.pserwylo.bnelicitator.io.AnswerLoader
import edu.monash.pserwylo.bnelicitator.io.ParticipantLoader
import edu.monash.pserwylo.bnelicitator.io.QuestionLoader
import edu.monash.pserwylo.bnelicitator.pogo.Answer
import edu.monash.pserwylo.bnelicitator.pogo.Participant
import edu.monash.pserwylo.bnelicitator.pogo.Question
import groovy.sql.Sql

// Set this to the real credentials of the database which you used for the bn-elicitator survey...
Sql sql = Sql.newInstance( "jdbc:mysql://localhost/insurance", "root", "root", "com.mysql.jdbc.Driver" )

println "Loading participants..."
def participantLoader = new ParticipantLoader( sql )
participantLoader.load()
List<Participant> participants = participantLoader.data

println "Loading questions..."
def questionLoader = new QuestionLoader( sql )
questionLoader.load()
List<Question> questions = questionLoader.data

println "Loading answers..."
def answerLoader = new AnswerLoader( sql, questions, participants )
answerLoader.load()
List<Answer> answers = answerLoader.data

println "Analysing participants..."
ParticipantAnalyser participantAnalyser = new ParticipantAnalyser( participants, questions, answers )
participantAnalyser.analyse()

List<ParticipantSummary> summaries = participantAnalyser.summaries.findAll { it.hasAgreementValues() }.sort { it1, it2 ->
	it2.averageAgreement <=> it1.averageAgreement
}

summaries.each {
	println "  $it.participant: $it.averageAgreement"
}