/*
Copyright (c) 2013-2015 Daniel Marbach

We release this software open source under an MIT license (see below). If this
software was useful for your scientific work, please cite our paper available at:
http://regulatorycircuits.org

Permission is hereby granted, free of charge, to any person obtaining a copy
of this software and associated documentation files (the "Software"), to deal
in the Software without restriction, including without limitation the rights
to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
copies of the Software, and to permit persons to whom the Software is
furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in
all copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
THE SOFTWARE.
 */
package ch.unil.gpsutils;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.zip.GZIPOutputStream;


/**
 * Write a text file
 */
public class FileExport {

	/** The file */
	private File file;
	/** The buffered file writer */
	private BufferedWriter writer_;
	
	
	// ============================================================================
	// PUBLIC METHODS
	    
	/** Constructor */
	public FileExport(Logger log, File file, boolean gzip) {

		try {
			this.file = file;
			log.println("Writing file: " + file.getPath());
		
			if (gzip) {
				FileOutputStream output = new FileOutputStream(file);
				writer_ = new BufferedWriter(new OutputStreamWriter(new GZIPOutputStream(output), "UTF-8"));
			} else {				
				FileWriter fstream = new FileWriter(file);
				writer_ = new BufferedWriter(fstream);
			}
			
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	/** Constructor for uncompressed file */
	public FileExport(Logger log, File file) {
		this(log, file, false);
	}

	// Better force the user to call new File() to avoid using strings for files
	
//	/** Constructor */
//	public FileExport(Logger log, String filename, boolean gzip) {
//		this(log, new File(gzip ? filename + ".gz" : filename), gzip);
//	}
//	
//	/** Constructor for uncompressed file */
//	public FileExport(Logger log, String filename) {
//		this(log, filename, false);
//	}

	
    // ----------------------------------------------------------------------------

	/** Write a line to the file */
	public void println(String str) {
		print(str + "\n");
	}

	/** Write a line to the file */
	public void println() {
		print("\n");
	}

	/** Write the given string to the file */
	public void print(String str) {
		try {
			writer_.write(str);
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}
	
	
    // ----------------------------------------------------------------------------

	/** Be polite and close the file writer */
	public void flush() {
		
		try {
			writer_.flush();
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

	
    // ----------------------------------------------------------------------------

	/** Be polite and close the file writer */
	public void close() {
		
		try {
			writer_.close();
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}
	
	// ============================================================================
	// GETTERS AND SETTERS
	
	public File getFile() { return file; }

	  
}
