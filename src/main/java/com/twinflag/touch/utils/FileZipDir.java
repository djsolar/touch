package com.twinflag.touch.utils;

import java.io.File;
import java.util.Objects;

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

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (!(o instanceof FileZipDir)) return false;
		FileZipDir that = (FileZipDir) o;
		return Objects.equals(getPrefix(), that.getPrefix()) &&
				Objects.equals(getFile(), that.getFile());
	}

	@Override
	public int hashCode() {

		return Objects.hash(getPrefix(), getFile());
	}
}
