package com.twinflag.touch.utils;

import java.io.File;

public class FileZipDir {
	
	private String prefix;
	
	private File file;

	public FileZipDir(String prefix, File file) {
		super();
		this.prefix = prefix;
		this.file = file;
	}

	public String getPrefix() {
		return prefix;
	}

	public void setPrefix(String prefix) {
		this.prefix = prefix;
	}

	public File getFile() {
		return file;
	}

	public void setFile(File file) {
		this.file = file;
	}
	
	
}
