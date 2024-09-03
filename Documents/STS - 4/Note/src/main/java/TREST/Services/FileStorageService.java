package TREST.Services;

import java.io.FileNotFoundException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import TREST.FileStorageConfig;

@Service
public class FileStorageService 
{
	private final Path fileStorangeLocation;

	public FileStorageService(FileStorageConfig fileStorageConfig) throws FileNotFoundException 
	{
		Path path = Paths.get(fileStorageConfig.getUploadDir()).toAbsolutePath().normalize();
		this.fileStorangeLocation = path;
		try {
			Files.createDirectories(this.fileStorangeLocation);
		}
		catch(Exception e) 
		{
			throw new FileNotFoundException();
		}
	}
	

	public String storeFile(MultipartFile file) throws Throwable 
	{
		String filename = StringUtils.cleanPath(file.getOriginalFilename());
		try {
			if (filename.contains("..")) 
			{
				throw new Exception(
					"Sorry! Filename contains invalid path sequence " + filename);
			}
			Path targetLocation = this.fileStorangeLocation.resolve(filename);
			Files.copy(file.getInputStream(), targetLocation, StandardCopyOption.REPLACE_EXISTING);
			return filename;
		} 
		catch (Exception e) 
		{
			throw new Exception(
				"Could not store file " + filename + ". Please try again!", e);
		}
	}
	
	public Resource loadFileAsResource(String filename) {
		try {
			Path filePath = this.fileStorangeLocation.resolve(filename).normalize();
			UrlResource resource = new UrlResource(filePath.toUri());
			if (resource.exists()) return resource;
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
		}
		return null;
	}
	
}
