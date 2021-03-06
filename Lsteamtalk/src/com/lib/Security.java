package com.lib;

public class Security {
	
	public native String DecryptMsg(String strMsg);
	public native String EncryptMsg(String strMsg);
	static{
		System.out.println(System.getProperty("java.library.path"));
		System.loadLibrary("msvcr120d");
		System.loadLibrary("msvcp120d");
		System.loadLibrary("security");
		System.loadLibrary("security");
	}
	
	private static Security m_pInstance;
	
	public static Security getInstance() {
		synchronized (Security.class) {
			if (m_pInstance == null) {
				m_pInstance = new Security();
			}

			return m_pInstance;
		}
	}
}
