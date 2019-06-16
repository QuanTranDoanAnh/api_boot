package vn.quantda.apiBoot.task

import org.gradle.api.DefaultTask
import org.gradle.api.tasks.TaskAction

class StartWebServerTask extends DefaultTask {
	String projectPath
	String outLogPath
	String errLogPath
	
	@TaskAction
	def start() {
		ProcessBuilder processBuilder = new ProcessBuilder()
		
		processBuilder.directory(new File(projectPath))
		processBuilder.redirectErrorStream(true)
		processBuilder.redirectOutput(new File(outLogPath))
		processBuilder.redirectError(new File(errLogPath))
		
		processBuilder.command("cmd", "/c", "gradle", "bootRun")
		Process process = processBuilder.start()
		//def ret = process.waitFor()
		println "Program exited with code: 0"
	}
}
