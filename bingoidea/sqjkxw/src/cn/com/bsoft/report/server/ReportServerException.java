package cn.com.bsoft.report.server;

public class ReportServerException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public ReportServerException() {
	}

	public ReportServerException(String message) {
		super(message);
	}

	public ReportServerException(Throwable cause) {
		super(cause);
	}

	public ReportServerException(String message, Throwable cause) {
		super(message, cause);
	}

}
