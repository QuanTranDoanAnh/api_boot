package vn.quantda.apiBoot.task

import org.gradle.api.DefaultTask
import org.gradle.api.tasks.TaskAction

import vn.quantda.apiBoot.process.NpmRunServer

class StartFrontEndServerTask extends DefaultTask {

	String targetDirPath = null

	Integer port = 8080
	boolean willWait = true
	Integer waitSeconds = 20

	String logDirPath = null
	String outLogFileName = "out.log"
	String errLogFileName = "err.log"

	String pid

	@TaskAction
	def action() {
		NpmRunServer server = new NpmRunServer()
		server.targetDirPath = targetDirPath
		server.port = port
		server.willWait = willWait
		server.waitSeconds = waitSeconds
		server.logDirPath = logDirPath
		server.outLogFileName = outLogFileName
		server.errLogFileName = errLogFileName
		println "Starting the Server"
		server.start()
		pid = server.pid
		println "Finished Starting the Server. Result PID is " + server.pid
	}
}
