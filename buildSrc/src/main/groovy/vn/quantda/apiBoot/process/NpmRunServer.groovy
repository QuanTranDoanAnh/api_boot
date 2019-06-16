package vn.quantda.apiBoot.process

class NpmRunServer extends AbstractServer {

	@Override
	protected void setCommand() {
		String portArgs = "-- --port " + port
		String[] cmdArgs = ["cmd", "/k", "npm", "start", portArgs]
		
		processBuilder.command(cmdArgs)
		
		
	}
}
