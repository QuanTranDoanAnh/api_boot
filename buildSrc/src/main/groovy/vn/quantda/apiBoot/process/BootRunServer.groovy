package vn.quantda.apiBoot.process

class BootRunServer extends AbstractServer {
	
	@Override
	protected void setCommand() {
		String portArgs = "--args=\"--server.port="+ port + "\""
		String[] cmdArgs = ["cmd", "/k", "gradle", "bootRun", portArgs]
		
		processBuilder.command(cmdArgs)
	}
}
