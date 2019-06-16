package vn.quantda.apiBoot.process

abstract class AbstractServer implements ServerRunnable {
	String targetDirPath = null

	Integer port = 8080
	boolean willWait = true
	Integer waitSeconds = 20

	String logDirPath = null
	String outLogFileName = "out.log"
	String errLogFileName = "err.log"
	String pid = null

	protected ProcessBuilder processBuilder = null
	private long startTime = 0L

	@Override
	public final void start() throws Exception {
		initProcessBuilder()
		setWorkingDirectory()
		setLogFiles()
		setCommand()
		Process process = processBuilder.start()
		waitTillEnd()
	}

	protected abstract void setCommand()

	protected String getRunningPid() {
		ArrayList<String[]> netstat = new ArrayList<String[]>()
		try {
			String line = "netstat -ano | findstr :" + port
			// execute the netstat command
			ProcessBuilder pb = new ProcessBuilder("cmd","/c","netstat -ano | findstr :" + port + " | findstr LISTENING")
			pb.directory(new File("C:/Windows/System32"));
			pb.redirectErrorStream(true);
			Process p = pb.start();
			// capture the output of netstat
			BufferedReader input = new BufferedReader(new InputStreamReader(p.getInputStream()))
			while ((line = input.readLine()) != null) {
				netstat.add(line.split("\\s+"))
			}
			input.close();
		} catch (Exception err) {
			err.printStackTrace();
		}
		// Removing headers from netstat command
		//netstat.remove(0); netstat.remove(0); netstat.remove(0); netstat.remove(0);
		// get first line to check1
		for (String[] line : netstat) {
			pid = line[5]
		}
		
	}

	//default implementation
	private void initProcessBuilder() throws Exception {
		processBuilder = new ProcessBuilder()
	}

	protected void setWorkingDirectory() {
		if (targetDirPath == null || targetDirPath.isEmpty()) {
			throw new IllegalArgumentException("Target Directory must be set")
		}
		File targetDir = new File(targetDirPath)
		if (! targetDir.exists()) {
			throw new IllegalArgumentException("Invalid Target Directory")
		}
		processBuilder.directory(targetDir)
	}

	private void setLogFiles() {
		if (logDirPath == null || logDirPath.isEmpty()) {
			throw new IllegalArgumentException("Log Directory must be set")
		}
		File logDir = new File(logDirPath)
		if (! logDir.exists()) {
			logDir.mkdirs()
		}

		File outLogFile = new File(logDirPath + File.separator + outLogFileName)
		outLogFile.createNewFile()
		File errLogFile = new File(logDirPath + File.separator + errLogFileName)
		errLogFile.createNewFile()
		processBuilder.redirectErrorStream(true)
		processBuilder.redirectOutput(outLogFile)
		processBuilder.redirectError(errLogFile)
	}

	private boolean timeout() {
		if (waitSeconds <= 0 ) {
			return false
		} else {
			long currTime = System.currentTimeMillis()
			if (currTime >= startTime + (waitSeconds * 1000)) {
				return true
			} else return false
		}
	}
	
	private void waitTillEnd() {
		Process process = processBuilder.start()
		startTime = System.currentTimeMillis()
		while(willWait && (!timeout())) {
			getRunningPid()
			if (pid != null && !pid.isEmpty()) {
				break;
			} else {
				Thread.sleep(1000)
			}
		}
	}

}
