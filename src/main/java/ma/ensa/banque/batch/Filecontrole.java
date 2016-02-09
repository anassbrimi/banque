package ma.ensa.banque.batch;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import ma.ensa.banque.entities.Operation;

import org.springframework.core.io.ClassPathResource;




public class Filecontrole {

	final static ClassPathResource resource = new ClassPathResource("Operations.txt");
	public File getHistorytFile() {
		File f = null;
		try {
			f = new File(resource.getURI());
			System.out.println("path " + resource.getPath() + " - " + resource.getURI());
			if (!f.exists()) {
				f.createNewFile();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return f;
	}
	
	
	public int addInFile(Operation t) {
		File history_file = this.getHistorytFile();
		System.out.println("**************////////****************:"+ history_file);
		if (history_file == null || t == null)
			return 0;
		BufferedWriter out = null;
		try {
			out = new BufferedWriter(new FileWriter(history_file, true));
			String _ligne = t.getIdOp()+"";
			this.write(out, _ligne);
		} catch (IOException e) {
			e.printStackTrace();
			return 0;
		} finally {
			if (out != null) {
				try {
					out.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return 1;
	}
	private void write(BufferedWriter bw, String string) throws IOException {
		if (bw == null)
			return;
		bw.append(string).append(System.lineSeparator());
		bw.flush();
	}
	public void cleanFile() {
		File history_file = this.getHistorytFile();
		if (history_file == null) {

		}
		BufferedWriter bw = null;
		try {
			bw = new BufferedWriter(new FileWriter(history_file));
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (bw != null) {
				try {
					bw.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}
	
	
}
