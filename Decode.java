import java.io.ByteArrayInputStream;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.io.FileUtils;

import com.yourkit.Natives;
import com.yourkit.util.ar;

public class Decode {
	public static void main(String[] args) throws Exception {
		new Decode().decode();
	}

	private void decode() throws Exception {
		File src = new File("C:\\Users\\Saravana\\workspace\\YourKitDecrypt\\res");
		List<File> files = getFiles(src);
		for (File file : files) {
			File destFile = new File(file.getAbsolutePath().replaceAll("res", "decryptedres").replaceAll("_", ""));
			destFile.getParentFile().mkdirs();
			byte[] bytes = FileUtils.readFileToByteArray(file);
			if (file.getName().endsWith("_")) 
			{				
				bytes = Natives.decipher1(ar.a(new ByteArrayInputStream(bytes)));
			}
			FileUtils.writeByteArrayToFile(destFile, bytes);
		}
	}

	private List<File> getFiles(File src) {
		List<File> files = new ArrayList<>();
		for (File file : src.listFiles()) {
			if (file.isDirectory()) {
				files.addAll(getFiles(file));
			} else {
				files.add(file);
			}
		}
		return files;
	}
}
