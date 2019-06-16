package vn.quantda.apiBoot.task

import org.gradle.api.DefaultTask
import org.gradle.api.tasks.TaskAction

class SampleTask extends DefaultTask {
	String helloName
	
	@TaskAction
	def hello() {
		println "Hello " + helloName
	}
}
