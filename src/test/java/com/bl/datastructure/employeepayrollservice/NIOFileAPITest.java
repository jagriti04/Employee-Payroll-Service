package com.bl.datastructure.employeepayrollservice;

import static org.junit.Assert.assertTrue;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.stream.IntStream;

import org.junit.Assert;
import org.junit.Test;

public class NIOFileAPITest {
	private static String HOME = System.getProperty("user.home");
	private static String PLAY_WITH_NIO = "TempPlayGround";

	@Test
	public void givenPathWhenCheckedThenConfirm() throws IOException {
		// check if file exists
		Path homePath = Paths.get(HOME);
		Assert.assertTrue(Files.exists(homePath));

		// delete file
		Path playPath = Paths.get(HOME + "/" + PLAY_WITH_NIO);
		if (Files.exists(playPath))
			FileUtils.deleteFiles(playPath.toFile());
		Assert.assertTrue(Files.notExists(playPath));

		// creating directory
		Files.createDirectory(playPath);
		Assert.assertTrue(Files.exists(playPath));

		// create file
		IntStream.range(1, 10).forEach(fileNum -> {
			Path tempFile = Paths.get(playPath + "/temp" + fileNum);
			Assert.assertTrue(Files.notExists(tempFile));
			try {
				Files.createFile(tempFile);
			} catch (IOException e) {
				System.out.println("Message- " + e.getMessage());
			}
			Assert.assertTrue(Files.exists(tempFile));
		});

		Files.list(playPath).filter(Files::isRegularFile).forEach(System.out::println);
		Files.newDirectoryStream(playPath).forEach(System.out::println);
		Files.newDirectoryStream(playPath, path -> path.toFile().isFile() && path.toString().startsWith("temp"))
				.forEach(System.out::println);
	}
}
